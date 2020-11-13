package com.lind.oauth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements com.lind.uaa.entity.Role {
    private String id;
    private String name;
}
