package com.yzf.king.net;

import com.yzf.king.kit.AppConfig;
import com.yzf.king.model.servicesmodels.AddRepaymentResults;
import com.yzf.king.model.servicesmodels.AddressResult;
import com.yzf.king.model.servicesmodels.BaseResults;
import com.yzf.king.model.servicesmodels.CheckVersionResults;
import com.yzf.king.model.servicesmodels.CountTeamInfoResult;
import com.yzf.king.model.servicesmodels.CountTransInfoResult;
import com.yzf.king.model.servicesmodels.GetAcctInfoResult;
import com.yzf.king.model.servicesmodels.GetAddApplyInfoResults;
import com.yzf.king.model.servicesmodels.GetAddShopInfoResults;
import com.yzf.king.model.servicesmodels.GetAgentInfoResults;
import com.yzf.king.model.servicesmodels.GetAuthIncomeInfoResult;
import com.yzf.king.model.servicesmodels.GetAuthInfoResult;
import com.yzf.king.model.servicesmodels.GetBenefitDailyResult;
import com.yzf.king.model.servicesmodels.GetBenefitDtlResult;
import com.yzf.king.model.servicesmodels.GetCardInfoResult;
import com.yzf.king.model.servicesmodels.GetCardOperateResult;
import com.yzf.king.model.servicesmodels.GetChannelInfoResults;
import com.yzf.king.model.servicesmodels.GetChannelMerchResult;
import com.yzf.king.model.servicesmodels.GetCreateResult;
import com.yzf.king.model.servicesmodels.GetCreditInfoResult;
import com.yzf.king.model.servicesmodels.GetFeeInfoResult;
import com.yzf.king.model.servicesmodels.GetGoodsInfoResults;
import com.yzf.king.model.servicesmodels.GetHelpInfoResult;
import com.yzf.king.model.servicesmodels.GetInstallResult;
import com.yzf.king.model.servicesmodels.GetLocationResult;
import com.yzf.king.model.servicesmodels.GetLoopShopResult;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoDtlResult;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoResults;
import com.yzf.king.model.servicesmodels.GetMailBillResult;
import com.yzf.king.model.servicesmodels.GetMerchInfoResult;
import com.yzf.king.model.servicesmodels.GetMposApplyRecordResult;
import com.yzf.king.model.servicesmodels.GetMposIncomeResult;
import com.yzf.king.model.servicesmodels.GetMyShopInfoResult;
import com.yzf.king.model.servicesmodels.GetMyTermResult;
import com.yzf.king.model.servicesmodels.GetNewsResult;
import com.yzf.king.model.servicesmodels.GetNoticeResult;
import com.yzf.king.model.servicesmodels.GetOrdersDaily;
import com.yzf.king.model.servicesmodels.GetOrdersDtl;
import com.yzf.king.model.servicesmodels.GetPlanChannelInfoResult;
import com.yzf.king.model.servicesmodels.GetPlanDtlResults;
import com.yzf.king.model.servicesmodels.GetPlanInfoResults;
import com.yzf.king.model.servicesmodels.GetPosChanlFeeResult;
import com.yzf.king.model.servicesmodels.GetPosKeyResult;
import com.yzf.king.model.servicesmodels.GetProcessPlanResults;
import com.yzf.king.model.servicesmodels.GetProfitDtlResult;
import com.yzf.king.model.servicesmodels.GetProfitResult;
import com.yzf.king.model.servicesmodels.GetPushMsgCountResult;
import com.yzf.king.model.servicesmodels.GetPushMsgJGResults;
import com.yzf.king.model.servicesmodels.GetReApplyOrderResults;
import com.yzf.king.model.servicesmodels.GetRecommendShopResults;
import com.yzf.king.model.servicesmodels.GetRepaymentResults;
import com.yzf.king.model.servicesmodels.GetShareImgResult;
import com.yzf.king.model.servicesmodels.GetShopApplyResults;
import com.yzf.king.model.servicesmodels.GetShopAroundResult;
import com.yzf.king.model.servicesmodels.GetShopInfoDtlResult;
import com.yzf.king.model.servicesmodels.GetShopInfoDtlResults;
import com.yzf.king.model.servicesmodels.GetSubmerchCountResult;
import com.yzf.king.model.servicesmodels.GetSubmerchDetailResult;
import com.yzf.king.model.servicesmodels.GetSubmerchInfoResult;
import com.yzf.king.model.servicesmodels.GetSunMerchInfoListResult;
import com.yzf.king.model.servicesmodels.GetTeamInfoCountDtlResult;
import com.yzf.king.model.servicesmodels.GetTeamInfoCountResult;
import com.yzf.king.model.servicesmodels.GetTeamInfoResult;
import com.yzf.king.model.servicesmodels.GetTeamTermInfoResult;
import com.yzf.king.model.servicesmodels.GetTermInfoResult;
import com.yzf.king.model.servicesmodels.GetTradeAbilityResult;
import com.yzf.king.model.servicesmodels.GetTranDtlResult;
import com.yzf.king.model.servicesmodels.GetTransDevicesResult;
import com.yzf.king.model.servicesmodels.GetTransInfoCountDtlResult;
import com.yzf.king.model.servicesmodels.GetTransInfoCountResult;
import com.yzf.king.model.servicesmodels.GetTransResult;
import com.yzf.king.model.servicesmodels.GetTransTermInfoResult;
import com.yzf.king.model.servicesmodels.GetUploadFileIdResult;
import com.yzf.king.model.servicesmodels.GetUploadFileResult;
import com.yzf.king.model.servicesmodels.GetUrlResult;
import com.yzf.king.model.servicesmodels.IncomeBindCardResult;
import com.yzf.king.model.servicesmodels.IncomeResults;
import com.yzf.king.model.servicesmodels.LoginResult;
import com.yzf.king.model.servicesmodels.OpenTransResult;
import com.yzf.king.model.servicesmodels.PosPayConfirmResult;
import com.yzf.king.model.servicesmodels.RefreshResult;
import com.yzf.king.model.servicesmodels.RegisterResult;
import com.yzf.king.model.servicesmodels.UploadPhotosResult;

