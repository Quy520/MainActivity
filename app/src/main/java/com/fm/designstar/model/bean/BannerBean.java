package com.fm.designstar.model.bean;


import com.fm.designstar.base.BaseBean;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 16:20
 * @update : 2018/9/21
 */
public class BannerBean extends BaseBean {


    /**
     * activityContent : string
     * activityId : string
     * activityStatus : 0
     * activityTime : string
     * activityTitle : string
     * address : string
     * author : string
     * banner : string
     * bannerHeight : 0
     * bannerWidth : 0
     * createTime : 0
     * fileName : string
     * top : 0
     * updateTime : 0
     */

    private String activityContent;
    private String activityId;
    private int activityStatus;
    private String activityTime;
    private String activityTitle;
    private String address;
    private String author;
    private String banner;
    private int bannerHeight;
    private int bannerWidth;
    private long createTime;
    private String fileName;
    private int top;
    private long updateTime;

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public int getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(int activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public int getBannerHeight() {
        return bannerHeight;
    }

    public void setBannerHeight(int bannerHeight) {
        this.bannerHeight = bannerHeight;
    }

    public int getBannerWidth() {
        return bannerWidth;
    }

    public void setBannerWidth(int bannerWidth) {
        this.bannerWidth = bannerWidth;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
