package com.maisel.hospital.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maisel.hospital.entity.Exam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @aucthor:jjx
 * @Create:2019-01-01 17:38
 */
public interface ExamDao extends BaseMapper<Exam> {

    List<Exam> getAllExam(@Param("start")Integer start,@Param("pageSize")Integer pageSize,@Param("name")String name);

    void updateStatus(@Param("examStatus")Integer examStatus,@Param("id")Integer id);

    Integer getCount(@Param("name")String name);

    Exam getOne(Exam exam);
}
