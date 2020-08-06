package com.fm.designstar.views.main.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.GetMessageBody;
import com.fm.designstar.model.server.body.HomeRecomBody;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.model.server.response.MessageResponse;
import com.fm.designstar.views.main.contract.HomeFindContract;
import com.fm.designstar.views.main.contract.MessageContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class MessagePresenter extends BasePresenter<MessageContract.View> implements MessageContract.Presenter {
    @Override
    public void Message(int pageNum, int pageSize,String type) {
        toSubscribe(HttpManager.getApi().getMessage(new GetMessageBody(pageNum,pageSize,type)), new AbstractHttpSubscriber<MessageResponse>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(MessageResponse loginResponse) {
                if (loginResponse!=null)

                    mView.MessageSuccess(loginResponse);

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
