package com.lind.gateway.handler;

import lombok.Data;

/**
 * @author lind
 * @date 2023/5/23 13:25
 * @since 1.0.0
 */
@Data
public class ResultData<T> {

	/** 结果状态 ,具体状态码参见ResultData.java */
	private int status;

	private String message;

	private T data;

	private boolean success;

	private long timestamp;

	public ResultData() {
		this.timestamp = System.currentTimeMillis();
	}

	public static  ResultData fail(int status,String message){
		ResultData resultData = new ResultData();
		resultData.setStatus(status);
		resultData.setMessage(message);
		resultData.setSuccess(false);
		return resultData;
	}

}
