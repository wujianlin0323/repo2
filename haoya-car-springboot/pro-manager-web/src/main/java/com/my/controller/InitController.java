package com.my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/model")
public class InitController {


    /**
     * 跳转至后台首页
     * @return
     */
    @GetMapping("/index")
    public String test() {
        return "index";
    }
    @GetMapping("/left")
    public String left() {
        return "left";
    }
    @GetMapping("/top")
    public String top() {
        return "top";
    }
    @GetMapping("/main")
    public String main() {
        return "main";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
