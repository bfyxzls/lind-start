package com.lind.start.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	// @Scheduled(cron = "0/1 * * * * ?")
	// public void saveFile() throws InterruptedException {
	//
	// System.out.println("start" + Thread.currentThread().getId());
	// Thread.sleep(100);
	// System.out.println("end" + Thread.currentThread().getId());
	//
	// }

}
