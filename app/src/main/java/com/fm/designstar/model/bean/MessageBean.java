package com.fm.designstar.model.bean;


import com.fm.designstar.base.BaseBean;

import java.util.List;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/8/15 9:05
 * @update : 2018/8/15
 */
public class MessageBean extends BaseBean {


    /**
     * code : 1290906394425425920
     * momentId : 1290209091557392384
     * mediaType : 0
     * content : 赞了你的动态
     * url : null
     * createTime : 2020-08-05T15:04:03.259
     * createTimeStamp : 1596611043259
     * userId : 1285160959761055744
     * nickName : qian
     * headUri : https://yuxuanlin.oss-cn-shanghai.aliyuncs.com/1-1-5f237bddb07660349022eb52-1596161409-158221.jpg
     * sex : 1
     */

    private long code;
    private long momentId;
    private int mediaType;
    private String content;
    private String url;
    private String createTime;
    private long createTimeStamp;
    private long userId;
    private String nickName;
    private String headUri;
    private int sex;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public long getMomentId() {
        return momentId;
    }

    public void setMomentId(long momentId) {
        this.momentId = momentId;
    }

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public long getCreateTimeStamp() {
        return createTimeStamp;
    }

    public void setCreateTimeStamp(long createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadUri() {
        return headUri;
    }

    public void setHeadUri(String headUri) {
        this.headUri = headUri;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
