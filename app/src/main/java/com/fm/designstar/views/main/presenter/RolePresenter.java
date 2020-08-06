package com.fm.designstar.views.main.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.Addbody;
import com.fm.designstar.model.server.response.RoleResponse;
import com.fm.designstar.views.main.contract.AddContract;
import com.fm.designstar.views.main.contract.RoleContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class RolePresenter extends BasePresenter<RoleContract.View> implements RoleContract.Presenter {

    @Override
    public void GetRole() {
        toSubscribe(HttpManager.getApi().getUserRoleInfo(), new AbstractHttpSubscriber<RoleResponse>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(RoleResponse Response) {

                mView.GetRoleSuccess(Response);

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
