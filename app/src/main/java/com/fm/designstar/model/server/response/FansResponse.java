package com.fm.designstar.model.server.response;

import com.fm.designstar.base.BaseBean;
import com.fm.designstar.model.bean.FansBean;
import com.fm.designstar.model.bean.MessageBean;

import java.util.List;

public  class FansResponse extends BaseBean {

    private List<FansBean> list;


    public List<FansBean> getResult() {
        return list;
    }

    public void setResult(List<FansBean> result) {
        this.list = result;
    }
}
