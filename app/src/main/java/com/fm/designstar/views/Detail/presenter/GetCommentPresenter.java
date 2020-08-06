package com.fm.designstar.views.Detail.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.Addbody;
import com.fm.designstar.model.server.body.GetCommentBody;
import com.fm.designstar.model.server.response.CommentsResponse;
import com.fm.designstar.views.Detail.contract.GetCommentContract;
import com.fm.designstar.views.main.contract.AddContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class GetCommentPresenter extends BasePresenter<GetCommentContract.View> implements GetCommentContract.Presenter {


    @Override
    public void GetComment(int pageNum, int pageSize, String messageType) {
        toSubscribe(HttpManager.getApi().findByMomentId(messageType, pageNum,  pageSize  ), new AbstractHttpSubscriber<CommentsResponse>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(CommentsResponse commentsResponse) {
if (commentsResponse!=null)
                mView.GetCommentSuccess(commentsResponse);

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
