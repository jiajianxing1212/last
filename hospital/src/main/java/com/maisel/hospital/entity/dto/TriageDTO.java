package com.maisel.hospital.entity.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;


public class TriageDTO {
    private Integer tid;
    private Integer pid;
    @Excel(name = "患者姓名")
    private String pname;
    private Integer did;
    @Excel(name = "科室名称")
    private String dname;
    private Integer uid;
    @Excel(name = "就诊医生")
    private String uname;
    @Excel(name = "症状描述")
    private String illnessInfo;
    @Excel(name = "诊断建议")
    private String advice;

    public String getIllnessInfo() {
        return illnessInfo;
    }

    public void setIllnessInfo(String illnessInfo) {
        this.illnessInfo = illnessInfo;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Override
    public String toString() {
        return "TriageDTO{" +
                "tid=" + tid +
                ", pid=" + pid +
                ", pname='" + pname + '\'' +
                ", did=" + did +
                ", dname='" + dname + '\'' +
                ", uid=" + uid +
                ", uname='" + uname + '\'' +
                '}';
    }
}
