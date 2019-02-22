package com.maisel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maisel.dao.AdminDao;
import com.maisel.entity.Admin;
import com.maisel.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @aucthor:jjx
 * @Create:2019-01-03 15:57
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin login(Admin admin) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<Admin>();
        wrapper.eq("username", admin.getUsername()).eq("password", admin.getPassword());
        Admin admin1 = adminDao.selectOne(wrapper);
        return admin1;
    }

}
