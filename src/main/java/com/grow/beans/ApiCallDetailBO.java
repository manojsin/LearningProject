package com.grow.beans;

import lombok.Data;

import java.util.Date;
@Data
public class ApiCallDetailBO {
    private String apiName;
    private String groupId;
    private Date crtdDate;
    private String channel;
    private String status;
    private Object request;
    private Object response;
    private Object headers;
    private String client;
    private long executionTime;
    private String errorMsg;
    private boolean isFacade = false;
    private String userId;
    private String userEmail;
    private String apiUrl;

}
