package com.maisel.service;

import com.maisel.entity.Article;

import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-05 14:02
 */
public interface ArticleService {
    Map getAll(Integer page, Integer rows, String name);

    void add(Article article);

    void update(Article article);

    void updateStatus(Article article);

    void mutilRemove(Integer []ids);
}
