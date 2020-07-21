package com.fm.designstar.https;




import com.fm.designstar.model.server.BaseResponse;
import com.fm.designstar.model.server.body.LoginBody;
import com.fm.designstar.model.server.response.LoginResponse;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface HttpApi {

    @POST("user/login")
    Observable<BaseResponse<LoginResponse>> login(@Body LoginBody body);

    /*  *//**
     * 登录
     * onHttpErrorMaaf saat ini server kami sedang sibuk, Coba lagi nanti
     *
     * @param body
     * @return
     *//*
    @POST("user/regist")
    Observable<BaseResponse> sign(@Body SignBody body);

    @POST("user/registered")
    Observable<BaseResponse<registeredSuccessResp>> registered(@Body RegisteredBody body);

    @POST("user/sendMsg")
    Observable<BaseResponse> sendMsg(@Body sendMsgBody body);*/
}