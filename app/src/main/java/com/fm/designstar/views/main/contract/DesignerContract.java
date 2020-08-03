package com.fm.designstar.views.main.contract;


import com.fm.designstar.base.BaseView;
import com.fm.designstar.model.server.response.DesignerResponse;
import com.fm.designstar.model.server.response.HomeFindResponse;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface DesignerContract {
    interface View extends BaseView {
        void DesignerSuccess(DesignerResponse homeFindResponse);

    }

    interface Presenter {
        void Designer(int pageNum, int pageSize);
    }
}
