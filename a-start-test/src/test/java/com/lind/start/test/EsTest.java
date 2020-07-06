package com.lind.start.test;

import com.alibaba.fastjson.JSONObject;
import com.lind.start.test.model.User;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.mapping.PutMapping;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;


@RunWith(SpringRunner.class)
@SpringBootTest()
@Slf4j
public class EsTest {

    static final String INDEX = "test";
    static final String TYPE = "user";
    @Autowired
    JestClient jestClient;


    /**
     * 创建所引
     *
     * @throws IOException
     */
    @Test
    public void createIndex() throws IOException {
        User user = new User("1", "张三", "test");
        Index index = new Index.Builder(user).index(INDEX).type(TYPE).build();
        try {
            JestResult jr = jestClient.execute(index);
            System.out.println(jr.isSucceeded());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void add() throws IOException {
        User user = new User("1", "李四", "张三");
        Index index = new Index.Builder(user).index(INDEX).type(TYPE).build();
        try {
            JestResult jr = jestClient.execute(index);
            System.out.println(jr.isSucceeded());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void search() throws Exception {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(new MultiMatchQueryBuilder("张三", "username", "infomartion", "desc"));
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("username");
        highlightBuilder.fragmentSize(500);
        highlightBuilder.preTags("<font class=\"red\">").postTags("</font>");
        searchSourceBuilder.highlighter(highlightBuilder);

        log.info(searchSourceBuilder.toString());
        SearchResult result = jestClient.execute(new Search.Builder(searchSourceBuilder.toString())
                .addIndex(INDEX)
                .addType(TYPE)
                .build());
        List<SearchResult.Hit<User, Void>> hits = result.getHits(User.class);
        for (var user : hits) {
            log.info(user.source.toString());
        }
        log.info(result.getJsonString());

    }


    /**
     * 删除索引
     *
     * @param index
     * @throws IOException
     */
    public void deleteIndex(String index) throws IOException {
        DeleteIndex deleteIndex = new DeleteIndex.Builder(index).build();
        JestResult result = jestClient.execute(deleteIndex);
        log.info("【删除索引:{}...】", index);
        checkArgument(result.isSucceeded(), result.getErrorMessage());
    }

    /**
     * mapping映射
     *
     * @throws IOException
     */
    public void createMapping() throws IOException {

        JSONObject objSource = new JSONObject().fluentPut("properties", new JSONObject()
                .fluentPut("title", new JSONObject()
                        .fluentPut("type", "text")
                )
                .fluentPut("author", new JSONObject()
                        .fluentPut("type", "text")
                )
                .fluentPut("content", new JSONObject()
                        .fluentPut("type", "text")
                )
                .fluentPut("publishDate", new JSONObject()
                        .fluentPut("type", "date")
                )
        );
        PutMapping putMapping = new PutMapping.Builder(INDEX, TYPE, objSource.toJSONString()).build();
        JestResult result = jestClient.execute(putMapping);

        log.info("【创建mapping映射成功...】");
        checkArgument(result.isSucceeded(), result.getErrorMessage());

    }

    /**
     * 根据id查询
     *
     * @return
     */
    @Test
    public void searchById() throws Exception {
        Get build = new Get.Builder(INDEX, String.valueOf("IGUYI3MBUsuzZ42R4UJh"))
                .index(INDEX)
                .type(TYPE)
                .build();
        JestResult result = jestClient.execute(build);
        log.info(result.getJsonString());
    }

    @Test
    public void updateById() throws IOException {
        Get build = new Get.Builder(INDEX, String.valueOf("IGUYI3MBUsuzZ42R4UJh"))
                .index(INDEX)
                .type(TYPE)
                .build();
        User result = jestClient.execute(build).getSourceAsObject(User.class);
        result.setInfomartion("测试中国人");
        Index index = new Index.Builder(result).index(INDEX).type(TYPE).id("IGUYI3MBUsuzZ42R4UJh").build();

        jestClient.execute(index);
    }

    /**
     * 根据id删除数据
     *
     * @throws Exception
     */
    @Test
    public void delete() throws Exception {
        Delete build = new Delete.Builder(String.valueOf("H2UUI3MBUsuzZ42RekIL"))
                .index(INDEX)
                .type(TYPE)
                .build();
        JestResult result = jestClient.execute(build);
        checkArgument(result.isSucceeded(), result.getErrorMessage());
        log.info("del result:{}", result.isSucceeded());
    }
}
