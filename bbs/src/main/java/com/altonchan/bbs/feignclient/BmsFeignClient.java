package com.altonchan.bbs.feignclient;

import com.altonchan.bbs.model.dto.response.ExampleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(value = "bms") // The application name registered inside Eureka server
public interface BmsFeignClient {
    // For the very first time request to bms, it fetches info details of bms from eureka and cache it locally

    @GetMapping(value = "openFeign/example", consumes = "application/json")
    ExampleResponse getExample();

    @GetMapping(value = "openFeign/example/{pathVariableExample}", consumes = "application/json")
    ExampleResponse getExampleWithPathVariable(@PathVariable String pathVariableExample);
}
