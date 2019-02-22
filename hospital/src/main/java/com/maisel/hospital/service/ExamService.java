package com.maisel.hospital.service;

import com.maisel.hospital.entity.Exam;

import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-01 18:19
 */
public interface ExamService {
    Map getAllExam(Integer page,Integer rows,String name);

    void updateStatus(Exam exam);

    Exam getOne(Exam exam);

    int insert(int id);



}
