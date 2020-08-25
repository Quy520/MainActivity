package com.fm.designstar.model.server;

import java.io.Serializable;
import java.util.List;

/**
 * 封装服务器返回数据
 *
 * @author DELL
 */
public class BaseListResponse<T> implements Serializable {
    public String code;
    public String msg;
    public List<T> data;

    public boolean success() {
        return "200".equals(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
