package com.maisel.service;

import com.maisel.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @aucthor:jjx
 * @Create:2019-02-18 19:48
 */
public interface AdminService {
    List<Admin> getAll();
}
