package com.maisel.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maisel.hospital.dao.UserDao;
import com.maisel.hospital.entity.User;
import com.maisel.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @aucthor:jjx
 * @Create:2018-12-29 21:53
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao UserDao;
    @Override
    public User login(User tUser) {
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("username", tUser.getUsername()).eq("password", tUser.getPassword());
        User user = UserDao.selectOne(wrapper);
        return user;
    }

    @Override
    public void register(User User) {
        UserDao.insert(User);
    }

}
