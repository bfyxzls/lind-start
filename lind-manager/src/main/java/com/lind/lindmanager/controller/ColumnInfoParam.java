package com.lind.lindmanager.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColumnInfoParam {

	private String tableName;

	private Integer len;

	private Date createTime;

}
