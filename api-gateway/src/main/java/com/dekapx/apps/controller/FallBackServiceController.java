package com.dekapx.apps.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackServiceController {
    @GetMapping("/userServiceFallBack")
    public String userServiceFallBack() {
        return "User Service not available";
    }
}
