package com.fm.designstar.views.main.contract;


import com.fm.designstar.base.BaseView;
import com.fm.designstar.model.server.response.DesignerResponse;
import com.fm.designstar.model.server.response.RoleResponse;
import com.fm.designstar.model.server.response.SearchDesignerResponse;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface FindDesignerContract {
    interface View extends BaseView {
        void FindDesignerSuccess(SearchDesignerResponse infoResponse);

    }

    interface Presenter {
        void FindDesigner(String name,int n,int s);
    }
}
