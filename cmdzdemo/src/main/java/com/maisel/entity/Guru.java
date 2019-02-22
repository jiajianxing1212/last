package com.maisel.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * (Guru)实体类
 *
 * @author makejava
 * @since 2019-01-03 17:11:44
 */
@TableName("cmfz_guru")
public class Guru implements Serializable {
    @TableId(type = IdType.AUTO)

    @Excel(name = "编号")
    private Integer guruId;
    @Excel(name = "名字")
    private String guruName;
    @Excel(name = "图片",type = 2 ,width = 40 , height = 20,imageType = 1,savePath = "c://cmfzdemo/src/main/webapp/shangshi")
    private String guruImage;
    @Excel(name = "法号")
    private String guruNickname;
    @Excel(name = "状态",replace = {"正常_2","冻结_1","删除_0"})
    //1冻结 冻结的同时需要下架相关的专辑和文章
    private Integer guruStatus;


    public Integer getGuruId() {
        return guruId;
    }

    public void setGuruId(Integer guruId) {
        this.guruId = guruId;
    }

    public String getGuruName() {
        return guruName;
    }

    public void setGuruName(String guruName) {
        this.guruName = guruName;
    }

    public String getGuruImage() {
        return guruImage;
    }

    public void setGuruImage(String guruImage) {
        this.guruImage = guruImage;
    }

    public String getGuruNickname() {
        return guruNickname;
    }

    public void setGuruNickname(String guruNickname) {
        this.guruNickname = guruNickname;
    }

    public Integer getGuruStatus() {
        return guruStatus;
    }

    public void setGuruStatus(Integer guruStatus) {
        this.guruStatus = guruStatus;
    }

    @Override
    public String toString() {
        return "Guru{" +
                "guruId=" + guruId +
                ", guruName='" + guruName + '\'' +
                ", guruImage='" + guruImage + '\'' +
                ", guruNickname='" + guruNickname + '\'' +
                ", guruStatus=" + guruStatus +
                '}';
    }
}