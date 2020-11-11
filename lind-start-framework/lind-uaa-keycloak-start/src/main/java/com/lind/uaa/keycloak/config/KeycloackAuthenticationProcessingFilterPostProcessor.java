package com.lind.uaa.keycloak.config;

import org.keycloak.adapters.AdapterTokenStore;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.OAuthRequestAuthenticator;
import org.keycloak.adapters.RequestAuthenticator;
import org.keycloak.adapters.spi.HttpFacade;
import org.keycloak.adapters.springsecurity.authentication.SpringSecurityRequestAuthenticator;
import org.keycloak.adapters.springsecurity.authentication.SpringSecurityRequestAuthenticatorFactory;
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class KeycloackAuthenticationProcessingFilterPostProcessor implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(KeycloackAuthenticationProcessingFilterPostProcessor.class);

    /**
     * 回调地址，需要在授权服务器配置.
     */
    @Value("${uaa.callbackUri:http://localhost:9090/token/authorizationCodeLogin}")
    private String callbackUri;

    private void process(KeycloakAuthenticationProcessingFilter filter) {
        filter.setRequestAuthenticatorFactory(new SpringSecurityRequestAuthenticatorFactory() {
            @Override
            public RequestAuthenticator createRequestAuthenticator(HttpFacade facade, HttpServletRequest request, KeycloakDeployment deployment, AdapterTokenStore tokenStore, int sslRedirectPort) {
                return new SpringSecurityRequestAuthenticator(facade, request, deployment, tokenStore, sslRedirectPort) {

                    @Override
                    protected OAuthRequestAuthenticator createOAuthAuthenticator() {
                        return new OAuthRequestAuthenticator(this, facade, deployment, sslRedirectPort, tokenStore) {

                            @Override
                            protected String getRequestUrl() {
                                return callbackUri;
                            }
                        };
                    }

                };
            }
        });
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof KeycloakAuthenticationProcessingFilter) {
            logger.info("Injecting KeycloakAuthenticationProcessingFilter handler...");
            process(((KeycloakAuthenticationProcessingFilter) bean));
        }
        return bean;
    }
}