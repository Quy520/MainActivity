package com.fm.designstar.views.main.contract;


import com.fm.designstar.base.BaseView;
import com.fm.designstar.model.server.response.HomeFindResponse;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface HomeGuanzhuContract {
    interface View extends BaseView {
        void HomeGuanzhuSuccess(HomeFindResponse homeFindResponse);

    }

    interface Presenter {
        void HomeGuanzhu(int pageNum, int pageSize);
    }
}
