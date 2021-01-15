package com.fm.designstar.views.main.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.FansListBody;
import com.fm.designstar.model.server.body.Finddesignerbody;
import com.fm.designstar.model.server.response.DesignerResponse;
import com.fm.designstar.model.server.response.FansResponse;
import com.fm.designstar.model.server.response.SearchDesignerResponse;
import com.fm.designstar.views.main.contract.FansContract;
import com.fm.designstar.views.main.contract.FindDesignerContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class FindDesignerPresenter extends BasePresenter<FindDesignerContract.View> implements FindDesignerContract.Presenter {


    @Override
    public void FindDesigner( String name,int s1,int n) {
        toSubscribe(HttpManager.getApi().findDesigner(new Finddesignerbody(name,1,10)), new AbstractHttpSubscriber<SearchDesignerResponse>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(SearchDesignerResponse Response) {

                mView.FindDesignerSuccess(Response);

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
