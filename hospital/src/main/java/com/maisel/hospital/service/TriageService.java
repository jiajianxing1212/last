package com.maisel.hospital.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.maisel.hospital.entity.Triage;
import com.maisel.hospital.entity.dto.TriageDTO;

import java.util.Map;


public interface TriageService {
    Map getByPage(int page, int rows);

    int insert(Triage triage);

    Map getByDid(int page, int rows, int did);

    int call(int id);

    TriageDTO getById(int id);

    TriageDTO getByPid(int id);

    int updateById(Triage triage);

}
