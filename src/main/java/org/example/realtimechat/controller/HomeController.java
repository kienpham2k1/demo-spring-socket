package org.example.realtimechat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1")
public class HomeController {
    @GetMapping("/get-home")
    public String getHome(){
        return new String("Hello world!");
    }
}
