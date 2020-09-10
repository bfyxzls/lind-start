package com.lind.hbase.service;

import com.lind.hbase.entity.DataRecord;

import java.io.IOException;

public interface HBaseService {

    /**
     * 创建表
     *
     * @param tableName tableName
     * @return
     * @throws IOException
     */
    boolean createTable(String tableName) throws IOException;

    /**
     * 表格是否已经存在
     *
     * @param tableName tableName
     * @return
     * @throws IOException
     */
    boolean tableExist(String tableName) throws IOException;


    /**
     * 保存数据
     *
     * @param tableName  tableName
     * @param dataRecord dataRecord
     * @return 保存数据的rowKey
     */
    String save(String tableName, DataRecord dataRecord);

    /**
     * 更新数据
     *
     * @param tableName  tableName
     * @param dataRecord dataRecord
     * @return
     */
    String update(String tableName, DataRecord dataRecord);

    /**
     * 获取数据的指定列
     *
     * @param tableName tableName
     * @param rowKey    rowKey
     * @param fieldName fieldName
     * @return
     */
    Object getValue(String tableName, String rowKey, String fieldName);


    /**
     * 根据rowKey获取数据
     *
     * @param tableName  tableName
     * @param rowKey     rowKey
     * @param fieldNames fieldNames
     * @return
     */
    DataRecord getByKey(String tableName, String rowKey, String... fieldNames);

    /**
     * 根据rowKey获取全字段
     *
     * @param tableName tableName
     * @param rowKey    rowKey
     * @return
     */
    DataRecord getByKey(String tableName, String rowKey);

}
