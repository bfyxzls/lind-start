package com.lind.hbase2;

import com.lind.hbase2.template.HBaseTemplate;
import javafx.scene.control.Tab;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest() //main程序必须有application入口，application.yml才工作
public class Hbase2Test {
    static final String TABLE_NAME = "lind-component";
    static final String COLUMN_FAMILY="info";
    @Autowired
    HBaseTemplate hBaseDao;

    /**
     * 创建表测试
     */
    @Test
    public void testCreateTable() {
        hBaseDao.createTable(TABLE_NAME,COLUMN_FAMILY);
    }

    /**
     * 删除表测试
     */
    @Test
    public void deleteTable() {
        hBaseDao.dropTable(TABLE_NAME);
    }


    /**
     * 判断表是否存在
     */
    @Test
    public void testTableExist() {
        System.out.println(hBaseDao.tableExists("lbs"));
        System.out.println(hBaseDao.tableExists("LBS"));
        System.out.println(hBaseDao.tableExists("goods"));
        System.out.println(hBaseDao.tableExists("GOODS"));
    }

    /**
     * 插入一条记录
     */
    @Test
    public void putTest() {
        String rowKey = UUID.randomUUID().toString();
        hBaseDao.put(TABLE_NAME, rowKey, COLUMN_FAMILY, "name", Bytes.toBytes("testData"));
    }

    /**
     * 批量插入数据
     */
    @Test
    public void testBatchPut() {
        String rowKey = String.valueOf(System.currentTimeMillis());
        Put put = new Put(rowKey.getBytes());
        String defaultColumn = "CF1";
        String column1 = "col1";
        String column2 = "col2";
        String column3 = "col3";

        String value = "test";
        put.addColumn(defaultColumn.getBytes(), column1.getBytes(), value.getBytes());
        put.addColumn(defaultColumn.getBytes(), column2.getBytes(), value.getBytes());
        put.addColumn(defaultColumn.getBytes(), column3.getBytes(), value.getBytes());

        List<Put> putList = new ArrayList<>();
        putList.add(put);
        putList.add(put);
        putList.add(put);
        putList.add(put);
        putList.add(put);

        hBaseDao.putBatch(TABLE_NAME, putList);
    }

    @Test
    public void deleteTest() {
        hBaseDao.delete(TABLE_NAME, "1534210201115", "CF1", "col2");
    }

    @Test
    public void deleteBatchTest() {
        String rowKey1 = "1534164113922";
        String rowKey2 = "1534168248328";

        List<Delete> deleteList = new ArrayList<>();
        Delete delete = new Delete(rowKey1.getBytes());
        Delete delete1 = new Delete(rowKey2.getBytes());
        deleteList.add(delete);
        deleteList.add(delete1);
        hBaseDao.deleteBatch(TABLE_NAME, deleteList);
    }

    @Test
    public void testQueryScan() {

         List<Long> timestampList = new ArrayList<>();
        Long timeDifference = 3L * 30L * 24L * 60L * 60L * 1000L;
        Long from = System.currentTimeMillis() - timeDifference;
        timestampList.add(from);
        TimestampsFilter timestampsFilter = new TimestampsFilter(timestampList);

        Scan scan = new Scan();
        try {
            scan.setTimeRange(from, System.currentTimeMillis());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResultScanner resultScanner = hBaseDao.queryByScan(TABLE_NAME, scan);

        resultScanner.forEach(result -> {
            result.listCells().forEach(cell -> {
                System.out.println("row:" + Bytes.toLong(CellUtil.cloneRow(cell)) + "      family:"
                        + Bytes.toString(CellUtil.cloneFamily(cell)) + " qualifier: " + Bytes.toString(CellUtil.cloneQualifier(cell))
                        + "    value:" + Bytes.toString(CellUtil.cloneValue(cell)));

            });
        });
    }

    @Test
    public void testPageFilter() {
         Scan scan = new Scan();

        PageFilter pageFilter = new PageFilter(1);

        SingleColumnValueFilter singleColumnValueFilter = new SingleColumnValueFilter(
                "CF1".getBytes(),
                "col1".getBytes(),
                CompareFilter.CompareOp.EQUAL,
                new SubstringComparator("group"));

        singleColumnValueFilter.setFilterIfMissing(true);
        FilterList filterList = new FilterList();
        filterList.addFilter(singleColumnValueFilter);
        filterList.addFilter(pageFilter);

        scan.setFilter(filterList);

        ResultScanner resultScanner = hBaseDao.queryByScan(TABLE_NAME, scan);

        try {
            resultScanner.forEach(result -> {
                result.listCells().forEach(cell -> {
                    System.out.println("row:" + Bytes.toString(CellUtil.cloneRow(cell)) + "      family:"
                            + Bytes.toString(CellUtil.cloneFamily(cell)) + " qualifier: " + Bytes.toString(CellUtil.cloneQualifier(cell))
                            + "    value:" + Bytes.toString(CellUtil.cloneValue(cell)));

                });
            });
        } finally {
            if (resultScanner != null) {
                resultScanner.close();
            }
        }
    }

}
