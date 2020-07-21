package com.fm.designstar.views.login.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.LoginBody;
import com.fm.designstar.model.server.response.LoginResponse;
import com.fm.designstar.views.login.contract.LoginContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    @Override
    public void login(String userPhone, String userPwd,String deviceid) {
        toSubscribe(HttpManager.getApi().login(new LoginBody(userPhone, userPwd,deviceid)), new AbstractHttpSubscriber<LoginResponse>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(LoginResponse loginResponse) {
                if (loginResponse != null) {
                    mView.loginSuccess(loginResponse);
                }
            }


            @Override
            protected void onHttpError(String message) {
                mView.showErrorMsg(message, 0);
            }

            @Override
            protected void onHttpCompleted() {
                mView.stopLoading(0);
            }
        });
    }
}
