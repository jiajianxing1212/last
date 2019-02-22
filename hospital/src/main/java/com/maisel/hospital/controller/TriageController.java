package com.maisel.hospital.controller;



import com.maisel.hospital.annotation.ServiceLog;
import com.maisel.hospital.entity.Triage;
import com.maisel.hospital.entity.User;
import com.maisel.hospital.entity.dto.TriageDTO;
import com.maisel.hospital.service.TriageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;


@RestController
@RequestMapping("/triage")
public class TriageController {
    @Autowired
    private TriageService triageService;

    @ServiceLog("分页展示科室")
    @RequestMapping("/getByPage")
    public Map getByPage(int page, int rows){
        return triageService.getByPage(page, rows);
    }

    @ServiceLog("挂号")
    @RequestMapping("/insert")
    public int insert(Triage triage, HttpSession session){
        User user = (User) session.getAttribute("user");
        triage.setUid(user.getId());
        return triageService.insert(triage);
    }

    @ServiceLog("展示科室病人")
    @RequestMapping("/getByDid")
    public Map getByDid(int page,int rows,int did){
        System.out.println(triageService.getByDid(page, rows, did));
        return triageService.getByDid(page, rows, did);
    }

    @ServiceLog("叫号")
    @RequestMapping("/call")
    public int call(int id){
        return triageService.call(id);
    }

    @ServiceLog("根据id查询分诊记录信息")
    @RequestMapping("/getById")
    public TriageDTO getById(int id, HttpSession session){
        User user = (User) session.getAttribute("user");
        TriageDTO triageDTO = triageService.getById(id);
        triageDTO.setUid(user.getId());
        triageDTO.setUname(user.getRealname());
        return triageDTO;
    }

    @ServiceLog("诊断完成")
    @RequestMapping("/update")
    public int update(Triage triage){
        return triageService.updateById(triage);
    }
}

