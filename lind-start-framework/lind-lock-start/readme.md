# 作用
实现对某个资源或者代码片断进行锁的添加，这种锁是跨进度，跨服务的。
# 依赖引用
```
<dependency>
 <groupId>com.pkulaw</groupId>
 <artifactId>pkulaw-lock-start</artifactId>
 <version>1.0.0</version>
</dependency>
```
# 配置
```
pkulaw.redis.lock.enable: true #开启
pkulaw.redis.lock.registryKey: system-lock #锁前缀
pkulaw.redis.lock.interrupt: false #没有获到锁是否立即中断,true表示中断,false表示阻塞可重入锁
pkulaw.redis.lock.manualLockKey：user-lock #手动锁键
```
# 使用
```
@Autowire
DistributedLockTemplate distributedLockTemplate;
private void lock5Second() {
    distributedLockTemplate.execute("订单流水号", 2, TimeUnit.SECONDS, new Callback() {
        @Override
        public Object onGetLock() throws InterruptedException {
            // 获得锁后要做的事
            log.info("{} 拿到锁，需要5秒钟，这时有请求打入应该被阻塞或者拒绝", Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(5L);
            return null;
        }

        @Override
        public Object onTimeout() throws InterruptedException {
            // 获取到锁（获取锁超时）后要做的事
            log.info("{} 没拿到锁", Thread.currentThread().getName());
            return null;
        }
    });
}
```
