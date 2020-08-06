package com.fm.designstar.model.bean;


import com.fm.designstar.base.BaseBean;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/8/15 9:05
 * @update : 2018/8/15
 */
public class CommentsBean extends BaseBean {


    /**
     * code : 1291207808695533568
     * content : g g h b b
     * userId : 1285160959761055744
     * userName : eedddd
     * createTime : 2020-08-06T11:01:46
     * replyList : null
     * avatar : https://yuxuanlin.oss-cn-shanghai.aliyuncs.com/1-1-5f237bddb07660349022eb52-1596161409-158221.jpg
     * createTimestamp : 1596682906000
     */

    private long code;
    private String content;
    private long userId;
    private String userName;
    private String createTime;
    private Object replyList;
    private String avatar;
    private long createTimestamp;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public Object getReplyList() {
        return replyList;
    }

    public void setReplyList(Object replyList) {
        this.replyList = replyList;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
}
