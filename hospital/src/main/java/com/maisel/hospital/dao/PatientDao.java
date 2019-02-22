package com.maisel.hospital.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maisel.hospital.entity.Patient;


public interface PatientDao extends BaseMapper<Patient> {
    int deleteSome(int[] ids);
}
