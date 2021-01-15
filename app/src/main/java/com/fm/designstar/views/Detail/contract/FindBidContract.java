package com.fm.designstar.views.Detail.contract;


import com.fm.designstar.base.BaseView;
import com.fm.designstar.model.bean.HomeFindBean;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface FindBidContract {
    interface View extends BaseView {
        void FindBidSuccess(HomeFindBean homeFindBean);

    }

    interface Presenter {
        void FindBid(long momentId);
    }
}
