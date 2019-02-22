package com.maisel.service;

import com.maisel.entity.Lesson;

import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-05 17:04
 */
public interface LessonService {
    Map getAll(Integer page, Integer rows, String name);

    void add(Lesson lesson);

    void update(Lesson lesson);

    void multiRemove(Integer[] ids);
}
