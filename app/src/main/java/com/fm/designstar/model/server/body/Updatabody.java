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
public class Updatabody extends BaseBody {
    @SerializedName("content")
    private String content;
    @SerializedName("flag")
    private boolean flag;
    @SerializedName("id")
    private long id;
    @SerializedName("type")
    private Integer type;

    public Updatabody(String content, boolean flag, long id, Integer type) {
        this.content = content;
        this.flag = flag;
        this.id = id;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