import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

public interface APPService {

    @FormUrlEncoded
    @POST(AppConfig.REGISTER)
    Observable<RegisterResult> regist(@Field("phone") String phone,
                                      @Field("password") String password,
                                      @Field("smsCode") String smsCode,
                                      @Field("fatherId") String fatherId,
                                      @Field("merchName") String merchName,
                                      @Field("version") String version);


    @POST(AppConfig.UPLOADFILE)
    @Multipart
    Observable<UploadPhotosResult> uploadPhoto(@Part MultipartBody.Part part,
                                               @Query("fileType") String fileType,
                                               @Query("merchId") String merchId,
                                               @Query("version") String version);

    @FormUrlEncoded
    @POST(AppConfig.CARDOPERATE)
    Observable<BaseResults> cardOperate(@Field("merchId") String merchId,
                                        @Field("orderId") String orderId,
                                        @Field("bindType") String bindType,
                                        @Field("cardId") String cardId,
                                        @Field("idName") String name,
                                        @Field("phone") String phone,
                                        @Field("idNo") String idNo,
                                        @Field("cvv") String cvv,
                                        @Field("expDate") String expDate,
                                        @Field("billDate") String billDate,
                                        @Field("repaymentDate") String repaymentDate,
                                        @Field("availAmount") String availAmount,
                                        @Field("limitAmount") String limitAmount,
                                        @Field("billAmount") String billAmount,
                                        @Field("smsCode") String code,
                                        @Field("origOrderId") String origOrderId,
                                        @Field("version") String version);

    @FormUrlEncoded
    @POST(AppConfig.CARDOPERATE)
    Observable<GetCardOperateResult> getcardOperate(@Field("merchId") String merchId,
                                                    @Field("orderId") String orderId,
                                                    @Field("bindType") String bindType,
                                                    @Field("cardId") String cardId,
                                                    @Field("idName") String name,
                                                    @Field("phone") String phone,
                                                    @Field("idNo") String idNo,
                                                    @Field("cvv") String cvv,
                                                    @Field("expDate") String expDate,
                                                    @Field("billDate") String billDate,
                                                    @Field("repaymentDate") String repaymentDate,
                                                    @Field("availAmount") String availAmount,
                                                    @Field("limitAmount") String limitAmount,
                                                    @Field("billAmount") String billAmount,
                                                    @Field("smsCode") String code,
                                                    @Field("origOrderId") String origOrderId,
                                                    @Field("version") String version);

    @FormUrlEncoded
    @POST(AppConfig.FACEMATCH)
    Observable<BaseResults> faceMatch(@Field("merchId") String merchId,
                                      @Field("type") String type,
                                      @Field("version") String version);

    /**
     * 登录
     *
     * @param phone
     * @param password
     * @return
     */
    @GET(AppConfig.LOGIN)
    Observable<LoginResult> login(@Query("phone") String phone,
                                  @Query("password") String password,
                                  @Query("smsCode") String smsCode,
                                  @Query("type") String type,
                                  @Query("latitude") String latitude,
                                  @Query("longitude") String longitude,
                                  @Query("city") String city,
                                  @Query("deviceId") String deviceId,
                                  @Query("version") String version);

    /**
     * 获取验证码
     *
     * @param phone
     * @param templateId
     * @return
     */
    @GET(AppConfig.GETSMSCODE)
    Observable<BaseResults> getSmsCode(@Query("phone") String phone,
                                       @Query("templateId") String templateId,
                                       @Query("merchId") String merchId,
                                       @Query("smsType") String smsType,
                                       @Query("version") String version);


    @GET(AppConfig.GETACCTINFO)
    Observable<GetAcctInfoResult> getAcctInfo(@Query("merchId") String merchId,
                                              @Query("version") String version);

