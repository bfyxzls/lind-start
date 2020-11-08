package com.lind.start.test.handler;

import com.lind.common.handler.ObjectEventListener;
import org.apache.hadoop.hbase.util.Order;

/**
 * 事件订阅需要实现这个接口.
 */
public interface OrderEventListener extends ObjectEventListener {
}
