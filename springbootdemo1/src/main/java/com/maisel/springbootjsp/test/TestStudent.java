package com.maisel.springbootjsp.test;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maisel.springbootjsp.Springbootdemo1Application;

import com.maisel.springbootjsp.dao.StudentDao;
import com.maisel.springbootjsp.entity.Student;
import com.maisel.springbootjsp.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Wrapper;
import java.util.List;

/**
 * @aucthor:jjx
 * @Create:2018-12-27 20:53
 */


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Springbootdemo1Application.class)
public class TestStudent {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentDao studentDao;

    @Test
    public void test1(){

        List<Student> list = studentService.getAll();
        for (Student student : list) {
            System.out.println(student);
        }
    }
    @Test
    public void test2(){
        Page<Student> page = new Page<Student>(1,3);

        IPage<Student> studentIPage = studentDao.selectPage(page, null);
        List<Student> studentList = studentIPage.getRecords();
        for (Student student : studentList) {
            System.out.println(student);
        }


    }


}