    @GET(AppConfig.GETFEEINFO)
    Observable<GetFeeInfoResult> getFeeInfo(@Query("merchId") String merchId,
                                            @Query("transType") String transType,
                                            @Query("version") String version);

    @GET(AppConfig.GETCARDINFO)
    Observable<GetCardInfoResult> getCardInfo(@Query("merchId") String merchId,
                                              @Query("cardId") String cardId,
                                              @Query("type") String type,
                                              @Query("version") String version);


    @GET(AppConfig.GETMERCHINFO)
    Observable<GetMerchInfoResult> getMerchInfo(@Query("merchId") String merchId,
                                                @Query("version") String version);




    @FormUrlEncoded
    @POST(AppConfig.MODIFYPWD)
    Observable<BaseResults> modifyPwd(@Field("merchId") String merchId,
                                      @Field("oldPwd") String oldPwd,
                                      @Field("password") String password,
                                      @Field("version") String version);



    @GET(AppConfig.GETSUBMERCHDETAIL)
    Observable<GetSubmerchDetailResult> getSubMerchDetail(@Query("merchId") String merchId,
                                                          @Query("merchLevel") String merchLevel,
                                                          @Query("page") int page,
                                                          @Query("pageSize") int pageSize,
                                                          @Query("version") String version);

    @FormUrlEncoded
    @POST(AppConfig.WITHDRAW)
    Observable<BaseResults> withDraw(@Field("merchId") String merchId,
                                     @Field("orderId") String orderId,
                                     @Field("orderAmt") String orderAmt,
                                     @Field("type") String type,
                                     @Field("version") String version);

    @GET(AppConfig.GETTRANSDTL)
    Observable<GetTranDtlResult> getTransDtl(@Query("merchId") String merchId,
                                             @Query("orderId") String orderId,
                                             @Query("transType") String transType,
                                             @Query("status") String status,
                                             @Query("page") int page,
                                             @Query("pageSize") int pageSize,
                                             @Query("beginTime") String beginTime,
                                             @Query("endTime") String endTime,
                                             @Query("version") String version);

    /**
     * 进件结果查询
     *
     * @param
     * @return
     */
    @GET(AppConfig.GETMPOSINCOMERESULT)
    Observable<GetMposIncomeResult> getMposIncomeResult(@Query("merchId") String merchId,
                                                        @Query("version") String version);

    /**
     * 卡美进件
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(AppConfig.KAYOUINCOME)
    Observable<BaseResults> kayouIncome(@Field("merchId") String merchId,
                                        @Field("password") String password,
                                        @Field("store") String store,
                                        @Field("version") String version);

    /**
     * 地址操作
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(AppConfig.ADDRESSOPERA)
    Observable<AddressResult> addressOpera(@Field("merchId") String merchId,
                                           @Field("type") String type,
                                           @Field("addressType") String addressType,
                                           @Field("name") String name,
                                           @Field("phone") String phone,
                                           @Field("address") String address,
                                           @Field("addressDtl") String addressDtl,
                                           @Field("zipCode") String zipCode,
                                           @Field("id") String id,
                                           @Field("version") String version);

    /**
     * 渠道费率
     *
     * @param
     * @return
     */
    @GET(AppConfig.GETPOSCHANLFEE)
    Observable<GetPosChanlFeeResult> getPosChanlFee(@Query("merchId") String merchId,
                                                    @Query("version") String version);



    /**
     * 获取进件申请记录信息
     *
     * @param
     * @return
     */
    @GET(AppConfig.GETMPOSAPPLYRECORD)
    Observable<GetMposApplyRecordResult> getMposApplyRecord(@Query("merchId") String merchId,
                                                            @Query("version") String version);

    /**
     * 确认收货
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(AppConfig.RECEIPT)
    Observable<BaseResults> receipt(@Field("merchId") String merchId,
                                    @Field("version") String version);


    @FormUrlEncoded
    @POST(AppConfig.OPENTRANS)
    Observable<OpenTransResult> openTrans(@Field("merchId") String merchId,
                                          @Field("type") String type,
                                          @Field("payType") String payType,
                                          @Field("orderId") String orderId,
                                          @Field("orderAmt") String orderAmt,
                                          @Field("cardId") String cardId,
                                          @Field("smsCode") String smsCode,
                                          @Field("pageUrl") String pageUrl,
                                          @Field("version") String version);


    @FormUrlEncoded
    @POST(AppConfig.FORGOTPWD)
    Observable<BaseResults> forgotPwd(@Field("phone") String phone,
                                      @Field("smsCode") String smsCode,
                                      @Field("password") String password,
                                      @Field("version") String version);


    @GET(AppConfig.GETPROFITDAILY)
    Observable<GetProfitResult> getProfitDaily(@Query("merchId") String merchId,
                                               @Query("type") String type,
                                               @Query("page") int page,
                                               @Query("pageSize") int pageSize,
                                               @Query("beginTime") String beginTime,
                                               @Query("endTime") String endTime,
                                               @Query("version") String version,
                                               @Query("transType") String transType);

    @GET(AppConfig.GETPROFITDTL)
    Observable<GetProfitDtlResult> getProfitDtl(@Query("merchId") String merchId,
                                                @Query("type") String type,
                                                @Query("orderId") String orderId,
                                                @Query("transType") String transType,
                                                @Query("page") int page,
                                                @Query("pageSize") int pageSize,
                                                @Query("beginTime") String beginTime,
                                                @Query("endTime") String endTime,
                                                @Query("version") String version);



    @GET(AppConfig.GETLOCATIONINFO)
    Observable<GetLocationResult> getLocationInfo(@Query("merchId") String merchId,
                                                  @Query("version") String version);






    @GET(AppConfig.GETMPOSCHANNELMERCHINFO)
    Observable<GetChannelMerchResult> getMposChannelMerchInfo(@Query("merchId") String merchId,
                                                              @Query("type") String type,
                                                              @Query("city") String city,
                                                              @Query("address") String address,
                                                              @Query("page") int page,
                                                              @Query("pageSize") int pageSize,
                                                              @Query("channelId") String channelId,
                                                              @Query("version") String version);

    /**
     * 检查更新
     *
     * @param
     * @return
     */
    @GET(AppConfig.CHECKAPPVERSION)
    Observable<CheckVersionResults> checkAppVersion(@Query("mobileOs") String mobileOs,
                                                    @Query("merchId") String merchId,
                                                    @Query("appVersion") String appVersion,
                                                    @Query("version") String version);

