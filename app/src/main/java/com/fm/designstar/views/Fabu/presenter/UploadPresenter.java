package com.fm.designstar.views.Fabu.presenter;


import com.fm.designstar.base.BasePresenter;
import com.fm.designstar.https.AbstractHttpSubscriber;
import com.fm.designstar.https.HttpManager;
import com.fm.designstar.model.bean.DesignerBean;
import com.fm.designstar.model.bean.MultimediaListBean;
import com.fm.designstar.model.bean.MultimediabodyBean;
import com.fm.designstar.model.server.body.uploadMomentbody;
import com.fm.designstar.model.server.response.TagInfoResponse;
import com.fm.designstar.views.Fabu.contract.GetTagContract;
import com.fm.designstar.views.Fabu.contract.UploadContract;

import java.util.List;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:20
 * @update : 2018/9/21
 */
public class UploadPresenter extends BasePresenter<UploadContract.View> implements UploadContract.Presenter {

    @Override
    public void Upload(String address, String content, long latitude, long longitude, int mediaType, int momentType, List<MultimediabodyBean> multimediaList, List<DesignerBean.TagsListBean> tagsList){
        toSubscribe(HttpManager.getApi().uploadMoment(new uploadMomentbody(address,  content,  latitude,  longitude,  mediaType,  momentType,  multimediaList, tagsList)), new AbstractHttpSubscriber() {
            @Override
            protected void onHttpStart() {
                mView.showLoading("", 0);
            }

            @Override
            protected void onHttpNext(Object tagInfoResponse ) {

                mView.UploadSuccess();

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
