package com.lind.elasticsearch;

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

@SpringBootTest
@Slf4j
public class TestApp {

    @Autowired
    EsRepo testDao;
    @Autowired
    ElasticsearchRestTemplate elasticsearchTemplate;

    @Test
    public void insert() {
        EsDto testBean = new EsDto("lind5", 1, "测试", "es hello world");
        testBean.setCreateTime("2020-02-02 16:30:30");
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
        Page<EsDto> page = testDao.search(searchQuery);
        List<EsDto> content = page.getContent();
        if (content.size() > 0) {
            for (EsDto dto : content) {
                log.info(dto.toString());
            }
        }
    }

    @Test
    public void delete() {
        String id = "4";
        Map<String, Object> sourceMap = new HashMap<>();
        sourceMap.put("delFlag", true);
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
        Long id = 4L;
        Optional<EsDto> optional = testDao.findById(id);
        Assert.notNull(optional.orElse(null));
    }
}
