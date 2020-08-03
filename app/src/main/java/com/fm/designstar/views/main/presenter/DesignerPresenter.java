package com.fm.designstar.views.main.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.HomeRecomBody;
import com.fm.designstar.model.server.response.DesignerResponse;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.views.main.contract.DesignerContract;
import com.fm.designstar.views.main.contract.HomeFindContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class DesignerPresenter extends BasePresenter<DesignerContract.View> implements DesignerContract.Presenter {
    @Override
    public void Designer(int pageNum, int pageSize) {
        toSubscribe(HttpManager.getApi().discoverDesigners(new HomeRecomBody(pageNum,pageSize)), new AbstractHttpSubscriber<DesignerResponse>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(DesignerResponse loginResponse) {

                    mView.DesignerSuccess(loginResponse);

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
