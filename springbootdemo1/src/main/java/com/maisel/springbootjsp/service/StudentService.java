package com.maisel.springbootjsp.service;
import com.maisel.springbootjsp.entity.Student;
import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2018-12-27 20:41
 */
public interface StudentService {
    List<Student> getAll();
    void mutilRemove(Integer[] ids);

    void add(Student student);

    void update(Student student);

    Map getAllStudent(Integer page, Integer rows);

    Student login(Student student);


}
