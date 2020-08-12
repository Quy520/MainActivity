package com.fm.designstar.views.mine.contract;


import com.fm.designstar.base.BaseView;
import com.fm.designstar.model.server.response.HomeFindResponse;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface UseMomentContract {
    interface View extends BaseView {
        void UseMomentSuccess(HomeFindResponse homeFindResponse);

    }

    interface Presenter {
        void UseMoment(int pageNum, int pageSize,Integer type,Integer mediaType ,String uuid);

    }
}
