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
public class LoginBody extends BaseBody {
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("password")
    private String password;
    @SerializedName("deviceNo")
    private String deviceNo;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public LoginBody(String mobile, String password,String deviceNo) {
        this.mobile = mobile;
        this.password = password;
        this.deviceNo=deviceNo;


    }


}
