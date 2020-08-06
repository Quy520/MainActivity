package com.fm.designstar.model.server.body;

import com.fm.designstar.model.bean.DesignerBean;
import com.fm.designstar.model.bean.MultimediaListBean;
import com.fm.designstar.model.bean.MultimediabodyBean;
import com.fm.designstar.model.server.base.BaseBody;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 11:43
 * @update : 2018/9/21
 */
public class uploadMomentbody extends BaseBody {
    @SerializedName("address")
    private String address;
    @SerializedName("content")
    private String content;
    @SerializedName("latitude")
    private long latitude;
    @SerializedName("longitude")
    private long longitude;
    @SerializedName("mediaType")
    private int mediaType;
    @SerializedName("momentType")
    private int momentType;

    @SerializedName("multimediaList")
    private List<MultimediabodyBean> multimediaList;

    @SerializedName("tagsList")
    private List<DesignerBean.TagsListBean> tagsList;

    public uploadMomentbody(String address, String content, long latitude, long longitude, int mediaType, int momentType, List<MultimediabodyBean> multimediaList, List<DesignerBean.TagsListBean> tagsList) {
        this.address = address;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
        this.mediaType = mediaType;
        this.momentType = momentType;
        this.multimediaList = multimediaList;
        this.tagsList = tagsList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }

    public int getMomentType() {
        return momentType;
    }

    public void setMomentType(int momentType) {
        this.momentType = momentType;
    }

    public List<MultimediabodyBean> getMultimediaList() {
        return multimediaList;
    }

    public void setMultimediaList(List<MultimediabodyBean> multimediaList) {
        this.multimediaList = multimediaList;
    }

    public List<DesignerBean.TagsListBean> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<DesignerBean.TagsListBean> tagsList) {
        this.tagsList = tagsList;
    }
}

