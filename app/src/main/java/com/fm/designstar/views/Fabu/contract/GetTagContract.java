package com.fm.designstar.views.Fabu.contract;


import com.fm.designstar.base.BaseView;
import com.fm.designstar.model.bean.TagBean;
import com.fm.designstar.model.server.response.TagInfoResponse;

import java.util.List;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface GetTagContract {
    interface View extends BaseView {
        void GetTagSuccess(TagInfoResponse infoResponse);
        void setTagSucess();

    }

    interface Presenter {
        void GetTag(int type);
        void setTag(String userId, List<TagBean> tagsList);
    }
}
