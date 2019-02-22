package com.maisel.hospital.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maisel.hospital.entity.Department;

/**
 * @aucthor:jjx
 * @Create:2018-12-29 20:56
 */
public interface DepartmentDao extends BaseMapper<Department> {

    void falseRemove(Integer[] ids);
}
