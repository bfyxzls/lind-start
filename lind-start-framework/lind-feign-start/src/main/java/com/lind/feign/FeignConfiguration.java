package com.lind.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @description: Feign内部调用时带上请求头信息
 **/
@Configuration
public class FeignConfiguration implements RequestInterceptor {
  @Override
  public void apply(RequestTemplate template) {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    Enumeration<String> headerNames = request.getHeaderNames();
    if (headerNames != null) {
      while (headerNames.hasMoreElements()) {
        String name = headerNames.nextElement();
        String values = request.getHeader(name);
        template.header(name, values);
      }
    }
    Enumeration<String> bodyNames = request.getParameterNames();
    StringBuffer body = new StringBuffer();
    if (bodyNames != null) {
      while (bodyNames.hasMoreElements()) {
        String name = bodyNames.nextElement();
        String values = request.getParameter(name);
        body.append(name).append("=").append(values).append("&");
      }
    }
    if (body.length() != 0) {
      body.deleteCharAt(body.length() - 1);
      template.body(body.toString());
    }
  }

  /**
   * 转向器.
   */
  public class HttpMessageConvertersConfiguration {
    @Bean
    Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> converters) {
      return new SpringFormEncoder(new SpringEncoder(converters));
    }
  }
}