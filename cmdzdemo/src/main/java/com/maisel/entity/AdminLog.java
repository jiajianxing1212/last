package com.maisel.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (AdminLog)实体类
 *
 * @author makejava
 * @since 2019-01-11 19:44:05
 */
@TableName("cmfz_admin_log")
public class AdminLog implements Serializable {

    @TableId(type = IdType.AUTO)
    @Excel(name = "log_id")
    private Integer logId;
    @Excel(name = "log_action")
    private String logAction;

    @Excel(name = "admin_usename")
    private String adminUsername;
    @Excel(name = "admin_id")
    private Integer adminId;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "log_Date")
    private Date logDate;
    @Excel(name = "logip")
    private String logIp;
    @Excel(name = "log_result")
    private String logResult;


    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getLogAction() {
        return logAction;
    }

    public void setLogAction(String logAction) {
        this.logAction = logAction;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public String getLogIp() {
        return logIp;
    }

    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }

    public String getLogResult() {
        return logResult;
    }

    public void setLogResult(String logResult) {
        this.logResult = logResult;
    }

    @Override
    public String toString() {
        return "AdminLog{" +
                "logId=" + logId +
                ", logAction='" + logAction + '\'' +
                ", adminUsername='" + adminUsername + '\'' +
                ", adminId=" + adminId +
                ", logDate=" + logDate +
                ", logIp='" + logIp + '\'' +
                ", logResult='" + logResult + '\'' +
                '}';
    }
}