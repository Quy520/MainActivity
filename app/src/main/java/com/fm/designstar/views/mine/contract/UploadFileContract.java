package com.fm.designstar.views.mine.contract;


import com.fm.designstar.base.BaseView;
import com.fm.designstar.model.server.response.OssTokenResponse;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/26 10:34
 * @update : 2018/9/26
 */
public interface UploadFileContract {
    interface View extends BaseView {

        void getOssTokenSuccess(OssTokenResponse response);
        void uploadImageSuccess(String url);
    }

    interface Presenter {
        void getOssToken();


        void uploadImage(OssTokenResponse response, String path);
    }
}
