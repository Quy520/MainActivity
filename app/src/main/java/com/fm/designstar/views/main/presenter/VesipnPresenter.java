package com.fm.designstar.views.main.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.Addbody;
import com.fm.designstar.model.server.body.VesionBody;
import com.fm.designstar.model.server.response.VesionResponse;
import com.fm.designstar.views.main.contract.AddContract;
import com.fm.designstar.views.main.contract.VesionContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class VesipnPresenter extends BasePresenter<VesionContract.View> implements VesionContract.Presenter {
    @Override
    public void Vesion(int code, String systemType){
        toSubscribe(HttpManager.getApi().getVersion(new VesionBody( code, "android")), new AbstractHttpSubscriber<VesionResponse>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(VesionResponse Response) {

                    mView.VesionSuccess(Response);

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
