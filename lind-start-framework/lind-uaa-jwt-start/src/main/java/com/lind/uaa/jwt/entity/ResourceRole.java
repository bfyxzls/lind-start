package com.lind.uaa.jwt.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * 角色.
 */
@JsonDeserialize(using = ResourceRoleDeserializer.class)
@JsonSerialize(using = ResourceRoleSerializer.class)
public interface ResourceRole extends Serializable {

    String getId();

    String getName();
}
