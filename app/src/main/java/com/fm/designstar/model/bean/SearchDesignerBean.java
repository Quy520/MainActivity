package com.fm.designstar.model.bean;


import com.fm.designstar.base.BaseBean;

import java.util.List;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/8/15 9:05
 * @update : 2018/8/15
 */
public class SearchDesignerBean extends BaseBean {






    /**
     * address : string
     * avatar : string
     * birthday : 2020-11-20
     * certificationMark : 0
     * code : 0
     * contactNumber : string
     * email : string
     * mobile : string
     * nickName : string
     * realName : string
     * role : 0
     * sex : 0
     * signature : string
     * status : 0
     * stringCode : string
     * tagInfo : {"tagId":0,"tagName":"string","top":0}
     * token : string
     * userName : string
     */

    private String address;
    private String avatar;
    private String birthday;
    private int certificationMark;

    private String contactNumber;
    private String email;
    private String mobile;
    private String nickName;
    private String realName;
    private int role;
    private int sex;
    private String signature;
    private int status;
    private String stringCode;
    private TagInfoBean tagInfo;
    private String token;
    private String userName;

    private String tagName;
    private String userAdddress;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getUserAdddress() {
        return userAdddress;
    }

    public void setUserAdddress(String userAdddress) {
        this.userAdddress = userAdddress;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getCertificationMark() {
        return certificationMark;
    }

    public void setCertificationMark(int certificationMark) {
        this.certificationMark = certificationMark;
    }



    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
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

    public String getStringCode() {
        return stringCode;
    }

    public void setStringCode(String stringCode) {
        this.stringCode = stringCode;
    }

    public TagInfoBean getTagInfo() {
        return tagInfo;
    }

    public void setTagInfo(TagInfoBean tagInfo) {
        this.tagInfo = tagInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public static class TagInfoBean {
        /**
         * tagId : 5
         * tagName : 阿姆斯特朗螺旋加速阿姆斯特朗螺旋炮
         */

        private int tagId;
        private String tagName;

        public int getTagId() {
            return tagId;
        }

        public void setTagId(int tagId) {
            this.tagId = tagId;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }
    }
}
