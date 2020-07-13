package com.lind.start.test.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
public class UserDTO {
    private String name;
    private String email;
    private Boolean sex;
    private Double total;
    private BigDecimal totalMoney;
}
