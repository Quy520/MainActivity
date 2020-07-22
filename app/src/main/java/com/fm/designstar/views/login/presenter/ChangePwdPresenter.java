package com.fm.designstar.views.login.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.LoginBody;
import com.fm.designstar.model.server.body.chnagepwdbody;
import com.fm.designstar.model.server.response.LoginResponse;
import com.fm.designstar.views.login.contract.ChangePwdContract;
import com.fm.designstar.views.login.contract.LoginContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class ChangePwdPresenter extends BasePresenter<ChangePwdContract.View> implements ChangePwdContract.Presenter {
    @Override
    public void changePwd(String userPhone, String code,String pwd) {
        toSubscribe(HttpManager.getApi().changePwd(new chnagepwdbody(userPhone, code,pwd)), new AbstractHttpSubscriber() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(Object loginResponse) {

                    mView.changepwdSuccess();

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
