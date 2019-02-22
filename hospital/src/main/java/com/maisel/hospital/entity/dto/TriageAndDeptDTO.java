package com.maisel.hospital.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;


public class TriageAndDeptDTO {
    private Integer id;
    private String name;
    private String telephone;
    private String area;
    private Integer isDelete;
    private Integer count;

    public TriageAndDeptDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "TriageAndDeptDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", area='" + area + '\'' +
                ", isDelete=" + isDelete +
                ", count=" + count +
                '}';
    }
}
