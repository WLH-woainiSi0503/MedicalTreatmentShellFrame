package com.dingtao.common.core.http;


import com.dingtao.common.bean.MyUser.MyConsultBean;
import com.dingtao.common.bean.MyUser.UserRecordBean;
import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.homepage.Banner;
import com.dingtao.common.bean.homepage.CjypBean;
import com.dingtao.common.bean.homepage.CjypBean1;
import com.dingtao.common.bean.homepage.DuotiaomuBean;
import com.dingtao.common.bean.homepage.SousuoBean;
import com.dingtao.common.bean.homepage.WzzxBean;
import com.dingtao.common.bean.homepage.YpxqBean;
import com.dingtao.common.bean.homepage.ZhuBean;
import com.dingtao.common.bean.homepage.ZiBean;
import com.dingtao.common.bean.homepage.ZxbkBean;
import com.dingtao.common.bean.login.LoginBean;
import com.dingtao.common.bean.video.DanBean;
import com.dingtao.common.bean.video.TopBean;
import com.dingtao.common.bean.video.VideoBean;
import com.dingtao.common.bean.wardBean.List_xiang_Bean;
import com.dingtao.common.bean.wardBean.Ping_lie_Bean;
import com.dingtao.common.bean.wardBean.SeachBean;
import com.dingtao.common.bean.wardBean.TabBean;
import com.dingtao.common.bean.wardBean.WardLieBean;
/*import com.dingtao.common.bean.video.TopBean;
import com.dingtao.common.bean.video.VideoBean;*/

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * @author dingtao
 * @date 2018/12/28 10:00
 * qq:1940870847
 */
public interface IAppRequest {

    //sichangyong-------------------------------------------------------------------别动我的
    //登录
    @FormUrlEncoded
    @POST("user/v1/login")
    Observable<Result<LoginBean>> getlogin(@Field("email") String email,
                                           @Field("pwd") String pwd);
    //注册
    @FormUrlEncoded
    @POST("user/v1/register")
    Observable<Result> getregister(@Field("email") String email,
                                   @Field("code") String code,
                                   @Field("pwd1") String pwd1,
                                   @Field("pwd2") String pwd2,
                                   @Field("invitationCode") String invitationCode);
    //邮箱
    @FormUrlEncoded
    @POST("user/v1/sendOutEmailCode")
    Observable<Result> getemail(@Field("email") String email);
    //重设密码(忘记密码使用)
    @FormUrlEncoded
    @PUT("user/v1/resetUserPwd")
    Observable<Result> anewpass(@Field("email") String email,
                                @Field("pwd1") String pwd1,
                                @Field("pwd2") String pwd2);
    //1.查询健康讲堂类目
   @GET("user/video/v1/findVideoCategoryList")
    Observable<Result<List<TopBean>>> topdata();
    //根据视频类目查询视频列表
    @GET("user/video/v1/findVideoVoList")
    Observable<Result<List<VideoBean>>> videodata(@Header("userId") String userId,
                                                  @Header("sessionId") String sessionId,
                                                  @Query("categoryId") int categoryId,
                                                  @Query("page") int page,
                                                  @Query("count") int count);
    // 查询视频评论列表
    @GET("user/video/v1/findVideoCommentList")
    Observable<Result<List<DanBean>>> videodan(@Query("videoId") int videoId);
    // 查询我的钱包
    @GET("user/verify/v1/findUserWallet")
    Observable<Result> videogetprice(@Header("userId") String userId,
                                     @Header("sessionId") String sessionId);
    // 查询我的钱包
    @FormUrlEncoded
    @POST("user/video/verify/v1/videoBuy")
    Observable<Result> videobuy(@Header("userId") String userId,
                                @Header("sessionId") String sessionId,
                                @Field("videoId") int videoId,
                                @Field("price") int price);
    //健康课堂视频收藏
    @FormUrlEncoded
    @POST("user/video/verify/v1/addUserVideoCollection")
    Observable<Result> videocollect(@Header("userId") String userId,
                                    @Header("sessionId") String sessionId,
                                    @Field("videoId") int videoId);
    //发表视频评论（弹幕）
    @FormUrlEncoded
    @POST("user/video/verify/v1/addVideoComment")
    Observable<Result> videosendbulletscreen(@Header("userId") String userId,
                                                @Header("sessionId") String sessionId,
                                                @Field("videoId") int videoId,
                                             @Field("content") String content);
    //sichangyong-----------------------------------------我的页面
    //用户签到
    @POST("user/verify/v1/addSign")
    Observable<Result> usersign(@Header("userId") String userId,
                                @Header("sessionId") String sessionId);


