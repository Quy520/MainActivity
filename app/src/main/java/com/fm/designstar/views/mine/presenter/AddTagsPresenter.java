package com.fm.designstar.views.mine.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.server.body.AddTagsBody;
import com.fm.designstar.model.server.body.Designerbody;
import com.fm.designstar.views.mine.contract.AddTagsContract;
import com.fm.designstar.views.mine.contract.BeDesignerContract;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class AddTagsPresenter extends BasePresenter<AddTagsContract.View> implements AddTagsContract.Presenter {
    @Override
    public void AddTags(String name,int type) {
        toSubscribe(HttpManager.getApi().AddTag(new AddTagsBody(type,name)), new AbstractHttpSubscriber() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(Object loginResponse) {

                    mView.AddTagsSuccess();

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
