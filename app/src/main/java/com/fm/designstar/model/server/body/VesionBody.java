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
public class VesionBody extends BaseBody {
    @SerializedName("code")
    private int code;
    @SerializedName("systemType")
    private String systemType;


    public VesionBody(int code, String systemType) {
        this.code = code;
        this.systemType = systemType;
    }
}
