package com.fm.designstar.views.login.contract;


import com.fm.designstar.base.BaseView;
import com.fm.designstar.model.server.response.LoginResponse;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface LoginContract {
    interface View extends BaseView {
        void loginSuccess(LoginResponse userToken);
    }

    interface Presenter {
        void login(String userPhone, String userPwd,String deviceId);
    }
}
