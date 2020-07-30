package com.fm.designstar.views.main.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.Addbody;
import com.fm.designstar.model.server.body.HomeRecomBody;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.views.main.contract.AddContract;
import com.fm.designstar.views.main.contract.HomeFindContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class AddPresenter extends BasePresenter<AddContract.View> implements AddContract.Presenter {
    @Override
    public void Add(String deviceNo, String guangKey, String operateSystem) {
        toSubscribe(HttpManager.getApi().add(new Addbody( deviceNo,  guangKey,  "android")), new AbstractHttpSubscriber<String>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(String Response) {

                    mView.AddSuccess(Response);

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
