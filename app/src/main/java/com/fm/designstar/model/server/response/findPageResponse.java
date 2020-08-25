package com.fm.designstar.model.server.response;

import com.fm.designstar.base.BaseBean;
import com.fm.designstar.model.bean.FansBean;
import com.fm.designstar.model.bean.RecordBean;

import java.util.List;

public  class findPageResponse extends BaseBean {

    private List<RecordBean> list;
    private boolean hasNextPage;


    public List<RecordBean> getResult() {
        return list;
    }

    public void setResult(List<RecordBean> result) {
        this.list = result;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }
}
