package com.fm.designstar.views.login.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.sendMsgBody;
import com.fm.designstar.views.login.contract.SendMsgContract;

public class SendMsgPresenter extends BasePresenter<SendMsgContract.View> implements SendMsgContract.Presenter {
    @Override
    public void SendMsg(String userPhone) {
        toSubscribe(HttpManager.getApi().sendMsg(userPhone), new AbstractHttpSubscriber() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(Object o) {
                mView.SendMsgSuccess();
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

    @Override
    public void SendMsgforget(String userPhone) {
        toSubscribe(HttpManager.getApi().sendMsgforget(userPhone), new AbstractHttpSubscriber() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(Object o) {
                mView.SendMsgforgetSuccess();
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
