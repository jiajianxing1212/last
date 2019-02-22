package com.maisel.springboot.test;

import com.maisel.springboot.RunClass;
import com.maisel.springboot.entity.Person;
import com.maisel.springboot.entity.Student;
import com.maisel.springboot.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @aucthor:jjx
 * @Create:2018-12-27 20:53
 */


@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunClass.class)
public class TestStudent {

    @Autowired
    private StudentService studentService;

    @Autowired
    private Person person;
    @Test
    public void test1(){

        List<Student> list = studentService.getAll();
        for (Student student : list) {
            System.out.println(student);
        }
    }
    @Test
    public void test2(){
        System.out.println(person);
    }



}
