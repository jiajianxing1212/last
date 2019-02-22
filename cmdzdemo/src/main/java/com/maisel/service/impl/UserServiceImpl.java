package com.maisel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maisel.dao.UserDao;
import com.maisel.entity.User;
import com.maisel.goeasy.TestEasy;
import com.maisel.service.UserService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @aucthor:jjx
 * @Create:2019-01-03 22:13
 */
@Service
public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    /**
     * IO密集型任务  （常出现于线程中：数据库数据交互、文件上传下载、网络数据传输等等 能够体现多核处理器的优势）
     * CPU密集型任务 (常出现于线程中：复杂算法 能体现CPU版本的优势）
     *
     */
    private static int corePoolSize = Runtime.getRuntime().availableProcessors();

    /**
     * public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,
     * TimeUnit unit,BlockingQueue<Runnable> workQueue)
     * corePoolSize用于指定核心线程数量
     * maximumPoolSize指定最大线程数
     * keepAliveTime和TimeUnit指定线程空闲后的最大存活时间
     * workQueue则是线程池的缓冲队列,还未执行的线程会在队列中等待
     * 监控队列长度，确保队列有界
     * 不当的线程池大小会使得处理速度变慢，稳定性下降，并且导致内存泄露。如果配置的线程过少，则队列会持续变大，消耗过多内存。
     * 而过多的线程又会 由于频繁的上下文切换导致整个系统的速度变缓——殊途而同归。队列的长度至关重要，它必须得是有界的，这样如果线程池不堪重负了它可以暂时拒绝掉新的请求。
     * ExecutorService 默认的实现是一个无界的 LinkedBlockingQueue。
     */
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,
            corePoolSize + 1,
            10l,
            TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(1000));


    @Autowired
    private UserDao userDao;

    public Map getAllCount(){
        final Map map = new HashMap();
        final CountDownLatch countDownLatch = new CountDownLatch(3);
        // 查询性别
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Map map1 = getCountSex();
                map.put("sex", map1);
                // 任务结束，count-1
                countDownLatch.countDown();

            }
        });

        // 查询注册用户时间
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Map map1 = getCountByWeekendOfRegister();
                map.put("week", map1);
                // 任务结束，count-1
                countDownLatch.countDown();
            }
        });

        // 查询用户所在区域
        executor.execute(new Runnable() {
            @Override
            public void run() {
                List<Map<String, Object>> mapList = getCountByArea();
                map.put("province", mapList);
                // 任务结束，count-1
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();

            logger.info("info+123"+logger.getName());
            logger.debug("debug+123"+logger.getName());
            //logger.error("error0"+logger.getName());
            int i = 1 / 0;
        } catch (InterruptedException e) {
            logger.error("error1"+e.toString());
        }
        return map;
    }
    @Override
    public Map getAll(Integer page, Integer rows, String name) {
        if (name != null || "".equals(name)) {
            name = "%" + name + "%";
        }else {
            name = null;
        }
        List<User> list = userDao.getAll(page, rows, name);
        int count = userDao.getCount(name);
        Map map = new HashMap();
        map.put("total", count);
        map.put("rows", list);
        return map;
    }

    @Override
    public void add(User user) {
        userDao.insert(user);
        Map map = getAllCount();
        TestEasy testEasy = new TestEasy();
        testEasy.publish(map);
    }

    @Override
    public void update(User user) {
        userDao.updateById(user);
    }

    @Override
    public void updateStatus(User user) {
        userDao.updateById(user);
    }

    @Override
    public void mutilRemove(User user) {
        user.setUserStatus(0);
        userDao.updateById(user);
    }

    @Override
    public Map getCountSex() {

        Map map = new HashMap();
        // 获取所有男用户的数量
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("sex", "男");
        List<User> users = userDao.selectList(wrapper);
        int size = users.size();
        // 获取所有的女用户数量
        QueryWrapper wrapper1 = new QueryWrapper();
        wrapper1.eq("sex", "女");
        List<User> list = userDao.selectList(wrapper1);
        int size1 = list.size();

        map.put("nan", size);
        map.put("nv", size1);
        return map;
    }

    @Override
    public Map getCountByWeekendOfRegister() {
        int count1 = userDao.getCountByWeekendOfRegister1();
        int count2 = userDao.getCountByWeekendOfRegister2();
        int count3 = userDao.getCountByWeekendOfRegister3();
        Map map = new HashMap();
        map.put("weekOne", count1);
        map.put("weekTwo", count2);
        map.put("weekThree", count3);
        return map;
    }

    @Override
    public List<Map<String,Object>> getCountByArea() {
        List<Map<String, Object>> mapList = userDao.getCountByArea();
        return mapList;
    }


}
