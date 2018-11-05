package com.my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/car")
public class CarController {
    @GetMapping("/model/list")
    public String carList() {
        return "pages/car/list";
    }
    @GetMapping("/model/save")
    public String carSave() {
        return "pages/car/save";
    }
    @GetMapping("/model/edit")
    public String carEdit() {
        return "pages/car/edit";
    }
}
