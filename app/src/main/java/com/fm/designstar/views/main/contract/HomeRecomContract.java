package com.fm.designstar.views.main.contract;


import com.fm.designstar.base.BaseView;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface HomeRecomContract {
    interface View extends BaseView {
        void HomeRecomSuccess();

    }

    interface Presenter {
        void HomeRecom(int pageNum, int pageSize);
    }
}
