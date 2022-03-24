package com.lind.uaa.jwt.three.entity;

import com.lind.common.util.SnowFlakeUtils;
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

    private String id= SnowFlakeUtils.getFlowIdInstance().toString();
    private String username;
    private String password;
    private String email;
    private List<? extends ResourcePermission> resourcePermissions;
    private List<? extends ResourceRole> resourceRoles;
}
