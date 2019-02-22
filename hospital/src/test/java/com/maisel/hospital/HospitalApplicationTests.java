package com.maisel.hospital;
import java.util.ArrayList;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maisel.hospital.dao.DepartmentDao;
import com.maisel.hospital.dao.DrugDao;
import com.maisel.hospital.dao.ExamDao;
import com.maisel.hospital.dao.MyFileDao;
import com.maisel.hospital.entity.Drug;
import com.maisel.hospital.entity.Exam;
import com.maisel.hospital.entity.MyFile;
import com.maisel.hospital.entity.User;
import com.maisel.hospital.entity.dto.TriageDTO;
import com.maisel.hospital.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HospitalApplication.class)
public class HospitalApplicationTests {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private UserService userService;
    @Autowired
    private ExamService examService;
    @Autowired
    private DrugDao drugDao;
    @Autowired
    private MyFileService myFileService;
    @Autowired
    private MyFileDao myFileDao;
    @Autowired
    private TriageService triageService;
    @Test
    public void contextLoads() {

        Map map = departmentService.getAllTDepartment(1, 3, "精神病科");
        System.out.println(map);
       /* List<TDepartment> list = departmentDao.selectList(null);
        for (TDepartment tDepartment : list) {
            System.out.println(tDepartment);
        }*/
    }

    @Test
    public void testLogin() {
        User user = new User();
        user.setUsername("123");
        user.setPassword("123456");
        User user1 = userService.login(user);
        System.out.println("user1 = " + user1);
    }

    @Test
    public void testDept() {
        Integer[] a = {1};
        departmentService.falseRemove(a);
    }
    @Test
    public void testExam() {

        Map map = examService.getAllExam(1, 3, "");
        System.out.println(map);
    }

    @Test
    public void testPatient() {
        List<TriageDTO> list = new ArrayList();
        TriageDTO triageDTO = triageService.getByPid(2);
        list.add(triageDTO);
        System.out.println(list);
    }

    @Test
    public void testExam1() {
        Exam exam = new Exam();
        exam.setDrug(new Drug());
        exam.setId(2);
        exam.setExamStatus(0);
        exam.setExamAdvice("呵呵");
        exam.setExamStartDate(new Date());
        exam.setExamEndDate(new Date());
        // exam.setDId(1);
        Exam one = examService.getOne(exam);
        System.out.println(one);
    }
    @Test
    public void testdrug() {

        Drug drug = drugDao.selectById(101);
        System.out.println(drug);

    }

    @Test
    public void testFile() {

        Map map = myFileService.getByPage(1, 5, null);

        System.out.println(map);
    }

}

