package com.lind.start.test.es;

import com.lind.elasticsearch.audit.EnableEsAuditing;
import com.lind.elasticsearch.util.EsPageUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.util.Assert;

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
        TestEsDto testBean = new TestEsDto("lind4", 1, "男", "es hello world");
        testDao.save(testBean);
        TestEsDto testBean2 = new TestEsDto("lind5", 1, "女", "es hello world");
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
        TermsAggregationBuilder agg = AggregationBuilders.terms("sex").field("sex.keyword");//keyword表示不使用分词进行聚合
        // 创建查询对象
        SearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder) //添加查询条件
                .addAggregation(agg) // 添加聚合条件
                .withPageable(PageRequest.of(0, 10)) //符合查询条件的文档分页（不是聚合的分页）
                .build();

        // 执行查询
        AggregatedPage<TestEsDto> testEntities = elasticsearchTemplate.queryForPage(build, TestEsDto.class);
        // 取出聚合结果
        Aggregations entitiesAggregations = testEntities.getAggregations();
        Terms terms = (Terms) entitiesAggregations.asMap().get("sex");
        // 遍历取出聚合字段列的值，与对应的数量
        for (Terms.Bucket bucket : terms.getBuckets()) {
            String keyAsString = bucket.getKeyAsString(); // 聚合字段列的值
            long docCount = bucket.getDocCount();// 聚合字段对应的数量
            log.info("keyAsString={},value={}", keyAsString, docCount);
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
