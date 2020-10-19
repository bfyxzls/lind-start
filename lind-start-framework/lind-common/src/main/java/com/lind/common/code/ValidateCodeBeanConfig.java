/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：ValidateCodeBeanConfig.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.lind.common.code;

import com.google.code.kaptcha.Producer;
import com.lind.common.code.email.DefaultEmailCodeSender;
import com.lind.common.code.email.EmailCodeSender;
import com.lind.common.code.image.ImageCodeGenerator;
import com.lind.common.code.properties.ImageCodeProperties;
import com.lind.common.code.sms.DefaultSmsCodeSender;
import com.lind.common.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码相关的扩展点配置。配置在这里的bean，业务系统都可以通过声明同类型或同名的bean来覆盖安全
 * 模块默认的配置。
 *
 * @author paascloud.net @gmail.com
 */
@Configuration
public class ValidateCodeBeanConfig {

	@Autowired
	private ImageCodeProperties imageCodeProperties;
	@Autowired
	private Producer captchaProducer;

	/**
	 * 图片验证码图片生成器
	 *
	 * @return validate code generator
	 */
	@Bean
	@ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
	public ValidateCodeGenerator imageValidateCodeGenerator() {
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
		codeGenerator.setSecurityProperties(imageCodeProperties);
		codeGenerator.setCaptchaProducer(captchaProducer);
		return codeGenerator;
	}

	/**
	 * 短信验证码发送器
	 *
	 * @return sms code sender
	 */
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}

	/**
	 * 邮箱验证码发送器
	 *
	 * @return sms code sender
	 */
	@Bean
	@ConditionalOnMissingBean(EmailCodeSender.class)
	public EmailCodeSender emailCodeSender() {
		return new DefaultEmailCodeSender();
	}

}
