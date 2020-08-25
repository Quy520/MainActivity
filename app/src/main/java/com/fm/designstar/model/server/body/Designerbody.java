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
public class Designerbody extends BaseBody {
    @SerializedName("imgUrl")
    private String imgUrl;


    public Designerbody(String imgUrl) {
        this.imgUrl = imgUrl;

    }


}
