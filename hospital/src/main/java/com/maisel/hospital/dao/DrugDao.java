package com.maisel.hospital.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maisel.hospital.entity.Department;
import com.maisel.hospital.entity.Drug;
import org.apache.ibatis.annotations.Param;

/**
 * @aucthor:jjx
 * @Create:2018-12-29 20:56
 */
public interface DrugDao extends BaseMapper<Drug> {

    void falseRemove(Integer[] ids);

    void updateStatus(@Param("id") Integer id, @Param("saleStatus") Integer saleStatus);
}
