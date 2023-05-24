package com.altonchan.bbs.feignclient;

import com.altonchan.bbs.model.dto.response.ExampleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient("bms") // The application name registered inside Eureka server
public interface BmsFeignClient {

    // For the very first time request to bms, it fetches info details of bms from eureka and cache it locally

    @RequestMapping(method = RequestMethod.GET, value = "openFeign/example", consumes = "application/json")
    ExampleResponse getExample();

}
