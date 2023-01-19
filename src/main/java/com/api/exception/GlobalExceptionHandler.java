package com.api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * web请求基础功能类
 *
 * @author lshuai
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(value = ApiException.class)
    @ResponseBody
    public ApiMessage apiException(ApiException exception) {
        logger.error("发生了一个未被捕获的异常", exception);
        return new ApiMessage(exception.getErrorCode(), exception.getErrorMsg().toString());
    }

    @ExceptionHandler(RuntimeException.class)
    public ApiMessage RuntimeException(Exception exception) {
        logger.error("发生了一个未被捕获的异常！", exception);
        return new ApiMessage(ErrorCode.ERROR, "");
    }
}
