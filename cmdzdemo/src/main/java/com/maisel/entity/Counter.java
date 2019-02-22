package com.maisel.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * (Counter)实体类
 *
 * @author makejava
 * @since 2019-01-03 17:15:44
 */
@TableName("cmfz_counter")
public class Counter implements Serializable {
    private static final long serialVersionUID = -73531116770966598L;
    
    private Integer counterId;
    
    private String counterName;
    
    private Date counterDate;
    
    private String lessonId;
    
    private Integer userId;
    
    private Integer counterCount;
    
    private Integer counterStatus;


    public Integer getCounterId() {
        return counterId;
    }

    public void setCounterId(Integer counterId) {
        this.counterId = counterId;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public Date getCounterDate() {
        return counterDate;
    }

    public void setCounterDate(Date counterDate) {
        this.counterDate = counterDate;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCounterCount() {
        return counterCount;
    }

    public void setCounterCount(Integer counterCount) {
        this.counterCount = counterCount;
    }

    public Integer getCounterStatus() {
        return counterStatus;
    }

    public void setCounterStatus(Integer counterStatus) {
        this.counterStatus = counterStatus;
    }

    @Override
    public String toString() {
        return "Counter{" +
                "counterId=" + counterId +
                ", counterName='" + counterName + '\'' +
                ", counterDate=" + counterDate +
                ", lessonId='" + lessonId + '\'' +
                ", userId=" + userId +
                ", counterCount=" + counterCount +
                ", counterStatus=" + counterStatus +
                '}';
    }
}