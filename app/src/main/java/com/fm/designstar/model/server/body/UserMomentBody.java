package com.fm.designstar.model.server.body;

import com.fm.designstar.model.server.base.BaseBody;
import com.google.gson.annotations.SerializedName;


/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2019/3/28 9:37
 * @update : 2019/3/28
 */
public class UserMomentBody extends BaseBody {
    @SerializedName("pageNum")
    private int pageNum;
    @SerializedName("pageSize")
    private int pageSize;
    @SerializedName("momentType")
    private Integer momentType;
    @SerializedName("mediaType")
    private Integer mediaType;
    @SerializedName("userId")
    private String userId;

    public UserMomentBody() {


    }


    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getMomentType() {
        return momentType;
    }

    public void setMomentType(Integer momentType) {
        this.momentType = momentType;
    }

    public Integer getMediaType() {
        return mediaType;
    }

    public void setMediaType(Integer mediaType) {
        this.mediaType = mediaType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
