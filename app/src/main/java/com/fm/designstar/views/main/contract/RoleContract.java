package com.fm.designstar.views.main.contract;


import com.fm.designstar.base.BaseView;
import com.fm.designstar.model.server.response.RoleResponse;
import com.fm.designstar.model.server.response.TagInfoResponse;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface RoleContract {
    interface View extends BaseView {
        void GetRoleSuccess(RoleResponse infoResponse);

    }

    interface Presenter {
        void GetRole();
    }
}
