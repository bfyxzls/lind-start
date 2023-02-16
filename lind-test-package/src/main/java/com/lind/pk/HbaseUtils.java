package com.lind.pk;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

/**
 * @author lind
 * @date 2023/2/16 11:17
 * @since 1.0.0
 */
public class HbaseUtils {

	// 创建hbase配置对象
	private static Configuration conf = HBaseConfiguration.create();

	// 通过连接工厂创建连接对象
	private static Connection conn = null;

	// create 'apperrorinfo','youmeng568'
	static {
		// 使用eclipse时必须添加这个，否则无法定位
		conf.set("hbase.zookeeper.quorum", "kafka146");
		conf.set("hbase.zookeeper.property.clientPort", "2181");
		try {
			conn = ConnectionFactory.createConnection(conf);
			System.out.println(conn.isClosed());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static TableName retrieveTableName(String tableName) {
		return TableName.valueOf("default" + ":" + tableName);
	}

	public static boolean exist(String tableName) throws IOException {

		Admin admin = conn.getAdmin();
		boolean result= admin.tableExists(retrieveTableName(tableName));
		return result;
	}

	public static boolean createTable(String tableName, String familyName) throws IOException {
		HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf("info", tableName));
		hTableDescriptor.addFamily(new HColumnDescriptor(familyName));
		conn.getAdmin().createTable(hTableDescriptor);
		return true;
	}

}
