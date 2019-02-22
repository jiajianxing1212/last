package com.maisel.hospital.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maisel.hospital.dao.PatientDao;
import com.maisel.hospital.entity.Patient;
import com.maisel.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientDao patientDao;

    @Override
    public Map getByPage(int page, int rows, String name) {
        IPage<Patient> patientPage = new Page<>(page, rows);
        QueryWrapper<Patient> wrapper = new QueryWrapper<>();
        if (name == null) {
            wrapper.eq("isDelete", 0);
        } else {
            wrapper.eq("isDelete", 0).like("name", name);
        }
        IPage<Patient> iPage = patientDao.selectPage(patientPage, wrapper);
        Map map = new HashMap();
        map.put("rows", iPage.getRecords());
        map.put("total", iPage.getTotal());
        return map;
    }

    @Transactional
    @Override
    public int insert(Patient patient) {
        return patientDao.insert(patient);
    }

    @Transactional
    @Override
    public int update(Patient patient) {
        return patientDao.updateById(patient);
    }

    @Transactional
    @Override
    public int deleteSome(int[] ids) {
        return patientDao.deleteSome(ids);
    }
}
