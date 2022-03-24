package com.lind.uaa.jwt.handler;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.lind.redis.service.RedisService;
import com.lind.uaa.jwt.config.Constants;
import com.lind.uaa.jwt.config.JwtAuthenticationToken;
import com.lind.uaa.jwt.config.JwtConfig;
import com.lind.uaa.jwt.service.JwtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 自动刷新token，通过token访问资源时，当达到某个时间，会返回新的token.
 */
public class JwtRefreshSuccessHandler implements AuthenticationSuccessHandler {
  @Autowired
  JwtConfig jwtConfig;
  @Autowired
  RedisService redisService;
  private JwtUserService jwtUserService;

  public JwtRefreshSuccessHandler(JwtUserService jwtUserService) {
    this.jwtUserService = jwtUserService;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                      Authentication authentication) throws IOException, ServletException {
    DecodedJWT jwt = ((JwtAuthenticationToken) authentication).getToken();
    boolean shouldRefresh = shouldTokenRefresh(jwt.getIssuedAt());
    if (shouldRefresh) {
      // 在线token续期
      redisService.expire(Constants.USER + authentication.getName(), jwtConfig.getExpiresAt() * 60);
      String newToken = jwtUserService.generateJwtJoinUser((UserDetails) authentication.getPrincipal());
      response.setHeader("Authorization", newToken);
    }
  }

  protected boolean shouldTokenRefresh(Date issueAt) {
    long tokenRefreshInterval = jwtConfig.getRefreshTokenExpiresAt();
    LocalDateTime issueTime = LocalDateTime.ofInstant(issueAt.toInstant(), ZoneId.systemDefault());
    return LocalDateTime.now().minusSeconds(tokenRefreshInterval).isAfter(issueTime);
  }

}