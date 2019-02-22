package com.maisel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maisel.dao.AlbumDao;
import com.maisel.dao.AudioDao;
import com.maisel.entity.Album;
import com.maisel.entity.Audio;
import com.maisel.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-05 14:50
 */
@Service
public class AlbumServiceImpl extends ServiceImpl<AudioDao, Audio> implements AlbumService {
    @Autowired
    private AlbumDao albumDao;

    @Override
    public Map getAll(Integer page, Integer rows, String name) {
        QueryWrapper<Album> wrapper = new QueryWrapper<Album>();
        if (name != null && !"".equals(name)) {
            wrapper.like("album_name", name);
        }
        Page<Album> albumPage = new Page<Album>(page, rows);
        IPage<Album> iPage = albumDao.selectPage(albumPage, wrapper);
        List<Album> list = iPage.getRecords();
        long total = iPage.getTotal();
        Map map = new HashMap();
        map.put("rows", list);
        map.put("total", total);
        return map;
    }


}
