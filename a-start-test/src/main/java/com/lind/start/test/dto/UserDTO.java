package com.lind.start.test.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lind.uaa.keycloak.scope.ScopeSet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String name;
    @ScopeSet
    private String email;
    private Boolean sex;
    private Double total;
    private BigDecimal totalMoney;
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings("EI_EXPOSE_REP")
    private Date birthday;
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings("EI_EXPOSE_REP")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date getup;

}
