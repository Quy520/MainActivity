package com.fm.designstar.views.Detail.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.Viewbody;
import com.fm.designstar.views.Detail.contract.ShareContract;
import com.fm.designstar.views.Detail.contract.ViewContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class SharePresenter extends BasePresenter<ShareContract.View> implements ShareContract.Presenter {


    @Override
    public void Share(long momentId) {
        toSubscribe(HttpManager.getApi().forward( momentId), new AbstractHttpSubscriber() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(Object commentsResponse) {

                mView.ViewSuccess();

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
