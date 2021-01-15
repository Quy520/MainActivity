package com.fm.designstar.views.Fabu.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.bean.DesignerTagsInfoVoBean;
import com.fm.designstar.model.bean.TagBean;
import com.fm.designstar.model.server.body.AdddesignerTagsCombody;
import com.fm.designstar.model.server.body.TagsBody;
import com.fm.designstar.model.server.response.DesignerTagInfoResponse;
import com.fm.designstar.model.server.response.TagInfoResponse;
import com.fm.designstar.model.server.response.UserinfoResponse;
import com.fm.designstar.model.server.response.UserlikeResponse;
import com.fm.designstar.views.Fabu.contract.GetTagContract;
import com.fm.designstar.views.mine.contract.GetInfoContract;

import java.util.List;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class GetTagPresenter extends BasePresenter<GetTagContract.View> implements GetTagContract.Presenter {

    @Override
    public void GetTag(int type) {
        toSubscribe(HttpManager.getApi().findAllTagInfo(type), new AbstractHttpSubscriber<DesignerTagInfoResponse>() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(DesignerTagInfoResponse tagInfoResponse ) {

                mView.GetTagSuccess(tagInfoResponse);

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

    @Override
    public void setTag(String userId, List<TagBean> tagsList) {
        toSubscribe(HttpManager.getApi().addDesignerTags(new AdddesignerTagsCombody(userId,tagsList)), new AbstractHttpSubscriber() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(Object tagInfoResponse ) {

                mView.setTagSucess();

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
