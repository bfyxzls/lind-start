package com.lind.uaa.jwt.three.entity;

import com.lind.uaa.jwt.entity.ResourceRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements ResourceRole {
    private String id;
    private String name;
}
