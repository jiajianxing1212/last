package com.maisel.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maisel.hospital.dao.LogDao;
import com.maisel.hospital.entity.Log;
import com.maisel.hospital.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-13 18:13
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;
    @Override
    public Map getAll(Integer page, Integer rows, String content) {
        QueryWrapper<Log> wrapper = new QueryWrapper<Log>();
        IPage page1 = new Page(page, rows);
        if (content != null && !"".equals(content)) {
            wrapper.like("content", content);
        }

        IPage<Log> iPage = logDao.selectPage(page1, wrapper);
        List<Log> LogList = iPage.getRecords();
        long count = iPage.getTotal();
        Map map = new HashMap();
        map.put("rows", LogList);
        map.put("total", count);
        return map;
    }
}
