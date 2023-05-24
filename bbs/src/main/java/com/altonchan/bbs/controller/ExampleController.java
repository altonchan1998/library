package com.altonchan.bbs.controller;

import com.altonchan.bbs.feignclient.BmsFeignClient;
import com.altonchan.bbs.model.dto.response.ExampleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @Autowired
    private BmsFeignClient bmsFeignClient;

    @GetMapping("feignClient/example/bms")
    public ExampleResponse getBmsFeignClientExample() {
        return bmsFeignClient.getExample();
    }
}
