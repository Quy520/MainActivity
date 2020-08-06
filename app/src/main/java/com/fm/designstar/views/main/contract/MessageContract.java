package com.fm.designstar.views.main.contract;


import com.fm.designstar.base.BaseView;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.model.server.response.MessageResponse;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface MessageContract {
    interface View extends BaseView {
        void MessageSuccess(MessageResponse response);

    }

    interface Presenter {
        void Message(int pageNum, int pageSize,String messageType);
    }
}
