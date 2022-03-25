package com.lind.logger.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class LoggerInfo {
  /**
   * 被记录的模块
   */
  private String module;
  /**
   * 模块的主键
   */
  private String objectId;
  /**
   * 操作人
   */
  private String operateUser;
  /**
   * 操作时间
   */
  private Date operateTime;
  /**
   * 内容
   */
  private Object detail;
}
