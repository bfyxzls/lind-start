package com.lind.uaa.jwt.three;

import com.lind.uaa.jwt.entity.ResourcePermission;
import com.lind.uaa.jwt.entity.ResourceRole;
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
    private List<ResourcePermission> resourcePermissions;
    private List<ResourceRole> resourceRoles;
}
