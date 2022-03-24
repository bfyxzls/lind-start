package com.lind.uaa.jwt.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lind.uaa.jwt.entity.serialize.ResourceRoleDeserializer;
import com.lind.uaa.jwt.entity.serialize.ResourceRoleSerializer;

import java.io.Serializable;
import java.util.List;

import static com.lind.common.util.BinFlagUtils.addValueList;
import static com.lind.common.util.BinFlagUtils.hasValue;

/**
 * 角色.
 */
@JsonDeserialize(using = ResourceRoleDeserializer.class)
@JsonSerialize(using = ResourceRoleSerializer.class)
public interface ResourceRole extends Serializable {

  /**
   * 角色ID.
   *
   * @return
   */
  String getId();

  /**
   * 角色名称.
   *
   * @return
   */
  String getName();

  /**
   * 按钮权限：使用二进制位运算实现.
   *
   * @return
   */
  Integer getButtonGrant();

  /**
   * 设置buttonGrant.
   *
   * @param grant
   */
  void setButtonGrant(Integer grant);

  /**
   * 添加按钮权限列表.
   *
   * @param grant
   */
  default void addGrant(List<Integer> grant) {
    setButtonGrant(0);//先清空，再重新赋值
    setButtonGrant(addValueList(getButtonGrant(), grant));
  }

  /**
   * 是否包含某个权限.
   *
   * @param grant
   * @return
   */
  default Boolean hasGrant(Integer grant) {
    return hasValue(getButtonGrant(), grant);
  }
}
