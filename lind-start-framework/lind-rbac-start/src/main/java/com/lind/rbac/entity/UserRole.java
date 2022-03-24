package com.lind.rbac.entity;

import lombok.Data;

/**
 *  用户角色表.
 */
@Data
public class UserRole {
  private static final long serialVersionUID = 1L;
  private String id;
  private String userId;
  private String roleId;
  private transient String roleName;
}
