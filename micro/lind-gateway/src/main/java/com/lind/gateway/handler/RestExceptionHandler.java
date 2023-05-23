package com.lind.gateway.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lind
 * @date 2023/5/23 13:21
 * @since 1.0.0
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

	/**
	 * 默认全局异常处理。
	 * @return ResultData
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResultData<String> exception(Exception e) {
		log.error("全局异常信息 ex={}", e.getMessage(), e);
		return ResultData.fail(500, e.getMessage());
	}

}
