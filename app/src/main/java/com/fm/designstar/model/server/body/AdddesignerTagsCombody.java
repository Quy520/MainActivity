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
public class AdddesignerTagsCombody extends BaseBody {
    @SerializedName("userId")
    private String userId;
    @SerializedName("tagsList")
    private List<TagBean> tagsList;

    public AdddesignerTagsCombody(String userId, List<TagBean> tagsList) {
        this.userId = userId;
        this.tagsList = tagsList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<TagBean> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<TagBean> tagsList) {
        this.tagsList = tagsList;
    }
}
