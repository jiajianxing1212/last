package com.maisel.hospital.service;

import com.maisel.hospital.entity.Patient;
import java.util.Map;


public interface PatientService {
    Map getByPage(int page, int rows, String name);
    int insert(Patient patient);
    int update(Patient patient);
    int deleteSome(int[] ids);
}
