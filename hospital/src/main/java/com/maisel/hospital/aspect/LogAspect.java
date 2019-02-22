package com.maisel.hospital.aspect;



import com.maisel.hospital.dao.LogDao;
import com.maisel.hospital.annotation.ServiceLog;
import com.maisel.hospital.entity.Log;
import com.maisel.hospital.entity.User;
import com.maisel.hospital.util.IPKit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
    private LogDao logDao;

    private final static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 切到方法上
     */
    @Pointcut("@annotation(com.maisel.hospital.annotation.ServiceLog)")
    public void testLog(){}

    @Around("testLog()")
    public Object testLogAdd(ProceedingJoinPoint proceedingJoinPoint){
        Log log = new Log();

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
        User user = null;
        try {
            Object proceed = proceedingJoinPoint.proceed();
            //        3.通过request可以得到session
            session = request.getSession();
            //        4.通过session可以得到session中的信息 id 和 用户名
             user = (User) session.getAttribute("user");
//            封装对象
            log.setContent(value);
            log.setId(user.getId());
            log.setLogDate(new Date());
            log.setType("登陆");
            log.setOprater(user.getUsername());

            //添加数据库
            logDao.insert(log);
            logger.info("操作成功:"+log);
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logger.error("操作失败");
            return null;
        }

    }
}
