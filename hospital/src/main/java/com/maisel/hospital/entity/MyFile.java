package com.maisel.hospital.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * (MyFile)实体类
 *
 * @author makejava
 * @since 2018-12-31 18:20:39
 */
@TableName("t_files")
public class MyFile implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String fileName;
    private String saveName;
    private Date upTime;
    private String fileSize;
    @TableField("is_Delete")
    private Integer isDelete;

    public MyFile() {
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "MyFile{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", saveName='" + saveName + '\'' +
                ", upTime=" + upTime +
                ", fileSize='" + fileSize + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}