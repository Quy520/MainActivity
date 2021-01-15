package com.fm.designstar.views.mine.contract;


import com.fm.designstar.base.BaseView;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface BeDesignerContract {
    interface View extends BaseView {
        void DesignerSuccess();

    }

    interface Presenter {
        void Designer(String imgUrl, String address, String birthday, String corporation, String position, String realName,String contactNumber);
    }
}
