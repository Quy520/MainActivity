package com.fm.designstar.views.main.contract;


import com.fm.designstar.base.BaseView;
import com.fm.designstar.model.server.response.VesionResponse;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface VesionContract {
    interface View extends BaseView {
        void VesionSuccess(VesionResponse Response);

    }

    interface Presenter {
        void Vesion(int code, String systemType);
    }
}
