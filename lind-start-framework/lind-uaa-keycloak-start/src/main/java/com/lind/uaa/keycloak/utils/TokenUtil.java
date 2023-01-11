package com.lind.uaa.keycloak.utils;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lind
 * @date 2022/9/20 13:58
 * @since 1.0.0
 */
public class TokenUtil {

	private static final Pattern authorizationPattern = Pattern.compile("^Bearer (?<token>[a-zA-Z0-9-:._~+/]+=*)$",
			Pattern.CASE_INSENSITIVE);

	/**
	 * 从token中获取bearer里的字符.
	 * @param authorization
	 * @return
	 */
	public static String resolveFromAuthorizationHeader(String authorization) {
		if (!StringUtils.startsWithIgnoreCase(authorization, "bearer")) {
			return null;
		}
		Matcher matcher = authorizationPattern.matcher(authorization);
		if (!matcher.matches()) {
			throw new IllegalArgumentException("Bearer token is malformed");
		}
		return matcher.group("token");
	}

}
