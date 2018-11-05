package com.my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/model/list")
    public String carList() {
        return "pages/admin/list";
    }

    @GetMapping("/model/save")
    public String carSave() {
        return "pages/admin/save";
    }

    @GetMapping("/model/edit")
    public String carEdit() {
        return "pages/admin/edit";
    }
}
