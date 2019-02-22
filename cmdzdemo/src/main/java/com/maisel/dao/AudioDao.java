package com.maisel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maisel.entity.Album;
import com.maisel.entity.Audio;

import java.util.List;

/**
 * @aucthor:jjx
 * @Create:2019-01-05 17:18
 */
public interface AudioDao extends BaseMapper<Audio> {
    List<Album> getAll();
}
