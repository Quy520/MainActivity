package com.fm.designstar.events;

public class messageEvent extends BaseEvent {
    private int TAG;//为0时（登录失效时）调用退出接口，为1时（手动退出已调用退出接口）不调用
    private int Type;

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public messageEvent(int tag,int type) {
        this.TAG = tag;
        this.Type=type;
    }

    public int getTAG() {
        return TAG;
    }

    public void setTAG(int TAG) {
        this.TAG = TAG;
    }

}
