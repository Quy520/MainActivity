package com.fm.designstar.model.bean;


import com.fm.designstar.base.BaseBean;

import java.util.List;

/**
 * Created by KyuYi on 2017/3/2.
 * E-Mail:kyu_yi@sina.com
 * 功能：
 */

public class DesignerPageBean extends BaseBean {


    /**
     * address : string
     * agreeFlag : 0
     * avatar : string
     * birthday : 2020-08-18
     * certificationMark : 0
     * code : 0
     * createTime : 2020-08-18T09:08:19.183Z
     * email : string
     * id : 0
     * mobile : string
     * nickName : string
     * password : string
     * realName : string
     * registerIp : string
     * role : 0
     * sex : 0
     * signature : string
     * status : 0
     * updateLastTime : 2020-08-18T09:08:19.183Z
     * userName : string
     */

    private int agreeFlag;
    private long code;
    private long createTimestamp;
    private String userName;
    private String createTime;
    private String updateLastTime;
    private String mobile;
    private String contactNumber;
    private String email;
    private int sex;
    private String birthday;
    private String address;
    private String nickName;
    private String realName;
    private String avatar;
    private int role;
    private String signature;
    private int certificationMark;
    private String token;
    private int status;
    private String corporation;
    private String position;
    private String imgUrl;

    private TagBean tagInfo;

    public long getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateLastTime() {
        return updateLastTime;
    }

    public void setUpdateLastTime(String updateLastTime) {
        this.updateLastTime = updateLastTime;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
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

    public int getCertificationMark() {
        return certificationMark;
    }

    public void setCertificationMark(int certificationMark) {
        this.certificationMark = certificationMark;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public TagBean getTagInfo() {
        return tagInfo;
    }

    public void setTagInfo(TagBean tagInfo) {
        this.tagInfo = tagInfo;
    }

    public int getAgreeFlag() {
        return agreeFlag;
    }

    public void setAgreeFlag(int agreeFlag) {
        this.agreeFlag = agreeFlag;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }



    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }


    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

