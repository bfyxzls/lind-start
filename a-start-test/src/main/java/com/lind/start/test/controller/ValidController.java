package com.lind.start.test.controller;

import com.lind.start.test.dto.DepartmentDto;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ValidController {

	/**
	 * @valid不能指向集合对象
	 * @param code
	 * @param request
	 * @return
	 */
	@PostMapping("valid/add/{code}")
	public ResponseEntity initialAccount(@ApiParam("客户编号") @PathVariable String code,
			@ApiParam("请求体") @Valid @RequestBody DepartmentDto request) {
		return ResponseEntity.ok("ok");
	}

}
