package com.lind.common.code.sms;

import com.lind.common.code.ValidateCode;
import com.lind.common.code.ValidateCodeGenerator;
import com.lind.common.code.properties.SmsCodeProperties;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码生成器
 *
 * @author paascloud.net @gmail.com
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

	@Autowired
	private SmsCodeProperties smsCodeProperties;

	/**
	 * Generate validate code.
	 *
	 * @param request the request
	 *
	 * @return the validate code
	 */
	@Override
	public ValidateCode generate(ServletWebRequest request) {
		String code = RandomStringUtils.randomNumeric(smsCodeProperties.getLength());
		return new ValidateCode(code, smsCodeProperties.getExpireIn());
	}
}
