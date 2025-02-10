package com.ps.assignment.employeeManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalApiCaller {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${employeeManagement.startupConfigs.thirdPartyApi}")
    private String API_URL;

    @Autowired
    public ExternalApiCaller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String callApi() {
        return restTemplate.getForObject(API_URL, String.class);
    }
}
