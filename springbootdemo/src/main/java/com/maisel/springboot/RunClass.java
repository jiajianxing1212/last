package com.maisel.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @aucthor:jjx
 * @Create:2018-12-27 16:59
 */
@SpringBootApplication
@MapperScan("com.maisel.springboot.dao")
public class RunClass extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(RunClass.class, args);
    }
}
