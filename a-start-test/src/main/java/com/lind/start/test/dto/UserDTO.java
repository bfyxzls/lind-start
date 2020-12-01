package com.lind.start.test.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lind.uaa.keycloak.scope.ScopeJsonSerializer;
import com.lind.uaa.keycloak.scope.ScopeSet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings("EI_EXPOSE_REP")
    private Date birthday;
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings("EI_EXPOSE_REP")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date getup;
    private List<Info> infoList;

}
