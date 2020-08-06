package com.fm.designstar.views.Detail.contract;


import com.fm.designstar.base.BaseView;
import com.fm.designstar.model.server.response.LikeResponse;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface LikeContract {
    interface View extends BaseView {
        void LikeSuccess(LikeResponse likeResponse);

    }

    interface Presenter {
        void Like(int type, long typeId);
    }
}
