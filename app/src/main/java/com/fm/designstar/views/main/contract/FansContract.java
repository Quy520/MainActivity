package com.fm.designstar.views.main.contract;


import com.fm.designstar.base.BaseView;
import com.fm.designstar.model.server.response.FansResponse;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface FansContract {
    interface View extends BaseView {
        void FansListSuccess(FansResponse Response);

        void GuanzhuListSuccess(FansResponse Response);

    }

    interface Presenter {
        void Fans(int pageNum, int pageSize, String followUserId);
        void Guanzhu(int pageNum, int pageSize, String followUserId);
    }
}
