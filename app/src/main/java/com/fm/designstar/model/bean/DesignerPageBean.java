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
    private String avatar;
    private long code;
    private String email;
    private int id;
    private String mobile;
    private String nickName;
    private String password;
    private String realName;
    private String registerIp;
    private int role;
    private int sex;
    private String signature;
    private int status;
    private String userName;

    private TagBean tagInfo;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
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

