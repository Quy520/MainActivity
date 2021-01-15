package com.fm.designstar.views.Detail.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.views.Detail.contract.DeleteContract;
import com.fm.designstar.views.Detail.contract.ShareContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class DeletePresenter extends BasePresenter<DeleteContract.View> implements DeleteContract.Presenter {


    @Override
    public void Delete(long momentId) {
        toSubscribe(HttpManager.getApi().deleteMoment( momentId), new AbstractHttpSubscriber() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(Object commentsResponse) {

                mView.DeleteSuccess(momentId);

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
