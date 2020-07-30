package com.fm.designstar.views.main.contract;


import com.fm.designstar.base.BaseView;
import com.fm.designstar.model.server.response.HomeFindResponse;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface AddContract {
    interface View extends BaseView {
        void AddSuccess(String Response );

    }

    interface Presenter {
        void Add(String deviceNo, String guangKey, String operateSystem);
    }
}
