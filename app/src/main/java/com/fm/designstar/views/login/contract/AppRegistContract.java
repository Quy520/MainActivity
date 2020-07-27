package com.fm.designstar.views.login.contract;


import com.fm.designstar.base.BaseView;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface AppRegistContract {
    interface View extends BaseView {
        void AppRegistSuccess();
    }

    interface Presenter {
        void AppRegist(String userPhone, String code, String pwd);
    }
}
