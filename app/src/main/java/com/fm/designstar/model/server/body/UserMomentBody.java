package com.fm.designstar.model.server.body;

import com.fm.designstar.model.server.base.BaseBody;
import com.google.gson.annotations.SerializedName;


/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2019/3/28 9:37
 * @update : 2019/3/28
 */
public class UserMomentBody extends BaseBody {
    @SerializedName("pageNum")
    private int pageNum;
    @SerializedName("pageSize")
    private int pageSize;
    @SerializedName("momentType")
    private int momentType;
    @SerializedName("userId")
    private String userId;


    public UserMomentBody(int pageNum, int pageSize,int momentType,String userId) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.momentType=momentType;
        this.userId=userId;

    }
}
