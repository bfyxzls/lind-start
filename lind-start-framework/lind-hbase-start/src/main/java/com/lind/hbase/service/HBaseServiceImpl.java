package com.lind.hbase.service;

import com.lind.hbase.api.RowMapper;
import com.lind.hbase.api.TableCallback;
import com.lind.hbase.entity.DataRecord;
import com.lind.hbase.template.HBaseTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.util.Assert;
import org.springframework.util.SerializationUtils;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class HBaseServiceImpl implements HBaseService {

    private static final String FAMILY_NAME = "info";
    private static final byte[] FAMILY_NAME_BYTE = Bytes.toBytes(FAMILY_NAME);
    private final HBaseTemplate hBaseTemplate;
    /**
     * HBase的命名空间,需要提前定义,通过构造方法赋值
     */
    private final String NAME_SPACE;

    @Override
    public boolean createTable(String tableName) throws IOException {
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(NAME_SPACE, tableName));
        hTableDescriptor.addFamily(new HColumnDescriptor(FAMILY_NAME));
        hBaseTemplate.getConnection().getAdmin().createTable(hTableDescriptor);
        return true;
    }

    @Override
    public boolean tableExist(String tableName) throws IOException {
        Admin admin = hBaseTemplate.getConnection().getAdmin();
        return admin.tableExists(retrieveTableName(tableName));
    }

    @Override
    public String save(String tableName, DataRecord dataRecord) {
        String execute = hBaseTemplate.execute(retrieveTableName(tableName), new TableCallback<String>() {
            @Override
            public String doInTable(Table table) throws Throwable {
                String id = dataRecord.getId();
                String idStr = StringUtils.isBlank(id) ? UUID.randomUUID().toString() : id;
                Put put = new Put(Bytes.toBytes(idStr));
                dataRecord.entrySet().stream()
                        .forEach(en -> {
                            put.addColumn(FAMILY_NAME_BYTE, // 列族
                                    Bytes.toBytes(en.getKey()), // 列
                                    SerializationUtils.serialize(en.getValue())); // 列值
                        });
                table.put(put);

                return new String(put.getRow(),"UTF-8");
            }
        });
        return execute;
    }

    @Override
    public String update(String tableName, DataRecord dataRecord) {
        Assert.notNull(dataRecord, "更新数据不能为空");
        Assert.hasText(dataRecord.getId(), "更新id不能为空");
        return save(tableName, dataRecord);
    }

    @Override
    public Object getValue(String tableName, String rowKey, String fieldName) {
        Object o = hBaseTemplate.get(retrieveTableName(tableName), rowKey, new RowMapper<Object>() {
            @Override
            public Object mapRow(Result result, int rowNum) throws Exception {
                if (result.isEmpty()) {
                    return null;
                }
                byte[] value = result.getValue(FAMILY_NAME_BYTE, Bytes.toBytes(fieldName));
                Object deserialize = SerializationUtils.deserialize(value);
                return deserialize;
            }
        });
        return o;
    }

    @Override
    public DataRecord getByKey(String tableName, String rowKey, String... fieldNames) {
        DataRecord dataRecord = hBaseTemplate.get(retrieveTableName(tableName), rowKey, FAMILY_NAME,
                new RowMapper<DataRecord>() {
                    @Override
                    public DataRecord mapRow(Result result, int rowNum) throws Exception {
                        if (result.isEmpty()) {
                            return null;
                        }
                        DataRecord dataRecord = new DataRecord();
                        for (String fieldName : fieldNames) {
                            //获取值
                            byte[] value = result.getValue(FAMILY_NAME_BYTE, Bytes.toBytes(fieldName));
                            if (value == null) {
                                dataRecord.append(fieldName, null);
                            } else {
                                dataRecord.append(fieldName, SerializationUtils.deserialize(value));
                            }
                        }
                        dataRecord.setId(rowKey);
                        return dataRecord;
                    }
                });
        return dataRecord;
    }

    @Override
    public DataRecord getByKey(String tableName, String rowKey) {
        return hBaseTemplate.get(retrieveTableName(tableName), rowKey, FAMILY_NAME, new RowMapper<DataRecord>() {
            @Override
            public DataRecord mapRow(Result result, int rowNum) throws Exception {
                if (result.isEmpty()) {
                    return null;
                }
                DataRecord dataRecord = new DataRecord();

                CellScanner cellScanner = result.cellScanner();
                while (cellScanner.advance()) {
                    Cell current = cellScanner.current();

                    byte[] qualifierArray = CellUtil.cloneQualifier(current);
                    byte[] valueArray = CellUtil.cloneValue(current);
                    dataRecord.append(Bytes.toString(qualifierArray), SerializationUtils.deserialize(valueArray));
                }
                dataRecord.setId(rowKey);
                return dataRecord;
            }
        });
    }

    private TableName retrieveTableName(String tableName) {
        return TableName.valueOf(NAME_SPACE + ":" + tableName);
    }
}
