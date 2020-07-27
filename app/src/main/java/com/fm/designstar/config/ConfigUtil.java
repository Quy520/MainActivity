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
    private String address;
    private String birthday;
    private String email;
    private String realName;
    private String singmarks;
    private int role;
    private int sex;


    public String[] urls = {
            "https://cdeuapi.laifuyun.com/"
    };


    /**
     * 用户的登陆状态
     */
    private boolean isLogin;
    private int isgoHome;

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
        address=SpUtil.getString(Constant.ADDRESS);
        birthday=SpUtil.getString(Constant.BRITH);
        singmarks=SpUtil.getString(Constant.singmarks);
        email=SpUtil.getString(Constant.EMAIL);
        realName=SpUtil.getString(Constant.REAMNAME);
        sex=SpUtil.getInt(Constant.SEX,1);
        role=SpUtil.getInt(Constant.ROLE,1);
        isgoHome=SpUtil.getInt(Constant.HOME,0);

    }

    public String getSingmarks() {
        return singmarks;
    }

    public void setSingmarks(String singmarks) {
       SpUtil.putString(Constant.singmarks,singmarks);

        this.singmarks = singmarks;
    }

    public int getIsgoHome() {
        return isgoHome;
    }

    public void setIsgoHome(int isgoHome) {
        SpUtil.putInt(Constant.HOME,isgoHome);
        this.isgoHome = isgoHome;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        SpUtil.putString(Constant.REAMNAME,realName);
        this.realName = realName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        SpUtil.putInt(Constant.ROLE,role);
        this.role = role;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        SpUtil.putInt(Constant.SEX,sex);
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        SpUtil.putString(Constant.EMAIL,email);
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        SpUtil.putString(Constant.BRITH,birthday);
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        SpUtil.putString(Constant.ADDRESS,address);
        this.address = address;
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
