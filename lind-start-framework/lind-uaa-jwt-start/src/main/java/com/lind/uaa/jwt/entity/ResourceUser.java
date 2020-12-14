package com.lind.uaa.jwt.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户.
 */
@JsonDeserialize(using = ResourceUserDeserializer.class)
@JsonSerialize(using = ResourceUserSerializer.class)
public interface ResourceUser extends UserDetails {
    String getEmail();

    String getId();

    /**
     * 当前用户的权限.
     *
     * @return
     */
    List<? extends ResourcePermission> getResourcePermissions();

    void setResourcePermissions(List<? extends ResourcePermission> resourcePermission);

    /**
     * 当前用户的角色.
     *
     * @return
     */
    List<? extends ResourceRole> getResourceRoles();

    void setResourceRoles(List<? extends ResourceRole> resourceRoles);

    default Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorityList = new ArrayList<>();
        List<? extends ResourcePermission> resourcePermissions = getResourcePermissions();
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

    default boolean isAccountNonExpired() {
        return true;
    }

    default boolean isAccountNonLocked() {
        return true;
    }

    default boolean isCredentialsNonExpired() {
        return true;
    }

    default boolean isEnabled() {
        return true;
    }
}
