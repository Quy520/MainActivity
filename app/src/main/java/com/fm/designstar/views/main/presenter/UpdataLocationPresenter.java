package com.fm.designstar.views.main.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.Addbody;
import com.fm.designstar.model.server.body.Locationbody;
import com.fm.designstar.views.main.contract.AddContract;
import com.fm.designstar.views.main.contract.UpdataLocationContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class UpdataLocationPresenter extends BasePresenter<UpdataLocationContract.View> implements UpdataLocationContract.Presenter {
    @Override
    public void UpdataLocation(String address, String city, String district, double latitude, double longitude) {
        toSubscribe(HttpManager.getApi().updateUserLocation(new Locationbody(  address,  city,  district,  latitude,  longitude)), new AbstractHttpSubscriber() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(Object Response) {

                    mView.UpdataLocationSuccess();

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
