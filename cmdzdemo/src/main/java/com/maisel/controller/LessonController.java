package com.maisel.controller;

import com.maisel.entity.Lesson;
import com.maisel.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-05 17:10
 */
@Controller
@RequestMapping("/lesson")
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @RequestMapping("/getAll")
    @ResponseBody
    public Map getAll(Integer page, Integer rows, String lessonName) {
        return lessonService.getAll(page, rows, lessonName);
    }

    @RequestMapping("/add")
    @ResponseBody
    public boolean add(Lesson lesson) {
        try {
            lessonService.add(lesson);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public boolean update(Lesson lesson) {
        try {
            lessonService.update(lesson);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/multiRemove")
    @ResponseBody
    public boolean multiRemove(Integer[] ids) {

        try {
            lessonService.multiRemove(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
