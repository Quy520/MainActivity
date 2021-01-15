package com.fm.designstar.views.Detail.contract;


import com.fm.designstar.base.BaseView;
import com.fm.designstar.model.server.response.LikeResponse;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface ViewContract {
    interface View extends BaseView {
        void ViewSuccess();

    }

    interface Presenter {
        void View(String actualTime, String duration, long momentId, int mediaType);
    }
}
