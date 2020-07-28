package com.fm.designstar.https;




import com.fm.designstar.model.server.BaseResponse;
import com.fm.designstar.model.server.body.HomeRecomBody;
import com.fm.designstar.model.server.body.LoginBody;
import com.fm.designstar.model.server.body.chnagepwdbody;
import com.fm.designstar.model.server.body.comInfobody;
import com.fm.designstar.model.server.body.sendMsgBody;
import com.fm.designstar.model.server.response.LoginResponse;
import com.fm.designstar.model.server.response.OssTokenResponse;
import com.fm.designstar.model.server.response.UserinfoResponse;
import com.fm.designstar.model.server.response.UserlikeResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface HttpApi {

    @POST("user/login")//登录
    Observable<BaseResponse<LoginResponse>> login(@Body LoginBody body);


    @GET("user/registered")//注册短信
    Observable<BaseResponse> sendMsg(@Query("mobile")String mobile);

    @POST("user/add")//注册
    Observable<BaseResponse> AppRegist(@Body chnagepwdbody body);

    @GET("user/forgetPwd")//忘记密码短信
    Observable<BaseResponse> sendMsgforget( @Query("mobile")String mobile);

    @POST("user/changePwd")//修改密码
    Observable<BaseResponse> changePwd(@Body chnagepwdbody body);

    @GET("user/getOtherUserInfo")//获取他人信息
    Observable<BaseResponse<UserinfoResponse>> getOtherUserInfo(@Query("userId")String userId);


    @POST("user/updateUserInfo")//完善信息
    Observable<BaseResponse> comInfo(@Body comInfobody body);

    @GET("user/loginOut")//获取他人信息
    Observable<BaseResponse> loginOut();
    @GET("user/getUserOtherInfo")//获取他人点赞

    Observable<BaseResponse> getUserOtherInfo();

    @GET("user/getUserSelfInfo")//获取自己点赞
    Observable<BaseResponse<UserlikeResponse>> getUserSelfInfo();

    @GET("media/moment/getToken")//获取自己点赞
    Observable<BaseResponse<OssTokenResponse>> ossToken(@Query("roleSessionName")String mobile);

    @POST("media/moment/recommend")//首页推荐
    Observable<BaseResponse> homeRecommend(@Body HomeRecomBody body);

    @POST("media/moment/find")//首页发现
    Observable<BaseResponse> homeFind(@Body HomeRecomBody body);

    @POST("media/moment/follow")//完善信息
    Observable<BaseResponse> homeFollow(@Body HomeRecomBody body);






}