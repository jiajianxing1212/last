package com.maisel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maisel.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2019-01-03 22:12
 */
public interface UserDao extends BaseMapper<User> {
    List<User> getAll(@Param("start") Integer start,
                      @Param("pageSize") Integer pageSize, @Param("name") String name);

    int getCount(@Param("name") String name);

    int getCountByWeekendOfRegister1();
    int getCountByWeekendOfRegister2();
    int getCountByWeekendOfRegister3();

    List<Map<String,Object>> getCountByArea();
}
