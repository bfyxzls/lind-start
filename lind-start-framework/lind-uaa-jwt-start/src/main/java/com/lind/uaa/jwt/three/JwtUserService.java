package com.lind.uaa.jwt.three;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lind.uaa.jwt.entity.SecurityUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Date;

public class JwtUserService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;
    @Value("${jwt.secret:lind123456}")
    private String jwtSecret;

    public JwtUserService() {
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();  //默认使用 bcrypt， strength=10
    }

    public UserDetails getUserLoginInfo(String username) {
        UserDetails user = loadUserByUsername(username);
        //将salt放到password字段返回
        return User.builder().username(user.getUsername()).password(jwtSecret).authorities(user.getAuthorities()).build();
    }

    public String saveUserLoginInfo(UserDetails user) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        Date date = new Date(System.currentTimeMillis() + 3600 * 1000);  //设置1小时后过期
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.lind.uaa.jwt.three.User user = new com.lind.uaa.jwt.three.User();
        user.setId("1");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setUsername("Jack");
        user.setResourcePermissions(Arrays.asList(new Permission("1", "chanpin", "/article/**", "USER", null)));
        return new SecurityUserDetails(user);
    }

    public void deleteUserLoginInfo(String username) {
        /**
         * @todo 清除数据库或者缓存中登录salt
         */
    }
}
