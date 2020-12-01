package com.lind.start.test.dto;

import com.lind.uaa.keycloak.scope.ScopeSet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {
    private String title;
    @ScopeSet("write")
    private String msg;
    private Date birthday;
}
