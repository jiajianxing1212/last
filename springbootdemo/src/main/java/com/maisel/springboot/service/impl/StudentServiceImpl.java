package com.maisel.springboot.service.impl;

import com.maisel.springboot.dao.StudentDao;
import com.maisel.springboot.entity.Student;
import com.maisel.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @aucthor:jjx
 * @Create:2018-12-27 20:42
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Override
    public List<Student> getAll() {
        return studentDao.getAll();
    }
}
