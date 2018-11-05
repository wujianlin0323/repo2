package com.my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/model/list")
    public String carList() {
        return "pages/order/list";
    }
    @GetMapping("/model/save")
    public String carSave() {
        return "pages/order/save";
    }
    @GetMapping("/model/edit")
    public String carEdit() {
        return "pages/order/edit";
    }
}
