package com.altonchan.bms.controller;

import com.altonchan.bms.model.dto.response.ExampleResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @GetMapping("/openFeign/example")
    public ExampleResponse getOpenFeignExample() {
        return new ExampleResponse("This is GET openFeign example.");
    }
}
