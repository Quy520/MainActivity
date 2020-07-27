package com.fm.designstar.views.mine.contract;


import com.fm.designstar.base.BaseView;
import com.fm.designstar.model.server.response.LoginResponse;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface LoginOutContract {
    interface View extends BaseView {
        void LoginOutSuccess();

    }

    interface Presenter {
        void LoginOut();
    }
}