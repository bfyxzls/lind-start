package com.lind.keycloak.config;

import com.lind.common.exception.AbstractRestExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorFilter extends AbstractRestExceptionHandler {
}
