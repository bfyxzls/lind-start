package com.lind.uaa.jwt.handler;

import com.alibaba.fastjson.JSONObject;
import com.lind.common.rest.CommonResult;
import com.lind.redis.service.RedisService;
import com.lind.uaa.jwt.config.JwtConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.lind.uaa.jwt.config.Constants.LOGIN_FAIL_LIMIT;

@RequiredArgsConstructor
public class HttpStatusLoginFailureHandler implements AuthenticationFailureHandler {
  private final RedisService redisService;
  private final JwtConfig jwtConfig;

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                      AuthenticationException exception) throws IOException, ServletException {
    response.setCharacterEncoding("utf-8");
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType("application/json;charset=utf-8");

    String key = LOGIN_FAIL_LIMIT + request.getSession().getId();
    if (!redisService.hasKey(key)) {
      redisService.set(key, 0, 60L);
    }
    if (Integer.valueOf(redisService.get(key).toString()) >= jwtConfig.getFailLimit()) {
      response.getWriter().print(JSONObject.toJSONString(CommonResult.unauthorizedFailure("用户登录被限制,请1分钟后重试!")));
      return;
    }
    redisService.incr(key, 1L);
    response.getWriter().print(JSONObject.toJSONString(CommonResult.unauthorizedFailure("用户名密码错误!")));
  }

}