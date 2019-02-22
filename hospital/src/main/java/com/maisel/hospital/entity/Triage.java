package com.maisel.hospital.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;


@TableName("t_triage")
public class Triage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer status;

    @TableField("illness_Info")
    private String illnessInfo;

    private String advice;

    private Integer pid;

    private Integer did;

    private Integer uid;

    /**
     * 0
     */
    @TableField("isDelete")
    private Integer isDelete;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Triage{" +
        "id=" + id +
        ", status=" + status +
        ", illnessInfo=" + illnessInfo +
        ", advice=" + advice +
        ", pid=" + pid +
        ", did=" + did +
        ", uid=" + uid +
        ", isDelete=" + isDelete +
        "}";
    }
}
