package com.lind.start.test.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserDTO {
    private String name;
    private String email;
    private Boolean sex;
    private Double total;
    private BigDecimal totalMoney;
}
