package com.fm.designstar.model.server.body;

import com.fm.designstar.model.bean.TagBean;
import com.fm.designstar.model.server.base.BaseBody;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 11:43
 * @update : 2018/9/21
 */
public class Firdesignerbody extends BaseBody {
    @SerializedName("userId")
    private long userId;

    public Firdesignerbody(long userId) {
        this.userId = userId;

    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


}
