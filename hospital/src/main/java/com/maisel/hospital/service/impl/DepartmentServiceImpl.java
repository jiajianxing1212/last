package com.maisel.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maisel.hospital.dao.DepartmentDao;
import com.maisel.hospital.entity.Department;
import com.maisel.hospital.service.DepartmentService;
import org.apache.taglibs.standard.tag.el.sql.QueryTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2018-12-29 20:56
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;
    @Override
    public Map getAllTDepartment(Integer page, Integer rows, String name) {

        // System.out.println(DepartmentDao);
        QueryWrapper<Department> wrapper = new QueryWrapper<Department>();
        IPage page1 = new Page(page,rows);
        if (name != null && !"".equals(name)) {
            wrapper.like("name", name);
        }
        wrapper.eq("status", 1);
        IPage<Department> iPage = departmentDao.selectPage(page1, wrapper);
        List<Department> departmentList = iPage.getRecords();
        // Integer count = DepartmentDao.selectCount(wrapper);
        long count = iPage.getTotal();
        Map map = new HashMap();
        map.put("rows", departmentList);
        map.put("total", count);
        return map;
    }

    @Override
    public void add(Department department) {

        departmentDao.insert(department);
    }

    // 真删除
    @Override
    public void mutilRemove(Integer[] ids) {
        List<Integer> list = Arrays.asList(ids);
        departmentDao.selectBatchIds(list);
    }

    @Override
    public void update(Department tDepartment) {

        departmentDao.updateById(tDepartment);
    }

    @Override
    public void falseRemove(Integer[] ids) {
        departmentDao.falseRemove(ids);
    }

    @Override
    public List getAll() {
        List<Department> list = departmentDao.selectList(null);
        return list;
    }


}
