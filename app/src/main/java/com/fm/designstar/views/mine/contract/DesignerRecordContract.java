package com.fm.designstar.views.mine.contract;


import com.fm.designstar.base.BaseView;
import com.fm.designstar.model.server.body.DesignerStatebody;
import com.fm.designstar.model.server.response.findPageResponse;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface DesignerRecordContract {
    interface View extends BaseView {
        void DesignerRecordSuccess(findPageResponse pageResponse);

    }

    interface Presenter {
        void DesignerRecord(int pageNum, int pageSize);
    }
}
