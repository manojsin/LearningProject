/*
package com.grow.Client;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component("httpServices")
public class HttpService {

    @Autowired
    @Qualifier("httpServices") HttpService httpService;
    private static Logger logger = LoggerFactory.getLogger(HttpService.class);

    private static RestTemplate restTemplate = new RestTemplate();
    private static ObjectMapper mapper = new ObjectMapper();

    public <E, T> T getDataFromRestService(String url, HttpHeaders headers, E reqBody, Class<T> outputClass) throws RequestProcessingException {

        String response = httpService.fetchStringResponseFromApi(url, reqBody, HttpMethod.GET, headers, RequestContextKeeper.getContext().getRequestId());
        T responseObj = mapStringToObject(response, outputClass);
        return responseObj;
    }

    public <E, T> T getDataFromRestService(String url, HttpHeaders headers, E reqBody, Class<T> outputClass, String requestId) throws RequestProcessingException {

        String response = httpService.fetchStringResponseFromApi(url, reqBody, HttpMethod.GET, headers, requestId);
        T responseObj = mapStringToObject(response, outputClass);
        return responseObj;
    }

    public <E, T> T postDataToRestService(String url, HttpHeaders headers, E reqBody, Class<T> outputClass) throws RequestProcessingException {

        String response = httpService.fetchStringResponseFromApi(url, reqBody, HttpMethod.POST, headers, RequestContextKeeper.getContext().getRequestId());
        T responseObj = mapStringToObject(response, outputClass);
        return responseObj;
    }

    public <E, T> T postDataToRestService(String url, HttpHeaders headers, E reqBody, Class<T> outputClass, String requestId) throws RequestProcessingException {

        String response = httpService.fetchStringResponseFromApi(url, reqBody, HttpMethod.POST, headers, requestId);
        T responseObj = mapStringToObject(response, outputClass);
        return responseObj;
    }

    public <E, T> T putDataToRestService(String url, HttpHeaders headers, E reqBody, Class<T> outputClass) throws RequestProcessingException {

        String response = httpService.fetchStringResponseFromApi(url, reqBody, HttpMethod.PUT, headers, RequestContextKeeper.getContext().getRequestId());
        T responseObj = mapStringToObject(response, outputClass);
        return responseObj;
    }
    public <E, T> T putDataToRestService(String url, HttpHeaders headers, E reqBody, Class<T> outputClass, String requstId) throws RequestProcessingException {

        String response = httpService.fetchStringResponseFromApi(url, reqBody, HttpMethod.PUT, headers, requstId);
        T responseObj = mapStringToObject(response, outputClass);
        return responseObj;
    }

    public static <C> C mapStringToObject(String string, Class<C> classType) throws RequestProcessingException {

        C output = null;
        try{
            output = mapper.readValue(string, classType);
        }catch (Exception e){
            logger.error("error while mapping string {} to class {}. Got error {}", string, classType, e.fillInStackTrace());
            throw new RequestProcessingException(ErrorMessageConstants.SOMETHING_WENT_WRONG_PLEASE_TRY_AGAIN_LATER,CommonConstants.GENERIC_ERR_MSG);
        }

        return output;
    }

    public <E> String fetchStringResponseFromApi(String url, E reqBody, HttpMethod method, HttpHeaders headers, String requestId) {

        logger.debug("Getting data from {} with body {}", url, reqBody);
        HttpEntity<E> entity = null;

        if(reqBody != null){
            entity = new HttpEntity<E>(reqBody, headers);
        }else{
            entity = new HttpEntity<E>(headers);
        }

        URI uri = UriComponentsBuilder.fromUriString(url).build().encode().toUri();

        String response = restTemplate.exchange(uri, method, entity, String.class).getBody();
        return response;
    }

    public <E> String getStringDataFromUnescapedUri(String url, E reqBody, HttpMethod method, HttpHeaders headers, String requestId) {

        logger.debug("Getting data from {} with body {}", url, reqBody);
        HttpEntity<E> entity = null;
        if(reqBody != null){
            entity = new HttpEntity<E>(reqBody, headers);
        }else{
            entity = new HttpEntity<E>(headers);
        }
        String response = restTemplate.exchange(url, method, entity, String.class).getBody();
        logger.debug("Response received {} from url [ {} ]", response, url);
        return response;
    }

    public <E> String getStringDataFromRestService(String url, HttpHeaders headers, E reqBody) {

        return httpService.getStringDataFromUnescapedUri(url, reqBody, HttpMethod.GET, headers, RequestContextKeeper.getContext().getRequestId());
    }

    public <E> String getStringDataFromRestService(String url, HttpHeaders headers, E reqBody, String requestId) {

        return httpService.getStringDataFromUnescapedUri(url, reqBody, HttpMethod.GET, headers, requestId);
    }

    public <E> String postStringDataToRestService(String url, HttpHeaders headers, E reqBody) {

        return httpService.getStringDataFromUnescapedUri(url, reqBody, HttpMethod.POST, headers, RequestContextKeeper.getContext().getRequestId());
    }

    public <E> String postStringDataToRestService(String url, HttpHeaders headers, E reqBody, String requestId) {

        return httpService.getStringDataFromUnescapedUri(url, reqBody, HttpMethod.POST, headers, requestId);
    }
    public <E> String postStringFormDataToRest(String url, MultiValueMap<String, String> reqBody, HttpHeaders headers, String requestId) {
        return httpService.getStringDataFormurl(url, reqBody, headers, requestId);

    }
    public <E> String getStringDataFormurl(String url, MultiValueMap<String, String> reqBody, HttpHeaders headers,String requestId) {

        logger.debug("Getting data from {} with body {}", url, reqBody);
        HttpEntity<MultiValueMap<String, String>> request = null;
        if (reqBody != null) {
            request = new HttpEntity(reqBody, headers);
        } else {
            request = new HttpEntity(headers);
        }
        String response = restTemplate.postForEntity(url, request, String.class).getBody();
        logger.debug("Response received {} from url [ {} ]", response, url);
        return response;
    }
*/
