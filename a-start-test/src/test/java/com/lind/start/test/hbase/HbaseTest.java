package com.lind.start.test.hbase;

import com.lind.common.util.SnowFlakeUtil;
import com.lind.start.test.hbase.entity.DataRecord;
import com.lind.start.test.hbase.service.HBaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HbaseTest {
    static final String TABLE_NAME = "lind";
    @Autowired
    HBaseService hBaseService;

    @Test
    public void create() throws IOException {
        hBaseService.createTable(TABLE_NAME);
    }

    @Test
    public void insert() {
        DataRecord dataRecord = new DataRecord();
        dataRecord.setId(String.valueOf(SnowFlakeUtil.getFlowIdInstance().nextId()));
        dataRecord.append("name", "zzl");
        dataRecord.append("age", "男");
        dataRecord.append("marry", true);
        hBaseService.save(TABLE_NAME, dataRecord);
    }

    @Test
    public void append() {
        DataRecord dataRecord = new DataRecord();
        dataRecord.setId(String.valueOf(SnowFlakeUtil.getFlowIdInstance().nextId()));
        dataRecord.append("name", "zhangzhanling");
        dataRecord.append("age", "男");
        dataRecord.append("marry", true);
        dataRecord.append("address", "北京市");
        hBaseService.save(TABLE_NAME, dataRecord);
    }

    @Test
    public void search() {
      DataRecord dataRecord=  hBaseService.getByKey(TABLE_NAME,"305849083174588416");
        System.out.print(dataRecord.toString());
    }
}
