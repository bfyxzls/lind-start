package com.lind.elasticsearch;

import com.lind.elasticsearch.entity.DataRecord;
import com.lind.elasticsearch.util.EsDataUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest()
@RunWith(SpringRunner.class) //让测试在Spring容器环境下执行
@Slf4j
public class EsDataUtilsTest {

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 添加json数据
     */
    @Test
    public void putJsonData() throws IOException {
        DataRecord dataRecord = new DataRecord();
        dataRecord.put("name", "lr");
        dataRecord.put("sex", "1");
       // dataRecord.setId("7782782b-e153-4a44-adff-5d408e7035de");
        String s = EsDataUtils.saveOrUpdate(elasticsearchRestTemplate.getClient(), "lind-test", dataRecord);
        System.out.println("s=" + s);
    }
}
