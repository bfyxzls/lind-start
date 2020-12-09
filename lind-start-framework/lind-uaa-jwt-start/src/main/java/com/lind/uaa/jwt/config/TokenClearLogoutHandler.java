package com.lind.uaa.jwt.config;

import com.lind.redis.service.RedisService;
import com.lind.uaa.jwt.event.LogoutSuccessEvent;
import com.lind.uaa.jwt.service.JwtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TokenClearLogoutHandler implements LogoutHandler {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    RedisService redisService;
    private JwtUserService jwtUserService;

    public TokenClearLogoutHandler(JwtUserService jwtUserService) {
        this.jwtUserService = jwtUserService;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        clearToken(authentication);
    }

    protected void clearToken(Authentication authentication) {
        if (authentication == null)
            return;
        UserDetails user = (UserDetails) authentication.getPrincipal();
        if (user != null && user.getUsername() != null) {
            //清除jwt token的策略.
            redisService.del(Constants.USER + authentication.getName());

            applicationEventPublisher.publishEvent(new LogoutSuccessEvent(user));
        }
    }

}