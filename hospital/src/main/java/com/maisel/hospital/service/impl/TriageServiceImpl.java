package com.maisel.hospital.service.impl;

import com.google.gson.Gson;
import com.maisel.hospital.dao.TriageDao;
import com.maisel.hospital.entity.Triage;
import com.maisel.hospital.entity.dto.TriageDTO;
import com.maisel.hospital.service.TriageService;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@Service
public class TriageServiceImpl implements TriageService {
    @Autowired
    private TriageDao triageDao;

    @Override
    public Map getByPage(int page, int rows) {
        Map map = new HashMap();
        int start = (page - 1) * rows;
        int end = page * rows;
        map.put("rows",triageDao.getByPage(start,end));
        map.put("total",triageDao.getByCount());
        return map;
    }

    @Transactional
    @Override
    public int insert(Triage triage) {
        int i = triageDao.insert(triage);

        Gson gson = new Gson();
        String json = gson.toJson("用户挂号");
        GoEasy goEasy = new GoEasy("rest-hangzhou.goeasy.io",
                "BC-1f689cc0a7c44600826000d770d5c605");
        goEasy.publish("cmfzChannel",json);
        return i;
    }

    @Override
    public Map getByDid(int page,int rows,int did) {
        Map map = new HashMap();
        int start = (page - 1) * rows;
        int end = page * rows;
        map.put("rows",triageDao.getByDid(start,end,did));
        map.put("total",triageDao.getByDidCount(did));
        return map;
    }

    @Transactional
    @Override
    public int call(int id) {
        return triageDao.call(id);
    }

    @Override
    public TriageDTO getById(int id) {
        return triageDao.getById(id);
    }

    @Override
    public TriageDTO getByPid(int id) {
        return triageDao.getByPid(id);
    }

    @Transactional
    @Override
    public int updateById(Triage triage) {
        // 未诊断是0
        // 诊断中是1

        // 已诊断将状态修改为2
        triage.setStatus(2);
        return triageDao.updateById(triage);
    }
}
