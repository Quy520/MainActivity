package com.fm.designstar.https;




import com.fm.designstar.model.server.BaseResponse;
import com.fm.designstar.model.server.body.LoginBody;
import com.fm.designstar.model.server.body.chnagepwdbody;
import com.fm.designstar.model.server.body.sendMsgBody;
import com.fm.designstar.model.server.response.LoginResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface HttpApi {

    @POST("user/login")
    Observable<BaseResponse<LoginResponse>> login(@Body LoginBody body);


    @POST("user/registered")
    Observable<BaseResponse> sendMsg(@Body sendMsgBody body);

    @GET("user/forgetPwd")
    Observable<BaseResponse> sendMsgforget( @Query("mobile")String mobile);

    @POST("user/changePwd")
    Observable<BaseResponse> changePwd(@Body chnagepwdbody body);



}