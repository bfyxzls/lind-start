package com.lind.start.test.hbase.service;

import com.lind.start.test.hbase.entity.DataRecord;

import java.io.IOException;

/**
 * @author 赵阳
 * @Description TODO
 * @date 2020/7/20 9:03
 **/
public interface HBaseService {

    /**
     * 创建表
     *
     * @param tableName
     * @return
     * @throws IOException
     */
    boolean createTable(String tableName) throws IOException;

    /**
     * 表格是否已经存在
     *
     * @param tableName
     * @return
     * @throws IOException
     */
    boolean tableExist(String tableName) throws IOException;


    /**
     * 保存数据
     *
     * @param tableName
     * @param dataRecord
     * @return 保存数据的rowKey
     */
    String save(String tableName, DataRecord dataRecord);

    /**
     * 更新数据
     *
     * @param tableName
     * @param dataRecord
     * @return
     */
    String update(String tableName, DataRecord dataRecord);

    /**
     * 获取数据的指定列
     *
     * @param tableName
     * @param rowKey
     * @param fieldName
     * @return
     */
    Object getValue(String tableName, String rowKey, String fieldName);


    /**
     * 根据rowKey获取数据
     *
     * @param tableName
     * @param rowKey
     * @param fieldNames
     * @return
     */
    DataRecord getByKey(String tableName, String rowKey, String... fieldNames);

    /**
     * 根据rowKey获取全字段
     *
     * @param tableName
     * @param rowKey
     * @return
     */
    DataRecord getByKey(String tableName, String rowKey);

}
