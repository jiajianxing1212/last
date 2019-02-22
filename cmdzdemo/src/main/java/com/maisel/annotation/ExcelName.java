package com.maisel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @aucthor:jjx
 * @Create:2019-01-07 16:21
 */
@Target(ElementType.FIELD)// 只能作用在属性上
@Retention(RetentionPolicy.RUNTIME)// 作用范围时runtime
public @interface ExcelName {
    String name() default "";// 代表可以写入一个值
}





