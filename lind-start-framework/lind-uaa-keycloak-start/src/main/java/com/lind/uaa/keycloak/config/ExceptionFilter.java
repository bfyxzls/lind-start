package com.lind.uaa.keycloak.config;

import com.lind.common.exception.AbstractRestExceptionHandler;
import com.lind.common.rest.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常拦截器.
 */
@RestControllerAdvice
@Slf4j
public class ExceptionFilter extends AbstractRestExceptionHandler {
    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public CommonResult<String> accessDeniedException(AccessDeniedException e) {
        String message = e.getMessage();
        log.error(message);
        return CommonResult.failure(403, message);
    }
}
