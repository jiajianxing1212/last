package com.maisel.aspect;


import com.maisel.annotation.ServiceLog;
import com.maisel.dao.AdminDao;
import com.maisel.dao.AdminLogDao;
import com.maisel.entity.Admin;
import com.maisel.entity.AdminLog;
import com.maisel.utils.IPKit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAspect {
    @Autowired
    private AdminLogDao adminLogDao;

    private final static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 切到方法上
     */
    @Pointcut("@annotation(com.maisel.annotation.ServiceLog)")
    public void testLog(){}

    @Around("testLog()")
    public Object testLogAdd(ProceedingJoinPoint proceedingJoinPoint){
        AdminLog adminLog = new AdminLog();

//        1.获取正在执行的方法 的方法对象
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
//        2.通过方法对象获取方法上的注解对象
        ServiceLog annotation = method.getAnnotation(ServiceLog.class);
//        3.获取注解中的值
        String value = annotation.value();

//        1.获取request
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
//        2.通过request和工具类得到ip
        String ip = IPKit.getIpAddrByRequest(request);


        HttpSession session = null;
        Admin admin = null;
        try {
            Object proceed = proceedingJoinPoint.proceed();
            //        3.通过request可以得到session
            session = request.getSession();
            //        4.通过session可以得到session中的信息 id 和 用户名
            admin = (Admin) session.getAttribute("admin");
//            封装对象
            adminLog.setLogAction(value);
            adminLog.setLogDate(new Date());
            adminLog.setLogIp(ip);
            adminLog.setAdminId(admin.getId());
            adminLog.setAdminUsername(admin.getUsername());
            adminLog.setLogResult("成功");
            //添加数据库
            adminLogDao.insert(adminLog);
            logger.info("操作成功:"+adminLog);
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logger.error("操作失败");
            return null;
        }

    }
}
