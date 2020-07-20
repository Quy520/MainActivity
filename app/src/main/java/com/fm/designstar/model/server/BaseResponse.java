package com.fm.designstar.model.server;

import java.io.Serializable;

/**
 * 封装服务器返回数据
 *
 * @author DELL
 */
public class BaseResponse<T> implements Serializable {
    public String code;
    public String msg;
    public T data;

    public boolean success() {
        return "0".equals(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
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
