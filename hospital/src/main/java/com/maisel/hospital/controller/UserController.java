package com.maisel.hospital.controller;

import com.maisel.hospital.annotation.ServiceLog;
import com.maisel.hospital.entity.User;
import com.maisel.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @aucthor:jjx
 * @Create:2018-12-30 20:56
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ServiceLog("登陆")
    public String login(User user, HttpSession session) {

        User u = userService.login(user);
        session.setAttribute("user", u);
        if (u == null) {
            return "redirect:/index.jsp";
        }
        return "layout";
    }
    @RequestMapping("/register")
    public String register(User user) {
        userService.register(user);
        return "navIndex";

    }
}