    /**
     * 获取极光推送通知
     *
     * @param page
     * @param pageNum
     * @param merchId
     * @return
     */
    @GET(AppConfig.GETPUSHMSG)
    Observable<GetPushMsgJGResults> getPushMsg(@Query("page") int page,
                                               @Query("pageNum") int pageNum,
                                               @Query("beginTime") String beginTime,
                                               @Query("endTime") String endTime,
                                               @Query("merchId") String merchId,
                                               @Query("status") String status,
                                               @Query("topBranchNo") String topBranchNo,
                                               @Query("version") String version,
                                               @Query("type") String type);

    @FormUrlEncoded
    @POST(AppConfig.UPDATEPUSHMSG)
    Observable<BaseResults> updatePushMsg(@Field("merchId") String merchId,
                                          @Field("id") String id,
                                          @Field("type") String type,
                                          @Field("version") String version);


    @GET(AppConfig.GETURL)
    Observable<GetUrlResult> getUrl(@Query("merchId") String merchId,
                                    @Query("type") String type,
                                    @Query("version") String version);

    @GET(AppConfig.GETPLANCHANNELINFO)
    Observable<GetPlanChannelInfoResult> getPlanChannelInfo(@Query("merchId") String merchId,
                                                            @Query("version") String version);

    /**
     * 获取落地商户渠道信息
     *
     * @param
     * @return
     */
    @GET(AppConfig.GETCHANNELINFO)
    Observable<GetChannelInfoResults> getChannelInfo(@Query("merchId") String merchId,
                                                     @Query("cardId") String cardId,
                                                     @Query("type") String type,
                                                     @Query("transType") String transType,
                                                     @Query("version") String version);


    @FormUrlEncoded
    @POST(AppConfig.PLANOPERATOR)
    Observable<BaseResults> planOperator(@Field("merchId") String merchId,
                                         @Field("regId") String regId,
                                         @Field("type") String type,
                                         @Field("operator") String operator,
                                         @Field("version") String version);

    @FormUrlEncoded
    @POST(AppConfig.MERCHINCOME)
    Observable<IncomeResults> merchIncome(@Field("merchId") String merchId,
                                          @Field("cardId") String cardId,
                                          @Field("action") String action,
                                          @Field("branchId") String branchId,
                                          @Field("channelId") String channelId,
                                          @Field("type") String type,
                                          @Field("version") String version);



    @FormUrlEncoded
    @POST(AppConfig.CREATE)
    Observable<AddRepaymentResults> create(@Field("merchId") String merchId,
                                           @Field("amount") String amount,
                                           @Field("type") String type,
                                           @Field("count") String count,
                                           @Field("times") String times,
                                           @Field("detail") String detail,
                                           @Field("cardId") String cardId,
                                           @Field("version") String version);



    @GET(AppConfig.GETPROCESSPLAN)
    Observable<GetProcessPlanResults> getProcessPlan(@Query("merchId") String merchId,
                                                     @Query("type") String type,
                                                     @Query("status") String status,
                                                     @Query("cardId") String cardId,
                                                     @Query("page") int page,
                                                     @Query("pageNum") int pageNum,
                                                     @Query("beginTime") String beginTime,
                                                     @Query("endTime") String endTime,
                                                     @Query("version") String version);

    /**
     * 渠道费率
     *
     * @param
     * @return
     */
    @GET(AppConfig.REFRESH)
    Observable<RefreshResult> refresh(@Query("merchId") String merchId,
                                      @Query("latitude") String latitude,
                                      @Query("longitude") String longitude,
                                      @Query("city") String city,
                                      @Query("deviceId") String deviceId,
                                      @Query("version") String version);

