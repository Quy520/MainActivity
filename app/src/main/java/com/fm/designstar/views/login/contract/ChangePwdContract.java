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
public interface ChangePwdContract {
    interface View extends BaseView {
        void changepwdSuccess();
    }

    interface Presenter {
        void changePwd(String userPhone, String code, String pwd);
    }
}
