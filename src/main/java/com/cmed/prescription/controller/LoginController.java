package com.cmed.prescription.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String viewLogin2() {
        return "login";
    }
}