    @GET(AppConfig.GETSHAREIMG)
    Observable<GetShareImgResult> getShareImg(@Query("merchId") String merchId,
                                              @Query("type") String type,
                                              @Query("version") String version);

    @GET(AppConfig.GETMERCHPHOTOS)
    Observable<GetShareImgResult> getMerchPhotos(@Query("merchId") String merchId,
                                                 @Query("type") String type,
                                                 @Query("version") String version);

    @GET(AppConfig.GETDYNAMICNEWS)
    Observable<GetNewsResult> getDynamicNews(@Query("merchId") String merchId,
                                             @Query("version") String version);

    @GET(AppConfig.GETTEAMINFO)
    Observable<GetTeamInfoResult> getTeamInfo(@Query("merchId") String merchId,
                                              @Query("page") int page,
                                              @Query("pageSize") int pageSize,
                                              @Query("beginTime") String beginTime,
                                              @Query("endTime") String endTime,
                                              @Query("version") String version);

    @GET(AppConfig.GETPUSHMSGCOUNT)
    Observable<GetPushMsgCountResult> getPushMsgCount(@Query("merchId") String merchId,
                                                      @Query("version") String version);


    @GET(AppConfig.GETAUTHINCOMEINFO)
    Observable<GetAuthIncomeInfoResult> getAuthIncomeInfo(@Query("merchId") String merchId,
                                                          @Query("cardId") String cardId,
                                                          @Query("channelId") String channelId,
                                                          @Query("version") String version);

    @FormUrlEncoded
    @POST(AppConfig.INCOMEBINDCARD)
    Observable<IncomeBindCardResult> incomeBindCard(@Field("merchId") String merchId,
                                                    @Field("orderId") String orderId,
                                                    @Field("cardId") String cardId,
                                                    @Field("userCode") String userCode,
                                                    @Field("userKey") String userKey,
                                                    @Field("channelId") String channelId,
                                                    @Field("bindType") String bindType,
                                                    @Field("bgUrl") String bgUrl,
                                                    @Field("type") String type,
                                                    @Field("version") String version);

    @GET(AppConfig.GETCREDITINFO)
    Observable<GetCreditInfoResult> getCreditInfo(@Query("merchId") String merchId,
                                                  @Query("cardId") String cardId,
                                                  @Query("amount") String amount,
                                                  @Query("version") String version);

    @GET(AppConfig.GETAUTHINFO)
    Observable<GetAuthInfoResult> getAuthInfo(@Query("merchId") String merchId,
                                              @Query("cardId") String cardId,
                                              @Query("version") String version);

    @FormUrlEncoded
    @POST(AppConfig.UPLOADCONTACT)
    Observable<BaseResults> uploadContact(@Field("merchId") String merchId,
                                          @Field("contact") String contact,
                                          @Field("version") String version);

    @GET(AppConfig.GETNOTICE)
    Observable<GetNoticeResult> getNotice(@Query("merchId") String merchId,
                                          @Query("type") String type,
                                          @Query("status") String status,
                                          @Query("version") String version);

    @FormUrlEncoded
    @POST(AppConfig.UPDATENOTICE)
    Observable<BaseResults> updateNotice(@Field("merchId") String merchId,
                                         @Field("id") String id,
                                         @Field("version") String version);


    @GET(AppConfig.COUNTTRANSINFO)
    Observable<CountTransInfoResult> countTransInfo(@Query("merchId") String merchId,
                                                    @Query("transType") String transType,
                                                    @Query("version") String version);

    @GET(AppConfig.GETTRANSINFOCOUNT)
    Observable<GetTransInfoCountResult> getTransInfoCount(@Query("merchId") String merchId,
                                                          @Query("type") String type,
                                                          @Query("merchName") String merchName,
                                                          @Query("phone") String phone,
                                                          @Query("transType") String transType,
                                                          @Query("beginTime") String beginTime,
                                                          @Query("endTime") String endTime,
                                                          @Query("version") String version);

    @GET(AppConfig.GETTRANSINFOCOUNTDTL)
    Observable<GetTransInfoCountDtlResult> getTransInfoCountDtl(@Query("merchId") String merchId,
                                                                @Query("merchName") String merchName,
                                                                @Query("phone") String phone,
                                                                @Query("transType") String transType,
                                                                @Query("beginTime") String beginTime,
                                                                @Query("endTime") String endTime,
                                                                @Query("page") int page,
                                                                @Query("pageSize") int pageSize,
                                                                @Query("version") String version);


    @GET(AppConfig.GETMYTERMINFO)
    Observable<GetMyTermResult> getMyTermInfo(@Query("merchId") String merchId,
                                              @Query("status") String status,
                                              @Query("page") int page,
                                              @Query("pageSize") int pageSize,
                                              @Query("beginTime") String beginTime,
                                              @Query("endTime") String endTime,
                                              @Query("version") String version);

