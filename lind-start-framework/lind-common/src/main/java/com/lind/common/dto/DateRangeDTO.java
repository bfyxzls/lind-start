package com.lind.common.dto;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class DateRangeDTO {
  @ApiParam("开始时间")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate fromDate;
  @ApiParam("结束时间")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate toDate;
}
