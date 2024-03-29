package com.lind.start.test.controller;

import com.lind.verification.code.CodeConstants;
import com.lind.verification.code.ValidateCodeException;
import com.lind.verification.code.ValidateCodeProcessor;
import com.lind.verification.code.ValidateCodeProcessorHolder;
import com.lind.common.rest.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 生成校验码的请求处理器
 *
 * @author paascloud.net @gmail.com
 */
@Slf4j
@RestController
public class ValidateCodeController {

	@Resource
	private ValidateCodeProcessorHolder validateCodeProcessorHolder;

	/**
	 * 创建验证码，根据验证码类型不同，调用不同的 {@link ValidateCodeProcessor}接口实现
	 * /auth/code/imageValidateCodeProcessor
	 * @param request the request
	 * @param response the response
	 * @param type the type
	 * @throws Exception the exception
	 */
	@GetMapping(CodeConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
	public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
			throws Exception {
		validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
	}

	/**
	 * Check code object.
	 * @param request the request
	 * @param response the response
	 * @param type the type
	 * @return the object
	 */
	@GetMapping(CodeConstants.DEFAULT_CHECK_CODE_URL_PREFIX + "/{type}")
	public Object checkCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) {
		CommonResult result = CommonResult.ok("校验成功");
		try {
			validateCodeProcessorHolder.findValidateCodeProcessor(type).check(new ServletWebRequest(request, response));
		}
		catch (ValidateCodeException e) {
			result = CommonResult.failure(500, e.getMessage());
		}
		catch (Exception e) {
			log.error("getAccessToken={}", e.getMessage(), e);
			result = CommonResult.failure(500, "验证码错误");
		}
		return result;
	}

}
