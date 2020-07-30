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
public class Addbody extends BaseBody {
    @SerializedName("deviceNo")
    private String deviceNo;
    @SerializedName("guangKey")
    private String guangKey;
    @SerializedName("operateSystem")
    private String operateSystem;

    public Addbody(String deviceNo, String guangKey, String operateSystem) {
        this.deviceNo = deviceNo;
        this.guangKey = guangKey;
        this.operateSystem = operateSystem;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getGuangKey() {
        return guangKey;
    }

    public void setGuangKey(String guangKey) {
        this.guangKey = guangKey;
    }

    public String getOperateSystem() {
        return operateSystem;
    }

    public void setOperateSystem(String operateSystem) {
        this.operateSystem = operateSystem;
    }
}
