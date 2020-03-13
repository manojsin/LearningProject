/*
package com.grow.Aop;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    private static Logger logger = LoggerFactory.getLogger(ExceptionHandlerControllerAdvice.class);

    @ExceptionHandler(value = {RequestProcessingException.class})
    public @ResponseBody
    ResponseEntity<ErrorMessageBO> hotelConfigServiceExceptionHandler(HttpServletRequest request, RequestProcessingException e) {
        logger.error(String.format("Exception with tracking Id: %s, dev message: %s and Message:", RequestContextKeeper.getContext().getRequestId(), e.getDeveloperMessage()), e);
        return new ResponseEntity<ErrorMessageBO>(new ErrorMessageBO(e.getErrorCode(), e.getMessage(), RequestContextKeeper.getContext().getRequestId(), e.getDeveloperMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public @ResponseBody
    ResponseEntity<ErrorMessageBO> runtimeExceptionHandler(HttpServletRequest request, RuntimeException e) {
        logger.error(String.format("Exception with tracking Id: %s and Message:", RequestContextKeeper.getContext().getRequestId()), e);
        return new ResponseEntity<ErrorMessageBO>(new ErrorMessageBO(1010, CommonConstants.GENERIC_ERR_MSG, RequestContextKeeper.getContext().getRequestId(), e.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(value = {Exception.class})
    public @ResponseBody
    ResponseEntity<ErrorMessageBO> exceptionHandler(HttpServletRequest request, Exception e) {
        logger.error(String.format("Exception with tracking Id: %s and Message:", RequestContextKeeper.getContext().getRequestId()), e);
        return new ResponseEntity<ErrorMessageBO>(new ErrorMessageBO(1011, CommonConstants.GENERIC_ERR_MSG, RequestContextKeeper.getContext().getRequestId(), e.getMessage()), HttpStatus.OK);
    }
}*/
