package com.maisel.springbootjsp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maisel.springbootjsp.dao.StudentDao;
import com.maisel.springbootjsp.entity.Student;
import com.maisel.springbootjsp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        System.out.println(studentDao);

        List<Student> list = studentDao.getAll();
        return list;
    }

    @Override
    public void mutilRemove(Integer[] ids) {
        List<Integer> list = Arrays.asList(ids);
        studentDao.deleteBatchIds(list);
    }

    @Override
    public void add(Student student) {
        studentDao.insert(student);
    }

    @Override
    public void update(Student student) {
        studentDao.updateById(student);
    }

    @Override
    public Map getAllStudent(Integer page, Integer rows) {
        Page<Student> page1 = new Page<Student>(page,rows);
        Integer count = studentDao.selectCount(null);
        IPage<Student> iPage = studentDao.selectPage(page1, null);
        List<Student> list = iPage.getRecords();
        Map map = new HashMap();
        map.put("rows", list);
        map.put("total", count);
        return map;
    }

    @Override
    public Student login(Student student) {
        QueryWrapper<Student> wrapper = new QueryWrapper<Student>();
        // eq是精确查询，like是模糊匹配
        wrapper.eq("name", student.getName()).eq("id", student.getId());
        Student student1 = studentDao.selectOne(wrapper);
        return student1;
    }
}
