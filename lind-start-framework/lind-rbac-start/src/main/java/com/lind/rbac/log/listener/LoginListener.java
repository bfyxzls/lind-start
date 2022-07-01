package com.lind.rbac.log.listener;

import com.lind.rbac.log.entity.SysLogLoginEntity;
import com.lind.rbac.log.service.SysLogLoginService;
import com.lind.uaa.jwt.event.LoginSuccessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author lind
 * @date 2022/7/1 14:49
 * @description
 */
@Component
public class LoginListener {
    @Autowired
    SysLogLoginService sysLogLoginService;

    /**
     * 登录成功事件.
     *
     * @param loginSuccessEvent
     */
    @EventListener(LoginSuccessEvent.class)
    public void setLoginSuccessEvent(LoginSuccessEvent loginSuccessEvent) {
        SysLogLoginEntity sysLogLoginEntity = new SysLogLoginEntity();
        sysLogLoginEntity.setCreatorName(loginSuccessEvent.getTokenResult().getSubject());
        sysLogLoginEntity.setIp(loginSuccessEvent.getIpAddress());
        sysLogLoginService.insert(sysLogLoginEntity);
    }
}
