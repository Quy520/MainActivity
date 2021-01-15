package com.fm.designstar.views.Detail.contract;


import com.fm.designstar.base.BaseView;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface DeleteContract {
    interface View extends BaseView {
        void DeleteSuccess(long momentId);

    }

    interface Presenter {
        void Delete(long momentId);
    }
}
