package com.sensetime.fis.senseguard.opendoor.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/5 11:27
 * @Version 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {
    @Autowired
    private HttpServletRequest request;

    @ExceptionHandler(OpenDoorException.class)
    public ResponseEntity<?> handlerOpenDoorException(OpenDoorException openDoorException){
        ExceptionBody build = ExceptionBody.builder().path(request.getRequestURI())
                .code(openDoorException.getErrorCode())
                .message(openDoorException.getErrorMessage())
                .method(request.getMethod()).build();
        return new ResponseEntity<>(build,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handlerBildException(BindException bildException){
        String code = bildException.getMessage();
        String values = ExceptionEnum.getValues(code);
        ExceptionBody build = ExceptionBody.builder().path(request.getRequestURI())
                .method(request.getMethod())
                .message(values)
                .code(code).build();
        return new ResponseEntity<>(build,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex){
        log.info("错误信息详情:------------>{}",ex.getMessage());
        ex.printStackTrace();
        ExceptionBody build = ExceptionBody.builder().code(ExceptionEnum.ERROR_SYSTEM.getCode())
                .message(ExceptionEnum.ERROR_SYSTEM.getMessage())
                .path(request.getContextPath())
                .method(request.getMethod())
                .build();
        return new ResponseEntity<>(build,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
