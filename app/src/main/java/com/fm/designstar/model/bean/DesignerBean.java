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
public class DesignerBean extends BaseBean {





    private List<TagsListBean> tagsList;
    /**
     * designerMomentVos : [{"momentId":1290212677465014272,"momentType":2,"mediaType":2,"multimediaList":[{"mediaId":1290212677465014273,"momentId":1290212677465014272,"multimediaType":2,"longGraph":null,"pictureOrder":null,"multimediaUrl":"https://yuxuanlin.oss-cn-shanghai.aliyuncs.com/output-2020-08-03-17:07:04-758.mp4","height":640,"width":360,"duration":"21.35166666666667","preUrl":"https://yuxuanlin.oss-cn-shanghai.aliyuncs.com/19807073081772949792/1596445642967.png"}],"tagsList":[],"userId":1278287241650765824,"title":null,"content":"","createTime":"2020-08-03T17:07:28.023","createTimeStamp":1596445648023,"address":null,"city":null,"district":null,"longitude":null,"latitude":null,"likes":1,"comments":0,"forwards":0,"views":0},{"momentId":1290138090215702528,"momentType":2,"mediaType":2,"multimediaList":[{"mediaId":1290138090362503168,"momentId":1290138090215702528,"multimediaType":2,"longGraph":null,"pictureOrder":null,"multimediaUrl":"https://yuxuanlin.oss-cn-shanghai.aliyuncs.com/output-2020-08-03-12:09:52-003.mp4","height":640,"width":360,"duration":"29.16","preUrl":"https://yuxuanlin.oss-cn-shanghai.aliyuncs.com/19807073081772949792/1596427846130.png"}],"tagsList":[],"userId":1278287241650765824,"title":null,"content":"","createTime":"2020-08-03T12:11:05.105","createTimeStamp":1596427865105,"address":"新镇路1399号七宝宝龙城市广场B1","city":"上海市","district":"闵行区","longitude":null,"latitude":null,"likes":0,"comments":0,"forwards":0,"views":0}]
     * userId : 1278287241650765824
     * userName : dvf
     * headUrl : https://ttmsocial-1256411278.cos.ap-shanghai.myqcloud.com/b_coldfish.png
     * sex : 1
     * time : 1596445581910
     * distance : 9940.38795727367
     * follow : false
     * mine : false
     */

    private String userId;
    private String userName;
    private String nickName;
    private String headUrl;
    private String headUri;
    private int sex;
    private long time;
    private String distance;
    private boolean follow;
    private boolean mine;
    private List<HomeFindBean> designerMomentVos;

    private String tagName;
    private String userAddress;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getUserAdddress() {
        return userAddress;
    }

    public void setUserAdddress(String userAdddress) {
        this.userAddress = userAdddress;
    }


    public String getHeadUri() {
        return headUri;
    }

    public void setHeadUri(String headUri) {
        this.headUri = headUri;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<TagsListBean> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<TagsListBean> tagsList) {
        this.tagsList = tagsList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
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

    public List<HomeFindBean> getDesignerMomentVos() {
        return designerMomentVos;
    }

    public void setDesignerMomentVos(List<HomeFindBean> designerMomentVos) {
        this.designerMomentVos = designerMomentVos;
    }


    public static class TagsListBean {
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

    public static class DesignerMomentVosBean {
        /**
         * momentId : 1290212677465014272
         * momentType : 2
         * mediaType : 2
         * multimediaList : [{"mediaId":1290212677465014273,"momentId":1290212677465014272,"multimediaType":2,"longGraph":null,"pictureOrder":null,"multimediaUrl":"https://yuxuanlin.oss-cn-shanghai.aliyuncs.com/output-2020-08-03-17:07:04-758.mp4","height":640,"width":360,"duration":"21.35166666666667","preUrl":"https://yuxuanlin.oss-cn-shanghai.aliyuncs.com/19807073081772949792/1596445642967.png"}]
         * tagsList : []
         * userId : 1278287241650765824
         * title : null
         * content :
         * createTime : 2020-08-03T17:07:28.023
         * createTimeStamp : 1596445648023
         * address : null
         * city : null
         * district : null
         * longitude : null
         * latitude : null
         * likes : 1
         * comments : 0
         * forwards : 0
         * views : 0
         */

        private long momentId;
        private int momentType;
        private int mediaType;
        private long userId;
        private String title;
        private String content;
        private String createTime;
        private long createTimeStamp;
        private int likes;
        private int comments;
        private int forwards;
        private int views;
        private List<MultimediaListBean> multimediaList;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public List<MultimediaListBean> getMultimediaList() {
            return multimediaList;
        }

        public void setMultimediaList(List<MultimediaListBean> multimediaList) {
            this.multimediaList = multimediaList;
        }

        public static class MultimediaListBean {
            /**
             * mediaId : 1290212677465014273
             * momentId : 1290212677465014272
             * multimediaType : 2
             * longGraph : null
             * pictureOrder : null
             * multimediaUrl : https://yuxuanlin.oss-cn-shanghai.aliyuncs.com/output-2020-08-03-17:07:04-758.mp4
             * height : 640.0
             * width : 360.0
             * duration : 21.35166666666667
             * preUrl : https://yuxuanlin.oss-cn-shanghai.aliyuncs.com/19807073081772949792/1596445642967.png
             */

            private long mediaId;
            private long momentId;
            private int multimediaType;
            private int longGraph;
            private int pictureOrder;
            private String multimediaUrl;
            private double height;
            private double width;
            private String duration;
            private String preUrl;

            public long getMediaId() {
                return mediaId;
            }

            public void setMediaId(long mediaId) {
                this.mediaId = mediaId;
            }

            public long getMomentId() {
                return momentId;
            }

            public void setMomentId(long momentId) {
                this.momentId = momentId;
            }

            public int getMultimediaType() {
                return multimediaType;
            }

            public void setMultimediaType(int multimediaType) {
                this.multimediaType = multimediaType;
            }

            public Object getLongGraph() {
                return longGraph;
            }

            public void setLongGraph(int longGraph) {
                this.longGraph = longGraph;
            }

            public Object getPictureOrder() {
                return pictureOrder;
            }

            public void setPictureOrder(int pictureOrder) {
                this.pictureOrder = pictureOrder;
            }

            public String getMultimediaUrl() {
                return multimediaUrl;
            }

            public void setMultimediaUrl(String multimediaUrl) {
                this.multimediaUrl = multimediaUrl;
            }

            public double getHeight() {
                return height;
            }

            public void setHeight(double height) {
                this.height = height;
            }

            public double getWidth() {
                return width;
            }

            public void setWidth(double width) {
                this.width = width;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getPreUrl() {
                return preUrl;
            }

            public void setPreUrl(String preUrl) {
                this.preUrl = preUrl;
            }
        }
    }
}
