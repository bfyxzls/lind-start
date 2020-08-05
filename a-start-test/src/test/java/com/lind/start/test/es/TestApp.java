package com.lind.start.test.es;

import com.lind.elasticsearch.audit.EnableEsAuditing;
import com.lind.elasticsearch.util.EsPageUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
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
        TestEsDto testBean = new TestEsDto("lind5", 1, "测试", "es hello world");
        testDao.save(testBean);
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
