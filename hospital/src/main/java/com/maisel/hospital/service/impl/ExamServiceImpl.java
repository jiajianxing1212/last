package com.maisel.hospital.service.impl;

import com.maisel.hospital.dao.ExamDao;
import com.maisel.hospital.entity.Exam;
import com.maisel.hospital.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-01 18:20
 */
@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamDao examDao;
    @Override
    public Map getAllExam(Integer page, Integer rows, String name) {

        Integer start = (page - 1) * rows;
        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        } else {
            name = null;
        }

        List<Exam> list = examDao.getAllExam(start, rows, name);
        Integer count = examDao.getCount(name);
        Map map = new HashMap();
        map.put("total", count);
        map.put("rows", list);
        return map;
    }



    @Override
    public void updateStatus(Exam exam){
        if (exam.getExamStatus() == 0) {
            exam.setExamStartDate(new Date());
            exam.setExamEndDate(new Date());
            exam.setExamStatus(2);
        } else {
            exam.setExamStartDate(new Date());
            exam.setExamEndDate(new Date());
            exam.setExamStatus(0);
        }
        //System.out.println(exam);
        examDao.updateById(exam);
    }
    @Override
    public Exam getOne(Exam exam) {
        return examDao.getOne(exam);
    }

    @Override
    public int insert(int id) {
        Exam exam = new Exam();
        exam.setDId(id);
        return examDao.insert(exam);
    }
}
