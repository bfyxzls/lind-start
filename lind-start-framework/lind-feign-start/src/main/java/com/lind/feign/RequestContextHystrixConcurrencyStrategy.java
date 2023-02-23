package com.lind.feign;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;

import java.util.concurrent.Callable;

/**
 * 线程上下文传递，hystrix的相关实现有兴趣可以看源码. 将当前使用的用户存到ThreadLocal中，并共享给feign生产的线程.
 */
public class RequestContextHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {

	public RequestContextHystrixConcurrencyStrategy() {
		// 注册成hystrix的插件
		HystrixPlugins.getInstance().registerConcurrencyStrategy(this);
	}

	public <T> Callable<T> wrapCallable(Callable<T> callable) {
		// 先包装一下要执行的任务，在这里把ThreadLocal的值取出来
		return new ThreadLocalCallable<T>(callable);
	}

	public static class ThreadLocalCallable<V> implements Callable<V> {

		private Callable<V> target;

		private String currentUserId;

		public ThreadLocalCallable(Callable<V> target) {
			this.target = target;
			// 取ThreadLocal的值并保存起来
			this.currentUserId = SecurityCurrentUser.getUser();
		}

		@Override
		public V call() throws Exception {
			try {
				// 真正执行的时候再设置进去
				SecurityCurrentUser.saveUser(this.currentUserId);
				// 真正执行的时候再设置进去
				return target.call();
			}
			finally {
				// 执行完毕再清理掉
				SecurityCurrentUser.removeUser();
			}
		}

	}

}
