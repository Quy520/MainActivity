package com.fm.designstar.model.server.response;

import com.fm.designstar.base.BaseBean;
import com.fm.designstar.model.bean.TagsBean;
import com.fm.designstar.model.bean.TagsInfoVoBean;
import com.fm.designstar.model.server.base.BaseBody;

import java.util.List;

public  class TagInfoResponse extends BaseBean {


    /**
     * tagType : 360°螺旋挂树
     * tagsInfoVo : [{"id":5,"tagName":"阿姆斯特朗螺旋加速阿姆斯特朗螺旋炮"}]
     */
    private List<TagsBean> list;

    public List<TagsBean> getList() {
        return list;
    }

    public void setList(List<TagsBean> list) {
        this.list = list;
    }
}


