package com.lind.common;

/**
 * 返回状态码
 *
 * @author BD-PC220
 */
public enum CodeEnum {

    /**
     * 成功
     */
    SUCCESS(200, "success"),

    /**
     * 失败
     */
    FAILURE(-1, "failure"),

    /**
     * 系统内部错误
     */
    INTERNAL_SERVER_ERROR(500, "系统错误"),

    /**
     * 客户端错误
     */
    ILLEGAL_ARGUMENT(400, "客户端错误"),


    /**
     * 权限不足
     */
    UNAUTHORIZED(403, "权限不足");


    private Integer code;
    private String message;

    CodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}
