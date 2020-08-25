package com.fm.designstar.views.mine.contract;


import com.fm.designstar.base.BaseView;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface FirDesignerContract {
    interface View extends BaseView {
        void FirignerSuccess();

    }

    interface Presenter {
        void FirDesigner(long uuid);
    }
}
