package com.lind.lindmanager.feign;

import feign.Retryer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author lind
 * @date 2023/1/29 13:47
 * @since 1.0.0
 */
@Configuration
@Slf4j
public class SimpleRetryer {

	public static class CommonFeignRetry extends Retryer.Default {

		public CommonFeignRetry() {
			// 重试5次 最大间隔时间1秒
			this(1, SECONDS.toMillis(10), 5);
		}

		public CommonFeignRetry(long period, long maxPeriod, int maxAttempts) {
			super(period, maxPeriod, maxAttempts);
		}

		@Override
		public Retryer clone() {
			return new CommonFeignRetry();
		}

	}

}
