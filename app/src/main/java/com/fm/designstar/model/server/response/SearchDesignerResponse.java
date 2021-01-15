package com.fm.designstar.model.server.response;


import com.fm.designstar.base.BaseBean;
import com.fm.designstar.model.bean.DesignerBean;
import com.fm.designstar.model.bean.SearchDesignerBean;

import java.util.List;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/27 11:21
 * @update : 2018/9/27
 */
public class SearchDesignerResponse extends BaseBean {

  private List<SearchDesignerBean> list;
  private boolean hasNextPage;

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public List<SearchDesignerBean> getResult() {
        return list;
    }

    public void setResult(List<SearchDesignerBean> result) {
        this.list = result;
    }
}
