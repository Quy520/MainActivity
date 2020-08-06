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
public class SendMessageBody extends BaseBody {
    @SerializedName("content")
    private String content;
    @SerializedName("momentType")
    private int momentType;

    @SerializedName("momentId")
    private String momentId;


    public SendMessageBody(String content, int momentType, String momentId) {
        this.content = content;
        this.momentType = momentType;
        this.momentId = momentId;


    }
}
