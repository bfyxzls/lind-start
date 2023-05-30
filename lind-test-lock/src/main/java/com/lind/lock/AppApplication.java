package com.lind.lock;

import com.lind.redis.lock.template.Callback;
import com.lind.redis.lock.template.DistributedLockTemplate;
import lombok.extern.slf4j.Slf4j;
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
	DistributedLockTemplate distributedLockTemplate;

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	void lock5Second() {
		// 锁最大排除时等待的时间，超过这个时间还没拿到锁，那就放弃.

		distributedLockTemplate.execute("订单流水号", 70, TimeUnit.SECONDS, new Callback() {
			@Override
			public Object onGetLock() throws InterruptedException {
				// 获得锁后要做的事
				log.info("{} 拿到锁，需要5秒钟，这时有请求打入应该被阻塞或者拒绝", Thread.currentThread().getName());
				TimeUnit.SECONDS.sleep(80);
				log.info("大任务执行完成");
				return null;
			}

			@Override
			public Object onTimeout() throws InterruptedException {
				// 获取锁超时后要做的事
				log.info("{} 没拿到锁", Thread.currentThread().getName());
				return null;
			}
		});
	}

	@GetMapping("lock")
	public ResponseEntity lock() {
		Thread thread1 = new Thread(() -> lock5Second());
		Thread thread2 = new Thread(() -> lock5Second());
		Thread thread3 = new Thread(() -> lock5Second());

		thread1.start();
		thread2.start();
		thread3.start();
		return ResponseEntity.ok("ok");
	}

}
