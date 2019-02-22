package com.maisel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * (Admin)实体类
 *
 * @author makejava
 * @since 2019-01-03 15:53:58
 */
@TableName("cmfz_admin")
public class Admin implements Serializable {
    private static final long serialVersionUID = -35364962509752758L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    private String username;
    
    private String password;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}