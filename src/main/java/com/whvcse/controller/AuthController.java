package com.whvcse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 彭高浛
 * 2020/6/5/13:47
 */
@Controller

public class AuthController {
    @RequestMapping("/admin/add")
    public String addAdmin() {
        return "adminAdd";
    }

}
