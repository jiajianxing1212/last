package com.maisel.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (Album)实体类
 *
 * @author makejava
 * @since 2019-01-03 17:09:35
 */
@TableName("cmfz_album")
public class Album implements Serializable {
    private static final long serialVersionUID = 447593435346309614L;
    @TableId(type = IdType.AUTO)
    @JsonProperty("id")
    @Excel(name = "编号")
    private Integer albumId;
    @JsonProperty("name")
    @Excel(name = "名字")
    private String albumName;
    @TableField("album_author")
    @Excel(name = "作者")

    private String albumAuthor;
    @Excel(name = "朗诵者")
    private String albumTeller;
    @Excel(name = "集数")
    private Integer albumEpisodes;

    @Excel(name = "上传时间")
    private Date albumDate;

    @Excel(name = "内容")

    private String albumContent;
    @Excel(name = "图片")

    private String albumImage;
    @Excel(name = "星级")
    private Integer albumStar;

    @TableField(exist = false)
    @ExcelCollection(name ="音频信息" )
    private List<Audio> children;
    @Excel(name = "状态",replace = {"正常_2","冻结_1","删除_0"})
    private Integer albumStatus;

    public Integer getAlbumStatus() {
        return albumStatus;
    }

    public void setAlbumStatus(Integer albumStatus) {
        this.albumStatus = albumStatus;
    }

    public List<Audio> getChildren() {
        return children;
    }

    public void setChildren(List<Audio> children) {
        this.children = children;
    }
    @JsonGetter("id")
    public Integer getAlbumId() {
        return albumId;
    }
    @JsonSetter("id")
    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }
    @JsonGetter("name")
    public String getAlbumName() {
        return albumName;
    }
    @JsonSetter("name")
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumAuthor() {
        return albumAuthor;
    }

    public void setAlbumAuthor(String albumAuthor) {
        this.albumAuthor = albumAuthor;
    }

    public String getAlbumTeller() {
        return albumTeller;
    }

    public void setAlbumTeller(String albumTeller) {
        this.albumTeller = albumTeller;
    }

    public Integer getAlbumEpisodes() {
        return albumEpisodes;
    }

    public void setAlbumEpisodes(Integer albumEpisodes) {
        this.albumEpisodes = albumEpisodes;
    }

    public Date getAlbumDate() {
        return albumDate;
    }

    public void setAlbumDate(Date albumDate) {
        this.albumDate = albumDate;
    }

    public String getAlbumContent() {
        return albumContent;
    }

    public void setAlbumContent(String albumContent) {
        this.albumContent = albumContent;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }

    public Integer getAlbumStar() {
        return albumStar;
    }

    public void setAlbumStar(Integer albumStar) {
        this.albumStar = albumStar;
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", albumAuthor='" + albumAuthor + '\'' +
                ", albumTeller='" + albumTeller + '\'' +
                ", albumEpisodes=" + albumEpisodes +
                ", albumDate=" + albumDate +
                ", albumContent='" + albumContent + '\'' +
                ", albumImage='" + albumImage + '\'' +
                ", albumStar=" + albumStar +
                ", children=" + children +
                '}';
    }
}