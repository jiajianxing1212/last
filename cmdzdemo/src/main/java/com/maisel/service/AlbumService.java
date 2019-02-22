package com.maisel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maisel.entity.Album;
import com.maisel.entity.Audio;

import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-05 14:46
 */
public interface AlbumService extends IService<Audio> {
    Map getAll(Integer page, Integer rows, String name);


}
