package com.maisel.controller;

import com.maisel.annotation.ServiceLog;
import com.maisel.entity.Admin;
import com.maisel.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @aucthor:jjx
 * @Create:2019-01-03 16:00
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    @ServiceLog("登陆")
    public String login(Admin admin, HttpSession session) {
        Admin admin1 = adminService.login(admin);
        session.setAttribute("admin",admin1);
        if (admin1 == null) {
            return "redirect:/login.jsp";
        }
        return "redirect:/main/main.jsp";
    }

    @RequestMapping("/exit")
    public String exit(HttpSession session) {
        session.removeAttribute("admin");
        return "redirect:/login.jsp";
    }
}
