package com.grow.Aop;

import com.grow.Client.ApiCallDetailQService;
import com.grow.beans.ApiCallDetailBO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class ApiCallDetailLoggerAdvice {

    @Autowired
    ApiCallDetailQService apiCallDetailService;

    private static Logger logger = LoggerFactory.getLogger(ApiCallDetailLoggerAdvice.class);

    @Pointcut("execution(* com.yatra.products.hotel.common.service.HttpService.fetchStringResponseFromApi(..))")
    public void gettingResponseFromAPI() {
    }

    @Pointcut("execution(* com.yatra.products.hotel.common.service.HttpService.getStringDataFromUnescapedUri(..))")
    public void gettingResponseFromUnescapedUri() {
    }

    @Pointcut("(gettingResponseFromAPI() || gettingResponseFromUnescapedUri()) && args(url, reqBody, method, headers, requestId)")
    public <E, T> void gettingResponseWithValidArguments(String url, E reqBody, HttpMethod method, HttpHeaders headers, String requestId) {
    }

    @Around("gettingResponseWithValidArguments(url, reqBody, method, headers, requestId)")
    public <E, T> Object trackApiCall(ProceedingJoinPoint joinPoint, String url, E reqBody, HttpMethod method, HttpHeaders headers, String requestId) throws Throwable {
        Object result = null;
        long start = System.currentTimeMillis();
        String status = null;
        String errorMsg = null;
        try {
            result = joinPoint.proceed();
            status = "SUCCESS";
        } catch (Exception e) {

            status = "FAILURE";
            errorMsg = stackTraceToError(e);

            throw e;

        } finally {

            long executionTime = System.currentTimeMillis() - start;

            ApiCallDetailBO callDetail = new ApiCallDetailBO();

            callDetail.setApiUrl(url);
            callDetail.setChannel("B2B");
            callDetail.setClient("hotel-services");
            callDetail.setCrtdDate(new Date());
            callDetail.setErrorMsg(errorMsg);
            callDetail.setExecutionTime(executionTime);
            callDetail.setGroupId(requestId);
            callDetail.setRequest(reqBody);
            callDetail.setResponse(result);
            callDetail.setStatus(status);
            callDetail.setHeaders(headers);

            try {
                apiCallDetailService.addToQueue(callDetail);
            } catch (Exception e) {
                logger.error("couldn't persist ApiCallDetailEntity {}", callDetail.toString());
            }
        }

        return result;

    }

    private String stackTraceToError(Exception e) {
        StringBuilder sb = new StringBuilder("");
        sb.append(e.getMessage());
        StackTraceElement[] traceArray = e.getStackTrace();
        if (traceArray != null) {
            for (int i = 0; i < traceArray.length && i <= 5; i++) {
                StackTraceElement elem = traceArray[i];
                sb.append("\n").append(elem.toString());
            }
        }

        return sb.toString();
    }

}
