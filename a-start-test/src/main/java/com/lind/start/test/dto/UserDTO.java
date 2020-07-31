package com.lind.start.test.dto;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String name;
    private String email;
    private Boolean sex;
    private Double total;
    private BigDecimal totalMoney;
    private Date birthday;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date getup;

}
