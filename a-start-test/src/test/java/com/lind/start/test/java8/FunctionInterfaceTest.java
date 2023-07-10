package com.lind.start.test.java8;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 函数式接口也叫功能性接口，用来特指某类型接口，为Java 中Lambda的实现而定义.
 */
public class FunctionInterfaceTest {

	private static final Logger logger = LoggerFactory.getLogger(FunctionInterfaceTest.class);

	@Test
	public void createInterface() {
		Run run = new Run() {
			@Override
			public void print() {
				logger.info("print()");
			}
		};
		run.print();
	}

	@Test
	public void createInterface2() {
		FunctionRun functionRun = new FunctionRun() {
			@Override
			public void print() {
				logger.info("print()");
			}
		};
		functionRun.print();
	}

	@Test
	public void createInterface3Lambda() {
		FunctionRun functionRun = () -> logger.info("print()");
		functionRun.print();
	}

	@Test
	public void official() {
		// Function<T, R> -T作为输入，返回的R作为输出
		Function<String, String> function = (x) -> {
			System.out.print(x + ": ");
			return "Function";
		};
		System.out.println(function.apply("hello world"));

		// Predicate<T> -T作为输入，返回的boolean值作为输出
		Predicate<String> pre = (x) -> {
			System.out.print(x);
			return false;
		};
		System.out.println(": " + pre.test("hello World"));

		// Consumer<T> - T作为输入，执行某种动作但没有返回值
		Consumer<String> con = (x) -> {
			System.out.println(x);
		};
		con.accept("hello world");

		// Supplier<T> - 没有任何输入，返回T
		Supplier<String> supp = () -> {
			return "Supplier";
		};
		System.out.println(supp.get());
	}

	interface Run {

		void print();

		default void eat() {
			logger.info("eat()");
		}

		default void drink() {
			logger.info("drink");
		}

	}

	interface FunctionRun {

		void print();

	}

}
