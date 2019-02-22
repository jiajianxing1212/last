package com.maisel.hospital.entity.dto;

import java.util.Date;


public class ExamDTO {
    private int id;
    private String name;
    private String productCompany;
    private Date examStartDate;
    private Date examEndDate;
    private int examStatus;
    private int dId;

    public ExamDTO() {
    }

    public int getdId() {
        return dId;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductCompany() {
        return productCompany;
    }

    public void setProductCompany(String productCompany) {
        this.productCompany = productCompany;
    }

    public Date getExamStartDate() {
        return examStartDate;
    }

    public void setExamStartDate(Date examStartDate) {
        this.examStartDate = examStartDate;
    }

    public Date getExamEndDate() {
        return examEndDate;
    }

    public void setExamEndDate(Date examEndDate) {
        this.examEndDate = examEndDate;
    }

    public int getExamStatus() {
        return examStatus;
    }

    public void setExamStatus(int examStatus) {
        this.examStatus = examStatus;
    }

    @Override
    public String toString() {
        return "ExamDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productCompany='" + productCompany + '\'' +
                ", examStartDate=" + examStartDate +
                ", examEndDate=" + examEndDate +
                ", examStatus=" + examStatus +
                ", dId=" + dId +
                '}';
    }
}
