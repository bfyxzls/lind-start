package com.lind.uaa.jwt.three.service;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.lind.uaa.jwt.entity.SecurityUserDetails;
import com.lind.uaa.jwt.service.ResourcePermissionService;
import com.lind.uaa.jwt.three.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
public class JwtUserService implements UserDetailsService {

    public static final String LOGIN_TOKEN = "LOGIN_TOKEN:";
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    ResourcePermissionService resourcePermissionService;

    private PasswordEncoder passwordEncoder;
    @Value("${jwt.secret:lind123456}")
    private String jwtSecret;

    public JwtUserService() {
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 带着token请求时，通过jwt里的username获取用户对象.
     *
     * @param username
     * @return
     */
    public UserDetails getUserLoginInfo(String username) {
        UserDetails user = loadUserByUsername(username);
        //将salt放到password字段返回/**/
        return User.builder()
                .username(user.getUsername())
                .password(jwtSecret)
                .authorities(user.getAuthorities())
                .build();
    }

    /**
     * 3.登陆成功后，存储这个用户信息.
     *
     * @param userDetails
     * @return
     */
    public String saveUserLoginInfo(UserDetails userDetails) {
        // 将salt保存到数据库或者缓存中
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        Date date = new Date(System.currentTimeMillis() + 3600 * 1000);  //设置1小时后过期
        JWTCreator.Builder jwt = JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .withClaim("auth", JSON.toJSONString(userDetails.getAuthorities()));
        return jwt.sign(algorithm);
    }

    /**
     * 2.由getAuthenticationManager().authenticate调用这个方法.
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.lind.uaa.jwt.three.entity.User user = new com.lind.uaa.jwt.three.entity.User();
        user.setId("1");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setUsername("Jack");
        user.setResourceRoles(Arrays.asList(new Role("1", "管理员")));
        user.setResourcePermissions(resourcePermissionService.getByUserId("1"));
        return new SecurityUserDetails(user);
    }

    public void deleteUserLoginInfo(String username) {
        /**
         * @todo 清除数据库或者缓存中登录salt
         */
        redisTemplate.delete(LOGIN_TOKEN + username);
    }
}
