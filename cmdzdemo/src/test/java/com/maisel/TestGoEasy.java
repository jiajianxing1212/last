package com.maisel;

import com.google.gson.Gson;
import com.maisel.dao.UserDao;
import com.maisel.entity.User;
import com.maisel.service.UserService;
import com.maisel.utils.PlaceUtil;
import io.goeasy.GoEasy;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @aucthor:jjx
 * @Create:2019-01-09 17:55
 */
public class TestGoEasy extends TestApp {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;

    @Test
    public void test1() {
        // userDao.insert(PlaceUtil.getUser());
        Map map = userService.getAllCount();
        Gson gson = new Gson();
        String json = gson.toJson(map);
        GoEasy goEasy = new GoEasy("rest-hangzhou.goeasy.io",
                "BC-1f689cc0a7c44600826000d770d5c605");
        goEasy.publish("cmfzChannel", json);
    }

    @Test
    public void test2() {
        User user = PlaceUtil.getUser();
        user.setCreateDate(new Date());
        user.setUserProvince("河南");
        userService.add(user);
    }

    @Test
    public void test3() throws Exception {
        User user = PlaceUtil.getUser();
        user.setName("燕十元");
        Class<? extends User> aClass = user.getClass();
        Object getName = aClass.getDeclaredMethod("getName",null).invoke(user, null);
        System.out.println(getName);
    }
    @Test
    public void test4() {

        Map map = userService.getAllCount();

    }
}

