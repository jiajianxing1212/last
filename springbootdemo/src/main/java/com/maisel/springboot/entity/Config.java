package com.maisel.springboot.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @aucthor:jjx
 * @Create:2018-12-28 15:01
 */
@Configuration
public class Config {
    @Bean
    public Person getPerson(){

        return new Person("xiaoyuanyuan");
    }
}
