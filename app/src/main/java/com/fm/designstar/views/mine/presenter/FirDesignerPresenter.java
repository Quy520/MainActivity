package com.fm.designstar.views.mine.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.Designerbody;
import com.fm.designstar.model.server.body.Firdesignerbody;
import com.fm.designstar.views.mine.contract.BeDesignerContract;
import com.fm.designstar.views.mine.contract.FirDesignerContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class FirDesignerPresenter extends BasePresenter<FirDesignerContract.View> implements FirDesignerContract.Presenter {
    @Override
    public void FirDesigner(long uuid) {
        toSubscribe(HttpManager.getApi().fireDesigner(new Firdesignerbody(uuid)), new AbstractHttpSubscriber() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(Object loginResponse) {

                    mView.FirignerSuccess();

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
