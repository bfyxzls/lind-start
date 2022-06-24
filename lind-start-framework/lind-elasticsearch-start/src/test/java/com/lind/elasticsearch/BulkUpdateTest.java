package com.lind.elasticsearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.BulkOptions;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 批量操作
 */
@SpringBootTest()
@RunWith(SpringRunner.class) //让测试在Spring容器环境下执行
@Slf4j
public class BulkUpdateTest {
    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 批量替换，不存在就建立，存在就替换，原字段会删除，新字段会添加
     *
     * @throws JsonProcessingException
     */
    @Test
    public void bulkIndex() throws JsonProcessingException {
        List<IndexQuery> queries = new ArrayList<>();

        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setId("552625903956398080");
        Map<String, Object> sourceMap = new HashMap<>();
        sourceMap.put("name", "占岭1");
        indexQuery.setSource(new ObjectMapper().writeValueAsString(sourceMap));
        indexQuery.setType("esdto");
        indexQuery.setIndexName("esdto");
        queries.add(indexQuery);

        indexQuery = new IndexQuery();
        indexQuery.setId("552306605039816704");
        sourceMap = new HashMap<>();
        sourceMap.put("name", "占岭2");
        indexQuery.setSource(new ObjectMapper().writeValueAsString(sourceMap));
        indexQuery.setType("esdto");
        indexQuery.setIndexName("esdto");
        queries.add(indexQuery);

        elasticsearchRestTemplate.bulkIndex(queries,
                BulkOptions.builder().withTimeout(TimeValue.MINUS_ONE).build());
    }

    /**
     * 批量更新某些字段.
     *
     * @throws JsonProcessingException
     */
    @Test
    public void bulkUpdate() throws JsonProcessingException {
        List<UpdateQuery> updateQueries = new ArrayList<>();

        Map<String, Object> sourceMap = new HashMap<>();
        sourceMap.put("name", "占岭3");
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.doc(sourceMap);
        UpdateQuery updateQuery = new UpdateQueryBuilder()
                .withId("552629576220545024")
                .withIndexName("esdto")
                .withType("esdto")
                .withUpdateRequest(updateRequest)
                .build();
        updateQueries.add(updateQuery);


        sourceMap = new HashMap<>();
        sourceMap.put("name", "占岭4");
        updateRequest = new UpdateRequest();
        updateRequest.doc(sourceMap);
        updateQuery = new UpdateQueryBuilder()
                .withId("552629636035514368")
                .withIndexName("esdto")
                .withType("esdto")
                .withUpdateRequest(updateRequest)
                .build();
        updateQueries.add(updateQuery);

        elasticsearchRestTemplate.bulkUpdate(updateQueries);
    }

}
