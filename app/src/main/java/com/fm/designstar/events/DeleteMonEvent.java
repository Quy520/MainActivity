package com.fm.designstar.events;

public class DeleteMonEvent extends BaseEvent {
    private long TAG;//

    public DeleteMonEvent(long tag) {
        this.TAG = tag;
    }

    public long getTAG() {
        return TAG;
    }

    public void setTAG(long TAG) {
        this.TAG = TAG;
    }

}