    //用户查看自己的档案
    @GET("user/verify/v1/findUserArchives")
    Observable<Result<UserRecordBean>> myuserrecord(@Header("userId") String userId,
                                                    @Header("sessionId") String sessionId);
    //23.删除用户档案
    @DELETE("user/verify/v1/deleteUserArchives")
    Observable<Result> deletemyuserrecord(@Header("userId") String userId,
                                          @Header("sessionId") String sessionId,
                                          @Field("archivesId") int archivesId);
    //.添加用户档案
    @FormUrlEncoded
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("user/verify/v1/addUserArchives")
    Observable<Result> addrecord(@Header("userId") String userId,
                                 @Header("sessionId") String sessionId,
                                 @Field("diseaseMain") String diseaseMain,
                                 @Field("diseaseNow") String diseaseNow,
                                 @Field("diseaseBefore") String diseaseBefore,
                                 @Field("treatmentHospitalRecent") String treatmentHospitalRecent,
                                 @Field("treatmentProcess") String treatmentProcess,
                                 @Field("treatmentStartTime") String treatmentStartTime,
                                 @Field("treatmentEndTime") String treatmentEndTime);
    //修改我的档案
    @FormUrlEncoded
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("user/verify/v1/addUserArchives")
    Observable<Result> uprecord(@Header("userId") String userId,
                                 @Header("sessionId") String sessionId,
                                 @Field("archivesId") int archivesId,
                                 @Field("diseaseMain") String diseaseMain,
                                 @Field("diseaseNow") String diseaseNow,
                                 @Field("diseaseBefore") String diseaseBefore,
                                 @Field("treatmentHospitalRecent") String treatmentHospitalRecent,
                                 @Field("treatmentProcess") String treatmentProcess,
                                 @Field("treatmentStartTime") String treatmentStartTime,
                                 @Field("treatmentEndTime") String treatmentEndTime);
    //.查询用户资讯收藏列表
    @GET("user/verify/v1/findUserInfoCollectionList")
    Observable<Result<List<MyConsultBean>>> mycollectconsult(@Header("userId") String userId,
                                                             @Header("sessionId") String sessionId,
                                                             @Query("page") int page,
                                                             @Query("count") int count);
    //.用户收藏病友圈列表
    @GET("user/verify/v1/findUserSickCollectionList")
    Observable<Result<List<WardLieBean>>> mycollectbing(@Header("userId") String userId,
                                                         @Header("sessionId") String sessionId,
                                                         @Query("page") int page,
                                                         @Query("count") int count);
    //.用户收藏健康课堂视频列表
    @GET("user/verify/v1/findVideoCollectionList")
    Observable<Result<List<VideoBean>>> mycollectvideo(@Header("userId") String userId,
                                                         @Header("sessionId") String sessionId,
                                                         @Query("page") int page,
                                                         @Query("count") int count);
    //取消收藏将康视频

    @DELETE("user/verify/v1/cancelVideoCollection")
    Observable<Result> deletemyuservideo(@Header("userId") String userId,
                                          @Header("sessionId") String sessionId,
                                          @Query("videoId") int videoId);
    //sichangyong-------------------------------------------------------------------别动我的




    /*==========================================LIFANGXIAN====================================================*/
    //科室
    @GET("share/knowledgeBase/v1/findDepartment")
    Observable<Result<List<TabBean>>> tab();
    //列表详情
    @GET("user/sickCircle/v1/findSickCircleList")
    Observable<Result<List<WardLieBean>>> wardLie(@Query("departmentId") int departmentId,
                                                  @Query("page") int page,
                                                  @Query("count") int count);
    //搜索病友病症
    @GET("user/sickCircle/v1/searchSickCircle")
    Observable<Result<List<SeachBean>>> seach(@Query("keyWord") String keyWord);
    //病友圈列表详情
    @GET("user/sickCircle/v1/findSickCircleInfo")
    Observable<Result<List_xiang_Bean>> ListXiang(@Header("userId") String userId,
                                                  @Header("sessionId") String sessionId,
                                                  @Query("sickCircleId") int sickCircleId);
    //评论列表
    @GET("user/sickCircle/v1/findSickCircleCommentList")
    Observable<Result<List<Ping_lie_Bean>>> ping(@Header("userId") String userId,
                                           @Header("sessionId") String sessionId,
                                           @Query("sickCircleId") int sickCircleId,
                                           @Query("page") int page,
                                           @Query("count") int count);

    //评论
    @FormUrlEncoded
    @POST("user/sickCircle/verify/v1/publishComment")
    Observable<Result> pinglun(@Header("userId") String userId,
                               @Header("sessionId") String sessionId,
                               @Field("sickCircleId") int sickCircleId,
                               @Field("content") String content);
    /*==========================================LIFANGXIAN====================================================*/





























    @GET("share/v1/bannersShow")
    Observable<Result<List<Banner>>> bannershow();
  /*  @GET("health/share/v1/homePageSearch")
    Observable<> bannershow();*/
  @GET("share/knowledgeBase/v1/findDepartment")
    Observable<Result<List<WzzxBean>>> wzzxshow();
  @GET("share/information/v1/findInformationPlateList")
    Observable<Result<List<ZxbkBean>>> zxbkshow();
    @GET("share/information/v1/findInformationList")
    Observable<Result<List<DuotiaomuBean>>> dtmshow(@Query("plateId") int plateId,@Query("page") int page,@Query("count") int count);
    @GET("share/knowledgeBase/v1/findDiseaseCategory")
    Observable<Result<List<ZiBean>>> zishow(@Query("departmentId") int departmentId);

    @GET("share/knowledgeBase/v1/findDiseaseKnowledge")
    Observable<Result<ZhuBean>> zhushow(@Query("id") int id);
    @GET("share/knowledgeBase/v1/findDrugsCategoryList")
    Observable<Result<List<CjypBean>>> cjypshow();
    @GET("share/knowledgeBase/v1/findDrugsKnowledgeList")
    Observable<Result<List<CjypBean1>>> cjyp1how(@Query("drugsCategoryId") int drugsCategoryId, @Query("page") int page, @Query("count") int count);
    @GET("share/knowledgeBase/v1/findDrugsKnowledge")
    Observable<Result<YpxqBean>> ypshow(@Query("id") int id);

    //xieqi-------------------------------------------------------------------别动我的
}
