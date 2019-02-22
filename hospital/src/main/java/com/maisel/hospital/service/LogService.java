package com.maisel.hospital.service;

import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-13 18:11
 */
public interface LogService {
    Map getAll(Integer page,Integer rows,String content);
}
