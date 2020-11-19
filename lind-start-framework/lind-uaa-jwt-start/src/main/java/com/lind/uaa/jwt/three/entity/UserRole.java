package com.lind.uaa.jwt.three.entity;

import lombok.Data;

/**
 * @author Exrickx
 */
@Data
public class UserRole {
    private static final long serialVersionUID = 1L;
    private String id;
    private String userId;

    private String roleId;

    private String roleName;
}
