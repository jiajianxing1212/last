package com.maisel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maisel.dao.CounterDao;
import com.maisel.entity.Counter;
import com.maisel.entity.Lesson;
import com.maisel.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-05 17:42
 */
@Service
public class CounterServiceImpl implements CounterService {
    @Autowired
    private CounterDao counterDao;
    @Override
    public Map getAll(Integer page, Integer rows, String name) {
        QueryWrapper<Counter> wrapper = new QueryWrapper<Counter>();
        if (name != null && !"".equals(name)) {
            wrapper.like("lesson_name", name);
        }
        Page<Counter> counterPage = new Page<Counter>(page, rows);
        IPage<Counter> iPage = counterDao.selectPage(counterPage, wrapper);
        List<Counter> list = iPage.getRecords();
        long total = iPage.getTotal();
        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", list);
        return map;
    }

    @Override
    public void add(Counter counter) {
        counterDao.insert(counter);
    }

    @Override
    public void update(Counter counter) {
        counterDao.updateById(counter);
    }

    @Override
    public void multiRemove(Counter counter) {
        counter.setCounterStatus(0);
        counterDao.updateById(counter);
    }
}
