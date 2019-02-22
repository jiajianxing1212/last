package com.maisel.hospital.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.io.Serializable;

/**
 * (Exam)实体类
 *
 * @author makejava
 * @since 2018-12-30 20:23:45
 */
@TableName("t_exam")
public class Exam implements Serializable {
    private static final long serialVersionUID = 270074728112577540L;

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", examStatus=" + examStatus +
                ", examAdvice='" + examAdvice + '\'' +
                ", examStartDate=" + examStartDate +
                ", examEndDate=" + examEndDate +
                ", dId=" + dId +
                ", drug=" + drug +
                '}';
    }

    @TableId(type = IdType.AUTO)
    private Integer id;
    
    private Integer examStatus;
    
    private String examAdvice;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date examStartDate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date examEndDate;
    
    private Integer dId;

    @TableField(exist = false)
    private Drug drug;

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExamStatus() {
        return examStatus;
    }

    public void setExamStatus(Integer examStatus) {
        this.examStatus = examStatus;
    }

    public String getExamAdvice() {
        return examAdvice;
    }

    public void setExamAdvice(String examAdvice) {
        this.examAdvice = examAdvice;
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

    public Integer getDId() {
        return dId;
    }

    public void setDId(Integer dId) {
        this.dId = dId;
    }

}