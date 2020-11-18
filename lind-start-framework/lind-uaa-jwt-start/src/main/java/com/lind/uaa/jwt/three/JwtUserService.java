package com.lind.uaa.jwt.three;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lind.uaa.jwt.entity.SecurityUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JwtUserService implements UserDetailsService {

    public static final String LOGIN_TOKEN = "LOGIN_TOKEN:";
    @Autowired
    RedisTemplate redisTemplate;
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
        // 从数据库或者缓存中取出jwt token生成时用的salt
        if (redisTemplate.hasKey(LOGIN_TOKEN + username)) {
            com.lind.uaa.jwt.three.User salt = (com.lind.uaa.jwt.three.User) redisTemplate.opsForValue().get(LOGIN_TOKEN + username);
            return new SecurityUserDetails(salt);
        }
        UserDetails user = loadUserByUsername(username);
        //将salt放到password字段返回
        return User.builder()
                .username(user.getUsername())
                .password(jwtSecret)
                .authorities(user.getAuthorities())
                .build();
    }

    /**
     * 登陆成功后，存储这个用户信息.
     *
     * @param userDetails
     * @return
     */
    public String saveUserLoginInfo(UserDetails userDetails) {
        // 将salt保存到数据库或者缓存中
        redisTemplate.opsForValue().set(LOGIN_TOKEN + userDetails.getUsername(), userDetails, 3600, TimeUnit.SECONDS);
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        Date date = new Date(System.currentTimeMillis() + 3600 * 1000);  //设置1小时后过期
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .withClaim("auth", JSON.toJSONString(userDetails.getAuthorities()))
                .sign(algorithm);
    }

    /**
     * 2. 由getAuthenticationManager().authenticate调用这个方法.
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.lind.uaa.jwt.three.User user = new com.lind.uaa.jwt.three.User();
        user.setId("1");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setUsername("Jack");
        user.setResourceRoles(Arrays.asList(new Role("1", "管理员")));
        user.setResourcePermissions(Arrays.asList(new Permission("1", "chanpin", "/article/**", "USER", null)));
        return new SecurityUserDetails(user);
    }

    public void deleteUserLoginInfo(String username) {
        /**
         * @todo 清除数据库或者缓存中登录salt
         */
    }
}
