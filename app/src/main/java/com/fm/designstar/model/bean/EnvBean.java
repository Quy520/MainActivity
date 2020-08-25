package com.fm.designstar.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.fm.designstar.base.BaseBean;


/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/28 9:57
 * @update : 2018/9/28
 */
public class EnvBean extends BaseBean implements Parcelable {
    private String imageUrl;
    private String imageDesc;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageDesc() {
        return imageDesc;
    }

    public void setImageDesc(String imageDesc) {
        this.imageDesc = imageDesc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<EnvBean> CREATOR = new Creator<EnvBean>() {
        @Override
        public EnvBean createFromParcel(Parcel in) {
            EnvBean envBean = new EnvBean();
            envBean.setImageUrl(in.readString());
            envBean.setImageDesc(in.readString());
            return envBean;
        }

        @Override
        public EnvBean[] newArray(int size) {
            return new EnvBean[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imageUrl);
        parcel.writeString(imageDesc);
    }
}
