package com.lind.rbac.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  用户角色表.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
  private static final long serialVersionUID = 1L;
  private String id;
  private String userId;
  private String roleId;
  private transient String roleName;
}
