package com.lind.feign;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 线程上下文传递，hystrix的相关实现有兴趣可以看源码. 将当前使用的用户存到ThreadLocal中，并共享给feign生产的线程.
 */
public class RequestContextHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {

	public <T> Callable<T> wrapCallable(Callable<T> callable) {
		// 先包装一下要执行的任务，在这里把ThreadLocal的值取出来
		return new ThreadLocalCallable<T>(callable);
	}

	public static class ThreadLocalCallable<V> implements Callable<V> {

		private Callable<V> target;

		private Map<String, String> dic = new HashMap<>();

		public ThreadLocalCallable(Callable<V> target) {
			this.target = target;
			// 取ThreadLocal的值并保存起来
			NextHttpHeader.get().forEach(o -> {
				this.dic.put(o, NextHttpHeader.get(o));
			});
		}

		@Override
		public V call() throws Exception {

			// 真正执行的时候再设置进去
			this.dic.keySet().forEach(o->{
				NextHttpHeader.set(o,this.dic.get(o));
			});
			// 真正执行的时候再设置进去
			return target.call();
		}

	}

}
