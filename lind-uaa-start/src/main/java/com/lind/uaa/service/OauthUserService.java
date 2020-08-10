package com.lind.uaa.service;

import com.lind.uaa.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface OauthUserService {
    User getByUserName(String username);
}
