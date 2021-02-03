package com.example.demo.commons.config.restprocessor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.example.demo.commons.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Map.Entry;


@Component
@Slf4j
public class RestProcessor {


    @Autowired
    private RestTemplate restTemplate;


    private static URI prepareURL(String url, Map<String, String> queryParameters) {
        URIBuilder builder;
        try {
            builder = new URIBuilder(url);
            if (queryParameters != null) {
                for (Entry<String, String> entry : queryParameters.entrySet()) {
                    builder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            return builder.build();
        } catch (URISyntaxException e) {
            try {
                log.error("Could not build URI, url: {}, queryParams: {}", url, MappingUtils.convertObjectToJson(queryParameters));
            } catch (JsonProcessingException e1) {
                log.error("Unable to print queryParams as json", e1);
            }
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<String> postForStringEntity(String url, String body) {
        return exchangeForStringEntity(url, null, null, body, HttpMethod.POST);
    }

    public <T> ResponseEntity<String> postForStringEntity(String url, HttpHeaders headers, T body) {
        return exchangeForStringEntity(url, null, headers, body, HttpMethod.POST);
    }

    public <T> ResponseEntity<String> postForStringEntity(String url, Map<String, String> queryParams, HttpHeaders headers, T body) {
        return exchangeForStringEntity(url, queryParams, headers, body, HttpMethod.POST);
    }

    public ResponseEntity<String> postForStringEntity(String url, Map<String, String> queryParams, HttpHeaders headers,
                                                      String body) {
        return exchangeForStringEntity(url, queryParams, headers, body, HttpMethod.POST);
    }

    public ResponseEntity<String> getForStringEntity(String url, HttpHeaders headers) {
        return exchangeForStringEntity(url, null, headers, null, HttpMethod.GET);
    }

    public ResponseEntity<String> getForStringEntity(String url, Map<String, String> queryParams, HttpHeaders headers) {
        return exchangeForStringEntity(url, queryParams, headers, null, HttpMethod.GET);
    }

    public ResponseEntity<String> putForStringEntity(String url, HttpHeaders headers) {
        return exchangeForStringEntity(url, null, headers, null, HttpMethod.PUT);
    }

    public ResponseEntity<String> putForStringEntity(String url, HttpHeaders headers, String body) {
        return exchangeForStringEntity(url, null, headers, body, HttpMethod.PUT);
    }

    public ResponseEntity<String> deleteForStringEntity(String url, HttpHeaders headers, String body) {
        return exchangeForStringEntity(url, null, headers, body, HttpMethod.DELETE);
    }

    public <T> ResponseEntity<String> exchangeForStringEntity(String url, Map<String, String> queryParams, HttpHeaders headers,
                                                              T body, HttpMethod httpMethod) {
        ResponseEntity<String> response;
        try {
            response = exchange(url, queryParams, headers, JsonUtils.serializeWithIgnoreNull(body), httpMethod, String.class);
        } catch (JsonProcessingException e) {
            response = new ResponseEntity<String>(e.getMessage(), null, HttpStatus.BAD_REQUEST);
        } catch (HttpStatusCodeException e) {
            response = new ResponseEntity<String>(e.getResponseBodyAsString(), e.getResponseHeaders(), e.getStatusCode());
        }

        return response;

    }

    public <T, S> ResponseEntity<T> exchange(String url, Map<String, String> queryParams, HttpHeaders headers, S body,
                                             HttpMethod httpMethod, Class<T> type) {
        log.debug(String.format(" url[%s]  queryParams[%s] headers[%s] httpMethod[%s] body[%s] ", url, queryParams, headers, httpMethod, body));
        URI uri = queryParams == null ? URI.create(url) : prepareURL(url, queryParams);
        long start = System.currentTimeMillis();
        RequestEntity<S> request = new RequestEntity<>(body, headers, httpMethod, uri);
        ResponseEntity<T> exchange = restTemplate.exchange(request, type);
        long now = System.currentTimeMillis();
        return exchange;
    }


}
