package com.fm.designstar.views.login.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.chnagepwdbody;
import com.fm.designstar.model.server.body.comInfobody;
import com.fm.designstar.views.login.contract.AppRegistContract;
import com.fm.designstar.views.login.contract.ComInfoContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class CominfoPresenter extends BasePresenter<ComInfoContract.View> implements ComInfoContract.Presenter {
    @Override
    public void ComInfo(String avatar, String userName, String birthday, int sex,String address,String signature,String contactNumber) {
        toSubscribe(HttpManager.getApi().comInfo(new comInfobody(avatar,  userName,  birthday,  sex,1,address,signature,contactNumber)), new AbstractHttpSubscriber() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(Object loginResponse) {

                    mView.ComInfoSuccess();

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
