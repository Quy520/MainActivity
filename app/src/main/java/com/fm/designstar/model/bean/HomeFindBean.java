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
public class HomeFindBean extends BaseBean {


    /**
     * momentId : 1287681768958328832
     * momentType : 2
     * mediaType : 2
     * multimediaList : [{"mediaId":1287681768958328833,"momentId":1287681768958328832,"multimediaType":2,"multimediaUrl":"https://yuxuanlin.oss-cn-shanghai.aliyuncs.com/output-2020-07-27-17:30:03-791.mp4","height":640,"width":360,"preUrl":"https://yuxuanlin.oss-cn-shanghai.aliyuncs.com/19807073081772949792/1595842226101.png"}]
     * tagsList : [{"tagId":5,"tagName":"阿姆斯特朗螺旋加速阿姆斯特朗螺旋炮"},{"tagId":1,"tagName":"test"}]
     * userId : 1280820288040337408
     * content :
     * createTime : 2020-07-27T17:30:32.422
     * createTimeStamp : 1595842232422
     * nickName : 老林
     * headUri : https://yuxuanlin.oss-cn-shanghai.aliyuncs.com/19807073081772949792/1595833810275.png
     * sex : 1
     * isLike : 0
     * follow : false
     * mine : false
     */

    private long momentId;
    private int momentType;
    private int mediaType;
    private long userId;
    private String content;
    private String address;
    private String city;
    private String createTime;
    private long createTimeStamp;
    private String nickName;
    private String headUri;
    private int sex;
    private int isLike;
    private int likes;
    private int comments;
    private int forwards;
    private boolean follow;
    private boolean mine;

    private List<MultimediaListBean> multimediaList;
    private List<TagsListBean> tagsList;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getForwards() {
        return forwards;
    }

    public void setForwards(int forwards) {
        this.forwards = forwards;
    }

    public long getMomentId() {
        return momentId;
    }

    public void setMomentId(long momentId) {
        this.momentId = momentId;
    }

    public int getMomentType() {
        return momentType;
    }

    public void setMomentType(int momentType) {
        this.momentType = momentType;
    }

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    public boolean isFollow() {
        return follow;
    }

    public void setFollow(boolean follow) {
        this.follow = follow;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public List<MultimediaListBean> getMultimediaList() {
        return multimediaList;
    }

    public void setMultimediaList(List<MultimediaListBean> multimediaList) {
        this.multimediaList = multimediaList;
    }

    public List<TagsListBean> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<TagsListBean> tagsList) {
        this.tagsList = tagsList;
    }



    public static class TagsListBean extends BaseBean {
        /**
         * tagId : 5
         * tagName : 阿姆斯特朗螺旋加速阿姆斯特朗螺旋炮
         */

        private int tagId;
        private String tagName;

        public int getTagId() {
            return tagId;
        }

        public void setTagId(int tagId) {
            this.tagId = tagId;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }
    }
}