    @GET(AppConfig.GETGOODSINFO)
    Observable<GetGoodsInfoResults> getGoodsInfo(@Query("merchId") String merchId,
                                                 @Query("id") String id,
                                                 @Query("version") String version);

    @GET(AppConfig.GETTEAMTERMINFO)
    Observable<GetTeamTermInfoResult> getTeamTermInfo(@Query("merchId") String merchId,
                                                      @Query("status") String status,
                                                      @Query("page") int page,
                                                      @Query("pageSize") int pageSize,
                                                      @Query("beginTime") String beginTime,
                                                      @Query("endTime") String endTime,
                                                      @Query("version") String version);

    @GET(AppConfig.GETTMACHINAPPLYINFO)
    Observable<GetMachinApplyInfoResults> getMachinApplyInfo(@Query("merchId") String merchId,
                                                             @Query("type") String type,
                                                             @Query("status") String status,
                                                             @Query("supStatus") String supStatus,
                                                             @Query("city") String city,
                                                             @Query("shopName") String shopName,
                                                             @Query("page") int page,
                                                             @Query("pageSize") int pageSize,
                                                             @Query("beginTime") String beginTime,
                                                             @Query("endTime") String endTime,
                                                             @Query("version") String version);

    @GET(AppConfig.GETTMACHINAPPLYINFODTL)
    Observable<GetMachinApplyInfoDtlResult> getMachinApplyInfoDtl(@Query("merchId") String merchId,
                                                                  @Query("orderId") String orderId,
                                                                  @Query("shopId") String shopId,
                                                                  @Query("version") String version);

    @FormUrlEncoded
    @POST(AppConfig.LOOTSHOP)
    Observable<GetLoopShopResult> LootShop(@Field("merchId") String merchId,
                                           @Field("orderId") String orderId,
                                           @Field("version") String version);

    @FormUrlEncoded
    @POST(AppConfig.SHOPCONFIRM)
    Observable<GetLoopShopResult> shopConfirm(@Field("merchId") String merchId,
                                              @Field("orderId") String orderId,
                                              @Field("termIdList") String termIdList,
                                              @Field("endDate") String endDate,
                                              @Field("bindType") String bindType,
                                              @Field("version") String version);


    @GET(AppConfig.GETTRANSDEVICES)
    Observable<GetTransDevicesResult> getTransDevices(@Query("merchId") String merchId,
                                                      @Query("status") String status,
                                                      @Query("page") int page,
                                                      @Query("pageSize") int pageSize,
                                                      @Query("beginTime") String beginTime,
                                                      @Query("endTime") String endTime,
                                                      @Query("version") String version);



    @GET(AppConfig.GETSHOPINFO)
    Observable<GetMyShopInfoResult> getShopInfo(@Query("merchId") String merchId,
                                                @Query("type") String type,
                                                @Query("page") int page,
                                                @Query("pageSize") int pageSize,
                                                @Query("beginTime") String beginTime,
                                                @Query("endTime") String endTime,
                                                @Query("version") String version);


    @GET(AppConfig.GETSHOPINFODTLINFO)
    Observable<GetShopInfoDtlResult> getShopInfoDtl(@Query("merchId") String merchId,
                                                    @Query("shopId") String shopId,
                                                    @Query("version") String version);

    @GET(AppConfig.GETSHOPINFODTLINFO)
    Observable<GetShopInfoDtlResults> getShopInfoDtls(@Query("merchId") String merchId,
                                                      @Query("shopId") String shopId,
                                                      @Query("version") String version);

    @FormUrlEncoded
    @POST(AppConfig.INSTALLCONFIRM)
    Observable<GetInstallResult> installConfirm(@Field("merchId") String merchId,
                                                @Field("orderId") String orderId,
                                                @Field("ordersInfo") String ordersInfo,
                                                @Field("bindType") String bindType,
                                                @Field("version") String version);

    @GET(AppConfig.GETBENEFITDAILY)
    Observable<GetBenefitDailyResult> getBenefitDaily(@Query("merchId") String merchId,
                                                      @Query("benefitType") String benefitType,
                                                      @Query("transType") String transType,
                                                      @Query("shopId") String shopId,
                                                      @Query("termId") String termId,
                                                      @Query("beginTime") String beginTime,
                                                      @Query("endTime") String endTime,
                                                      @Query("version") String version);

    @GET(AppConfig.GETBENEFITDTL)
    Observable<GetBenefitDtlResult> getBenefitDtl(@Query("merchId") String merchId,
                                                  @Query("benefitType") String benefitType,
                                                  @Query("transType") String transType,
                                                  @Query("shopId") String shopId,
                                                  @Query("termId") String termId,
                                                  @Query("page") int page,
                                                  @Query("pageSize") int pageSize,
                                                  @Query("beginTime") String beginTime,
                                                  @Query("endTime") String endTime,
                                                  @Query("version") String version);

