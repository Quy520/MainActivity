package com.fm.designstar.model.server.body;

import com.fm.designstar.model.server.base.BaseBody;
import com.google.gson.annotations.SerializedName;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 11:43
 * @update : 2018/9/21
 */
public class Viewbody extends BaseBody {
    @SerializedName("actualTime")
    private String actualTime;
    @SerializedName("duration")
    private String duration;
    @SerializedName("momentId")
    private long momentId;
    @SerializedName("mediaType")
    private int mediaType;

    public Viewbody(String actualTime, String duration, long momentId, int mediaType) {
        this.actualTime = actualTime;
        this.duration = duration;
        this.momentId = momentId;
        this.mediaType = mediaType;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public long getMomentId() {
        return momentId;
    }

    public void setMomentId(long momentId) {
        this.momentId = momentId;
    }

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }
}
