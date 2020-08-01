package com.fm.designstar.model.bean;

import com.fm.designstar.base.BaseBean;

public class MultimediaListBean extends BaseBean {

        /**
         * mediaId : 1287681768958328833
         * momentId : 1287681768958328832
         * multimediaType : 2
         * multimediaUrl : https://yuxuanlin.oss-cn-shanghai.aliyuncs.com/output-2020-07-27-17:30:03-791.mp4
         * height : 640
         * width : 360
         * preUrl : https://yuxuanlin.oss-cn-shanghai.aliyuncs.com/19807073081772949792/1595842226101.png
         */

        private long mediaId;
        private long momentId;
        private int multimediaType;
        private String multimediaUrl;
        private int height;
        private int width;
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

        public String getMultimediaUrl() {
            return multimediaUrl;
        }

        public void setMultimediaUrl(String multimediaUrl) {
            this.multimediaUrl = multimediaUrl;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public String getPreUrl() {
            return preUrl;
        }

        public void setPreUrl(String preUrl) {
            this.preUrl = preUrl;
        }

}
