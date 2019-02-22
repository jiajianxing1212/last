package com.maisel.hospital.controller;

import com.maisel.hospital.annotation.ServiceLog;
import com.maisel.hospital.entity.Exam;
import com.maisel.hospital.service.DrugService;
import com.maisel.hospital.service.ExamService;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-01 19:06
 */
@Controller
@RequestMapping("/exam")
public class ExamController {
    @Autowired
    private ExamService examService;
    @Autowired
    private DrugService drugService;

    @RequestMapping("/getAllExam")
    @ResponseBody
    public Map getAllExam(Integer page, Integer rows, String name) {
        return examService.getAllExam(page, rows, name);
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public boolean updateStatus(Exam exam) {
        try {
            examService.updateStatus(exam);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/exam")
    @ResponseBody
    public Exam exam(Exam exam){
        return examService.getOne(exam);
    }

    @ServiceLog("添加审核")
    @RequestMapping("/submit")
    public int submit(int id) {
        return examService.insert(id);
    }
}