    @GET(AppConfig.GETSHOPAROUND)
    Observable<GetShopAroundResult> getShopAround(@Query("merchId") String merchId,
                                                  @Query("key") String key,
                                                  @Query("location") String location,
                                                  @Query("region") String region,
                                                  @Query("city") String city,
                                                  @Query("radius") String radius,
                                                  @Query("sortName") String sortName,
                                                  @Query("sortRule") String sortRule,
                                                  @Query("page") int page,
                                                  @Query("pageSize") int pageSize,
                                                  @Query("version") String version);


    @GET(AppConfig.GETTRANSTERMINFO)
    Observable<GetTransTermInfoResult> getTransTermInfo(@Query("merchId") String merchId,
                                                        @Query("status") String status,
                                                        @Query("orderId") String orderId,
                                                        @Query("page") int page,
                                                        @Query("pageSize") int pageSize,
                                                        @Query("beginTime") String beginTime,
                                                        @Query("endTime") String endTime,
                                                        @Query("version") String version);

    @GET(AppConfig.GETSUNMERCHINFOLIST)
    Observable<GetSunMerchInfoListResult> getSunMerchInfoList(@Query("merchId") String merchId,
                                                              @Query("shopId") String shopId,
                                                              @Query("version") String version);

    @FormUrlEncoded
    @POST(AppConfig.ADDSHOPINFO)
    Observable<GetAddShopInfoResults> addShopInfo(@Field("merchId") String merchId,
                                                  @Field("shopId") String shopId,
                                                  @Field("transType") String transType,
                                                  @Field("status") String status,
                                                  @Field("ranges") String ranges,
                                                  @Field("type") String type,
                                                  @Field("businessUrl") String businessUrl,
                                                  @Field("licenseNo") String licenseNo,
                                                  @Field("shopName") String shopName,
                                                  @Field("lisExpDate") String lisExpDate,
                                                  @Field("provinceName") String provinceName,
                                                  @Field("cityName") String cityName,
                                                  @Field("provinceCode") String provinceCode,
                                                  @Field("cityCode") String cityCode,
                                                  @Field("zoneName") String zoneName,
                                                  @Field("addressDtl") String addressDtl,
                                                  @Field("mailId") String mailId,
                                                  @Field("phone") String phone,
                                                  @Field("payType") String payType,
                                                  @Field("zizhiUrl") String zizhiUrl,
                                                  @Field("outShopUr1") String outShopUr1,
                                                  @Field("outShopUr2") String outShopUr2,
                                                  @Field("outShopUr3") String outShopUr3,
                                                  @Field("inShopUrl") String inShopUrl,
                                                  @Field("shopUrl") String shopUrl,
                                                  @Field("idFrontUrl") String idFrontUrl,
                                                  @Field("idBackUrl") String idBackUrl,
                                                  @Field("idNo") String idNo,
                                                  @Field("idName") String idName,
                                                  @Field("idnoExpDate") String idnoExpDate,
                                                  @Field("settBankName") String settBankName,
                                                  @Field("settBankNo") String settBankNo,
                                                  @Field("settName") String settName,
                                                  @Field("settCardNo") String settCardNo,
                                                  @Field("settPhone") String settPhone,
                                                  @Field("alipayId") String alipayId,
                                                  @Field("version") String version);

    @POST(AppConfig.UPLOADFILE)
    @Multipart
    Observable<GetUploadFileResult> uploadFile(@Part MultipartBody.Part part,
                                               @Query("merchId") String merchId,
                                               @Query("shopId") String shopId,
                                               @Query("fileType") String fileType,
                                               @Query("merchType") String merchType,
                                               @Query("sortId") String sortId,
                                               @Query("imgDesc") String imgDesc,
                                               @Query("imgKey") String imgKey,
                                               @Query("file") String file,
                                               @Query("version") String version);

    @POST(AppConfig.UPLOADFILE)
    @Multipart
    Observable<GetUploadFileIdResult> uploadFileId(@Part MultipartBody.Part part,
                                                   @Query("merchId") String merchId,
                                                   @Query("shopId") String shopId,
                                                   @Query("fileType") String fileType,
                                                   @Query("merchType") String merchType,
                                                   @Query("sortId") String sortId,
                                                   @Query("imgDesc") String imgDesc,
                                                   @Query("imgKey") String imgKey,
                                                   @Query("file") String file,
                                                   @Query("version") String version);

    @GET(AppConfig.GETORDERSDAILY)
    Observable<GetOrdersDaily> getOrdersDaily(@Query("merchId") String merchId,
                                              @Query("transType") String transType,
                                              @Query("shopId") String shopId,
                                              @Query("termId") String termId,
                                              @Query("beginTime") String beginTime,
                                              @Query("endTime") String endTime,
                                              @Query("version") String version);

    @GET(AppConfig.GETORDERSDTL)
    Observable<GetOrdersDtl> getOrdersDtl(@Query("merchId") String merchId,
                                          @Query("transType") String transType,
                                          @Query("shopId") String shopId,
                                          @Query("termId") String termId,
                                          @Query("type") String type,
                                          @Query("page") int page,
                                          @Query("pageSize") int pageSize,
                                          @Query("beginTime") String beginTime,
                                          @Query("endTime") String endTime,
                                          @Query("version") String version);

