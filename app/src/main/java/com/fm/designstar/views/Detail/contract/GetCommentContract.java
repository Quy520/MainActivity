package com.fm.designstar.views.Detail.contract;


import com.fm.designstar.base.BaseView;
import com.fm.designstar.model.server.response.CommentsResponse;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface GetCommentContract {
    interface View extends BaseView {
        void GetCommentSuccess(CommentsResponse commentsResponse);

    }

    interface Presenter {
        void GetComment(int pageNum, int pageSize,String messageType);
    }
}
