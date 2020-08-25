package com.fm.designstar.views.mine.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.Designerbody;
import com.fm.designstar.model.server.body.Updatabody;
import com.fm.designstar.views.mine.contract.BeDesignerContract;
import com.fm.designstar.views.mine.contract.UptaDesignerContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class UpdataDesignerPresenter extends BasePresenter<UptaDesignerContract.View> implements UptaDesignerContract.Presenter {
    @Override
    public void UptaDesigner(String content, boolean flag, long id, Integer type) {
        toSubscribe(HttpManager.getApi().update(new Updatabody(content,  flag,  id,  type)), new AbstractHttpSubscriber() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(Object loginResponse) {

                    mView.UptaDesignerSuccess();

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
