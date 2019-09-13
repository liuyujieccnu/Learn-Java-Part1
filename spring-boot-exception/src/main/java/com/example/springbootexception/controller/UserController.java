package com.example.springbootexception.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @RequestMapping("/add")
    public String add() {
        int num = 10 / 0;
        return "add";
    }

    @RequestMapping("/update")
    public String update(){
        String name = null;
        name = name.toLowerCase();
        return "update";
    }

}


