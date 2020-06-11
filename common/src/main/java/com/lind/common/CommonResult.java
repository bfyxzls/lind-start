package com.lind.common;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Author 赵阳
 * @Description 通用返回结果
 * @Date 10:25 2019-04-23
 * @Param
 * @return
 **/
@Getter
@Setter
public class CommonResult<T> implements Serializable {


    private static final long serialVersionUID = 1573835682597272725L;
    /**
     * 状态码
     */
    private int code;
    private String message;
    /**
     * 数据
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
     * 根据状态码和消息构建结果
     *
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static <T> CommonResult<T> build(Integer code, String msg, T data) {
        return new CommonResult<>(code, msg, data);
    }


    /**
     * 成功返回
     *
     * @return
     */
    public static CommonResult<Object> ok() {
        return ok(null);
    }

    /**
     * 成功返回并带相应数据
     *
     * @param data
     * @return
     */
    public static <T> CommonResult<T> ok(T data) {
        CodeEnum success = CodeEnum.SUCCESS;
        return new CommonResult<T>(success.getCode(), success.getMessage(), data);
    }

    public static <T> CommonResult<T> failure() {
        CommonResult<T> result = new CommonResult<>();
        result.code = CodeEnum.FAILURE.getCode();
        result.message = CodeEnum.FAILURE.getMessage();

        return result;
    }

    /**
     * @author: 赵阳
     * @description: 服务端异常
     * @date: 2020-05-08 10:57
     * @Param message:
     * @return: com.pkulaw.tec.common.core.entity.CommonResult
     **/
    public static <T> CommonResult<T> serverFailure(String message) {
        CommonResult<T> result = new CommonResult<>();
        result.code = CodeEnum.INTERNAL_SERVER_ERROR.getCode();
        result.message = message;
        return result;
    }

    public static <T> CommonResult<T> clientFailure(String message) {
        CommonResult<T> result = new CommonResult<>();
        result.code = CodeEnum.ILLEGAL_ARGUMENT.getCode();
        result.message = message;
        return result;
    }

    /**
     * @author: 赵阳
     * @description: TODO
     * @date: 2020-05-08 10:55
     * @Param codeEnum:
     * @return: com.pkulaw.tec.common.core.entity.CommonResult
     **/
    public static <T> CommonResult<T> failure(CodeEnum codeEnum) {
        CommonResult<T> result = new CommonResult<>();
        result.code = codeEnum.getCode();
        result.message = codeEnum.getMessage();
        return result;
    }

    /**
     * 失败无响应数据自定义状态码及消息
     */
    public static <T> CommonResult<T> failure(Integer code, String message) {
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.message = message;

        return result;
    }

    /**
     * 返回数据是否成功
     *
     * @return
     */
    @JsonIgnore
    public boolean isSuccess() {
        return CodeEnum.SUCCESS.getCode().equals(this.code);
    }

    /**
     * 判断是否包含数据
     *
     * @return
     */
    public boolean hasData() {
        return Objects.nonNull(data);
    }

}
