package com.fm.designstar.model.server.response;


import com.fm.designstar.base.BaseBean;
import com.fm.designstar.model.bean.DesignerBean;
import com.fm.designstar.model.bean.HomeFindBean;

import java.util.List;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/27 11:21
 * @update : 2018/9/27
 */
public class DesignerResponse extends BaseBean {

  private List<DesignerBean> result;
  private boolean hasNextPage;

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public List<DesignerBean> getResult() {
        return result;
    }

    public void setResult(List<DesignerBean> result) {
        this.result = result;
    }
}
