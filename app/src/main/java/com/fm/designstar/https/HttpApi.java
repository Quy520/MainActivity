package com.fm.designstar.https;




import com.fm.designstar.model.bean.DesignerTagsInfoVoBean;
import com.fm.designstar.model.bean.HomeFindBean;
import com.fm.designstar.model.server.BaseListResponse;
import com.fm.designstar.model.server.BaseResponse;
import com.fm.designstar.model.server.body.AddTagsBody;
import com.fm.designstar.model.server.body.Addbody;
import com.fm.designstar.model.server.body.AdddesignerTagsCombody;
import com.fm.designstar.model.server.body.DeleteCombody;
import com.fm.designstar.model.server.body.DesignerStatebody;
import com.fm.designstar.model.server.body.Designerbody;
import com.fm.designstar.model.server.body.FansListBody;
import com.fm.designstar.model.server.body.Finddesignerbody;
import com.fm.designstar.model.server.body.Firdesignerbody;
import com.fm.designstar.model.server.body.GetCommentBody;
import com.fm.designstar.model.server.body.GetMessageBody;
import com.fm.designstar.model.server.body.HomeRecomBody;
import com.fm.designstar.model.server.body.LikeCombody;
import com.fm.designstar.model.server.body.Locationbody;
import com.fm.designstar.model.server.body.LoginBody;
import com.fm.designstar.model.server.body.SendMessageBody;
import com.fm.designstar.model.server.body.TagsBody;
import com.fm.designstar.model.server.body.Updatabody;
import com.fm.designstar.model.server.body.UserMomentBody;
import com.fm.designstar.model.server.body.VesionBody;
import com.fm.designstar.model.server.body.Viewbody;
import com.fm.designstar.model.server.body.chnagepwdbody;
import com.fm.designstar.model.server.body.comInfobody;
import com.fm.designstar.model.server.body.followbody;
import com.fm.designstar.model.server.body.sendMsgBody;
import com.fm.designstar.model.server.body.uploadMomentbody;
import com.fm.designstar.model.server.response.BannerResponse;
import com.fm.designstar.model.server.response.CommentsResponse;
import com.fm.designstar.model.server.response.DesignerPageResponse;
import com.fm.designstar.model.server.response.DesignerResponse;
import com.fm.designstar.model.server.response.DesignerTagInfoResponse;
import com.fm.designstar.model.server.response.FansResponse;
import com.fm.designstar.model.server.response.HomeFindResponse;
import com.fm.designstar.model.server.response.LikeResponse;
import com.fm.designstar.model.server.response.LoginResponse;
import com.fm.designstar.model.server.response.MessageResponse;
import com.fm.designstar.model.server.response.OssTokenResponse;
import com.fm.designstar.model.server.response.RoleResponse;
import com.fm.designstar.model.server.response.SearchDesignerResponse;
import com.fm.designstar.model.server.response.TagInfoResponse;
import com.fm.designstar.model.server.response.UserinfoResponse;
import com.fm.designstar.model.server.response.UserlikeResponse;
import com.fm.designstar.model.server.response.VesionResponse;
import com.fm.designstar.model.server.response.findPageResponse;

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

    @GET("user/loginOut")//退出登录
    Observable<BaseResponse> loginOut();
    @GET("user/getUserOtherInfo")//获取他人点赞 find

    Observable<BaseResponse<UserlikeResponse>> getUserOtherInfo(@Query("userId")String userId);

    @GET("user/getUserSelfInfo")//获取自己点赞
    Observable<BaseResponse<UserlikeResponse>> getUserSelfInfo();

    @GET("media/moment/getToken")//获取阿里token
    Observable<BaseResponse<OssTokenResponse>> ossToken(@Query("roleSessionName")String mobile);

    @POST("media/moment/recommend")//首页推荐
    Observable<BaseResponse<HomeFindResponse>> homeRecommend(@Body HomeRecomBody body);

    @POST("media/moment/getHotMoment")//首页猜你喜欢
    Observable<BaseResponse<HomeFindResponse>> getHotMoment(@Body HomeRecomBody body);

    @POST("media/moment/getBannerActivity")//首页banner
    Observable<BaseResponse<BannerResponse>> getBannerMoment(@Body HomeRecomBody body);





    @POST("media/moment/find")//首页发现/media/moment/findMomentById
    Observable<BaseResponse<HomeFindResponse>> homeFind(@Body HomeRecomBody body);

    @POST("media/moment/follow")//首页关注
    Observable<BaseResponse<HomeFindResponse>> homeFollow(@Body HomeRecomBody body);

    @POST("media/moment/userMoment")//获取个人或他人作品和动态
    Observable<BaseResponse<HomeFindResponse>> userMoment(@Body UserMomentBody body);




    @POST("user/device/add")//获取融云token
    Observable<BaseResponse> add(@Body Addbody body);


    @POST("media/moment/view")//获取融云token
    Observable<BaseResponse> view(@Body Viewbody body);

    @POST("user/userLocation/updateUserLocation")//更新自己的地理位置
    Observable<BaseResponse> updateUserLocation(@Body Locationbody body);


    @POST("media/moment/discoverDesigners")//设计师附近/media/moment/userMoment
    Observable<BaseResponse<DesignerResponse>> discoverDesigners(@Body HomeRecomBody body);

    @POST("media/moment/designers")//设计师列表
    Observable<BaseResponse<DesignerResponse>> designers(@Body HomeRecomBody body);


    @POST("user/follow/findByUserId")//获取关注列表
    Observable<BaseResponse<FansResponse>> findByUserId(@Body FansListBody body);


    @POST("user/follow/findByFollowUserId")//获取粉丝列表
    Observable<BaseResponse<FansResponse>> findByFollowUserId(@Body FansListBody body);


    @POST("media/moment/uploadMoment")//获取粉丝列表
    Observable<BaseResponse> uploadMoment(@Body uploadMomentbody body);

    @POST("media/tag/addDesignerTags")//获取粉丝列表
    Observable<BaseResponse> addDesignerTags(@Body AdddesignerTagsCombody body);



    @GET("media/tag/findAllTagInfo")//获取标签信息
    Observable<BaseResponse<DesignerTagInfoResponse>> findAllTagInfo(@Query("tagType")int tagType);

    @POST("media/tag/addDesignerTagInfo")//增加标签
    Observable<BaseResponse> AddTag(@Body AddTagsBody tagType);



    @GET("user/getUserRoleInfo")//获取个人角色信息
    Observable<BaseResponse<RoleResponse>> getUserRoleInfo();
    @GET("media/moment/forward")//获取个人角色信息
    Observable<BaseResponse> forward(@Query("momentId")long momentId);
    @GET("media/moment/deleteMoment")//获取个人角色信息

    Observable<BaseResponse> deleteMoment(@Query("momentId")long momentId);

    @GET("media/moment/findMomentById")//

    Observable<BaseResponse<HomeFindBean>> findMomentById(@Query("momentId")long momentId);


    @GET("media/comment/findByMomentId")//获取评论
    Observable<BaseResponse<CommentsResponse>> findByMomentId(@Query("momentId") String momentId, @Query("pageNum") int pageNum, @Query("pageSize") int pageSize);

    @POST("media/comment/insertComment")//发送评论
    Observable<BaseResponse> insertComment(@Body SendMessageBody body);

    @POST("media/comment/delComment")//删除评论
    Observable<BaseResponse> delComment(@Body DeleteCombody body);


    @POST("media/likes/like")//点赞
    Observable<BaseResponse<LikeResponse>> like(@Body LikeCombody body);


    @POST("media/message/getMessage")//评论列表
    Observable<BaseResponse<MessageResponse>> getMessage(@Body GetMessageBody body);

    @POST("user/follow/follow")//关注b
    Observable<BaseResponse> follow(@Body followbody body);

    @POST("user/follow/cancel")//取消关注
    Observable<BaseResponse> cancelfollow(@Body followbody body);

    @POST("user/version/getVersion")//取消关注
    Observable<BaseResponse<VesionResponse>> getVersion(@Body VesionBody body);

    @POST("user/audit/add")//提交审核
    Observable<BaseResponse> Designer(@Body Designerbody body);

    @POST("user/audit/findByStatus")//查询审核状态
    Observable<BaseResponse<DesignerStatebody>> findByStatus();

    @POST("user/audit/findPage")//审核类表
    Observable<BaseResponse<findPageResponse>> findPage(@Body HomeRecomBody body);

    @POST("user/audit/update")//修改审核
    Observable<BaseResponse> update(@Body Updatabody body);

    @POST("user/designerPage")//设计师类表
    Observable<BaseResponse<DesignerPageResponse>> designerPage(@Body HomeRecomBody body);

    @POST("user/fireDesigner")//解雇设计师
    Observable<BaseResponse> fireDesigner(@Body Firdesignerbody body);

    @POST("user/searchDesigner")//搜索设计师
    Observable<BaseResponse<SearchDesignerResponse>> findDesigner(@Body Finddesignerbody body);






















}