package com.example.demo.commons.config.restprocessor;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration("HttpClientConfig")
public class HttpClientConfig {

    @Value("${http.max.conn.per-route:100}")
    private int maxConnPerRoute;

    @Value("${http.max.conn.total:200}")
    private int maxTotalConn;

    @Value("${http.timeout:40000}")
    private int timeoutMs;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        return restTemplate;
    }

    @Bean("HttpComponentsClientHttpRequestFactory-LedgerEngine")
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(getHttpClient());
        return clientHttpRequestFactory;
    }

    private CloseableHttpClient getHttpClient() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotalConn);
        connectionManager.setDefaultMaxPerRoute(maxConnPerRoute);

        RequestConfig config = RequestConfig.custom().setConnectTimeout(timeoutMs).setSocketTimeout(timeoutMs)
                .setConnectionRequestTimeout(timeoutMs).build();

        CloseableHttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(config).setConnectionManager(connectionManager).build();
        return httpclient;
    }
}
