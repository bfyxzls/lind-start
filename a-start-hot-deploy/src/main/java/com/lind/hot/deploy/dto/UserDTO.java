package com.lind.hot.deploy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lind.hot.deploy.scope.ScopeSet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private Date birthday;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date getup;

}
