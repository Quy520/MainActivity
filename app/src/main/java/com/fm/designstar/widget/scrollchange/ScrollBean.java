package com.fm.designstar.widget.scrollchange;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by Raul_lsj on 2018/3/22.
 */

public class ScrollBean extends SectionEntity<ScrollBean.ScrollItemBean> {

    public ScrollBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public ScrollBean(ScrollBean.ScrollItemBean bean) {
        super(bean);
    }

    public static class ScrollItemBean {
        private String text;
        private String type;
        private int id;
        private int i;

        public ScrollItemBean(String text, String type) {
            this.text = text;
            this.type = type;
        } public ScrollItemBean(String text, String type,int i ,int id) {
            this.text = text;
            this.type = type;
            this.id=id;
            this.i=i;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
