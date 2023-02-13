package com.lind.uaa.jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lind.redis.service.RedisService;
import com.lind.uaa.jwt.config.JwtConfig;
import com.lind.uaa.jwt.entity.ResourceRole;
import com.lind.uaa.jwt.entity.ResourceUser;
import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * jwt操作类.
 */
@Component
public class JwtUserService {

    public static final String CLAIM_USER = "user";
    public static final String CLAIM = "role";

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    RedisService redisService;
    @Autowired
    HttpServletRequest request;

    /**
     * 3.登陆成功后，存储这个用户信息.
     *
     * @param userDetails
     * @return
     */
    @SneakyThrows
    public String generateJwtJoinUser(ResourceUser userDetails) {
        Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());
        Date date = DateUtils.addMinutes(new Date(), Integer.parseInt(jwtConfig.getExpiresAt().toString()));
        String uri = request.getScheme() + "://" +
                request.getServerName() + ":" +
                request.getServerPort();
        JWTCreator.Builder jwt = JWT.create()
                .withIssuedAt(new Date())
                .withIssuer(uri)
                .withJWTId(UUID.randomUUID().toString())
                .withKeyId(userDetails.getId())
                .withSubject(userDetails.getUsername())
                .withClaim(CLAIM, new ObjectMapper().writeValueAsString(userDetails.getAuthorities()))
                .withExpiresAt(date).withIssuedAt(new Date());
        return jwt.sign(algorithm);
    }

    /**
     * 在原来用户jwt主体基础上，生成新的token.
     *
     * @param username
     * @param claim
     * @return
     */
    @SneakyThrows
    public String generateJwtJoinToken(String username, Claim claim) {
        Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());
        Date date = DateUtils.addMinutes(new Date(), Integer.parseInt(jwtConfig.getExpiresAt().toString()));
        JWTCreator.Builder jwt = JWT.create().withSubject(username).withClaim(CLAIM_USER, claim.asString())
                .withExpiresAt(date).withIssuedAt(new Date());
        return jwt.sign(algorithm);
    }

    /**
     * 通过token解析用户信息.
     *
     * @param token
     * @return
     */
    @SneakyThrows
    public ResourceUser getUserDetailsByToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        ResourceUser resourceUser = new ResourceUser() {
            @Override
            public String getEmail() {
                return null;
            }

            @Override
            public String getId() {
                return jwt.getKeyId();
            }

            @Override
            public Integer isAdmin() {
                return null;
            }

            @Override
            public List<? extends ResourceRole> getResourceRoles() {
                return null;
            }

            @Override
            public void setResourceRoles(List<? extends ResourceRole> resourceRoles) {

            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return jwt.getSubject();
            }
        };
        return resourceUser ;
    }

}
