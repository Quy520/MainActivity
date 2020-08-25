package com.fm.designstar.views.mine.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.HomeRecomBody;
import com.fm.designstar.model.server.response.DesignerPageResponse;
import com.fm.designstar.model.server.response.findPageResponse;
import com.fm.designstar.views.mine.contract.DesignerPageContract;
import com.fm.designstar.views.mine.contract.DesignerRecordContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class DesignerPagePresenter extends BasePresenter<DesignerPageContract.View> implements DesignerPageContract.Presenter {
    @Override
    public void DesignerPage(int pageNum, int pageSize) {
        toSubscribe(HttpManager.getApi().designerPage( new HomeRecomBody(pageNum,  pageSize)), new AbstractHttpSubscriber<DesignerPageResponse>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(DesignerPageResponse loginResponse) {
                if (loginResponse!=null){
                    mView.DesignerPageSuccess(loginResponse);
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
