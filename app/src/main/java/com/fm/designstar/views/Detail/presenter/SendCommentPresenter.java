package com.fm.designstar.views.Detail.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.SendMessageBody;
import com.fm.designstar.model.server.response.CommentsResponse;
import com.fm.designstar.views.Detail.contract.GetCommentContract;
import com.fm.designstar.views.Detail.contract.SendCommentContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class SendCommentPresenter extends BasePresenter<SendCommentContract.View> implements SendCommentContract.Presenter {


    @Override
    public void SendComment(String content, int momentType, String momentIde) {
        toSubscribe(HttpManager.getApi().insertComment(new SendMessageBody(content,momentType,momentIde)), new AbstractHttpSubscriber() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(Object commentsResponse) {

                mView.SendCommentSuccess();

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
