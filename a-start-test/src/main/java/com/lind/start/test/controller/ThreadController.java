package com.lind.start.test.controller;

import com.lind.start.test.CurrentThreadObj;
import com.lind.start.test.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

import static java.util.Arrays.asList;

@RestController
public class ThreadController {

	@Autowired
	UserService userService;

	@GetMapping("/thread-static")
	public void threadStatic(HttpServletRequest request) {
		CurrentThreadObj.set("hello", "你好世界" + request.getSession().getId());
		userService.getDetail(1);
	}

	@GetMapping("/do-fork")
	public void doFork() throws ExecutionException, InterruptedException {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		ForkDoWork forkDoWork = new ForkDoWork(Arrays.asList("a", "b", "c", "d"));
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		Future<Integer> result = forkJoinPool.submit(forkDoWork);
		System.out.println("结果--》result:" + result.get());
		stopWatch.stop();
		System.out.println(stopWatch.getLastTaskTimeMillis());

	}

	@GetMapping("/do-fast")
	public void doFast() throws InterruptedException, ExecutionException {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		ExecutorService executor = Executors.newFixedThreadPool(2);
		List<Future<Integer>> results = executor
				.invokeAll(asList(new DoWork(Arrays.asList("a", "b")), new DoWork(Arrays.asList("c", "d"))));
		executor.shutdown();

		// 合并结果
		for (Future<Integer> result : results) {
			System.out.println(result.get());
		}
		stopWatch.stop();
		System.out.println(stopWatch.getLastTaskTimeMillis());
	}

	/**
	 * 干一件不好干的事，使用Callable接口，需要 FutureTask实现类的支持，用于接收运算结果.
	 */
	class DoWork implements Callable<Integer> {

		/**
		 * 需要处理的对象集合，每个线程传递自己的对象.
		 */
		List<String> list;

		public DoWork(List<String> list) {
			this.list = list;
		}

		@Override
		public Integer call() throws Exception {
			for (String s : list) {
				System.out.println(Thread.currentThread().getId() + ":" + s);
			}
			Thread.sleep(3000);
			return 1;
		}

	}

	/**
	 * 分而制之.
	 */
	class ForkDoWork extends RecursiveTask<Integer> {

		List<String> list;

		public ForkDoWork(List<String> list) {
			this.list = list;
		}

		@SneakyThrows
		@Override
		protected Integer compute() {
			for (String s : list) {
				System.out.println(Thread.currentThread().getId() + ":" + s);
			}
			Thread.sleep(3000);
			return 1;
		}

	}

}
