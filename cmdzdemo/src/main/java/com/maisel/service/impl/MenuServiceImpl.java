package com.maisel.service.impl;

import com.maisel.dao.MenuDao;
import com.maisel.entity.Menu;
import com.maisel.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @aucthor:jjx
 * @Create:2019-01-03 19:50
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;
    @Override
    public List<Menu> getAll() {
        List<Menu> list = menuDao.getAll();
        return list;
    }
}
