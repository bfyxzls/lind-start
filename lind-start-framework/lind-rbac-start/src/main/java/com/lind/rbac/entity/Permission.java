package com.lind.rbac.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lind.uaa.jwt.entity.ResourcePermission;
import lombok.*;

import java.util.List;

import static com.lind.common.util.BinFlagUtils.splitBinPower;

/**
 * 权限菜单表.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonDeserialize(as = Permission.class)
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
     * 单行按钮组.
     */
    private Integer rowButton;
    /**
     * 批量操作按钮组.
     */
    private Integer bulkButton;
    /**
     * 单行按钮列表.
     */
    private transient List<Integer> rowButtonList;
    /**
     * 批量操作按钮列表.
     */
    private transient List<Integer> bulkButtonList;
    /**
     * 子菜单列表.
     */
    private transient List<? extends ResourcePermission> sons;

    /**
     * 获取单选按钮组列表.
     *
     * @return
     */
    public List<Integer> getRowButtonList() {
        return splitBinPower(getRowButton());
    }

    /**
     * 获取批量按钮列表.
     *
     * @return
     */
    public List<Integer> getBulkButtonList() {
        return splitBinPower(getBulkButton());
    }
}
