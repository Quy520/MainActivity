package com.fm.designstar.views.main.contract;


import com.fm.designstar.base.BaseView;
import com.fm.designstar.model.server.response.BannerResponse;
import com.fm.designstar.model.server.response.HomeFindResponse;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface HomeRecomContract {
    interface View extends BaseView {
        void HomeRecomSuccess(HomeFindResponse homeFindResponse);
        void HomehotecomSuccess(HomeFindResponse homeFindResponse);
        void HomebannerRecomSuccess(BannerResponse homeFindResponse);


    }

    interface Presenter {
        void HomeRecom(int pageNum, int pageSize);
        void Homehote(int pageNum, int pageSize);
        void Homebanner(int pageNum, int pageSize);
    }
}
