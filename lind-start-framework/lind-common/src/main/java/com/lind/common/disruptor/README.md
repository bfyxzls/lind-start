# 事件数据源
LongEvent
# 事件数据源工厂 
LongEventFactory，实现了EventFactory
# 事件生产者
LongEventProducer，向RingBuffer这个环形缓冲区里生产数据
* 第一步：先从 RingBuffer 获取下一个可以写入的事件的序号；
* 第二步：获取对应的事件对象，将数据写入事件对象；
* 第三部：将事件提交到 RingBuffer;
> 生产者在生产数据时，需要从RingBuffer里取到可用的节点，如果消费者比较慢时，有可能把所有RingBuffer都占完，这时，生产者会出现阻塞情况，当有空闲节点时，数据才会进行新的生产。
# 事件订阅者
LongEventHandler，实现了EventHandler，从RingBuffer里拿数据
# Disruptor
* 对订阅者来说，它的绑定是通过disruptor完成的，还可以控制订阅者的顺序和依赖关系
* 对发布者来说，通过disruptor对象说发布事件
* Disruptor里会有对RingBuffer的引用，生产者需要这个`RingBuffer`对象，用来获取节点，为节点赋值，和发布等