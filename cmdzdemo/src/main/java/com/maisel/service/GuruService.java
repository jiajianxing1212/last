package com.maisel.service;

import com.maisel.entity.Guru;

import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-05 13:40
 */
public interface GuruService {
    Map getAll(Integer page, Integer rows, String name);

    List<Guru> getAll();

    void add(Guru guru);

    void updateStatus(Guru guru);

    void update(Guru guru);

    void multiRemove(Integer[] ids);
}
