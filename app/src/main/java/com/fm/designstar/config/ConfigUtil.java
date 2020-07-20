package com.fm.designstar.config;

import android.text.TextUtils;

import com.fm.designstar.BuildConfig;
import com.fm.designstar.utils.SpUtil;
import com.fm.designstar.utils.StringUtil;


/**
 * @author DELL
 */
public class ConfigUtil {
    private static final String TEXT_APK_MARKET = "my_self";
    private static final String BASE_URL = "https://api.recomeapp.com/recome/";//todo
    /**
     * 是否调试模式,上线必须改为false
     */
    private boolean isDebug = BuildConfig.DEBUG || BuildConfig.FLAVOR.equals(TEXT_APK_MARKET);

    private String baseUrl = !StringUtil.isBlank(BuildConfig.BASE_URL) ? BuildConfig.BASE_URL : BASE_URL;



    private String userToken;
    private String user_name;
    private String user_head;
    private String userPhone;


    public String[] urls = {
            "https://api.recomeapp.com/recome/",
            "https://api.recomeapp.com/recome2/",
            "http://47.74.178.77:8080/recome/",
            "http://47.74.178.77:8081/recome/",
            "http://192.168.0.99/RecommandMe/",
            "https://t.100baoxian.com/RecommandMe/",
            "http://192.168.0.100/RecommandMe/",
            "http://192.168.0.97:8080/recome/",
            "http://nothreenofour.51vip.biz/recome/"
    };


    /**
     * 用户的登陆状态
     */
    private boolean isLogin;

    private String bindPhone;








    public ConfigUtil() {
        //初始化H5地址
        setBaseUrl(baseUrl);
        userToken = SpUtil.getString(Constant.USERTOKEN);
        user_name = SpUtil.getString(Constant.USER_NAME);
        user_head = SpUtil.getString(Constant.USER_HEAD);
        userPhone = SpUtil.getString(Constant.USER_PHONE);
        isLogin = !StringUtil.isBlank(userToken);
        bindPhone=SpUtil.getString(Constant.BindPhone);

    }



    public String getUserToken() {
        return userToken;
    }



    public void setUserToken(String userToken) {
        isLogin = !StringUtil.isBlank(userToken);
        this.userToken = userToken;
        SpUtil.putString(Constant.USERTOKEN, userToken);
    }



    public void setUser_name(String user_name) {
        this.user_name = user_name;
        SpUtil.putString(Constant.USER_NAME, user_name);
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_head() {
        return user_head;
    }

    public void setUser_head(String user_head) {
        this.user_head = user_head;
        SpUtil.putString(Constant.USER_HEAD, user_head);
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
        SpUtil.putString(Constant.USER_PHONE, userPhone);
    }



    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean isDebug) {
        this.isDebug = isDebug;
    }

    /**
     * 获取用户当前登录状态
     *
     * @return
     */
    public boolean getLoginStatus() {
        return isLogin;
    }

    public String getBaseUrl() {
        if (isDebug() && !TextUtils.isEmpty(SpUtil.getString(Constant.URL_KEY))) {
            baseUrl = SpUtil.getString(Constant.URL_KEY);
        }
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBindPhone() {
        return bindPhone;
    }

    public void setBindPhone(String bindPhone) {
        this.bindPhone = bindPhone;
        SpUtil.putString(Constant.BindPhone, bindPhone);
    }
}
