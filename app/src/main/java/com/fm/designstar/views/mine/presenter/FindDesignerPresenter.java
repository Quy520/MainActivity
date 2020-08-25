package com.fm.designstar.views.mine.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.DesignerStatebody;
import com.fm.designstar.model.server.body.Designerbody;
import com.fm.designstar.views.mine.contract.BeDesignerContract;
import com.fm.designstar.views.mine.contract.FindDesignerContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class FindDesignerPresenter extends BasePresenter<FindDesignerContract.View> implements FindDesignerContract.Presenter {
    @Override
    public void FindDesigner() {
        toSubscribe(HttpManager.getApi().findByStatus(), new AbstractHttpSubscriber<DesignerStatebody>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(DesignerStatebody loginResponse) {
                if (loginResponse!=null){
                    mView.DFindDesignerSuccess(loginResponse);
                }



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
