package com.fm.designstar.views.Detail.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.bean.HomeFindBean;
import com.fm.designstar.views.Detail.contract.DeleteContract;
import com.fm.designstar.views.Detail.contract.FindBidContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class FindByIdPresenter extends BasePresenter<FindBidContract.View> implements FindBidContract.Presenter {


    @Override
    public void FindBid(long momentId) {
        toSubscribe(HttpManager.getApi().findMomentById( momentId), new AbstractHttpSubscriber<HomeFindBean>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(HomeFindBean commentsResponse) {

                mView.FindBidSuccess(commentsResponse);

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
