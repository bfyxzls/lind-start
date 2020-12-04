package com.lind.uaa.jwt.service;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lind.uaa.jwt.config.JwtConfig;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUserService {
    public static final String CLAIM_USER = "user";
    @Autowired
    ResourcePermissionService resourcePermissionService;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    JwtConfig jwtConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 带着token请求时，通过jwt里的username获取用户对象.
     *
     * @param username
     * @return
     */
    public UserDetails getUserLoginInfo(String username) {
        UserDetails user = userDetailsService.loadUserByUsername(username);
        return User.builder()
                .username(user.getUsername())
                .password(jwtConfig.getSecret())
                .authorities(user.getAuthorities())
                .build();
    }

    /**
     * 3.登陆成功后，存储这个用户信息.
     *
     * @param userDetails
     * @return
     */
    public String generateJwtJoinUser(UserDetails userDetails) {
        Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());
        Date date = DateUtils.addMinutes(new Date(), jwtConfig.getExpiresAt());
        JWTCreator.Builder jwt = JWT.create()
                .withSubject(userDetails.getUsername())
                .withClaim(CLAIM_USER, JSON.toJSONString(userDetails))
                .withExpiresAt(date)
                .withIssuedAt(new Date());
        return jwt.sign(algorithm);
    }

    /**
     * 通过token解析用户信息.
     *
     * @param token
     * @return
     */
    public <T extends UserDetails> T getUserDetailsByToken(String token, Class<T> clazz) {
        DecodedJWT jwt = JWT.decode(token);
        Claim claim = jwt.getClaim(CLAIM_USER);
        return JSON.parseObject(claim.asString(), clazz);
    }
}
