package com.fm.designstar.views.main.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.HomeRecomBody;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.views.main.contract.HomeFindContract;
import com.fm.designstar.views.main.contract.HomeGuanzhuContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class HomeGuanzhuPresenter extends BasePresenter<HomeGuanzhuContract.View> implements HomeGuanzhuContract.Presenter {
    @Override
    public void HomeGuanzhu(int pageNum, int pageSize) {
        toSubscribe(HttpManager.getApi().homeFollow(new HomeRecomBody(pageNum,pageSize)), new AbstractHttpSubscriber<HomeFindResponse>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(HomeFindResponse loginResponse) {

                    mView.HomeGuanzhuSuccess(loginResponse);

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
