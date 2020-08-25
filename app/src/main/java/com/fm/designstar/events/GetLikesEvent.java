package com.fm.designstar.events;

import java.util.List;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/25 16:13
 * @update : 2018/9/25
 */
public class GetLikesEvent extends BaseEvent {
    private boolean select;//为0时（登录失效时）调用退出接口，为1时（手动退出已调用退出接口）不调用

   private int num;

    public GetLikesEvent(boolean select, int num) {
        this.select = select;
        this.num = num;
    }



    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getName() {
        return num;
    }

    public void setName(int num) {
        this.num = num;
    }
}
