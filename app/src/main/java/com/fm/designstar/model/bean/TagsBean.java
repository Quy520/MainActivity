package com.fm.designstar.model.bean;


import com.fm.designstar.base.BaseBean;

import java.util.List;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/8/15 9:05
 * @update : 2018/8/15
 */
public class TagsBean extends BaseBean {


    private String parentTag;
    private List<TagsInfoVoBean> tagInfoVoList;

    public String getParentTag() {
        return parentTag;
    }

    public void setParentTag(String parentTag) {
        this.parentTag = parentTag;
    }

    public List<TagsInfoVoBean> getTagInfoVoList() {
        return tagInfoVoList;
    }

    public void setTagInfoVoList(List<TagsInfoVoBean> tagInfoVoList) {
        this.tagInfoVoList = tagInfoVoList;
    }
}
