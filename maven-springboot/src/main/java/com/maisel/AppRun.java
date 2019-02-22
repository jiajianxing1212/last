package com.maisel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @aucthor:jjx
 * @Create:2019-02-19 19:46
 */
@SpringBootApplication
@MapperScan("com.maisel.dao")
public class AppRun {
    public static void main(String[] args) {
        SpringApplication.run(AppRun.class, args);
    }
}
