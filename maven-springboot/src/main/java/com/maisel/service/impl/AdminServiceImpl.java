package com.maisel.service.impl;

import com.maisel.dao.AdminDao;
import com.maisel.entity.Admin;
import com.maisel.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @aucthor:jjx
 * @Create:2019-02-19 19:45
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Override
    public List<Admin> getAll() {
        return adminDao.getAll();
    }
}
