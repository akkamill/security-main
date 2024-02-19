package org.pro.security.controllers;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/main")
    public String main() {
        return "main.html";
    }
}