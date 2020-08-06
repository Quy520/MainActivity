package com.fm.designstar.model.server.response;

import com.fm.designstar.base.BaseBean;
import com.fm.designstar.model.bean.HomeFindBean;
import com.fm.designstar.model.bean.MessageBean;

import java.util.List;

public  class MessageResponse extends BaseBean {

    private List<MessageBean> result;

    public List<MessageBean> getResult() {
        return result;
    }

    public void setResult(List<MessageBean> result) {
        this.result = result;
    }
}
