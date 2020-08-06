package com.fm.designstar.views.Fabu.contract;


import com.fm.designstar.base.BaseView;
import com.fm.designstar.model.server.response.TagInfoResponse;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface GetTagContract {
    interface View extends BaseView {
        void GetTagSuccess(TagInfoResponse infoResponse);

    }

    interface Presenter {
        void GetTag();
    }
}
