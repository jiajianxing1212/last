package com.maisel.hospital;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.maisel.hospital.util.DateConvert;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@MapperScan("com.maisel.hospital.dao")
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
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

