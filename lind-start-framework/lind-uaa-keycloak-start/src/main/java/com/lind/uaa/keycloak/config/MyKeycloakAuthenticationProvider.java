package com.lind.uaa.keycloak.config;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.adapters.springsecurity.account.KeycloakRole;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.lind.uaa.keycloak.config.Constant.VERIFY_SESSION;

/**
 * 重写token与认证的适配.
 * 本方法只做token有效性校验，没有做合法性校验(即是否由私钥颁发的token)
 * 20210716添加token在线校验功能
 */
@RequiredArgsConstructor
public class MyKeycloakAuthenticationProvider implements AuthenticationProvider {
  private final KeycloakSpringBootProperties keycloakSpringBootProperties;
  private GrantedAuthoritiesMapper grantedAuthoritiesMapper;
  private HttpServletRequest request;
  private HttpServletResponse response;
  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  public void setGrantedAuthoritiesMapper(GrantedAuthoritiesMapper grantedAuthoritiesMapper) {
    this.grantedAuthoritiesMapper = grantedAuthoritiesMapper;
  }

  @SneakyThrows
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    //在线token校验
    KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) authentication;
    String tokenString = token.getAccount().getKeycloakSecurityContext().getTokenString();
    Map<String, Object> params = new HashMap<>();
    params.put("client_id", keycloakSpringBootProperties.getResource());
    params.put("client_secret", keycloakSpringBootProperties.getClientKeyPassword());
    params.put("token", tokenString);
    String verifyResult = HttpUtil.post(keycloakSpringBootProperties.getAuthServerUrl() + VERIFY_SESSION, params);
    JSONObject jsonObj = JSON.parseObject(verifyResult);
    System.out.println("access_token:" + tokenString);
    // 验证在线token，如果已退出，直接401，由业务方自己跳转
    if (!jsonObj.getBoolean("active")) {
      throw new AuthenticationCredentialsNotFoundException("access_token is invalid");
    }

    List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
    // Extract roles from the `realm_access.roles`
    Set<String> roles = token.getAccount().getRoles();
    if (roles != null) {
      grantedAuthorities.addAll(
          roles.stream().map(
              role -> new KeycloakRole(role)
          ).collect(Collectors.toList())
      );
    }

    // Extract roles from the `realm_access.scopes`
    if (token.getPrincipal() instanceof KeycloakPrincipal) {
      KeycloakPrincipal keycloakPrincipal = (KeycloakPrincipal) token.getPrincipal();
      String scope = keycloakPrincipal.getKeycloakSecurityContext().getToken().getScope();
      Set<String> scopes = new HashSet<>();
      for (String item : StringUtils.split(scope, " ")) {
        scopes.add(item);
      }
      grantedAuthorities.addAll(
          scopes.stream().map(
              role -> new KeycloakRole(role)
          ).collect(Collectors.toList())
      );
    }


    // Extract roles from the `resource_access.<client-id>.roles`
    Map<String, AccessToken.Access> resourceAccess = token.getAccount().getKeycloakSecurityContext().getToken().getResourceAccess();
    if (resourceAccess != null) {
      grantedAuthorities.addAll(
          resourceAccess.entrySet().stream().flatMap(
              access -> access.getValue().getRoles().stream().map(
                  role -> access.getKey() + "/" + role
              )
          ).map(
              role -> new KeycloakRole(role)
          ).collect(Collectors.toList())
      );
    }

    return new KeycloakAuthenticationToken(token.getAccount(), token.isInteractive(), mapAuthorities(grantedAuthorities));
  }

  private Collection<? extends GrantedAuthority> mapAuthorities(
      Collection<? extends GrantedAuthority> authorities) {
    return grantedAuthoritiesMapper != null
        ? grantedAuthoritiesMapper.mapAuthorities(authorities)
        : authorities;
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return KeycloakAuthenticationToken.class.isAssignableFrom(aClass);
  }
}