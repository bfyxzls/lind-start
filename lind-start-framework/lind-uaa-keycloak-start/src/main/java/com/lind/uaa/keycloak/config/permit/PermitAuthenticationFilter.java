package com.lind.uaa.keycloak.config.permit;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lind.uaa.keycloak.config.UaaProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.lind.uaa.keycloak.config.Constant.VERIFY_TOKEN;


/**
 * 白名单过滤器，完成将header中的Authorization删除.
 */
@Component("permitAuthenticationFilter")
@RequiredArgsConstructor
@Slf4j
public class PermitAuthenticationFilter extends OncePerRequestFilter {
  private final UaaProperties uaaProperties;
  private final KeycloakSpringBootProperties keycloakSpringBootProperties;

  /**
   * token是否在线.
   * 如果是白名单，token没有在线，就直接跳过，如果在线，就带着token
   */
  private boolean isTokenOnline(String value) {
    if (value != null) {
      String tokenString = value.split("Bearer ")[1];
      Map<String, Object> params = new HashMap<>();
      params.put("client_id", keycloakSpringBootProperties.getResource());
      params.put("client_secret", keycloakSpringBootProperties.getClientKeyPassword());
      params.put("token", tokenString);
      String verifyResult = HttpUtil.post(keycloakSpringBootProperties.getAuthServerUrl()
          + String.format(VERIFY_TOKEN, keycloakSpringBootProperties.getRealm()), params);
      logger.info("token.verify:" + verifyResult);
      JSONObject jsonObj = JSON.parseObject(verifyResult);
      return jsonObj.getBoolean("active");
    }
    return false;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    Set<String> set = new HashSet<>();
    String[] urls = uaaProperties.getPermitAll();
    if (urls != null && urls.length > 0) {
      Collections.addAll(set, urls);
    }
    if (set.contains(request.getRequestURI()) || set.contains(request.getServletPath())) {

      request = new HttpServletRequestWrapper(request) {
        private Set<String> headerNameSet;

        @Override
        public Enumeration<String> getHeaderNames() {
          if (headerNameSet == null) {
            headerNameSet = new HashSet<>();
            Enumeration<String> wrappedHeaderNames = super.getHeaderNames();
            while (wrappedHeaderNames.hasMoreElements()) {
              String headerName = wrappedHeaderNames.nextElement();
              if (!"Authorization".equalsIgnoreCase(headerName)) {
                headerNameSet.add(headerName);
              }
            }
          }
          return Collections.enumeration(headerNameSet);
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
          if ("Authorization".equalsIgnoreCase(name)) {
            if (!isTokenOnline(super.getHeader(name)))
              return Collections.<String>emptyEnumeration();
          }
          return super.getHeaders(name);
        }

        @Override
        public String getHeader(String name) {
          if ("Authorization".equalsIgnoreCase(name)) {
            if (!isTokenOnline(super.getHeader(name))) {
              return null;
            }
          }
          return super.getHeader(name);
        }
      };

    }
    filterChain.doFilter(request, response);

  }
}
