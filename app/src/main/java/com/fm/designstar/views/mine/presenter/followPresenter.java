package com.fm.designstar.views.mine.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.followbody;
import com.fm.designstar.views.mine.contract.LoginOutContract;
import com.fm.designstar.views.mine.contract.followContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class followPresenter extends BasePresenter<followContract.View> implements followContract.Presenter {

    @Override
    public void follow(String uuid) {
        toSubscribe(HttpManager.getApi().follow(new followbody((uuid))), new AbstractHttpSubscriber() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(Object loginResponse) {

                mView.followSuccess();

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
    public void canclefollow(String uuid) {
        toSubscribe(HttpManager.getApi().cancelfollow(new followbody((uuid))), new AbstractHttpSubscriber() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(Object loginResponse) {

                mView.canclefollowSuccess();

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
