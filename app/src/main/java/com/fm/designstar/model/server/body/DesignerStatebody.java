package com.fm.designstar.model.server.body;

import com.fm.designstar.base.BaseBean;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/21 11:43
 * @update : 2018/9/21
 */
public class DesignerStatebody extends BaseBean {


    /**
     * id : 1
     * code : 1293847825205035008
     * userId : 1285160959761055744
     * imgUrl : https://yuxuanlin.oss-cn-shanghai.aliyuncs.com/1-1-5f3507dab07698431c0f66ec-1597312329-613964.jpg
     * status : 0
     * updateUserId : null
     * updateLastTime : null
     * createTime : 2020-08-13T17:52:15
     */

    private int id;
    private long code;
    private long userId;
    private String imgUrl;
    private int status;
    private int type;
    private String createTime;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
