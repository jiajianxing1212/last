package com.maisel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @aucthor:jjx
 * @Create:2019-01-11 16:31
 * 注解在属性和方法上  标记	当前方法的作用  用于日志记录
 */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface  ServiceLog {
    String value() default "";
}
