package com.fm.designstar.views.main.contract;


import com.fm.designstar.base.BaseView;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface UpdataLocationContract {
    interface View extends BaseView {
        void UpdataLocationSuccess();

    }

    interface Presenter {
        void UpdataLocation(String address, String city, String district, double latitude, double longitude);
    }
}
