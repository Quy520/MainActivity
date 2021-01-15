package com.fm.designstar.model.server.response;

import com.fm.designstar.base.BaseBean;
import com.fm.designstar.model.bean.DesignerTagsBean;
import com.fm.designstar.model.bean.TagsBean;

import java.util.List;

public  class DesignerTagInfoResponse extends BaseBean {


    /**
     * tagType : 360°螺旋挂树
     * tagsInfoVo : [{"id":5,"tagName":"阿姆斯特朗螺旋加速阿姆斯特朗螺旋炮"}]
     */
    private List<DesignerTagsBean> list;

    public List<DesignerTagsBean> getList() {
        return list;
    }

    public void setList(List<DesignerTagsBean> list) {
        this.list = list;
    }
}


