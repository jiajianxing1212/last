package com.maisel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maisel.dao.LessonDao;
import com.maisel.entity.Lesson;
import com.maisel.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-05 17:05
 */
@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private LessonDao lessonDao;
    @Override
    public Map getAll(Integer page, Integer rows, String name) {
        QueryWrapper<Lesson> wrapper = new QueryWrapper<Lesson>();
         if (name != null && !"".equals(name)) {
             wrapper.like("lesson_name", name);
        }
        Page<Lesson> lessonPage = new Page<Lesson>(page, rows);
        IPage<Lesson> iPage = lessonDao.selectPage(lessonPage, wrapper);
        List<Lesson> list = iPage.getRecords();
        long total = iPage.getTotal();
        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", list);
        return map;
    }

    @Override
    public void add(Lesson lesson) {
        lessonDao.insert(lesson);
    }

    @Override
    public void update(Lesson lesson) {

        lessonDao.updateById(lesson);
    }

    @Override
    public void multiRemove(Integer[] ids) {

        List<Integer> list = Arrays.asList(ids);
        Lesson lesson = new Lesson();
        lesson.setLessonStatus(0);
        UpdateWrapper<Lesson> wrapper = new UpdateWrapper<Lesson>();
        wrapper.in("lesson_id", list);
        lessonDao.update(lesson, wrapper);
    }
}
