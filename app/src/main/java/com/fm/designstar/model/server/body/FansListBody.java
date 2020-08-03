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
public class FansListBody extends BaseBody {
    @SerializedName("pageNum")
    private int pageNum;
    @SerializedName("pageSize")
    private int pageSize;
    @SerializedName("followUserId")
    private String followUserId;


    public FansListBody(int pageNum, int pageSize, String followUserId) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.followUserId=followUserId;

    }
}
