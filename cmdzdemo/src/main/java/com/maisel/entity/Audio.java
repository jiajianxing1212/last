package com.maisel.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;

/**
 * (Audio)实体类
 *
 * @author makejava
 * @since 2019-01-03 17:13:46
 */
@TableName("cmfz_audio")
public class Audio implements Serializable {
    private static final long serialVersionUID = -28812220583665452L;
    @JsonProperty("id")
    @Excel(name = "编号")
    private Integer audioId;
    @JsonProperty("name")
    @Excel(name = "名字")
    private String audioName;
    @Excel(name = "专辑编号")
    private Integer albumId;
    @Excel(name = "音频地址")
    private String audioUrl;
    @Excel(name = "音频大小")
    private String audioSize;
    @Excel(name = "排序")
    private Integer audioOrder;

    @JsonGetter("id")
    public Integer getAudioId() {
        return audioId;
    }

    @JsonSetter("id")
    public void setAudioId(Integer audioId) {
        this.audioId = audioId;
    }

    @JsonGetter("name")
    public String getAudioName() {
        return audioName;
    }

    @JsonSetter("name")
    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getAudioSize() {
        return audioSize;
    }

    public void setAudioSize(String audioSize) {
        this.audioSize = audioSize;
    }

    public Integer getAudioOrder() {
        return audioOrder;
    }

    public void setAudioOrder(Integer audioOrder) {
        this.audioOrder = audioOrder;
    }

    @Override
    public String toString() {
        return "Audio{" +
                "audioId=" + audioId +
                ", audioName='" + audioName + '\'' +
                ", albumId=" + albumId +
                ", audioUrl='" + audioUrl + '\'' +
                ", audioSize='" + audioSize + '\'' +
                ", audioOrder=" + audioOrder +
                '}';
    }
}