package com.lind.uaa.jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * Jwt保存到redis的实体配置器.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtUserAdapter {
    private String id;
    private String password;
    private String username;
    private String email;
    private List<DefaultAuthority> authorities;

    public JwtUserAdapter(ResourceUser resourceUser) {
        this.id = resourceUser.getId();
        this.email = resourceUser.getEmail();
        this.username = resourceUser.getUsername();
        this.password = null;
        List<DefaultAuthority> authorities = new ArrayList<>();
        for (GrantedAuthority authority : resourceUser.getAuthorities()) {
            authorities.add(new DefaultAuthority(authority.getAuthority(), authority.getAuthority()));
        }
        this.authorities = authorities;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DefaultAuthority implements GrantedAuthority {
        private String role;
        private String authority;

        @Override
        public String getAuthority() {
            return authority;
        }
    }

}
