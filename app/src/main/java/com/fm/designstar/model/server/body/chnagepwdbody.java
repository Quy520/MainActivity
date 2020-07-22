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
public class chnagepwdbody extends BaseBody {
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("code")
    private String code;
    @SerializedName("password")
    private String password;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public chnagepwdbody(String mobile,String code,String password) {
        this.mobile = mobile;
        this.code=code;
        this.password=password;

    }


}
