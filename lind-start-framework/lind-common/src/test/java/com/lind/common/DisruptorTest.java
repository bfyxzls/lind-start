package com.lind.common;

import com.lind.common.disruptor.LongEvent;
import com.lind.common.disruptor.LongEventFactory;
import com.lind.common.disruptor.LongEventHandler;
import com.lind.common.disruptor.LongEventProducer;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DisruptorTest {
    @Test
    public void test() throws Exception {
        // The factory for the event
        LongEventFactory factory = new LongEventFactory();

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;

        // Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, bufferSize, DaemonThreadFactory.INSTANCE);

        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandler());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; l < 100; l++) {
            bb.putLong(0, l);
            producer.onData(bb);
            Thread.sleep(1000);
        }
    }

    @Test
    public void test2() {
        StopWatch stopWatch = new StopWatch(
        );
        stopWatch.start();
        // 参数准备工作
        OrderEventFactory orderEventFactory = new OrderEventFactory();
        int ringBufferSize = 8;//31ms
       // int ringBufferSize = 4;//100064
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        /**
         * 1 eventFactory: 消息(event)工厂对象
         * 2 ringBufferSize: 容器的长度
         * 3 executor: 线程池(建议使用自定义线程池) RejectedExecutionHandler
         * 4 ProducerType: 单生产者 还是 多生产者
         * 5 waitStrategy: 等待策略
         */
        //1. 实例化disruptor对象
        Disruptor<OrderEvent> disruptor = new Disruptor<OrderEvent>(orderEventFactory,
                ringBufferSize,
                executor,
                ProducerType.SINGLE,
                new BlockingWaitStrategy());

        //2. 添加消费者的监听 (构建disruptor 与 消费者的一个关联关系)
        disruptor.handleEventsWith(new OrderEventHandler());

        //3. 启动disruptor
        disruptor.start();

        //4. 获取实际存储数据的容器: RingBuffer
        RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();

        OrderEventProducer producer = new OrderEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);

        for (long i = 0; i < 100; i++) {
            bb.putLong(0, i);
            producer.sendData(bb);
        }

        disruptor.shutdown();
        executor.shutdown();
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }

    public class OrderEvent {

        private long value; //订单的价格

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }
    }

    public class OrderEventFactory implements EventFactory<OrderEvent> {

        public OrderEvent newInstance() {
            return new OrderEvent();        //这个方法就是为了返回空的数据对象（Event）
        }
    }

    public class OrderEventHandler implements EventHandler<OrderEvent> {

        public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {
            Thread.sleep(1000);
            System.err.println("消费者: " + event.getValue());
        }
    }

    public class OrderEventProducer {

        private RingBuffer<OrderEvent> ringBuffer;

        public OrderEventProducer(RingBuffer<OrderEvent> ringBuffer) {
            this.ringBuffer = ringBuffer;
        }

        public void sendData(ByteBuffer data) {
            //1 在生产者发送消息的时候, 首先 需要从我们的ringBuffer里面 获取一个可用的序号
            long sequence = ringBuffer.next();  //0
            try {
                //2 根据这个序号, 找到具体的 "OrderEvent" 元素 注意:此时获取的OrderEvent对象是一个没有被赋值的"空对象"
                OrderEvent event = ringBuffer.get(sequence);
                //3 进行实际的赋值处理
                event.setValue(data.getLong(0));
            } finally {
                //4 提交发布操作
                ringBuffer.publish(sequence);
            }
        }
    }

}
