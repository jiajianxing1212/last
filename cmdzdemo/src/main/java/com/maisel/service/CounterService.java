package com.maisel.service;

import com.maisel.entity.Counter;

import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-05 17:26
 */
public interface CounterService {
    Map getAll(Integer page, Integer rows, String name);

    void add(Counter counter);

    void update(Counter counter);

    void multiRemove(Counter counter);
}
