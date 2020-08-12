package com.fm.designstar.events;

public class messageEvent extends BaseEvent {
    private int TAG;//为0时（登录失效时）调用退出接口，为1时（手动退出已调用退出接口）不调用

    public messageEvent(int tag) {
        this.TAG = tag;
    }

    public int getTAG() {
        return TAG;
    }

    public void setTAG(int TAG) {
        this.TAG = TAG;
    }

}
