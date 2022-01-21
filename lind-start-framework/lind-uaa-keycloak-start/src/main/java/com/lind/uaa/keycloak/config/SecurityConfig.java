package com.lind.uaa.keycloak.config;

import com.lind.uaa.keycloak.config.permit.PermitAllSecurityConfig;
import com.lind.uaa.keycloak.config.permit.PermitAllUrl;
import com.lind.uaa.keycloak.scope.ScopeSetInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@KeycloakConfiguration
@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter implements WebMvcConfigurer {

  @Autowired(required = false)
  ScopeSetInterceptor scopeSetInterceptor;
  @Autowired(required = false)
  KeycloakSessionStateInterceptor keycloakSessionStateInterceptor;
  @Autowired
  UaaProperties uaaProperties;
  @Autowired
  PermitAllSecurityConfig permitAllSecurityConfig;
  @Autowired
  private KeycloakSpringBootProperties keycloakSpringBootProperties;
  @Autowired
  private Filter permitAuthenticationFilter;

  /**
   * Registers the MyKeycloakAuthenticationProvider with the authentication manager.
   */
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    MyKeycloakAuthenticationProvider authenticationProvider = new MyKeycloakAuthenticationProvider(keycloakSpringBootProperties);
    authenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
    auth.authenticationProvider(authenticationProvider);
  }

  @Bean
  public KeycloakConfigResolver keycloakConfigResolver() {
    return new KeycloakSpringBootConfigResolver();
  }

  /**
   * Defines the session authentication strategy.
   */
  @Bean
  @Override
  protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
    return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    String[] urls = uaaProperties.getPermitAll();
    super.configure(http);
    http.apply(permitAllSecurityConfig).and().authorizeRequests()
        .antMatchers(PermitAllUrl.permitAllUrl(urls)).permitAll()//白名单也会进行token校验
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic().and().csrf().disable().cors();

  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    if (scopeSetInterceptor != null) {
      registry.addInterceptor(scopeSetInterceptor).addPathPatterns("/**");
    }

    // 这些接口需要同步登录状态，而不需要在未登录时去登录页
    String[] urls = uaaProperties.getPermitAll();

    if (keycloakSessionStateInterceptor != null && urls != null && urls.length > 0) {
      registry.addInterceptor(keycloakSessionStateInterceptor).addPathPatterns(urls);
    }
  }

}