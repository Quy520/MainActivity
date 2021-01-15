package com.fm.designstar.views.mine.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.Designerbody;
import com.fm.designstar.views.main.contract.DesignerContract;
import com.fm.designstar.views.mine.contract.BeDesignerContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class BeDesignerPresenter extends BasePresenter<BeDesignerContract.View> implements BeDesignerContract.Presenter {
    @Override
    public void Designer(String imgUrl, String address, String birthday, String corporation, String position, String realName,String contactNumber) {
        toSubscribe(HttpManager.getApi().Designer(new Designerbody(imgUrl,  address,  birthday,  corporation,  position,  realName, contactNumber)), new AbstractHttpSubscriber() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(Object loginResponse) {

                    mView.DesignerSuccess();

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
