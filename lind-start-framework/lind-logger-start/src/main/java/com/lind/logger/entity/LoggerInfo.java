package com.lind.logger.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LoggerInfo {
    /**
     * 被记录的模块
     */
    private Integer moduleType;
    /**
     * 被记录的操作
     */
    private Integer operateType;
    /**
     * 日志数据ID.
     */
    private String dataId;

    /**
     * 日志数据标题.
     */
    private String dataTitle;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 操作时间
     */
    private LocalDateTime operateTime;
    /**
     * 操作人IP.
     */
    private String operatorIp;
    /**
     * 内容
     */
    private String detail;

    /**
     * 请求URI
     */
    private String requestUri;
    /**
     * 请求方式
     */
    private String requestMethod;
    /**
     * 请求参数
     */
    private String requestParams;
    /**
     * 请求时长(毫秒)
     */
    private Integer requestTime;
    /**
     * 用户代理
     */
    private String userAgent;
}
