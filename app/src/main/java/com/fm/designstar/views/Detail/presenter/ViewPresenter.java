package com.fm.designstar.views.Detail.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.LikeCombody;
import com.fm.designstar.model.server.body.Viewbody;
import com.fm.designstar.model.server.response.LikeResponse;
import com.fm.designstar.views.Detail.contract.LikeContract;
import com.fm.designstar.views.Detail.contract.ViewContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class ViewPresenter extends BasePresenter<ViewContract.View> implements ViewContract.Presenter {


    @Override
    public void View(String actualTime, String duration, long momentId, int mediaType) {
        toSubscribe(HttpManager.getApi().view(new Viewbody(actualTime,  duration,  momentId,  mediaType)), new AbstractHttpSubscriber() {
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
