package com.altonchan.bms.controller;

import com.altonchan.bms.model.dto.response.ExampleResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @GetMapping("/openFeign/example")
    public ExampleResponse getOpenFeignExample() {
        return new ExampleResponse("This is GET openFeign example.");
    }

    @GetMapping("/openFeign/example/{pathVariable}")
    public ExampleResponse getOpenFeignExample(@PathVariable String pathVariable) {
        return new ExampleResponse("This is GET openFeign example with pathVariable: " + pathVariable);
    }

}
