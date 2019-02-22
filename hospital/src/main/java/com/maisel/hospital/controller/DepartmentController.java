package com.maisel.hospital.controller;

import com.maisel.hospital.annotation.ServiceLog;
import com.maisel.hospital.entity.Department;
import com.maisel.hospital.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2018-12-30 21:25
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @RequestMapping("/getAllDepartment")
    @ResponseBody
    public Map getAllDepartment(Integer page,Integer rows,String name) {

        Map map = departmentService.getAllTDepartment(page, rows, name);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/add")
    @ResponseBody
    public boolean add(Department department) {

        try {
            departmentService.add(department);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public boolean update(Department department) {
        try {
            departmentService.update(department);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @RequestMapping("/mutilRemove")
    @ResponseBody
    public boolean mutilRemove(Integer[] ids) {
        try {
            departmentService.mutilRemove(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @RequestMapping("/falseRemove")
    @ResponseBody
    public boolean falseRemove(Integer[] ids) {
        try {
            departmentService.falseRemove(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // @ServiceLog("获取所有科室")
    @RequestMapping("/getAll")
    @ResponseBody
    public List getAll() {
        return departmentService.getAll();
    }

}
