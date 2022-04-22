package com.lind.rbac.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.lind.uaa.jwt.entity.ResourcePermission;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@ApiModel("角色DTO")
public class RoleDTO {
  @ApiModelProperty("主键")
  @TableField("id")
  private String id;
  @ApiModelProperty("名称")
  @TableField("name")
  @Size(max = 10, min = 4, message = "长度限制")
  private String name;
  @ApiModelProperty("菜单ID列表")
  private List<String> permissionIdList;
  @ApiModelProperty("菜单列表")
  private List<? extends ResourcePermission> permissionList;
}
