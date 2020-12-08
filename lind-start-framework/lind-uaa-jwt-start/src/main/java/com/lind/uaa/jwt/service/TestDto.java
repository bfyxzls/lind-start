package com.lind.uaa.jwt.service;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface TestDto {
    Collection<? extends GrantedAuthority> getAuthorities();

    String getPassword();

    String getUsername();

    boolean isAccountNonExpired();

    boolean isAccountNonLocked();

    boolean isCredentialsNonExpired();


}