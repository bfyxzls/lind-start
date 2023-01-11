package com.lind.uaa.jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lind.redis.service.RedisService;
import com.lind.uaa.jwt.config.JwtConfig;
import com.lind.uaa.jwt.entity.ResourceUser;
import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * jwt操作类.
 */
@Component
public class JwtUserService {

	public static final String CLAIM_USER = "user";

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	JwtConfig jwtConfig;

	@Autowired
	RedisService redisService;

	/**
	 * 3.登陆成功后，存储这个用户信息.
	 * @param userDetails
	 * @return
	 */
	@SneakyThrows
	public String generateJwtJoinUser(UserDetails userDetails) {
		Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());
		Date date = DateUtils.addMinutes(new Date(), Integer.parseInt(jwtConfig.getExpiresAt().toString()));
		String json = new ObjectMapper().writeValueAsString(userDetails);
		JWTCreator.Builder jwt = JWT.create().withSubject(userDetails.getUsername()).withClaim(CLAIM_USER, json)
				.withExpiresAt(date).withIssuedAt(new Date());
		return jwt.sign(algorithm);
	}

	/**
	 * 在原来用户jwt主体基础上，生成新的token.
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
	 * @param token
	 * @return
	 */
	@SneakyThrows
	public <T extends ResourceUser> T getUserDetailsByToken(String token, Class<T> clazz) {
		DecodedJWT jwt = JWT.decode(token);
		Claim claim = jwt.getClaim(CLAIM_USER);
		return new ObjectMapper().readValue(claim.asString(), clazz);
	}

}
