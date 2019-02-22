package com.maisel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maisel.dao.AlbumDao;
import com.maisel.dao.AudioDao;
import com.maisel.entity.Album;
import com.maisel.entity.Audio;
import com.maisel.entity.Lesson;
import com.maisel.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-05 17:49
 */
@Service
public class AudioServiceImpl extends ServiceImpl<AlbumDao,Album> implements AudioService   {
    @Autowired
    private AudioDao audioDao;
    @Override
    public List<Album> getAll() {
        return audioDao.getAll();
    }



}
