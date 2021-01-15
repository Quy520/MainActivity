package com.fm.designstar.model.bean;


import com.fm.designstar.base.BaseBean;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/8/15 9:05
 * @update : 2018/8/15
 */
public class RecordBean extends BaseBean {


    /**
     * createTime : 2020-08-14T05:55:58.674Z
     * id : 0
     * imgUrl : string
     * status : 0
     * updateLastTime : 2020-08-14T05:55:58.674Z
     */

    private String createTime;
    private int id;
    private String imgUrl;
    private String avatar;
    private String content;
    private String userName;
    private String realName;
    private String birthday;
    private String address;
    private String corporation;
    private String position;
    private String mobile;
    private String contactNumber;

    private int status;
    private long createTimestamp;
    private String updateLastTime;

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public long getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUpdateLastTime() {
        return updateLastTime;
    }

    public void setUpdateLastTime(String updateLastTime) {
        this.updateLastTime = updateLastTime;
    }
}
