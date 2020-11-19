package com.lind.uaa.jwt.entity;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户认证.
 */
@Slf4j
public class SecurityUserDetails implements UserDetails {


    private ResourceUser user;

    public SecurityUserDetails(ResourceUser user) {
        if (user == null) {
            throw new IllegalArgumentException("请实现ResourceUser接口");
        }
        this.user = user;
    }

    /**
     * 获取用户对象.
     *
     * @return
     */
    public ResourceUser getUser() {
        return user;
    }

    /**
     * 添加用户拥有的权限和角色
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorityList = new ArrayList<>();
        List<ResourcePermission> resourcePermissions = user.getResourcePermissions();
        // 添加请求权限
        if (resourcePermissions != null && resourcePermissions.size() > 0) {
            for (ResourcePermission resourcePermission : resourcePermissions) {
                if (StringUtils.isNotBlank(resourcePermission.getTitle())
                        && StringUtils.isNotBlank(resourcePermission.getPath())) {
                    authorityList.add(new SimpleGrantedAuthority(resourcePermission.getTitle()));
                }
            }
        }
        return authorityList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * 账户是否过期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    /**
     * 是否禁用
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    /**
     * 密码是否过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否启用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {

        return true;
    }
}