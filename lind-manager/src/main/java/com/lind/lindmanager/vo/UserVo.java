package com.lind.lindmanager.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class UserVo {

	private String name;

	private Integer age;

	private String address;

	private Date date;

}
