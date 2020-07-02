package com.lind.start.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Optional;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Index;

import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * shade插件测试.
 */
public class EsShadeTest {
    @Autowired
    JestClient jestClient;
    @Autowired
    ElasticsearchClient elasticsearchClient;

    private Search buildLawArticleSearch(String keyword) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("Title", keyword).operator(Operator.AND));
        // 只获取 现行有效 01 的数据
        boolQueryBuilder.must(QueryBuilders.matchQuery("TimelinessDic", "01").operator(Operator.AND));
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.size(1);
        return new Search.Builder(searchSourceBuilder.toString())
                .addIndex("articlesoflaw")
                .addType("articlesoflaw")
                .build();
    }

    @Test
    public void hello() {

        // 创建查询语句
        Search search = buildLawArticleSearch("合同");

        try {
            SearchResult searchResult = jestClient.execute(search);
            System.out.println(searchResult.isSucceeded());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
