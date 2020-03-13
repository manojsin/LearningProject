package com.grow.Client;

import com.grow.beans.ApiCallDetailBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class ApiCallDetailQService {

    private Logger logger = LoggerFactory.getLogger(ApiCallDetailQService.class);

    private BlockingQueue<ApiCallDetailBO> apiCallDetailBQ = new LinkedBlockingQueue<ApiCallDetailBO>();

    public Boolean addToQueue(ApiCallDetailBO apiCallDetailEntity) {

        Boolean result = false;
        try {
            if (apiCallDetailBQ.remainingCapacity() != 0) {
                apiCallDetailBQ.add(apiCallDetailEntity);
                result = true;
            }
        } catch (Exception exp) {
            logger.error("Error while adding to api call detail queue");
        }
        return result;
    }

    public Boolean addAllToQueue(List<ApiCallDetailBO> callDetailList) {

        Boolean result = false;
        try {
            if (apiCallDetailBQ.remainingCapacity() != 0) {
                apiCallDetailBQ.addAll(callDetailList);
                result = true;
            }
        } catch (Exception exp) {
            logger.error("Error while adding to api call detail queue");
        }
        return result;
    }

    public List<ApiCallDetailBO> takeFromQueue() {
        List<ApiCallDetailBO> callDetailList = new ArrayList<>();
        try {
            if (!apiCallDetailBQ.isEmpty()) {
                apiCallDetailBQ.poll();
            }
        } catch (Exception exp) {
            logger.error("Error while getting data from api call detail queue");
        }
        return callDetailList;
    }

    public List<ApiCallDetailBO> takeAllFromQueue() {
        List<ApiCallDetailBO> callDetailList = new ArrayList<>();
        try {
            if (!apiCallDetailBQ.isEmpty()) {
                apiCallDetailBQ.drainTo(callDetailList);
            }
        } catch (Exception exp) {
            logger.error("Error while getting data from api call detail queue");
        }
        return callDetailList;
    }
}

