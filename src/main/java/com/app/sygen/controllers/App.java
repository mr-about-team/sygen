package com.app.sygen.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class App {
    @GetMapping("/app")
    public String viewHomePage(){
        return "examen/Index";
    }
}