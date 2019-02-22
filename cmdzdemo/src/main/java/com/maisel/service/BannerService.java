package com.maisel.service;

import com.maisel.entity.Banner;

import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-03 20:37
 */
public interface BannerService {
    Map getAll(Integer page, Integer rows, String name);

    void add(Banner banner);

    void update(Banner banner);

    void mutilRemove(Banner banner);

    void updateStatus(Banner banner);
}
