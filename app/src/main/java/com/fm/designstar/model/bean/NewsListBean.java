package com.fm.designstar.model.bean;

import com.fm.designstar.base.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * GsonFormat gen
 */




        public  class NewsListBean extends BaseBean{
            /**
             * news_id : 1001
             * news_title : 手机充电速度到底取决于充电头还是数据线？以前一直搞错了
             * content_type : 1
             * web_url : https://m.toutiao.com/i6681254554287211015/?W2atIF=1
             * img_list : ["https://p3.pstatp.com/list/190x124/pgc-image/ROHTP7jD0TBlNZ"]
             * created : 1555878709
             * source_name : 凤凰新闻
             */

            private List<HomeFindBean> hot;
            private List<HomeFindBean> banner;
            private List<HomeFindBean> recom;


    public List<HomeFindBean> getHot() {
        return hot;
    }

    public void setHot(List<HomeFindBean> hot) {
        this.hot = hot;
    }

    public List<HomeFindBean> getBanner() {
        return banner;
    }

    public void setBanner(List<HomeFindBean> banner) {
        this.banner = banner;
    }

    public List<HomeFindBean> getRecom() {
        return recom;
    }

    public void setRecom(List<HomeFindBean> recom) {
        this.recom = recom;
    }
}

