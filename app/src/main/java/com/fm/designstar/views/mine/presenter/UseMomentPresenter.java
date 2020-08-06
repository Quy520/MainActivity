package com.fm.designstar.views.mine.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.HomeRecomBody;
import com.fm.designstar.model.server.body.UserMomentBody;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.views.main.contract.HomeRecomContract;
import com.fm.designstar.views.mine.contract.UseMomentContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class UseMomentPresenter extends BasePresenter<UseMomentContract.View> implements UseMomentContract.Presenter {
    @Override
    public void UseMoment(int pageNum, int pageSize,int type,String uuid) {
        toSubscribe(HttpManager.getApi().userMoment(new UserMomentBody(pageNum,pageSize,type, uuid)), new AbstractHttpSubscriber<HomeFindResponse>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(HomeFindResponse loginResponse) {

                    mView.UseMomentSuccess(loginResponse);

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
