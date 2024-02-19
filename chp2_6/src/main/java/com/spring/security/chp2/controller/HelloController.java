package com.spring.security.chp2.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }

    @PostMapping("/hello")
    public String save(@RequestBody String body) {
        return body;
    }

    @PutMapping("/hello/{id}")
    public String update(@PathVariable String id, @RequestBody String body) {
        return body;
    }

    @DeleteMapping("/hello/{id}")
    public void delete(@PathVariable String id) {
        return;
    }

}

