package com.lind.uaa.jwt.three.entity;

import com.lind.uaa.jwt.entity.ResourceUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements ResourceUser {

    private String id;
    private String username;
    private String password;
    private String email;
    private List<Permission> resourcePermissions;
    private List<Role> resourceRoles;
}