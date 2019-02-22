package com.maisel.controller;

import com.maisel.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @aucthor:jjx
 * @Create:2019-02-18 20:42
 */

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/test")
    public void test() {
        System.out.println(11111);
    }
}
