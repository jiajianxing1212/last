package com.maisel.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maisel.hospital.dao.DrugDao;
import com.maisel.hospital.entity.Drug;
import com.maisel.hospital.service.DrugService;
import com.maisel.hospital.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DrugServiceImpl implements DrugService {

    @Autowired
    private DrugDao drugDao;
    @Override
    public Map getAllDrug(Integer page, Integer rows, String name) {

        // System.out.println(DrugDao);
        QueryWrapper<Drug> wrapper = new QueryWrapper<Drug>();
        IPage page1 = new Page(page,rows);
        if (name != null && !"".equals(name)) {
            wrapper.like("name", name);
        }
        IPage<Drug> iPage = drugDao.selectPage(page1, wrapper);
        List<Drug> drugList = iPage.getRecords();
        long count = iPage.getTotal();
        Map map = new HashMap();
        map.put("rows", drugList);
        map.put("total", count);
        return map;
    }

    @Override
    public Drug getOne(Integer id) {
        Drug drug = drugDao.selectById(id);
        return drug;
    }

    @Override
    public void add(Drug drug) {

        drugDao.insert(drug);
    }

    @Override
    public void mutilRemove(Integer[] ids) {
        List<Integer> list = Arrays.asList(ids);
        drugDao.deleteBatchIds(list);
    }


    @Override
    public void update(Drug drug) {

        drugDao.updateById(drug);
    }

    @Override
    public void falseRemove(Integer[] ids) {
        drugDao.falseRemove(ids);
    }

    @Override
    public void updateStatus(Integer id, Integer saleStatus) {
        if (saleStatus == 1) {
            saleStatus = 2;
        } else {
            saleStatus = 1;
        }
       drugDao.updateStatus(id,saleStatus);
    }

    /*@Override
    public void updateStatus(Drug drug) {
        QueryWrapper<Drug> wrapper = new QueryWrapper<Drug>();
        Drug drug1 = drugDao.selectOne(wrapper);
        if (drug1.getSaleStatus() == 1) {
            drug1.setSaleStatus(0);
        }else {
            drug1.setSaleStatus(1);
        }
        drugDao.updateById(drug1);
    }*/



}
