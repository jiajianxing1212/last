package com.maisel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maisel.entity.Album;
import com.maisel.entity.Audio;

import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-05 17:47
 */
public interface AudioService extends IService<Album> {
    List<Album> getAll();


}
