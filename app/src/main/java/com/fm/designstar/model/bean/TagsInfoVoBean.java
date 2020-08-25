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

    private int id;
    private String tagName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

}

