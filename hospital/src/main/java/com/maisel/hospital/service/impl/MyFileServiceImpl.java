package com.maisel.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maisel.hospital.dao.MyFileDao;
import com.maisel.hospital.entity.MyFile;
import com.maisel.hospital.service.MyFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


@Service
public class MyFileServiceImpl implements MyFileService {
    @Autowired
    private MyFileDao myFileDao;

    @Transactional
    @Override
    public int insert(MyFile file) {
        return myFileDao.insert(file);
    }

    @Override
    public Map getByPage(int page, int rows, String fileName) {
        IPage<MyFile> filePage = new Page<>(page, rows);
        QueryWrapper<MyFile> wrapper = new QueryWrapper<>();
        if (fileName == null) {
            wrapper.eq("is_Delete", 0);
        } else {
            wrapper.eq("is_Delete", 0).like("file_name", fileName);
        }
        IPage<MyFile> iPage = myFileDao.selectPage(filePage, wrapper);
        Map map = new HashMap();
        map.put("rows", iPage.getRecords());
        map.put("total", iPage.getTotal());
        return map;
    }

    @Override
    public MyFile getByFileName(String fileName) {
        QueryWrapper<MyFile> wrapper = new QueryWrapper<>();
        wrapper.eq("file_name", fileName);
        return myFileDao.selectOne(wrapper);
    }
}
