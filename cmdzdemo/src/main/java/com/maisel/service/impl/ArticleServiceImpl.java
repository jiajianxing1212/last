package com.maisel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maisel.dao.ArticleDao;
import com.maisel.entity.Article;
import com.maisel.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-05 14:03
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Override
    public Map getAll(Integer page, Integer rows, String name) {
        QueryWrapper<Article> wrapper = new QueryWrapper<Article>();
        if (name != null && !"".equals(name)) {
            wrapper.like("article_name", name);
        }
        Page<Article> articlePage = new Page<Article>(page,rows);
        IPage<Article> iPage = articleDao.selectPage(articlePage, wrapper);
        List<Article> list = iPage.getRecords();
        long total = iPage.getTotal();
        Map map = new HashMap();
        map.put("rows", list);
        map.put("total", total);
        return map;
    }

    @Override
    public void add(Article article) {
        articleDao.insert(article);
    }

    @Override
    public void update(Article article) {
        articleDao.updateById(article);
    }

    @Override
    public void updateStatus(Article article) {
        articleDao.updateById(article);
    }

    @Override
    public void mutilRemove(Integer []ids) {
        List<Integer> list = Arrays.asList(ids);
        Article article = new Article();
        article.setArticleStatus(0);
        UpdateWrapper<Article> wrapper = new UpdateWrapper<Article>();
        wrapper.in("article_id", list);
        articleDao.update(article, wrapper);
    }
}
