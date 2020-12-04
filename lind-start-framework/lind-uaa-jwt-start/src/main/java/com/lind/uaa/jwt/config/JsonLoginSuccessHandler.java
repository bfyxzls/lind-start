package com.lind.uaa.jwt.config;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lind.uaa.jwt.entity.TokenResult;
import com.lind.uaa.jwt.event.LoginSuccessEvent;
import com.lind.uaa.jwt.service.JwtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 3. 登陆成功,返回jwt.
 */
public class JsonLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;
    private JwtUserService jwtUserService;

    public JsonLoginSuccessHandler(JwtUserService jwtUserService) {
        this.jwtUserService = jwtUserService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String token = jwtUserService.generateJwtJoinUser((UserDetails) authentication.getPrincipal());
        response.setHeader("Authorization", token);
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        DecodedJWT jwt = JWT.decode(token);
        TokenResult tokenResult = new TokenResult();
        tokenResult.setExpiresAt(jwt.getExpiresAt());
        tokenResult.setSubject(jwt.getSubject());
        tokenResult.setToken(token);
        response.getWriter().write(JSON.toJSONString(tokenResult));
        // 登录成功后发布一个事件,外部可以订阅它.
        applicationEventPublisher.publishEvent(new LoginSuccessEvent(tokenResult));
    }


}