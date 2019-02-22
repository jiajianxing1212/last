package com.maisel.hospital.service;

import com.maisel.hospital.entity.Drug;

import java.util.Map;

/**
 * @aucthor:jjx
 * @Create:2018-12-29 20:55
 */
public interface DrugService {

    Map getAllDrug(Integer page, Integer rows, String name);

    Drug getOne(Integer id);

    void add(Drug drug);

    void mutilRemove(Integer[] ids);

    void update(Drug drug);

    void falseRemove(Integer[] ids);

    void updateStatus(Integer id,Integer saleStatus);

}
