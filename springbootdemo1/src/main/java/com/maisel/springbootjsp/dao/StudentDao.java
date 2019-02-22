package com.maisel.springbootjsp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maisel.springbootjsp.entity.Student;

import java.util.List;

/**
 * @aucthor:jjx
 * @Create:2018-12-27 20:41
 */
public interface StudentDao extends BaseMapper<Student> {
    List<Student> getAll();
}
