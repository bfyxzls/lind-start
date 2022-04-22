package com.lind.uaa.jwt.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lind.uaa.jwt.entity.serialize.ResourcePermissionDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 权限.
 */
@JsonDeserialize(using = ResourcePermissionDeserializer.class)
@ApiModel("菜单/权限实体")
public interface ResourcePermission extends Serializable {
    String getId();

    /**
     * 权限名称.
     */
    @ApiModelProperty("权限名称")
    String getTitle();

    /**
     * 页面路径/资源url.
     */
    @ApiModelProperty("页面路径")
    String getPath();

    /**
     * api后台接口URL.
     */
    @ApiModelProperty("api后台接口URL")
    String getApiUrl();

    /**
     * 上级权限Id.
     *
     * @return
     */
    @ApiModelProperty("上级权限Id")
    String getParentId();

    /**
     * 权限类型，0菜单，1按钮.
     *
     * @return
     */
    @ApiModelProperty("权限类型，0菜单，1按钮")
    Integer getType();
    /**
     * http请求方式：GET,POST,DELETE,PUT
     *
     * @return
     */
    @ApiModelProperty("http请求方式：GET,POST,DELETE,PUT")
    String getHttpMethod();

    List<? extends ResourcePermission> getSons();

    void setSons(List<? extends ResourcePermission> resourcePermissions);
}