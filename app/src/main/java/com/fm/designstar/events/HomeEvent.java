package com.fm.designstar.events;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/25 16:13
 * @update : 2018/9/25
 */
public class HomeEvent extends BaseEvent {
    private int TAG;//为0时（登录失效时）调用退出接口，为1时（手动退出已调用退出接口）不调用

    public HomeEvent( int tag) {
        this.TAG = tag;
    }

    public int getTAG() {
        return TAG;
    }

    public void setTAG(int TAG) {
        this.TAG = TAG;
    }
}
