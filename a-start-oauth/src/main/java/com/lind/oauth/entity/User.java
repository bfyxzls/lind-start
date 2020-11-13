package com.lind.oauth.entity;

import com.lind.uaa.entity.ResourcePermission;
import com.lind.uaa.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements com.lind.uaa.entity.User {

    private String id;
    private String username;
    private String password;
    private List<Role> roles;
    private List<ResourcePermission> resourcePermissions;

    @Override
    public List<Role> getRoles() {
        return Arrays.asList(new com.lind.oauth.entity.Role("1", "管理员"));
    }

    @Override
    public List<ResourcePermission> getResourcePermissions() {
        return Arrays.asList(new com.lind.oauth.entity.Permission("1", "chanpin", "/products", "", "email"));

    }
}
