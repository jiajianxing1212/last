package com.maisel.service;

import com.maisel.entity.AdminLog;

import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-11 20:44
 */
public interface AdminLogService {
    Map getAll(Integer page,Integer rows,String action);

    void multiPart(Integer[] ids);

    List<AdminLog> getAll();
}
