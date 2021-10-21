package com.lind.uaa.keycloak.config;

import javax.servlet.http.HttpServletRequest;

public interface Constant {
  /**
   * 表示登录的cookie
   */
  String COOKIE_IS_LOGIN = "isLogin";
  String TOKEN_APPLET = "token/applet";
  String TOKEN_PASSWORD_LOGIN = "token/passwordLogin";
  String TOKEN_CLIENT_CREDENTIALS_LOGIN = "token/clientCredentialsLogin";
  String TOKEN_AUTHORIZATION_CODE_REDIRECT = "token/authorizationCodeRedirect";
  String TOKEN_AUTHORIZATION_CODE_RESPONSE = "token/authorizationCodeResponse";
  String VERIFY_SESSION = "/realms/fabao/protocol/openid-connect/token/introspect";

  /**
   * 返回当前Url，除去参数的部分.
   *
   * @param request
   * @return
   */
  static String getCurrentHost(HttpServletRequest request) {
    StringBuffer url = request.getRequestURL();
    String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getSession().getServletContext().getContextPath()).append("/").toString();
    return tempContextUrl;
  }

  /**
   * 得到当前域的地址.
   *
   * @param request
   * @param path
   * @return
   */
  static String getCurrentHost(HttpServletRequest request, String path) {
    return getCurrentHost(request) + path;
  }
}
