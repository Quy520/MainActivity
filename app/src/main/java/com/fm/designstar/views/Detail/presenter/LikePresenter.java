package com.fm.designstar.views.Detail.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.DeleteCombody;
import com.fm.designstar.model.server.body.LikeCombody;
import com.fm.designstar.model.server.response.LikeResponse;
import com.fm.designstar.views.Detail.contract.DelCommentContract;
import com.fm.designstar.views.Detail.contract.LikeContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class LikePresenter extends BasePresenter<LikeContract.View> implements LikeContract.Presenter {


    @Override
    public void Like(int type, long typeId) {
        toSubscribe(HttpManager.getApi().like(new LikeCombody(1,  typeId)), new AbstractHttpSubscriber<LikeResponse>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(LikeResponse commentsResponse) {

                mView.LikeSuccess(commentsResponse);

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
