package com.lind.elasticsearch.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.lind.elasticsearch.entity.DataRecord;
import com.lind.elasticsearch.entity.DateDeserialize;
import com.lind.elasticsearch.entity.DateSerializer;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * ES数据操作，主要用于操作json数据.
 */
public class EsDataUtils {

    private static final String DEFAULT_TYPE = "info";

    private static ObjectMapper objectMapper = new ObjectMapper();


    static {
        //常用配置
        objectMapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        objectMapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_MISSING_VALUES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //大小写脱敏 默认为false  需要改为true
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        SimpleModule simpleModule = new SimpleModule();
        //时间序列化
        simpleModule.addSerializer(Date.class, new DateSerializer());
        //时间反序列化
        simpleModule.addDeserializer(Date.class, new DateDeserialize());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(simpleModule);
    }

    /**
     * 插入数据
     *
     * @param client
     * @param indexName
     * @param dataRecord
     * @return
     */
    public static String saveOrUpdate(RestHighLevelClient client, String indexName,
                                      DataRecord dataRecord) throws IOException {
        Assert.hasText(indexName, "索引名不能为空");
        Assert.notNull(dataRecord, "数据不能为null");
        IndexRequest indexRequest = null;
        String id = dataRecord.getId();

        if (StringUtils.isNotBlank(id)) {
            indexRequest = new IndexRequest(indexName, DEFAULT_TYPE, id);
        } else {

            indexRequest = new IndexRequest(indexName, DEFAULT_TYPE);
        }

        //填充数据
        indexRequest.source(objectMapper.writeValueAsString(dataRecord), XContentType.JSON);

        String documentId = client.index(indexRequest, RequestOptions.DEFAULT).getId();

        return documentId;
    }


    /**
     * 处理高亮数据
     *
     * @param clazz
     * @return
     */
    public static SearchResultMapper getHighlighterMapper(Class clazz) {
        return new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                SearchHits hits = response.getHits();
                ArrayList<T> result = Lists.newArrayList();
                for (SearchHit hit : hits.getHits()) {
                    T t = mapSearchHit(hit, clazz);
                    result.add(t);
                }
                return new AggregatedPageImpl<T>(result, pageable, hits.getTotalHits());
            }

            @SneakyThrows
            @Override
            public <T> T mapSearchHit(SearchHit searchHit, Class<T> type) {
                Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
                Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
                highlightFields.entrySet().forEach(
                        entry -> sourceAsMap.put(entry.getKey(), Joiner.on("").join(entry.getValue().fragments()))
                );

                return (T) objectMapper.convertValue(sourceAsMap, clazz);
            }
        };
    }

}
