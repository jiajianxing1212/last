package com.maisel.service;

import com.maisel.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-03 22:13
 */
public interface UserService {
    Map getAll(Integer page, Integer rows, String name);

    void add(User user);

    void update(User user);

    void updateStatus(User user);

    void mutilRemove(User user);

    Map getCountSex();

    Map getCountByWeekendOfRegister();

    List<Map<String,Object>> getCountByArea();

    public Map getAllCount();


}
