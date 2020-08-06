package com.fm.designstar.views.Fabu.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.response.TagInfoResponse;
import com.fm.designstar.model.server.response.UserinfoResponse;
import com.fm.designstar.model.server.response.UserlikeResponse;
import com.fm.designstar.views.Fabu.contract.GetTagContract;
import com.fm.designstar.views.mine.contract.GetInfoContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class GetTagPresenter extends BasePresenter<GetTagContract.View> implements GetTagContract.Presenter {

    @Override
    public void GetTag() {
        toSubscribe(HttpManager.getApi().findAllTagInfo(), new AbstractHttpSubscriber<TagInfoResponse>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(TagInfoResponse tagInfoResponse ) {

                mView.GetTagSuccess(tagInfoResponse);

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
