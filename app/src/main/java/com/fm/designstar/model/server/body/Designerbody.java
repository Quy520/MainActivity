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
    @SerializedName("address")
    private String address;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("corporation")
    private String corporation;
    @SerializedName("position")
    private String position;
    @SerializedName("realName")
    private String realName;
    @SerializedName("contactNumber")
    private String contactNumber;


    public Designerbody(String imgUrl, String address, String birthday, String corporation, String position, String realName,String contactNumber) {
        this.imgUrl = imgUrl;
        this.address = address;
        this.birthday = birthday;
        this.corporation = corporation;
        this.position = position;
        this.realName = realName;
        this.contactNumber=contactNumber;
    }
}
