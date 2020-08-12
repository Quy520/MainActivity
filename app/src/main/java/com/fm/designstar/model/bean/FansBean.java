package com.fm.designstar.model.bean;


import com.fm.designstar.base.BaseBean;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/8/15 9:05
 * @update : 2018/8/15
 */
public class FansBean extends BaseBean {


    /**
     * code : 1290903095169122304
     * userId : 1278287241650765824
     * followedUserId : 1285160959761055744
     * status : 1
     * type : 1
     * avatar :
     * userName :
     * createTime : 2020-08-05T14:50:56
     */

    private long code;
    private long userId;
    private long followedUserId;
    private int status;
    private int type;
    private String avatar;
    private String userName;
    private String createTime;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getFollowedUserId() {
        return followedUserId;
    }

    public void setFollowedUserId(long followedUserId) {
        this.followedUserId = followedUserId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
