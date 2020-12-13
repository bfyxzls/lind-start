package com.lind.uaa.jwt.service;

import com.lind.uaa.jwt.entity.ResourcePermission;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单与按钮权限接口.
 */
public interface ResourcePermissionService {

    /**
     * 获取所有资源权限.
     *
     * @return
     */
    List<? extends ResourcePermission> getAll();

    /**
     * 得到树型结构.
     *
     * @return
     */
    default List<? extends ResourcePermission> getTreeMenus() {
        List<ResourcePermission> ones = getAll().stream()
                .filter(o -> o.getParentId() == null)
                .collect(Collectors.toList());
        getTreeMenuRzSons(ones);
        return ones;
    }

    /**
     * 根据角色取权限.
     *
     * @param roleId
     * @return
     */
    List<? extends ResourcePermission>  getAllByRoleId(String roleId);

    /**
     * 递归找子孙.
     *
     * @param ones
     */
    default void getTreeMenuRzSons(List<? extends ResourcePermission> ones) {
        for (ResourcePermission resourcePermission : ones) {
            if (resourcePermission != null) {
                List<ResourcePermission> sons = getAll().stream()
                        .filter(o -> o.getParentId() != null
                                && o.getParentId().equals(resourcePermission.getId()))
                        .collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(sons)) {
                    resourcePermission.setSons(sons);
                    getTreeMenuRzSons(sons);
                }
            }
        }
    }


}
