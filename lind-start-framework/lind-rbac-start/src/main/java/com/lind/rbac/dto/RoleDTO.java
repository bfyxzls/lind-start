package com.lind.rbac.dto;

import com.lind.uaa.jwt.entity.ResourcePermission;
import lombok.Data;

import java.util.List;

@Data
public class RoleDTO {
  private String id;
  private String name;
  private List<Integer> buttonGrantList;
  private List<? extends ResourcePermission> permissionList;
}
