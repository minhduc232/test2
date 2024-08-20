package com.example.baitest2.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/hello")
    public ResponseEntity hello()
    {
        return ResponseEntity.ok("Hello, welcome to spring security!");
    }
}
