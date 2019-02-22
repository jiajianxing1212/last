package com.maisel.springboot.controller;

import com.maisel.springboot.entity.Student;
import com.maisel.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @aucthor:jjx
 * @Create:2018-12-27 16:57
 */
@Controller
public class TestController {

    @Autowired
    private StudentService studentService;
    @RequestMapping("test")
    public @ResponseBody String test(){


        return "你是不是傻！呵呵！";
    }

    @RequestMapping("/getAll")
    public String getAll(HttpSession session){

        List<Student> list = studentService.getAll();
        session.setAttribute("list",list);
        return "index";
    }
}
