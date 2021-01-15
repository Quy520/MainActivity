package com.fm.designstar.views.main.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.HomeRecomBody;
import com.fm.designstar.model.server.response.BannerResponse;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.views.main.contract.HomeRecomContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class HomeRecomPresenter extends BasePresenter<HomeRecomContract.View> implements HomeRecomContract.Presenter {
    @Override
    public void HomeRecom(int pageNum, int pageSize) {
        toSubscribe(HttpManager.getApi().homeRecommend(new HomeRecomBody(pageNum,pageSize)), new AbstractHttpSubscriber<HomeFindResponse>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(HomeFindResponse loginResponse) {

                    mView.HomeRecomSuccess(loginResponse);

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

    @Override
    public void Homehote(int pageNum, int pageSize) {
        toSubscribe(HttpManager.getApi().getHotMoment(new HomeRecomBody(pageNum,pageSize)), new AbstractHttpSubscriber<HomeFindResponse>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(HomeFindResponse loginResponse) {

                mView.HomehotecomSuccess(loginResponse);

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

    @Override
    public void Homebanner(int pageNum, int pageSize) {
        toSubscribe(HttpManager.getApi().getBannerMoment(new HomeRecomBody(pageNum,pageSize)), new AbstractHttpSubscriber<BannerResponse>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(BannerResponse loginResponse) {

                mView.HomebannerRecomSuccess(loginResponse);

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
