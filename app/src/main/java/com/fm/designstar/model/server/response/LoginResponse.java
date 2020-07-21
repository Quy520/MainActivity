package com.fm.designstar.model.server.response;

import com.fm.designstar.base.BaseBean;

public class LoginResponse extends BaseBean {


    /**
     * code : 1285160959761055744
     * userName : qsd
     * mobile : null
     * email : 601228243@qq.com
     * sex : 1
     * birthday : null
     * address : qq
     * nickName : qsd
     * realName : null
     * avatar : https://sf.laifuyun.com/img/orig/4,6e73a98cb34a
     * role : 1
     * signature : qqqq
     * certificationMark : 0
     * token : 5f16ca13b076a5d00b829dde
     */

    private long code;
    private String userName;
    private Object mobile;
    private String email;
    private int sex;
    private Object birthday;
    private String address;
    private String nickName;
    private Object realName;
    private String avatar;
    private int role;
    private String signature;
    private int certificationMark;
    private String token;

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

    public Object getMobile() {
        return mobile;
    }

    public void setMobile(Object mobile) {
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

    public Object getBirthday() {
        return birthday;
    }

    public void setBirthday(Object birthday) {
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

    public Object getRealName() {
        return realName;
    }

    public void setRealName(Object realName) {
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
