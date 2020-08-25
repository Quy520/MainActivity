package com.fm.designstar.views.mine.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.DesignerStatebody;
import com.fm.designstar.model.server.body.HomeRecomBody;
import com.fm.designstar.model.server.response.findPageResponse;
import com.fm.designstar.views.mine.contract.DesignerRecordContract;
import com.fm.designstar.views.mine.contract.FindDesignerContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class DesignerRecordPresenter extends BasePresenter<DesignerRecordContract.View> implements DesignerRecordContract.Presenter {
    @Override
    public void DesignerRecord(int pageNum, int pageSize) {
        toSubscribe(HttpManager.getApi().findPage( new HomeRecomBody(pageNum,  pageSize)), new AbstractHttpSubscriber<findPageResponse>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(findPageResponse loginResponse) {
                if (loginResponse!=null){
                    mView.DesignerRecordSuccess(loginResponse);
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
