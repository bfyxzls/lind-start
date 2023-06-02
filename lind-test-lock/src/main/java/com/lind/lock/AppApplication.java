package com.lind.lock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author lind
 * @date 2023/5/26 17:11
 * @since 1.0.0
 */
@RestController
@SpringBootApplication
@Slf4j
public class AppApplication {

	@Autowired
	RedissonClient redissonClient;

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	String lock5Second(String orderId) {
		//初始化锁的对象
		RLock rLock = redissonClient.getLock("lock_" + orderId);
		try {
			//尝试加锁, 最多等待5秒
			boolean lock = rLock.tryLock(20, -1, TimeUnit.SECONDS);
			if (lock) {
				log.info("获取到锁，执行支付流程");
				//延时15s
				Thread.sleep(15000);
				log.info("支付完成");
				return "支付完成";
			} else {
				log.info("请稍等，有人正在支付");
				return "请稍等，有人正在支付";
			}
		} catch (InterruptedException e) {
			log.error("获取锁异常 e:{}", e.getMessage());
			return "获取锁异常";
		} finally {
			//是锁定状态，并且是当前执行线程的锁，释放锁
			if (rLock.isLocked() && rLock.isHeldByCurrentThread()) {
				rLock.unlock();
			}
		}
	}

	@GetMapping("lock")
	public ResponseEntity lock() {
		// 三个人同时支付同一个订单，当第一个执行15秒后，
		// 由于第二人最多等待20秒，所以也可以拿到锁，
		// 而第三个人没有抢到锁，它在第二个人执行一段时间后，达到了20S（第一个人用了15S，第二个人用了5S）后，他就不去等待了
		Thread thread1 = new Thread(() -> lock5Second("o1"));
		Thread thread2 = new Thread(() -> lock5Second("o1"));
		Thread thread3 = new Thread(() -> lock5Second("o1"));

		thread1.start();
		thread2.start();
		thread3.start();
		return ResponseEntity.ok("ok");
	}

}
