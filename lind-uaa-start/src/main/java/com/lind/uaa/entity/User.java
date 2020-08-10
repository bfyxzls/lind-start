package com.lind.uaa.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lind.mybatis.base.BaseEntity;
import lombok.Data;
import lombok.ToString;
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
@Data
@ToString
@TableName("t_user")
public class User extends BaseEntity implements UserDetails {

    /**
     * 用户名.
     */
    private String username;

    /**
     * 密码.
     */
    private String password;

    /**
     * 用户头像.
     */
    private String avatar = "";

    /**
     * 状态 默认0正常 -1拉黑.
     */
    private Integer status = 0;

    /**
     * 所属部门id.
     */
    private String departmentId;

    /**
     * 用户拥有角色.
     */
    private List<Role> roles;

    /**
     * 用户拥有的权限.
     */
    private List<Permission> permissions;

    /**
     * 自定义数据权限-部门ID.
     */
    private List<String> customDepartmentIds;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        List<Permission> permissions = this.getPermissions();
        // 添加请求权限
        if (permissions != null && permissions.size() > 0) {
            for (Permission permission : permissions) {
                if (permission.getType() == 1
                        && StringUtils.isNotBlank(permission.getTitle())
                        && StringUtils.isNotBlank(permission.getPath())) {

                    authorityList.add(new SimpleGrantedAuthority(permission.getTitle()));
                }
            }
        }
        // 添加角色
        List<Role> roles = this.getRoles();
        if (roles != null && roles.size() > 0) {
            // lambda表达式
            roles.forEach(item -> {
                if (StringUtils.isNotBlank(item.getName())) {
                    authorityList.add(new SimpleGrantedAuthority(item.getName()));
                }
            });
        }
        return authorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.getStatus() == 0 ? false : true;

    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.getStatus() == 0 ? true : false;
    }
}
