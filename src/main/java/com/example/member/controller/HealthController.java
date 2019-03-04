package com.example.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HealthController {
    @RequestMapping("/health")
    public ResponseEntity getStatus() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
