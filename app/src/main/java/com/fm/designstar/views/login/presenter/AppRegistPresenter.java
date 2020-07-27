package com.fm.designstar.views.login.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.chnagepwdbody;
import com.fm.designstar.views.login.contract.AppRegistContract;
import com.fm.designstar.views.login.contract.ChangePwdContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class AppRegistPresenter extends BasePresenter<AppRegistContract.View> implements AppRegistContract.Presenter {
    @Override
    public void AppRegist(String userPhone, String code,String pwd) {
        toSubscribe(HttpManager.getApi().AppRegist(new chnagepwdbody(userPhone, code,pwd)), new AbstractHttpSubscriber() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(Object loginResponse) {

                    mView.AppRegistSuccess();

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
