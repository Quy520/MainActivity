package com.fm.designstar.views.mine.contract;


import com.fm.designstar.base.BaseView;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface UptaDesignerContract {
    interface View extends BaseView {
        void UptaDesignerSuccess();

    }

    interface Presenter {
        void UptaDesigner(String content, boolean flag, long id, Integer type);
    }
}
