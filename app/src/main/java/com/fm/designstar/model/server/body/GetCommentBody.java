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
public class GetCommentBody extends BaseBody {
    @SerializedName("pageNum")
    private int pageNum;
    @SerializedName("pageSize")
    private int pageSize;

    @SerializedName("momentId")
    private String momentId;


    public GetCommentBody(int pageNum, int pageSize, String momentId) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.momentId = momentId;


    }
}
