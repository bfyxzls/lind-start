package com.lind.start.test;

import com.lind.common.exception.AbstractRestExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionFilter extends AbstractRestExceptionHandler {
}
