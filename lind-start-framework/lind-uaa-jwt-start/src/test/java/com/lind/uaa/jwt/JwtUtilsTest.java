package com.lind.uaa.jwt;

import com.lind.uaa.jwt.utils.JwtUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Slf4j
public class JwtUtilsTest {
    @SneakyThrows
    @Test
    public void create() {
        String token = JwtUtils.generTokenByRS256(new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return "abc123";
            }

            @Override
            public String getUsername() {
                return "lind";
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        });
        log.info("token={}", token);
        log.info("verifty:{}", JwtUtils.verifierToken(token).getSignature());
    }
}
