package com.fm.designstar.model.server.response;

import com.fm.designstar.base.BaseBean;
import com.fm.designstar.model.bean.CommentsBean;
import com.fm.designstar.model.bean.MessageBean;

import java.util.List;

public  class CommentsResponse extends BaseBean {

    private List<CommentsBean> list;

    public List<CommentsBean> getResult() {
        return list;
    }

    public void setResult(List<CommentsBean> list) {
        this.list = list;
    }
}
