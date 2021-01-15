package com.fm.designstar.model.server.response;

import com.fm.designstar.base.BaseBean;
import com.fm.designstar.model.bean.TagBean;
import com.fm.designstar.model.bean.TagsBean;

public class LoginResponse extends BaseBean {




    private long code;
    private String userName;
    private String mobile;
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
    private String contactNumber;
    private TagBean tagInfo;

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public TagBean getTagBean() {
        return tagInfo;
    }

    public void setTagBean(TagBean tagBean) {
        this.tagInfo = tagBean;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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
}
