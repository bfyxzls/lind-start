package com.lind.start.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {

	private String title;

	private String msg;

	private Date birthday;

	private String type;

	private double price;

}
