package com.example.member.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HealthController {
    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public ResponseEntity getStatus() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
