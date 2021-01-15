package com.fm.designstar.model.bean;


import com.contrarywind.interfaces.IPickerViewData;
import com.fm.designstar.base.BaseBean;

import java.io.Serializable;

/**
 * Created by KyuYi on 2017/3/2.
 * E-Mail:kyu_yi@sina.com
 * 功能：
 */

public class TagsInfoVoBean extends BaseBean {
    /**
     * id : 5
     * tagName : 阿姆斯特朗螺旋加速阿姆斯特朗螺旋炮
     */

    private int tagId;
    private String tagName;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getId() {
        return tagId;
    }

    public void setId(int id) {
        this.tagId = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

}

