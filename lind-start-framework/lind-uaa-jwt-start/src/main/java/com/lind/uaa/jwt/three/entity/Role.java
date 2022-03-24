package com.lind.uaa.jwt.three.entity;

import com.lind.common.util.SnowFlakeUtils;
import com.lind.uaa.jwt.entity.ResourceRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role implements ResourceRole {
    private String id= SnowFlakeUtils.getFlowIdInstance().toString();
    private String name;
    private Integer buttonGrant;
}
