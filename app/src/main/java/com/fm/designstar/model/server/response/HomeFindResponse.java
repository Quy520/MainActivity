package com.fm.designstar.model.server.response;


import com.fm.designstar.base.BaseBean;
import com.fm.designstar.model.bean.HomeFindBean;

import java.util.List;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/27 11:21
 * @update : 2018/9/27
 */
public class HomeFindResponse extends BaseBean {

  private List<HomeFindBean> result;

    public List<HomeFindBean> getResult() {
        return result;
    }

    public void setResult(List<HomeFindBean> result) {
        this.result = result;
    }
}
