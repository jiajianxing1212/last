package com.maisel.hospital.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public class TriageAndPatientDTO {
    private Integer id;
    private String name;
    private Integer sex;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TriageAndPatientDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", birth=" + birth +
                ", status=" + status +
                '}';
    }
}
