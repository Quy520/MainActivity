package com.fm.designstar.views.login.contract;

import com.fm.designstar.base.BaseView;

public interface SendMsgContract {
    interface View extends BaseView {
        void SendMsgSuccess();
        void SendMsgforgetSuccess();
    }

    interface Presenter {
        void SendMsg(String userPhone);
        void SendMsgforget(String userPhone);
    }
}
