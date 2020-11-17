package com.lind.uaa.jwt.three;

import com.lind.uaa.jwt.entity.ResourcePermission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements ResourcePermission {
    private String id;
    private String title;
    private String path;
    private String auth;
    private String scope;

}
