package com.maisel.service.impl;

import com.maisel.dao.AdminDao;
import com.maisel.entity.Admin;
import com.maisel.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @aucthor:jjx
 * @Create:2019-02-18 19:50
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Override
    @Transactional
    public List<Admin> getAll() {
        return adminDao.getAll();
    }
}
