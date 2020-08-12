package com.fm.designstar.model.server.response;

import com.fm.designstar.base.BaseBean;

public class VesionResponse extends BaseBean {


    /**
     * type : 0
     * versionCode : 0
     * versionInfo : string
     * versionName : string
     */

    private int type;
    private int versionCode;
    private String versionInfo;
    private String versionName;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(String versionInfo) {
        this.versionInfo = versionInfo;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
}
