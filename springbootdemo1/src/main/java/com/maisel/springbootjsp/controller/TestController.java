package com.maisel.springbootjsp.controller;


import com.maisel.springbootjsp.entity.Student;
import com.maisel.springbootjsp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2018-12-27 16:57
 */
@Controller
public class TestController {

    @Autowired
    private StudentService studentService;
    @RequestMapping("test")
    @ResponseBody
    public String test(){
     return "你是不是傻！呵呵！";
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public String getAll(HttpSession session){
        List<Student> list = studentService.getAll();
        session.setAttribute("list",list);
        return "index";
    }

    @RequestMapping("/getAllStudent")
    @ResponseBody
    public Map getAllStudent(Integer page,Integer rows){
        Map map = studentService.getAllStudent(page, rows);
        return map;
    }

    @RequestMapping("/mutilRemove")
    @ResponseBody
    public boolean mutilRemove(Integer[] ids) {
        try {
            studentService.mutilRemove(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("add")
    @ResponseBody
    public boolean add(Student student) {
        try {
            studentService.add(student);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("update")
    @ResponseBody
    public boolean update(Student student) {
        try {
            studentService.update(student);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("login")
    public String login(Student student,HttpSession session,String code) {
        String code1 = (String) session.getAttribute("code");
        // System.out.println(code);
        // System.out.println(code1);
//        System.out.println(student);
        if (!code1.equalsIgnoreCase(code)) {
            return "redirect:index.jsp";
        }
        Student student1 = studentService.login(student);
        if (student1 != null) {
            return "test";
        }
        return "redirect:index.jsp";
    }
}
