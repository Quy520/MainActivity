package com.fm.designstar.views.mine.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.response.UserinfoResponse;
import com.fm.designstar.model.server.response.UserlikeResponse;
import com.fm.designstar.views.mine.contract.GetInfoContract;
import com.fm.designstar.views.mine.contract.LoginOutContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class GetInfoPresenter extends BasePresenter<GetInfoContract.View> implements GetInfoContract.Presenter {

    @Override
    public void GetotherLikeInfo(String id) {
        toSubscribe(HttpManager.getApi().getUserOtherInfo(id), new AbstractHttpSubscriber<UserlikeResponse>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(UserlikeResponse userlikeResponse ) {

                mView.GetotherLikeInfoSuccess(userlikeResponse);

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
    public void GetuserLikeInfo() {
        toSubscribe(HttpManager.getApi().getUserSelfInfo(), new AbstractHttpSubscriber<UserlikeResponse>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(UserlikeResponse Response) {
                mView.GetuserlikeInfoSuccess(Response);

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
    public void getOtherUserInfo(String uuid) {
        toSubscribe(HttpManager.getApi().getOtherUserInfo(uuid), new AbstractHttpSubscriber<UserinfoResponse>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(UserinfoResponse loginResponse) {

                mView.getOtherUserInfoSuccess(loginResponse);

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
