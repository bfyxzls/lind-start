package com.lind.start.test.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

	@ApiModelProperty("id")
	private String id;

	@ApiModelProperty("上级Id")
	private String parentId;

	@ApiModelProperty("编号")
	@NotBlank(message = "部门编号不能为空。")
	private String code;

	@ApiModelProperty("名称")
	@NotBlank(message = "部门名称不能为空。")
	private String name;

	@ApiModelProperty("员工集合")
	@Builder.Default
	private List<Employee> employees = new ArrayList<>();

}