package com.lind.hbase;

import com.lind.common.util.SnowFlakeUtils;
import com.lind.hbase.entity.DataRecord;
import com.lind.hbase.service.HBaseService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class HbaseTest {
    static final String TABLE_NAME = "lind-test";
    @Autowired
    HBaseService hBaseService;

    @Test
    public void create() throws IOException {
        hBaseService.createTable(TABLE_NAME);
    }

    @Test
    public void insert() {
        DataRecord dataRecord = new DataRecord();
        dataRecord.setId(String.valueOf(SnowFlakeUtils.getFlowIdInstance().nextId()));
        dataRecord.append("name", "zzl");
        dataRecord.append("age", "男");
        dataRecord.append("marry", true);
        hBaseService.save(TABLE_NAME, dataRecord);
        log.info("data:{}", dataRecord);
    }

    @Test
    public void insertDynamicFields() {
        DataRecord dataRecord = new DataRecord();
        dataRecord.setId(String.valueOf(SnowFlakeUtils.getFlowIdInstance().nextId()));
        dataRecord.append("name", "zhanghangzheng");
        dataRecord.append("age", "男");
        dataRecord.append("marry", false);
        dataRecord.append("school", "shiyan-2-school-daxing");
        hBaseService.save(TABLE_NAME, dataRecord);
    }

    @Test
    public void append() {
        DataRecord dataRecord = new DataRecord();
        dataRecord.setId(String.valueOf(SnowFlakeUtils.getFlowIdInstance().nextId()));
        dataRecord.append("name", "zhangzhanling");
        dataRecord.append("age", "男");
        dataRecord.append("marry", true);
        dataRecord.append("address", "北京市");
        hBaseService.save(TABLE_NAME, dataRecord);
    }

    @Test
    public void search() {
        DataRecord dataRecord = hBaseService.getByKey(TABLE_NAME, "409438564758065152");
        System.out.print(dataRecord.toString());
    }

    @Test
    public void getByRowKey() {
        DataRecord dataRecord = hBaseService.getByKey(TABLE_NAME, "407730685881618432", "id");
        Assert.assertNotNull(dataRecord != null);
        dataRecord = hBaseService.getByKey(TABLE_NAME, "407730685881618431", "id");
        Assert.assertNotNull(dataRecord != null);
    }

    @Test
    public void existByRowKey() {
        Assert.assertTrue(hBaseService.existDataRecord(TABLE_NAME, "448645502448177152"));
    }

    @Test
    public void update() {
        DataRecord dataRecord = hBaseService.getByKey(TABLE_NAME, "409438564758065152");
        dataRecord.put("name", null);
        hBaseService.update(TABLE_NAME, dataRecord);
        dataRecord = hBaseService.getByKey(TABLE_NAME, "409438564758065152");
        System.out.print(dataRecord.toString());
    }
}
