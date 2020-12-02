package com.lind.keycloak.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lind.uaa.keycloak.scope.ScopeJsonSerializer;
import com.lind.uaa.keycloak.scope.ScopeSet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(using = ScopeJsonSerializer.class)
public class UserDTO {

    private String name;
    @ScopeSet(value = "read")
    private String email;
    private Boolean sex;
    private Double total;
    private BigDecimal totalMoney;
    private Date birthday;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date getup;
}
