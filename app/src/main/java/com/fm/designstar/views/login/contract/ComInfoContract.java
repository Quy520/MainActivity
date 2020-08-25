package com.fm.designstar.views.login.contract;


import com.fm.designstar.base.BaseView;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface ComInfoContract {
    interface View extends BaseView {
        void ComInfoSuccess();
    }

    interface Presenter {
        void ComInfo(String avatar, String userName, String birthday, int sex,String address,String signature);
    }
}
