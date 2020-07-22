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
public class sendMsgBody extends BaseBody {
    @SerializedName("mobile")
    private String mobile;
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public sendMsgBody(String mobile) {
        this.mobile = mobile;

    }


}
