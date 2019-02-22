package com.maisel.hospital.controller;

import com.maisel.hospital.entity.Drug;
import com.maisel.hospital.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-01 17:21
 */
@Controller
@RequestMapping("/drug")
public class DrugController {
    @Autowired
    private DrugService drugService;

    @ResponseBody
    @RequestMapping("/getAllDrug")
    public Map getAllDrug(Integer page, Integer rows, String name) {
        Map map = drugService.getAllDrug(page, rows, name);
        return map;
    }


    @RequestMapping("/add")
    @ResponseBody
    public boolean add(Drug drug) {

        try {
            drugService.add(drug);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public boolean update(Drug drug) {
        try {
            drugService.update(drug);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/getOne")
    @ResponseBody
    public Drug getOne(Integer id) {
        Drug drug = drugService.getOne(id);
        return drug;
    }


    @RequestMapping("/mutilRemove")
    @ResponseBody
    public boolean mutilRemove(Integer[] ids) {
        try {
            drugService.mutilRemove(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/updateStatus")
    public String updateStatus(Integer id, Integer saleStatus) {
        System.out.println(saleStatus);
        System.out.println(id);
        drugService.updateStatus(id, saleStatus);
        return "redirect:/drug.jsp";
    }
}
