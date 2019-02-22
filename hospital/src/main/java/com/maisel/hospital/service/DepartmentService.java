package com.maisel.hospital.service;

import com.maisel.hospital.entity.Department;

import java.util.List;
import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2018-12-29 20:55
 */
public interface DepartmentService {

    Map getAllTDepartment(Integer page, Integer rows, String name);

    void add(Department Department);

    void mutilRemove(Integer[] ids);

    void update(Department tDepartment);

    void falseRemove(Integer[] ids);

    List getAll();
}
