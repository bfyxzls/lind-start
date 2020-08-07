package com.lind.uaa.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lind.uaa.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionDao extends BaseMapper<Permission> {
}
