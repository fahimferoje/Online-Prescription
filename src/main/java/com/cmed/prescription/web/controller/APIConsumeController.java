package com.cmed.prescription.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class APIConsumeController {

    @GetMapping("/api_consume")
    public String method() {
       return "api-consume";
    }

}