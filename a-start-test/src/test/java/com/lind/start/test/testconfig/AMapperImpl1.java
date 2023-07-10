package com.lind.start.test.testconfig;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AMapperImpl1 implements AMapper {

	private final AConfig config;

	public void print() {
		System.out.print("AMapperImpl1" + config.toString());
	}

}
