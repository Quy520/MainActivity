package com.fm.designstar.views.Detail.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.DeleteCombody;
import com.fm.designstar.model.server.body.SendMessageBody;
import com.fm.designstar.views.Detail.contract.DelCommentContract;
import com.fm.designstar.views.Detail.contract.SendCommentContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class DelCommentPresenter extends BasePresenter<DelCommentContract.View> implements DelCommentContract.Presenter {


    @Override
    public void DelComment(String momentIde,String momentId) {
        toSubscribe(HttpManager.getApi().delComment(new DeleteCombody(momentIde,momentId)), new AbstractHttpSubscriber() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(Object commentsResponse) {

                mView.DelCommentSuccess();

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
