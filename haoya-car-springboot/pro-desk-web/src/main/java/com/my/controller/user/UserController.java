package com.my.controller.user;

import com.my.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TXW55 on 2018.9.5
 */
@RestController    //相当于@Controller和@ResponseBody
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public Map<Object, Object> test() {
        Map<Object, Object> map = new HashMap<>();
        map.put("result", userService.test());
        return map;
    }


}
