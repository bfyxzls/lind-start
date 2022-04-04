package com.lind.rbac.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lind.uaa.jwt.entity.ResourceRole;
import com.lind.uaa.jwt.entity.ResourceUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户表
 * 因为ResourceUser有自己的序列化，所有User也需要加上@JsonDeserialize(as = User.class)
 * 参考：https://stackoverflow.com/questions/25387978/how-to-add-custom-deserializer-to-interface-using-jackson
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(as = User.class)
public class User implements ResourceUser {
    /**
     * 接口中的几个字段
     */
    private String id;
    private String username;
    private String password;
    private String email;

    /*
     * 自定义的几个字段phone,realName
     */
    private String phone;
    private String realName;
    /**
     * 用户的角色列表
     */
    private transient List<? extends ResourceRole> resourceRoles;
}
