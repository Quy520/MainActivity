package com.fm.designstar.views.main.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.Addbody;
import com.fm.designstar.model.server.body.FansListBody;
import com.fm.designstar.model.server.response.FansResponse;
import com.fm.designstar.views.main.contract.AddContract;
import com.fm.designstar.views.main.contract.FansContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class FansPresenter extends BasePresenter<FansContract.View> implements FansContract.Presenter {


    @Override
    public void Fans(int pageNum, int pageSize, String followUserId) {
        toSubscribe(HttpManager.getApi().findByFollowUserId(new FansListBody( pageNum,  pageSize,  followUserId)), new AbstractHttpSubscriber<FansResponse>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(FansResponse Response) {

                mView.FansListSuccess(Response);

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
    public void Guanzhu(int pageNum, int pageSize, String followUserId) {
        toSubscribe(HttpManager.getApi().findByUserId(new FansListBody( pageNum,  pageSize,  followUserId)), new AbstractHttpSubscriber<FansResponse>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(FansResponse Response) {

                mView.GuanzhuListSuccess(Response);

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
