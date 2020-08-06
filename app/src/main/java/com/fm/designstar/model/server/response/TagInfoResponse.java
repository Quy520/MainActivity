package com.fm.designstar.model.server.response;

import com.fm.designstar.base.BaseBean;

import java.util.List;

public  class TagInfoResponse extends BaseBean {


    /**
     * tagType : 360°螺旋挂树
     * tagsInfoVo : [{"id":5,"tagName":"阿姆斯特朗螺旋加速阿姆斯特朗螺旋炮"}]
     */

    private String tagType;
    private List<TagsInfoVoBean> tagsInfoVo;

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public List<TagsInfoVoBean> getTagsInfoVo() {
        return tagsInfoVo;
    }

    public void setTagsInfoVo(List<TagsInfoVoBean> tagsInfoVo) {
        this.tagsInfoVo = tagsInfoVo;
    }

    public static class TagsInfoVoBean {
        /**
         * id : 5
         * tagName : 阿姆斯特朗螺旋加速阿姆斯特朗螺旋炮
         */

        private int id;
        private String tagName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }
    }
}
