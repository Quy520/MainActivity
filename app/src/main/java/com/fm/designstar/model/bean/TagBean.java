package com.fm.designstar.model.bean;


import com.fm.designstar.base.BaseBean;

/**
 * Created by KyuYi on 2017/3/2.
 * E-Mail:kyu_yi@sina.com
 * 功能：
 */

public class TagBean extends BaseBean {

    /**
     * tagId : 10
     * tagName : string
     * top : 0
     */

    private int tagId;
    private String tagName;
    private int top;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }
}

