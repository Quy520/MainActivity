package com.fm.designstar.model.server.response;


import com.fm.designstar.base.BaseBean;

/**
 * description : $todo
 *
 * @author : Totcy
 * @date : 2018/9/27 11:21
 * @update : 2018/9/27
 */
public class OssTokenResponse extends BaseBean {
    private String accessKeyId;
    private String accessKeySecret;
    private String securityToken;
    private String expiration;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getSecurityToken() {
        return securityToken;
    }

    public void setSecurityToken(String securityToken) {
        this.securityToken = securityToken;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}
