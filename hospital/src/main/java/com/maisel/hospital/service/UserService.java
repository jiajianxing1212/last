package com.maisel.hospital.service;

import com.maisel.hospital.entity.User;


public interface UserService {

    User login(User User);

    void register(User User);
}
