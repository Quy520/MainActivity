package com.fm.designstar.views.mine.contract;


import com.fm.designstar.base.BaseView;
import com.fm.designstar.model.server.response.UserinfoResponse;
import com.fm.designstar.model.server.response.UserlikeResponse;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface followContract {
    interface View extends BaseView {
        void followSuccess();
        void canclefollowSuccess();

    }

    interface Presenter {
        void follow(String uuid);
        void canclefollow(String uuid);

    }
}
