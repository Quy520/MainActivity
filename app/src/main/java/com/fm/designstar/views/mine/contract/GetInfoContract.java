package com.fm.designstar.views.mine.contract;


import com.fm.designstar.base.BaseView;
import com.fm.designstar.model.server.response.UserinfoResponse;
import com.fm.designstar.model.server.response.UserlikeResponse;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface GetInfoContract {
    interface View extends BaseView {
        void GetotherLikeInfoSuccess(UserlikeResponse userlikeResponse);
        void GetuserlikeInfoSuccess(UserlikeResponse userlikeResponse);
        void getOtherUserInfoSuccess(UserinfoResponse response);
    }

    interface Presenter {
        void GetotherLikeInfo(String id);
        void GetuserLikeInfo();
        void getOtherUserInfo(String uuid);

    }
}
