package com.maisel.hospital.service;

import com.maisel.hospital.entity.MyFile;
import java.util.Map;


public interface MyFileService {
    int insert(MyFile file);

    Map getByPage(int page, int rows, String fileName);

    MyFile getByFileName(String fileName);
}
