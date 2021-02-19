package com.lind.uaa.keycloak.controller;

import com.alibaba.fastjson.JSON;
import com.lind.uaa.keycloak.config.UaaProperties;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("token")
public class TokenController {

    @Autowired
    KeycloakSpringBootProperties keycloakSpringBootProperties;
    @Autowired
    UaaProperties uaaProperties;


    private void writeToken(HttpServletResponse response, MultiValueMap<String, String> map, HttpHeaders headers) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        //获取OutputStream输出流
        OutputStream outputStream = response.getOutputStream();
        //将字符转换成字节数组，指定以UTF-8编码进行转换
        byte[] dataByteArr = JSON.toJSONString(
                restTemplate.postForEntity(
                        getTokenUri(),
                        request,
                        Map.class)
                        .getBody()).getBytes("UTF-8");
        //使用OutputStream流向客户端输出字节数组
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        outputStream.write(dataByteArr);
    }

    private void writeTokenRedirect(HttpServletResponse response, MultiValueMap<String, String> map, HttpHeaders headers) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        //获取OutputStream输出流
        OutputStream outputStream = response.getOutputStream();
        Map maps = restTemplate.postForEntity(
                getTokenUri(),
                request,
                Map.class).getBody();
        response.sendRedirect(uaaProperties.getRedirectUri()
                + "?access_token=" + maps.get("access_token")
                + "&refresh_token=" + maps.get("refresh_token"));
    }

    /**
     * 得到获取code的URI.
     *
     * @return
     */
    private String getCodeUri() {
        return String.format("%s/realms/%s/protocol/openid-connect/auth?scope=openid&response_type=code&client_id=%s&redirect_uri=%s",
                keycloakSpringBootProperties.getAuthServerUrl(),
                keycloakSpringBootProperties.getRealm(),
                keycloakSpringBootProperties.getResource(),
                uaaProperties.getCallbackUri());
    }

    /**
     * 得到获取tokenURI.
     *
     * @return
     */
    private String getTokenUri() {
        return String.format("%s/realms/%s/protocol/openid-connect/token",
                keycloakSpringBootProperties.getAuthServerUrl(),
                keycloakSpringBootProperties.getRealm());
    }

    /**
     * 授权码认证-统一登陆的回调地址通过它获取token.
     *
     * @param code
     * @param response
     * @throws IOException
     */
    @ApiOperation("授权码认证")
    @GetMapping(path = "/authorizationCodeLogin")
    public void authorizationCodeLogin(@RequestParam(required = false) String code, HttpServletResponse response) throws IOException {
        if (StringUtils.isBlank(code)) {
            // step1
            response.sendRedirect(getCodeUri());
        } else {
            // step2
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("grant_type", "authorization_code");
            map.add("code", code);
            map.add("client_id", keycloakSpringBootProperties.getResource());
            map.add("client_secret", keycloakSpringBootProperties.getClientKeyPassword());
            map.add("redirect_uri", uaaProperties.getCallbackUri());
            writeToken(response, map, headers);
        }

    }

    @ApiOperation("授权码认证")
    @GetMapping(path = "/authorizationCodeRedirect")
    public void authorizationCodeRedirect(@RequestParam(required = false) String code, HttpServletResponse response) throws IOException {
        if (StringUtils.isBlank(code)) {
            // step1
            response.sendRedirect(getCodeUri());
        } else {
            // step2
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("grant_type", "authorization_code");
            map.add("code", code);
            map.add("client_id", keycloakSpringBootProperties.getResource());
            map.add("client_secret", keycloakSpringBootProperties.getClientKeyPassword());
            map.add("redirect_uri", uaaProperties.getCallbackUri());
            writeTokenRedirect(response, map, headers);
        }

    }

    /**
     * 客户端认证.
     *
     * @param scope
     * @param response
     * @throws IOException
     */
    @ApiOperation("客户端认证")
    @GetMapping(path = "/clientCredentialsLogin")
    public void clientCredentialsLogin(@RequestParam(required = false) String scope, HttpServletResponse response) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");
        map.add("client_id", keycloakSpringBootProperties.getResource());
        if (StringUtils.isNoneBlank(scope)) {
            map.add("scope", scope);
        }
        map.add("client_secret", keycloakSpringBootProperties.getClientKeyPassword());
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        writeToken(response, map, headers);
    }

    /**
     * 密码认证.
     *
     * @param username
     * @param password
     * @param response
     * @throws IOException
     */
    @ApiOperation("密码认证")
    @PostMapping(path = "/passwordLogin")
    public void passwordLogin(String username, String password, HttpServletResponse response) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        map.add("client_id", keycloakSpringBootProperties.getResource());
        map.add("client_secret", keycloakSpringBootProperties.getClientKeyPassword());
        map.add("username", username);
        map.add("password", password);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        writeToken(response, map, headers);
    }
}
