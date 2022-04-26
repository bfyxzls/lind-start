package com.lind.common.proxy.staticproxy;

import com.lind.common.proxy.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImpl implements UserService {
    public void select() {
        System.out.println("查询 selectById");
    }
    public void update(User user) {
        System.out.println("更新 update");
        log.info("user:{}",user);
    }
}