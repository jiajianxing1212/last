package com.maisel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * (Lesson)实体类
 *
 * @author makejava
 * @since 2019-01-03 17:15:09
 */
@TableName("cmfz_lesson")
public class Lesson implements Serializable {
    private static final long serialVersionUID = -33064634609454802L;
    @TableId(type = IdType.AUTO)
    private Integer lessonId;
    
    private String lessonName;
    
    private Integer userId;
    
    private Integer lessonStatus;


    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLessonStatus() {
        return lessonStatus;
    }

    public void setLessonStatus(Integer lessonStatus) {
        this.lessonStatus = lessonStatus;
    }

}