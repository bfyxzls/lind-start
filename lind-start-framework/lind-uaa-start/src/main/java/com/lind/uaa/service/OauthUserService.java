package com.lind.uaa.service;

import com.lind.uaa.entity.User;

public interface OauthUserService {
    User getByUserName(String username);
}
