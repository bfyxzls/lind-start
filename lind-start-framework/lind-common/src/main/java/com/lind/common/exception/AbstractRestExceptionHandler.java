package com.lind.common.exception;

import com.lind.common.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * 全局异常拦截器.
 * 如果某个模块需要,直接继承然后加入@RestControllerAdvice即可
 */
@Slf4j
public class AbstractRestExceptionHandler {

    /**
     * Exception.
     *
     * @param e 异常
     * @return CommonResult
     */
    @ExceptionHandler({Exception.class, RuntimeException.class})
    @ResponseStatus(HttpStatus.OK)
    public CommonResult handleException(Exception e) {
        String message = "系统内部异常";
        log.error(message, e);
        return CommonResult.serverFailure(message);
    }

    @ExceptionHandler({HttpClientErrorException.class})
    @ResponseStatus(HttpStatus.OK)
    public CommonResult handleHttpErrorException(HttpClientErrorException e) {
        log.error(e.getMessage(), e);
        return CommonResult.failure(400, e.getMessage());
    }

    /**
     * 统一处理请求参数校验(普通传参).
     *
     * @param e ConstraintViolationException
     * @return CommonResult
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path path = violation.getPropertyPath();
            String[] pathArr =
                    StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
            message.append(pathArr[1]).append(violation.getMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        log.error(message.toString());
        return CommonResult.clientFailure(message.toString());
    }

    /**
     * 统一处理请求参数校验(json).
     *
     * @param e ConstraintViolationException
     * @return CommonResult
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder message = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        log.error(message.toString());
        return CommonResult.clientFailure(message.toString());
    }

    /**
     * AccessDeniedException.
     *
     * @return CommonResult
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult handleAccessDeniedException() {
        return CommonResult.failure(HttpCodeEnum.UNAUTHORIZED);
    }

    /**
     * LindException.
     *
     * @param lindException 异常
     * @return CommonResult
     */
    @ExceptionHandler(value = LindException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult businessErrorException(LindException lindException) {
        return CommonResult.failure(HttpStatus.BAD_REQUEST.value(), lindException.getMessage());
    }

    /**
     * NoSuchElementException.
     *
     * @param e 异常
     * @return CommonResult
     */
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult handlerNoSuchElementException(NoSuchElementException e) {
        String message = e.getMessage();
        log.error(message);
        return CommonResult.clientFailure(message);
    }

    /**
     * IllegalArgumentException.
     *
     * @param e 异常
     * @return CommonResult
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult<String> handlerIllegalArgumentException(IllegalArgumentException e) {
        String message = e.getMessage();
        log.error(message);
        return CommonResult.clientFailure(message);
    }

}
