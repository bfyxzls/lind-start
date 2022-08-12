package com.lind.hbase;

import cn.hutool.core.lang.Assert;
import com.lind.hbase.util.HbaseConnectionUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author lind
 * @date 2022/8/10 9:18
 * @since 1.0.0
 */
public class UtilTest {
    @Test
    public void getTable() throws IOException {
        Table table = HbaseConnectionUtil.getConnection373839().getTable(TableName.valueOf("nezha:nvwa_pfnl_dev"));
        String rowKey = "123";
        Get get = new Get(Bytes.toBytes(rowKey));
        Assert.isFalse(table.exists(get));

    }
}
