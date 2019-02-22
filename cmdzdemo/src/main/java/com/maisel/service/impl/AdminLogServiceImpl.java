package com.maisel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maisel.dao.AdminLogDao;
import com.maisel.entity.AdminLog;
import com.maisel.service.AdminLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-11 20:45
 */
@Service
public class AdminLogServiceImpl implements AdminLogService {
    @Autowired
    private AdminLogDao adminLogDao;
    @Override
    public Map getAll(Integer page,Integer rows,String action) {
        QueryWrapper<AdminLog> wrapper = new QueryWrapper<AdminLog>();
        if (action != null && "".equals(action)) {
            wrapper.like("log_action", action);
        }
        Page<AdminLog> logPage = new Page<AdminLog>(page, rows);
        IPage<AdminLog> iPage = adminLogDao.selectPage(logPage, wrapper);
        List<AdminLog> logList = iPage.getRecords();
        long total = iPage.getTotal();
        Map map = new HashMap();
        map.put("rows", logList);
        map.put("total", total);
        return map;
    }

    @Override
    public void multiPart(Integer[] ids) {
        List<Integer> list = Arrays.asList(ids);
        adminLogDao.deleteBatchIds(list);
    }
    @Override
    public List<AdminLog> getAll(){
        List<AdminLog> logList = adminLogDao.selectList(null);
        return logList;
    }
}
