package com.fm.designstar.model.server.response;

import com.fm.designstar.base.BaseBean;
import com.fm.designstar.model.bean.BannerBean;
import com.fm.designstar.model.bean.FansBean;

import java.util.List;

public  class BannerResponse extends BaseBean {

    private List<BannerBean> list;
    private boolean hasNextPage;

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public List<BannerBean> getResult() {
        return list;
    }

    public void setResult(List<BannerBean> result) {
        this.list = result;
    }
}
