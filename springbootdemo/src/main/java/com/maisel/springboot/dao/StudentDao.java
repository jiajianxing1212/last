package com.maisel.springboot.dao;

import com.maisel.springboot.entity.Student;

import java.util.List;

/**
 * @aucthor:jjx
 * @Create:2018-12-27 20:41
 */
public interface StudentDao {
    List<Student> getAll();
}
