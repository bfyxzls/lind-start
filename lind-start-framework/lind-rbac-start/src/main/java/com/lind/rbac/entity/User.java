package com.lind.rbac.entity;

import com.lind.uaa.jwt.entity.ResourceRole;
import com.lind.uaa.jwt.entity.ResourceUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户表.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements ResourceUser {
  private String id;
  private String username;
  private String password;
  private String email;
  /**
   * 用户的角色列表
   */
  private transient List<? extends ResourceRole> resourceRoles;
}
