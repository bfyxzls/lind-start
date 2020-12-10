package com.lind.uaa.jwt.config;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lind.redis.service.RedisService;
import com.lind.uaa.jwt.entity.ResourcePermission;
import com.lind.uaa.jwt.entity.TokenResult;
import com.lind.uaa.jwt.event.LoginSuccessEvent;
import com.lind.uaa.jwt.service.JwtUserService;
import com.lind.uaa.jwt.service.ResourcePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 3. 登陆成功,返回jwt.
 */
public class JsonLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    JwtConfig jwtConfig;
    @Autowired
    RedisService redisService;
    @Autowired
    ResourcePermissionService resourcePermissionService;
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
        // 初始化权限表到redis
        if (!redisService.hasKey(Constants.PERMISSION_ALL)) {
            //授权服务在实现了ResourcePermissionService之后,将数据返回，并写到redis
            List<? extends ResourcePermission> all = resourcePermissionService.getAll();
            if (all != null) {
                redisService.set(Constants.PERMISSION_ALL, new ObjectMapper().writeValueAsString(all));
            }
        }
        // 登录成功后发布一个事件,外部可以订阅它.
        applicationEventPublisher.publishEvent(new LoginSuccessEvent(tokenResult));
    }


}