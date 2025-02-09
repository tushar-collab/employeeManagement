package com.ps.assignment.employeeManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.assignment.employeeManagement.config.ExternalApiCaller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/api")
public class ResilentApiController {

    @Autowired
    private ExternalApiCaller externalApiCaller;

    @GetMapping("/circuit-breaker")
    @CircuitBreaker(name = "CircuitBreakerService", fallbackMethod = "fallbackAfterCircuitBreaker")
    public String circuitBreaker() {
        return externalApiCaller.callApi();
    }

    public String fallbackAfterCircuitBreaker(Exception ex) {
        return "External service is not available...";
    }

    @GetMapping("/retry")
    @Retry(name = "retryApi", fallbackMethod = "fallbackAfterRetry")
    public String retryApi() {
        return externalApiCaller.callApi();
    }

    public String fallbackAfterRetry(Exception ex) {
        return "all retries have exhausted";
    }
}
