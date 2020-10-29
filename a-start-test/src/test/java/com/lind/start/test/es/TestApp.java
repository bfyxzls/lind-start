package com.lind.start.test.es;

import com.lind.elasticsearch.audit.EnableEsAuditing;
import com.lind.elasticsearch.util.EsPageUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.nested.Nested;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.ParsedSum;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQueryBuilder;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest()
@Slf4j
@EnableEsAuditing
public class TestApp {

    @Autowired
    TestDao testDao;
    @Autowired
    ElasticsearchRestTemplate elasticsearchTemplate;

    @Test
    public void insert() {
        TestEsDto testBean = new TestEsDto("李四", 5, "测试", "es world", Arrays.asList(
                new TestEsDto.Person( 10, "测试", "中国"),
                new TestEsDto.Person( 10, "测试", "日本")));
        testDao.save(testBean);
        TestEsDto testBean2 = new TestEsDto("张三", 5, "中国", "es world", Arrays.asList(
                new TestEsDto.Person( 10, "中国", "中国"),
                new TestEsDto.Person( 10, "中国", "美国")));
        testDao.save(testBean2);

    }

    @Test
    public void search() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("delFlag", false));
        Pageable pageable = EsPageUtil.getPageable(0, 2);
        String[] fieldNames = new String[]{"name", "age"};
        SearchQuery searchQuery = queryBuilder.withQuery(boolQueryBuilder)
                .withSourceFilter(new FetchSourceFilter(fieldNames, null))
                .withPageable(pageable).build();
        Page<TestEsDto> page = testDao.search(searchQuery);
        List<TestEsDto> content = page.getContent();
        if (content.size() > 0) {
            for (TestEsDto dto : content) {
                log.info(dto.toString());
            }
        }
    }

    @Test
    public void aggregate() {
        // 创建一个查询条件对象
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        // 拼接查询条件
        queryBuilder.should(QueryBuilders.termQuery("creator", "1"));

        // 创建聚合查询条件
        TermsAggregationBuilder sexAgg = AggregationBuilders
                .terms("sex")
                .field("sex");//sex.keyword表示不使用分词进行聚合,全字匹配,但如果sex本身就是keyword类型的，本身就不会分词，所以不需要加keyword
        TermsAggregationBuilder descAgg = AggregationBuilders
                .terms("desc")
                .field("desc");//keyword表示不使用分词进行聚合,全字匹配
        SumAggregationBuilder ageSumAgg = AggregationBuilders
                .sum("ageSum")
                .field("age");
        //嵌套
        descAgg.subAggregation(ageSumAgg);
        sexAgg.subAggregation(descAgg);

        // 创建查询对象
        SearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder) //添加查询条件
                .addAggregation(sexAgg) // 添加聚合条件
                .withPageable(PageRequest.of(0, 1)) //符合查询条件的文档分页，如果文档比较大，可以把这个分页改小（不是聚合的分页）
                .build();
        // 执行查询
        AggregatedPage<TestEsDto> testEntities = elasticsearchTemplate.queryForPage(build, TestEsDto.class);

        // 取出聚合结果
        Aggregations entitiesAggregations = testEntities.getAggregations();
        Terms terms = (Terms) entitiesAggregations.asMap().get("sex");

        // 遍历取出聚合字段列的值，与对应的数量
        for (Terms.Bucket bucket : terms.getBuckets()) {
            Terms descTerms = (Terms) bucket.getAggregations().asMap().get("desc");
            for (Terms.Bucket descTermsBucket : descTerms.getBuckets()) {
                ParsedSum parsedSum = descTermsBucket.getAggregations().get("ageSum");//注意从bucket而不是searchResponse
                System.out.println(bucket.getKeyAsString() + "\t" +
                        bucket.getDocCount() + "\t" +
                        descTermsBucket.getKeyAsString() + "\t" +
                        parsedSum.getValueAsString());
            }
        }
    }

    @Test
    public void aggregateNest() {
        // 创建一个查询条件对象
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        // 拼接查询条件
        queryBuilder.should(QueryBuilders.termQuery("creator", "1"));

        // 嵌套聚合
        AbstractAggregationBuilder aggregation =
                AggregationBuilders
                    .nested("personList", "personList")
                    .subAggregation(AggregationBuilders
                        .terms("sex").field("personList.sex")
                        .subAggregation(
                            AggregationBuilders
                                .terms("desc").field("personList.desc")
                                .subAggregation(
                                    AggregationBuilders
                                            .sum("ageSum").field("personList.age")
                                )
                        )
                    );


        // 创建查询对象
        SearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder) //添加查询条件
                .addAggregation(aggregation) // 添加聚合条件
                .withPageable(PageRequest.of(0, 1)) //符合查询条件的文档分页，如果文档比较大，可以把这个分页改小（不是聚合的分页）
                .build();
        // 执行查询
        AggregatedPage<TestEsDto> testEntities = elasticsearchTemplate.queryForPage(build, TestEsDto.class);

        // 取出聚合结果
        Nested agg = testEntities.getAggregations().get("personList");
        Terms terms = agg.getAggregations().get("sex");
        // 遍历
        for (Terms.Bucket bucket : terms.getBuckets()) {
            Terms descTerms = bucket.getAggregations().get("desc");
            for (Terms.Bucket descTermsBucket : descTerms.getBuckets()) {
                ParsedSum parsedSum = descTermsBucket.getAggregations().get("ageSum");//注意从bucket而不是searchResponse
                System.out.println(bucket.getKeyAsString() + "\t" +
                        bucket.getDocCount() + "\t" +
                        descTermsBucket.getKeyAsString() + "\t" +
                        parsedSum.getValueAsString());
            }
        }

    }

    @Test
    public void update() {
        String id = "303280141742641152";
        Map<String, Object> sourceMap = new HashMap<>();
        sourceMap.put("name", "占岭");
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.source(sourceMap);
        UpdateQuery updateQuery = new UpdateQueryBuilder()
                .withId(id)
                .withClass(TestEsDto.class)
                .withIndexRequest(indexRequest).build();
        elasticsearchTemplate.update(updateQuery);
    }

    @Test
    public void detail() {
        String id = "303280141742641152";
        Optional<TestEsDto> optional = testDao.findById(id);
        Assert.notNull(optional.orElse(null));
        log.info(optional.get().toString());
    }
}
