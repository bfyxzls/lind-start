package com.lind.start.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @NotBlank(message = "员工名称不能为空。")
    private String name;
    private String email;
    private String idNumber;
}
