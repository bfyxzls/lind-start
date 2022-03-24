package com.lind.rbac.entity;

import com.lind.uaa.jwt.entity.ResourcePermission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 权限菜单表.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Permission implements ResourcePermission {
  private String id;
  /**
   * 菜单-按钮-名称.
   */
  private String title;
  /**
   * 资源相对路径.
   */
  private String path;
  /**
   * 类型：0菜单,1按钮.
   */
  private Integer type;
  /**
   * 上级Id
   */
  private String parentId;
  /**
   * 父菜单.
   */
  private transient ResourcePermission parent;
  /**
   * 子菜单列表.
   */
  private transient List<? extends ResourcePermission> sons;
}
