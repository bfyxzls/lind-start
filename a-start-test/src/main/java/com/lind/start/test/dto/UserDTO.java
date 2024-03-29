package com.lind.start.test.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

	private String id;

	private String name;

	private String email;

	private Boolean sex;

	private Double total;

	private BigDecimal totalMoney;

	private Date birthday;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date getup;

	private List<Info> infoList;

}
