package com.lind.rbac.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.lind.uaa.jwt.entity.ResourceRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import static com.lind.common.util.BinFlagUtils.splitBinPower;

/**
 * 角色表.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role implements ResourceRole {
  private String id;
  private String name;
  private Integer buttonGrant;

  /**
   * 不在数据表存在，只是个导航属性.
   */
  @TableField(exist = false)
  private List<Integer> buttonGrantList;

  public void initButtonGrantList() {
    buttonGrantList = splitBinPower(buttonGrant);
  }


}