/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lind.common.util;

import org.apache.tomcat.util.http.SameSiteCookies;
import org.jboss.logging.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class CookieUtils {

  public static final String LEGACY_COOKIE = "_LEGACY";
  private static final Logger logger = Logger.getLogger(CookieUtils.class);

  /**
   * Set a response cookie.  This solely exists because JAX-RS 1.1 does not support setting HttpOnly cookies
   *
   * @param name
   * @param value
   * @param path
   * @param domain
   * @param maxAge
   * @param secure
   * @param httpOnly
   * @param sameSite
   */
  public static void addCookie(String name, String value, String path, String domain, int maxAge, boolean secure, boolean httpOnly, SameSiteCookies sameSite) {

    // when expiring a cookie we shouldn't set the sameSite attribute; if we set e.g. SameSite=None when expiring a cookie, the new cookie (with maxAge == 0)
    // might be rejected by the browser in some cases resulting in leaving the original cookie untouched; that can even prevent user from accessing their application
    if (maxAge == 0) {
      sameSite = null;
    }

    boolean secure_sameSite = sameSite == SameSiteCookies.NONE || secure; // when SameSite=None, Secure attribute must be set
    ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

    HttpServletResponse response = servletRequestAttributes.getResponse();

    ResponseCookie cookie = ResponseCookie.from(name, value)
        .httpOnly(httpOnly)
        .domain(domain)
        .path(path)
        .maxAge(Duration.ofSeconds(maxAge))
        .build();
    if (sameSite != null) {
      cookie = ResponseCookie.from(name, value)
          .httpOnly(httpOnly)
          .secure(secure_sameSite)
          .domain(domain)
          .path(path)
          .maxAge(Duration.ofSeconds(maxAge))
          .sameSite(sameSite.name())
          .build();
    }

    response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

  }

  /**
   * Set a response cookie avoiding SameSite parameter
   *
   * @param name
   * @param value
   * @param path
   * @param domain
   * @param maxAge
   * @param secure
   * @param httpOnly
   */
  public static void addCookie(String name, String value, String path, String domain, int maxAge, boolean secure, boolean httpOnly) {
    addCookie(name, value, path, domain, maxAge, secure, httpOnly, null);
  }

  public static void addCookie(String name, String value) {
    addCookie(name, value, null, null, -1, false, true, null);
  }

  public static void addCookie(String name, String value, boolean secure, SameSiteCookies sameSiteCookies) {
    addCookie(name, value, null, null, -1, secure, true, sameSiteCookies);
  }


  public static Cookie getCookie(String name) {
   ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

    HttpServletRequest request = servletRequestAttributes.getRequest();
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals(name)) {
          return cookie;
        }
      }
    }
    return null;
  }
}