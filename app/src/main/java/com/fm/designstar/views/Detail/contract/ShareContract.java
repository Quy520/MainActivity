package com.fm.designstar.views.Detail.contract;


import com.fm.designstar.base.BaseView;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface ShareContract {
    interface View extends BaseView {
        void ViewSuccess();

    }

    interface Presenter {
        void Share(long  momentId);
    }
}
