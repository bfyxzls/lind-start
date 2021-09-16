package com.lind.elasticsearch;

import com.lind.elasticsearch.util.EsIndexUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest()
@RunWith(SpringRunner.class) //让测试在Spring容器环境下执行
@Slf4j
public class EsIndexUtilsTest {
    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 建立索引
     *
     * @throws IOException
     */
    @Test
    public void createIndex() throws IOException {
        EsIndexUtils.IndexDTO indexDTO = new EsIndexUtils.IndexDTO("lind-test",
                1,
                1);
        Assert.assertTrue(EsIndexUtils.createIndex(elasticsearchRestTemplate.getClient(), indexDTO));
    }

    /**
     * 更新mapping
     *
     * @throws IOException
     */
    @Test
    public void putMapping() throws IOException {
        createIndex();
        List<EsIndexUtils.FieldDTO> list = new ArrayList<>();
        EsIndexUtils.FieldDTO fieldDTO = new EsIndexUtils.FieldDTO();
        fieldDTO.setName("name");
        fieldDTO.setFieldType(FieldType.Keyword);
        list.add(fieldDTO);

        EsIndexUtils.FieldDTO city = new EsIndexUtils.FieldDTO();
        city.setName("city");
        city.setFieldType(FieldType.Keyword);

        EsIndexUtils.FieldDTO district = new EsIndexUtils.FieldDTO();
        district.setName("district");
        district.setFieldType(FieldType.Keyword);

        EsIndexUtils.FieldDTO address = new EsIndexUtils.FieldDTO();
        address.setName("address");
        address.setFieldType(FieldType.Object);
        address.setChildren(Arrays.asList(city, district));
        list.add(address);

        EsIndexUtils.putMapping(elasticsearchRestTemplate.getClient(), "lind-test", list, true);
    }


}
