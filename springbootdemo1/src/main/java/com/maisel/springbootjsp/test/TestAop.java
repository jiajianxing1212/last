package com.maisel.springbootjsp.test;

/**
 * @aucthor:jjx
 * @Create:2018-12-29 14:07
 */

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * 3.声明这个类为切面类
 */
@Aspect
@Configuration
public class TestAop {
    /**
     *   * 2.设置切入点 添加方法上 被添加的方法不要写任何实现 只是用来让@Pointcut注解依附
     *   * 切入点的名字 是方法名()
     *   *
     *   
     */
    @Pointcut(value = "execution(* com.maisel.springbootjsp.service.*.*(..))")
    public void testPoint() {
    }

    /**
     *   * 1.创建了一个后置增强
     *  
     *   通知对应的注解 加载方法上
     * 1. @Before
     * 2. @After
     * 3. @Around
     * 4. @Throws
     *   
     */
    @After(value = "testPoint()")
    public void testAfter() {
        System.out.println("执行了后置增强----------");
    }

    @Before(value = "testPoint()")
    public void testBefore() {
        System.out.println("执行了前置增强-----");
    }
}