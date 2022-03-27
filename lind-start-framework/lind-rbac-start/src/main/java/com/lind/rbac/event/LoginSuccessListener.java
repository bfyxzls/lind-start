package com.lind.rbac.event;

import com.lind.uaa.jwt.entity.ResourceUser;
import com.lind.uaa.jwt.entity.TokenResult;
import com.lind.uaa.jwt.event.LoginSuccessEvent;
import com.lind.uaa.jwt.service.JwtUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoginSuccessListener implements ApplicationListener<LoginSuccessEvent> {
    @Autowired
    JwtUserService jwtUserService;

    @Override
    public void onApplicationEvent(LoginSuccessEvent event) {
        TokenResult tokenResult = (TokenResult) event.getSource();
        System.out.println("login success:" + tokenResult.getSubject() + ",token:" + tokenResult.getToken());
        log.info("login success\nname:{}\ntoken:{}\n", tokenResult.getSubject(), tokenResult.getToken());
        ResourceUser userDetails = jwtUserService.getUserDetailsByToken(tokenResult.getToken(), ResourceUser.class);
        log.info("user:{}", userDetails);
    }
}