package com.altonchan.bbs.controller;

import com.altonchan.bbs.feignclient.BmsFeignClient;
import com.altonchan.bbs.model.dto.response.ExampleResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class ExampleController {

    @Autowired
    private BmsFeignClient bmsFeignClient;

    @GetMapping("feignClient/bms/example")
    @Timed(value = "getBmsFeignClientExample.time", description = "Time taken to getBmsFeignClientExample")
    public ExampleResponse getBmsFeignClientExample() {
        return bmsFeignClient.getExample();
    }

    @GetMapping("feignClient/bms/example/{pathVariable}")
    public ExampleResponse getBmsFeignClientExample(@PathVariable String pathVariable) {
        return bmsFeignClient.getExampleWithPathVariable(pathVariable);
    }

    @GetMapping("/openFeign/example/resilience/circuitBreaker")
    @CircuitBreaker(name = "getCircuitBreakerExample", fallbackMethod = "getCircuitBreakerExampleFallback")
    public ExampleResponse getCircuitBreakerExample() {
        return bmsFeignClient.getExampleWithPathVariable("resilience circuitBreaker");
    }

    // CircuitBreaker FallBack function fires if exceptions thrown from origin method
    // Must contain same output and param type + throwable as origin method
    private ExampleResponse getCircuitBreakerExampleFallback(Exception e) {
        return new ExampleResponse("resilience circuitBreaker fallback");
    }

    @GetMapping("/openFeign/example/resilience/retry")
    @Retry(name = "getRetryExample", fallbackMethod = "getRetryExampleFallback")
    public ExampleResponse getRetryExample() {
        log.info("Invoke getRetryExample");
        return bmsFeignClient.getExampleWithPathVariable("resilience retry");
    }

    // Retry FallBack function fires if max no. of call attempts is reached
    // Must contain same output and param type + throwable as origin method
    private ExampleResponse getRetryExampleFallback(Exception e) {
        return new ExampleResponse("resilience retry fallback");
    }

    @GetMapping("/openFeign/example/resilience/rateLimiter")
    @RateLimiter(name = "getRateLimiterExample", fallbackMethod = "getRateLimiterExampleFallback")
    public ExampleResponse getRateLimiterExample() {
        return bmsFeignClient.getExampleWithPathVariable("resilience rateLimiter");
    }

    private ExampleResponse getRateLimiterExampleFallback(Exception e) {
        return new ExampleResponse("resilience rateLimiter  fallback");
    }

    @GetMapping("/openFeign/example/resilience/bulkHead")
    @RateLimiter(name = "getBulkHeadExample", fallbackMethod = "getBulkHeadExampleFallback")
    public ExampleResponse getBulkHeadExample() {
        return bmsFeignClient.getExampleWithPathVariable("resilience bulkHead");
    }

    private ExampleResponse getBulkHeadExampleFallback(Exception e) {
        return new ExampleResponse("resilience bulkHead fallback");
    }
}
