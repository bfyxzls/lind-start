package com.lind.rbac.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lind.mybatis.base.BaseEntity;
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
@JsonDeserialize(as = Role.class)
public class Role extends BaseEntity implements ResourceRole {
    private String name;
}
