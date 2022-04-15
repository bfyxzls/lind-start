package com.lind.rbac.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lind.mybatis.base.BaseEntity;
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
@JsonDeserialize(as = User.class)
public class User extends BaseEntity implements ResourceUser {
  private String username;
  private String password;
  private String email;

  /*
   * 自定义的几个字段phone,realName
   */
  private String phone;
  private String realName;

  /**
   * 用户的角色列表
   */
  private transient List<? extends ResourceRole> resourceRoles;
}
