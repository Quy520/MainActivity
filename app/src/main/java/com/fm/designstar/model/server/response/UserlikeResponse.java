package com.fm.designstar.model.server.response;


import com.fm.designstar.base.BaseBean;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/27 11:21
 * @update : 2018/9/27
 */
public class UserlikeResponse extends BaseBean {

    /**
     * followNum : 0
     * fansNum : 0
     * likeNum : 0
     * momentNum : 0
     */

    private int followNum;
    private int fansNum;
    private int likeNum;
    private int momentNum;

    public int getFollowNum() {
        return followNum;
    }

    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }

    public int getFansNum() {
        return fansNum;
    }

    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getMomentNum() {
        return momentNum;
    }

    public void setMomentNum(int momentNum) {
        this.momentNum = momentNum;
    }
}
