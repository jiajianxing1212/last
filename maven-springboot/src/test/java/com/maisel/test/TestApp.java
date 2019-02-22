package com.maisel.test;

import com.maisel.AppRun;
import com.maisel.entity.Admin;
import com.maisel.service.AdminService;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @aucthor:jjx
 * @Create:2019-02-19 19:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApp {

    @Autowired
    private AdminService adminService;
    @Test
    public void test() {
        List<Admin> all = adminService.getAll();
        System.out.println(all);
    }
}
