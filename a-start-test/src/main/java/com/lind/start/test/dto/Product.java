package com.lind.start.test.dto;

import com.lind.uaa.keycloak.scope.ScopeSet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String title;
    @ScopeSet("write")
    private String msg;
}
