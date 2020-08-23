package com.lind.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lind.mybatis.audit.EnableMybatisAuditing;
import com.lind.mybatis.entity.TUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("integTest")
@SpringBootTest()
@RunWith(SpringRunner.class)
@Slf4j
@EnableMybatisAuditing
@MapperScan("com.pkulaw.mybatis.dao")
public class BasicTest {
    @Autowired
    UserService userService;

    @Test
    public void insert() {
        TUser user = new TUser();
        user.setUsername("lind");
        userService.insert(user);
        print("lind");
    }

    @Test
    public void update() throws InterruptedException {
        TUser user = new TUser();
        user.setUsername("lind");
        userService.insert(user);
        Thread.sleep(1000);
        user.setUsername("lind修改");
        userService.update(user);
        print("lind修改");
    }

    @Test
    public void pageList() {
        for (int i = 0; i < 10; i++) {
            TUser user = new TUser();
            user.setUsername("lind" + i);
            userService.insert(user);
        }
        QueryWrapper<TUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().isNotNull(TUser::getUsername);

        for (TUser item : userService.findAll(1, 10, wrapper).getRecords()) {
            log.info("user={}", item.toString());
        }
    }

    private void print(String name) {
        QueryWrapper<TUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(TUser::getUsername, name);
        for (TUser item : userService.findAll(wrapper)) {
            log.info("user={}", item.toString());
        }
    }
}