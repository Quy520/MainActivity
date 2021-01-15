package com.fm.designstar.model.server.response;


import com.fm.designstar.base.BaseBean;
import com.fm.designstar.model.bean.DesignerBean;
import com.fm.designstar.model.bean.TagBean;

import java.util.List;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/27 11:21
 * @update : 2018/9/27
 */
public class RoleResponse extends BaseBean {

 private int role;
    private TagBean tagInfo;

    public TagBean getTagBean() {
        return tagInfo;
    }

    public void setTagBean(TagBean tagBean) {
        this.tagInfo = tagBean;
    }


    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
