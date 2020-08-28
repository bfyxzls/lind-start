package com.lind.common.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lind.common.exception.HttpCodeEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * 通用返回结果.
 **/
@Getter
@Setter
public class CommonResult<T> implements Serializable {


    private static final long serialVersionUID = 1573835682597272725L;
    /**
     * 状态码.
     */
    private int code;
    private String message;
    /**
     * 数据.
     */
    private T data;

    private CommonResult() {
    }

    private CommonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 根据状态码和消息构建结果.
     */
    public static <T> CommonResult<T> build(Integer code, String msg, T data) {
        return new CommonResult<>(code, msg, data);
    }


    /**
     * 成功返回.
     */
    public static CommonResult<Object> ok() {
        return ok(null);
    }

    /**
     * 成功返回并带相应数据.
     */
    public static <T> CommonResult<T> ok(T data) {
        HttpCodeEnum success = HttpCodeEnum.SUCCESS;
        return new CommonResult<T>(success.getCode(), success.getMessage(), data);
    }

    /**
     * fail.
     */
    public static <T> CommonResult<T> failure() {
        CommonResult<T> result = new CommonResult<>();
        result.code = HttpCodeEnum.FAILURE.getCode();
        result.message = HttpCodeEnum.FAILURE.getMessage();

        return result;
    }


    /**
     * failure.
     **/
    public static <T> CommonResult<T> failure(HttpCodeEnum httpCodeEnum) {
        CommonResult<T> result = new CommonResult<>();
        result.code = httpCodeEnum.getCode();
        result.message = httpCodeEnum.getMessage();
        return result;
    }

    /**
     * 失败无响应数据自定义状态码及消息.
     */
    public static <T> CommonResult<T> failure(Integer code, String message) {
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.message = message;

        return result;
    }

    /**
     * 服务端异常.
     **/
    public static <T> CommonResult<T> serverFailure(String message) {
        CommonResult<T> result = new CommonResult<>();
        result.code = HttpCodeEnum.INTERNAL_SERVER_ERROR.getCode();
        result.message = message;
        return result;
    }

    /**
     * clientFailure.
     */
    public static <T> CommonResult<T> clientFailure(String message) {
        CommonResult<T> result = new CommonResult<>();
        result.code = HttpCodeEnum.ILLEGAL_ARGUMENT.getCode();
        result.message = message;
        return result;
    }


    /**
     * 返回数据是否成功.
     */
    @JsonIgnore
    public boolean isSuccess() {
        return HttpCodeEnum.SUCCESS.getCode().equals(this.code);
    }

    /**
     * 判断是否包含数据.
     */
    public boolean hasData() {
        return Objects.nonNull(data);
    }

}
