package com.maisel;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.maisel.utils.DateConvert;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

/**
 * @aucthor:jjx
 * @Create:2019-01-03 16:03
 */
@SpringBootApplication
@MapperScan("com.maisel.dao")
public class RunApp {
    public static void main(String[] args) {
        SpringApplication.run(RunApp.class, args);
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public DateConvert dateConvert() {
        Set<String> set = new HashSet<String>();
        set.add("yyyy-MM-dd");

        DateConvert dateConvert = new DateConvert();
        dateConvert.setSet(set);

        return dateConvert;
    }

}
