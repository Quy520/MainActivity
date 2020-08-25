package com.fm.designstar.model.server.response;

import com.fm.designstar.base.BaseBean;
import com.fm.designstar.model.bean.DesignerPageBean;
import com.fm.designstar.model.bean.RecordBean;

import java.util.List;

public  class DesignerPageResponse extends BaseBean {
    private boolean hasNextPage;


    private List<DesignerPageBean> list;



    public List<DesignerPageBean> getResult() {
        return list;
    }

    public void setResult(List<DesignerPageBean> result) {
        this.list = result;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }
}
