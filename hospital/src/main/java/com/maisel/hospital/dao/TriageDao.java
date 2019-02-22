package com.maisel.hospital.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maisel.hospital.entity.Triage;
import com.maisel.hospital.entity.dto.TriageAndDeptDTO;
import com.maisel.hospital.entity.dto.TriageAndPatientDTO;
import com.maisel.hospital.entity.dto.TriageDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TriageDao extends BaseMapper<Triage> {
    List<TriageAndDeptDTO> getByPage(@Param("start") Integer start, @Param("end") Integer end);
    int getByCount();

    List<TriageAndPatientDTO> getByDid(@Param("start") int start,
                                       @Param("end") int end,
                                       @Param("did") int did);

    int getByDidCount(@Param("did") int did);

    int call(int id);

    // int update(Triage triage);

    TriageDTO getById(int id);

    TriageDTO getByPid(int id);


}
