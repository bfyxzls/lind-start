package com.lind.elasticsearch;

import com.lind.elasticsearch.util.EsPageUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQueryBuilder;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.apache.commons.lang3.Validate.notNull;

@SpringBootTest()
@Slf4j
public class TestApp {

    @Autowired
    EsRepo testDao;
    @Autowired
    ElasticsearchRestTemplate elasticsearchTemplate;

    @Test
    public void insert() {
        EsDto testBean = new EsDto("lind is 5", 1, "测试", "es hello world");
        testDao.save(testBean);
    }

    @Test
    public void search() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("desc", "es hello world"));//matchQuery先分词再检索,termQuery精确检索
        Pageable pageable = EsPageUtil.getPageable(0, 10);
        String[] fieldNames = new String[]{"name", "age"};
        SearchQuery searchQuery = queryBuilder.withQuery(boolQueryBuilder)
                //.withSourceFilter(new FetchSourceFilter(fieldNames, null))
                .withSourceFilter(null) //返回所有字段
                .withPageable(pageable)
                .build();
        Page<EsDto> page = testDao.search(searchQuery);
        List<EsDto> content = page.getContent();
        if (content.size() > 0) {
            for (EsDto dto : content) {
                log.info(dto.toString());
            }
        }
    }

    @Test
    public void highSearch() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("desc", "hello"));//matchQuery先分词再检索,termQuery精确检索
        Pageable pageable = EsPageUtil.getPageable(0, 10);

        // 高亮检索
        String preTag = "<font color='#dd4b39'>";
        String postTag = "</font>";

        SearchQuery searchQuery = queryBuilder.withQuery(boolQueryBuilder)
                .withSourceFilter(null) //返回所有字段
                .withHighlightFields(
                        new HighlightBuilder.Field("desc")
                                .preTags(preTag)
                                .postTags(postTag)
                                .numOfFragments(5).fragmentSize(100).noMatchSize(5)
                ) //高亮显示
                .withPageable(pageable)
                .build();

        AggregatedPage<EsDto> pageinfo = elasticsearchTemplate.queryForPage(searchQuery, EsDto.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                //先来一个容器把数据装起来...
                List<EsDto> gzDataList = new ArrayList<>();
                //我们所有查询的结果都放在这个searchResponse里面
                //我们现在就是要把我们想要的内容从这个searchResponse里面获取到
                SearchHits hits = searchResponse.getHits();
                //如果getTotalHits是0 则表示查询不到数据
                if (hits.getTotalHits() <= 0) {
                    return null;
                } else {
                    //从里面获取到一条一条的数据了
                    for (SearchHit hit : hits) {
                        EsDto gzData = new EsDto();
                        //还要获取到某个字段的高亮特征 高亮的特征和当前的数据 做一个替换
                        HighlightField companyHighlight = hit.getHighlightFields().get("desc");
                        if (companyHighlight != null) {
                            gzData.setName(companyHighlight.fragments()[0].toString());
                        } else {
                            gzData.setName((String) hit.getSourceAsMap().get("desc"));
                        }
                        gzDataList.add(gzData);
                    }
                }
                return new AggregatedPageImpl(gzDataList);
            }

            @Override
            public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
                return null;
            }
        });
        pageinfo.forEach(o -> log.info(o.toString()));

    }


    @Test
    public void setAdd() {
        List<String> stringList = new ArrayList<>();
        List<String> old = new ArrayList<>();
        old.add("1");
        old.add("2");
        old.forEach(stringList::add);
        notNull(stringList);
        stringList.forEach(System.out::printf);
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
                .withClass(EsDto.class)
                .withIndexRequest(indexRequest).build();
        elasticsearchTemplate.update(updateQuery);
    }

    @Test
    public void detail() {
        String id = "303280141742641152";
        Optional<EsDto> optional = testDao.findById(id);
        Assert.notNull(optional.orElse(null));
        log.info(optional.get().toString());
    }
}
