package com.cmed.prescription.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class APIConsumeController {

    @GetMapping("/api-consume")
    public String exampleAPIConsumption() {
       return "api-consume";
    }

}
