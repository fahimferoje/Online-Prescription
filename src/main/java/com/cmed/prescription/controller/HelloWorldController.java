package com.cmed.prescription.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping({ "/hello" })
    public ResponseEntity<?> firstPage() {
        System.out.println("Here");
        return ResponseEntity.ok("hello");
    }
}