    @GET(AppConfig.GETRECOMMENDSHOP)
    Observable<GetRecommendShopResults> getRecommendShop(@Query("merchId") String merchId,
                                                         @Query("city") String city,
                                                         @Query("page") int page,
                                                         @Query("pageSize") int pageSize,
                                                         @Query("beginTime") String beginTime,
                                                         @Query("endTime") String endTime,
                                                         @Query("version") String version);

    @FormUrlEncoded
    @POST(AppConfig.SHOPAPPLY)
    Observable<GetShopApplyResults> shopApply(@Field("merchId") String merchId,
                                              @Field("applyType") String applyType,
                                              @Field("type") String type,
                                              @Field("ranges") String ranges,
                                              @Field("businessUrl") String businessUrl,
                                              @Field("licenseNo") String licenseNo,
                                              @Field("shopName") String shopName,
                                              @Field("lisExpDate") String lisExpDate,
                                              @Field("provinceName") String provinceName,
                                              @Field("cityName") String cityName,
                                              @Field("provinceCode") String provinceCode,
                                              @Field("cityCode") String cityCode,
                                              @Field("zoneName") String zoneName,
                                              @Field("addressDtl") String addressDtl,
                                              @Field("mailId") String mailId,
                                              @Field("phone") String phone,
                                              @Field("name") String name,
                                              @Field("zoneCode") String zoneCode,
                                              @Field("idFrontUrl") String idFrontUrl,
                                              @Field("idBackUrl") String idBackUrl,
                                              @Field("idNo") String idNo,
                                              @Field("idName") String idName,
                                              @Field("idnoExpDate") String idnoExpDate,
                                              @Field("version") String version);

    @FormUrlEncoded
    @POST(AppConfig.ADDAPPLYINFO)
    Observable<GetAddApplyInfoResults> addApplyInfo(@Field("merchId") String merchId,
                                                    @Field("shopId") String shopId,
                                                    @Field("applyType") String applyType,
                                                    @Field("status") String status,
                                                    @Field("idFrontUrl") String idFrontUrl,
                                                    @Field("idBackUrl") String idBackUrl,
                                                    @Field("idNo") String idNo,
                                                    @Field("idName") String idName,
                                                    @Field("idnoExpDate") String idnoExpDate,
                                                    @Field("settBankName") String settBankName,
                                                    @Field("settBankNo") String settBankNo,
                                                    @Field("settName") String settName,
                                                    @Field("settCardNo") String settCardNo,
                                                    @Field("settPhone") String settPhone,
                                                    @Field("alipayId") String alipayId,
                                                    @Field("zizhiUrl") String zizhiUrl,
                                                    @Field("shopUrl") String shopUrl,
                                                    @Field("outShopUr1") String outShopUr1,
                                                    @Field("outShopUr2") String outShopUr2,
                                                    @Field("outShopUr3") String outShopUr3,
                                                    @Field("inShopUrl") String inShopUrl,
                                                    @Field("fatherId") String fatherId,
                                                    @Field("bindType") String bindType,
                                                    @Field("type") String type,
                                                    @Field("ranges") String ranges,
                                                    @Field("businessUrl") String businessUrl,
                                                    @Field("licenseNo") String licenseNo,
                                                    @Field("shopName") String shopName,
                                                    @Field("lisExpDate") String lisExpDate,
                                                    @Field("provinceName") String provinceName,
                                                    @Field("cityName") String cityName,
                                                    @Field("provinceCode") String provinceCode,
                                                    @Field("cityCode") String cityCode,
                                                    @Field("zoneName") String zoneName,
                                                    @Field("addressDtl") String addressDtl,
                                                    @Field("mailId") String mailId,
                                                    @Field("phone") String phone,
                                                    @Field("zoneCode") String zoneCode,
                                                    @Field("name") String name,
                                                    @Field("version") String version);


    @GET(AppConfig.GETAGENTINFO)
    Observable<GetAgentInfoResults> getAgentInfo(@Query("phone") String merchId,
                                                 @Query("page") int page,
                                                 @Query("pageSize") int pageSize,
                                                 @Query("beginTime") String beginTime,
                                                 @Query("endTime") String endTime,
                                                 @Query("version") String version);

    @FormUrlEncoded
    @POST(AppConfig.SHOPORERATE)
    Observable<GetAddApplyInfoResults> shopOperate(@Field("merchId") String merchId,
                                                    @Field("shopId") String shopId,
                                                    @Field("termId") String termId,
                                                    @Field("orderId") String orderId,
                                                    @Field("replaceTermId") String replaceTermId,
                                                    @Field("type") String type,
                                                    @Field("version") String version);
    @FormUrlEncoded
    @POST(AppConfig.REAPPLYORDER)
    Observable<GetReApplyOrderResults> reApplyOrder(@Field("merchId") String merchId,
                                                    @Field("orderId") String orderId,
                                                    @Field("version") String version);
}