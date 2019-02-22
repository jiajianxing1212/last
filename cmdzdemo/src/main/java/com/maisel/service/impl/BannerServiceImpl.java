package com.maisel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maisel.dao.BannerDao;
import com.maisel.entity.Banner;
import com.maisel.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-03 21:00
 */
@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;
    @Override
    public Map getAll(Integer page, Integer rows, String name) {
        QueryWrapper<Banner> wrapper = new QueryWrapper<Banner>();
        Page<Banner> page1 = new Page<Banner>(page, rows);

        if (name != null && !"".equals(name)) {
            wrapper.like("banner_old_name", name);
        }
        IPage<Banner> iPage = bannerDao.selectPage(page1, wrapper);
        List<Banner> list = iPage.getRecords();
        long total = iPage.getTotal();
        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", list);
        return map;
    }

    @Override
    public void add(Banner banner) {
        // 状态为2时。为正常状态
        banner.setBannerState(2);
        bannerDao.insert(banner);
    }

    @Override
    public void update(Banner banner) {
        bannerDao.updateById(banner);
    }

    @Override
    public void mutilRemove(Banner banner) {
        // 状态为0时。为删除状态
        banner.setBannerState(0);
        bannerDao.updateById(banner);
    }

    @Override
    public void updateStatus(Banner banner) {
        // 状态为1时。为冻结状态
        bannerDao.updateById(banner);
    }
}
