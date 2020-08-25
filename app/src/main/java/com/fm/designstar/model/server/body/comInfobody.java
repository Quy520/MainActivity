package com.fm.designstar.model.server.body;

import com.fm.designstar.model.server.base.BaseBody;
import com.google.gson.annotations.SerializedName;

public class comInfobody extends BaseBody {
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("userName")
    private String userName;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("address")
    private String address;
    @SerializedName("signature")
    private String signature;
    @SerializedName("sex")
    private int sex;
    @SerializedName("certificationMark")
    private int certificationMark;



    public comInfobody(String avatar, String userName, String birthday, int sex,int certificationMark,String address,String signature) {
        this.avatar = avatar;
        this.userName = userName;
        this.birthday = birthday;
        this.sex = sex;
        this.certificationMark=certificationMark;
        this.address=address;
        this.signature=signature;
    }
}
