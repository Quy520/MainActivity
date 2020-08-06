package com.fm.designstar.views.Fabu.contract;


import com.fm.designstar.base.BaseView;
import com.fm.designstar.model.bean.DesignerBean;
import com.fm.designstar.model.bean.MultimediaListBean;
import com.fm.designstar.model.bean.MultimediabodyBean;
import com.fm.designstar.model.server.response.TagInfoResponse;

import java.util.List;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 13:14
 * @update : 2018/9/21
 */
public interface UploadContract {
    interface View extends BaseView {
        void UploadSuccess();

    }

    interface Presenter {
        void Upload(String address, String content, long latitude, long longitude, int mediaType, int momentType, List<MultimediabodyBean> multimediaList, List<DesignerBean.TagsListBean> tagsList);
    }
}
