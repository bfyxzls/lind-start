package com.lind.start.test.controller;

import com.lind.lock.template.UserIdAuditorAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class CurrentUserAware implements UserIdAuditorAware {
    @Autowired
    ApplicationContext applicationContext;

    @Override
    public Optional<String> getCurrentAuditor() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        if (request != null) {
            return Optional.of(request.getSession().getAttribute("id").toString());
        } else {
            return Optional.empty();
        }
    }
}
