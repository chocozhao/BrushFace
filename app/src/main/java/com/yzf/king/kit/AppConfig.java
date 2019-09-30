package com.yzf.king.kit;

import android.os.Environment;

import com.yzf.king.App;
import com.yzf.king.R;

import java.io.File;

/**
 * ProjectName：AppConfig
 * Description：全局配置类
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2016/7/11 11:38
 * Modified By：
 * Fixtime：2016/7/11 11:38
 * FixDescription：
 */
public class AppConfig {
    /**
     * 开发模式
     */
    public static final boolean DEV = true;
    /**
     * 测试环境
     */
    public static final boolean TEST = true;

    /**
     * 是否是O单
     */
    public static final boolean O_SINGLE = false;
    /**
     * 是否使用推送
     */
    public static final boolean PUSH = AppTools.strToBool(App.getContext().getString(R.string.PUSH));
    /**
     * 一级代理商编号
     */
    public static final String TOPBRANCHNO = App.getContext().getString(R.string.TOPBRANCHNO);
    /**
     *  H5生产地址
     */
    public static final String H5_URL_PROD = App.getContext().getString(R.string.H5_URL);
    /**
     * 生产环境地址
     */
    public static final String BASE_URL_PROD = App.getContext().getString(R.string.BASE_URL);
    /**
     * 测试环境地址
     */
    public static final String BASE_URL_TEST = "http://smilepay.yiyoupay.net/apiservice/";
    /**
     * H5测试地址
     */
    public static final String H5_URL_TEST = "http://newtesth5.yiyoupay.net/";
    /**
     * 获取h5链接
     */
    public static final String H5_URL = AppTools.getH5Url();
    public static final String BASE_URL = AppTools.getBaseUrl();
    public static final String QIYU_KEY = App.getContext().getString(R.string.QIYU_KEY);

    /**
     *  微信分享地址
     */
    public static final String WX_URL = "http://weixin.yiyoupay.net/wxpay/front/split?";

    //根目录地址
    public static final String DIR_YZF_ROOT = Environment.getExternalStorageDirectory() + File.separator + "YZF" + File.separator;

    public static final String DIR_PICTURE = DIR_YZF_ROOT + "picture/";

    public static final String REFRESH = "register/refresh";
    // 登录
    public static final String LOGIN = "register/login";
    
    // 修改密码
    public static final String MODIFYPWD = "register/modifyPwd";
    // 获取验证码
    public static final String GETSMSCODE = "register/getSmsCode";
    // 注册帐号
    public static final String REGISTER = "register/register";
    //卡操作
    public static final String CARDOPERATE = "register/cardOperate";
    // 上传注册照片
    public static final String UPLOADFILE = "register/uploadFile";
    // 找回密码
    public static final String FORGOTPWD = "register/forgotPwd";
    //人脸对比
    public static final String FACEMATCH = "register/faceMatch";


    //快捷交易
    public static final String QUICKPAY = "trade/quickPay";
    //快捷交易申请
    public static final String QUICKPAYAPPLY = "trade/quickPayApply";
    //提现交易
    public static final String WITHDRAW = "trade/withDraw";
    public static final String OPENTRANS = "trade/openTrans";
    public static final String GETTRADEABILITY = "trade/getTradeAbility";

    //获取随机养卡规划
    public static final String RANDOM = "plan/random";
    //新增养卡规划
    public static final String CREATE = "plan/create";
    //支付保证金
    public static final String CONFIRM = "plan/confirm";
    public static final String GETCREDITINFO = "plan/getCreditInfo";

    //规划操作接口
    public static final String PLANOPERATOR = "plan/planOperator";
    public static final String GETPLANCHANNELINFO = "plan/getPlanChannelInfo";
    public static final String GETPLANINFO = "plan/getPlanInfo";
    public static final String GETPLANDTL = "plan/getPlanDtl";
    public static final String GETPROCESSPLAN = "plan/getProcessPlan";
    public static final String GETAUTHINFO = "plan/getAuthInfo";
    public static final String UPLOADCONTACT = "plan/uploadContact";
    public static final String GETMAILBILL = "plan/getMailBill";


    // 获取账户信息
    public static final String GETACCTINFO = "merch/getAcctInfo";
    // 获取商户信息
    public static final String GETMERCHINFO = "merch/getMerchInfo";
    // 获取费率
    public static final String GETFEEINFO = "merch/getFeeInfo";
    //获取帮助
    public static final String GETHELPINFO = "merch/getHelpInfo";
    // 获取白名单卡
    public static final String GETCARDINFO = "merch/getCardInfo";
    //获取极光推送消息
    public static final String GETPUSHMSG = "merch/getPushMsg";
    public static final String UPDATEPUSHMSG = "merch/updatePushMsg";
    public static final String GETNOTICE = "merch/getNotice";
    public static final String UPDATENOTICE = "merch/updateNotice";
    // 获取子商户信息
    public static final String GETSUBMERCHINFO = "merch/getSubMerchInfoNew";
    public static final String GETSUBMERCHDETAIL = "merch/getSubMerchDetail";
    public static final String GETSUBMERCHCOUNT = "merch/getSubMerchCount";

    public static final String GETTRANSDTL = "merch/getTransDtl";
    public static final String GETTRANSDAILY = "merch/getTransDaily";
    public static final String GETPROFITDTL = "merch/getProfitDtl";
    public static final String GETPROFITDAILY = "merch/getProfitDaily";
    public static final String GETLOCATIONINFO = "merch/getLocationInfo";
    public static final String CHECKAPPVERSION = "merch/checkAppVersion";
    public static final String GETURL = "merch/getUrl";
    public static final String GETSHAREIMG = "merch/getShareImg";
    public static final String GETMERCHPHOTOS = "merch/getMerchPhotos";
    public static final String GETDYNAMICNEWS = "merch/getDynamicNews";
    public static final String GETTEAMINFO = "merch/getTeamInfo";
    //消息推送
    public static final String  GETPUSHMSGCOUNT= "merch/getPushMsgCount";

    //我的设备查询
    public static final String  GETMYTERMINFO= "merch/getMyTermInfo";
    //产品查询
    public static final String  GETGOODSINFO= "merch/getGoodsInfo";
    //团队设备查询
    public static final String  GETTEAMTERMINFO= "merch/getTeamTermInfo";
    public static final String  GETTMACHINAPPLYINFO= "merch/getMachinApplyInfo";
    public static final String  GETTMACHINAPPLYINFODTL= "merch/getMachinApplyInfoDtl";
    public static final String  GETTRANSDEVICES= "merch/getTransDevices";
    public static final String  GETTERMINFO= "merch/getTermInfo";
    public static final String  GETSHOPINFO= "merch/getShopInfo";
    public static final String  GETSPREADSHOPINFO= "merch/getSpreadShopInfo";
    public static final String  GETSHOPINFODTLINFO= "merch/getShopInfoDtl";
    public static final String  GETBENEFITDAILY= "merch/getBenefitDaily";
    public static final String  GETBENEFITDTL= "merch/getBenefitDtl";
    public static final String  GETSHOPAROUND= "merch/getShopAround";
    public static final String  GETTRANSTERMINFO= "merch/getTransTermInfo";
    public static final String  GETSUNMERCHINFOLIST= "merch/getSunMerchInfoList";
    public static final String  GETORDERSDAILY= "merch/getOrdersDaily";
    public static final String  GETORDERSDTL= "merch/getOrdersDtl";
    public static final String  GETRECOMMENDSHOP= "merch/getRecommendShop";
    public static final String  GETAGENTINFO= "merch/getAgentInfo";


    public static final String  LOOTSHOP= "trade/LootShop";
    public static final String  SHOPCONFIRM= "trade/shopConfirm";
    public static final String  INSTALLCONFIRM= "trade/installConfirm";
    public static final String  SHOPORERATE= "trade/shopOperate";
    public static final String  REAPPLYORDER= "trade/reApplyOrder";

    public static final String  ADDSHOPINFO= "register/addShopInfo";
    public static final String  SHOPAPPLY= "register/shopApply";
    public static final String  ADDAPPLYINFO= "register/addApplyInfo";







    public static final String GETKAYOUCHANLFEE = "kayou/getPosChanlFee";
    public static final String GETKAYOUINCOMERESULT = "kayou/getMposIncomeResult";
    public static final String KAYOUINCOME = "kayou/kayouIncome";
    public static final String GETMPOSAPPLYRECORD = "kayou/getMposApplyRecord";
    public static final String RECEIPT = "kayou/receipt";
    public static final String MPOSPAY = "kayou/mPosPay";

    public static final String GETPOSKEY = "mpos/getPosKey";
    public static final String POSPAYAPPLY = "mpos/posPayApply";
    public static final String POSPAYCONFIRM = "mpos/posPayconfirm";
    public static final String GETMPOSCHANNELMERCHINFO = "mpos/getMposChannelMerchInfo";
    public static final String GETMPOSINCOMERESULT = "mpos/getMposIncomeResult";
    public static final String GETPOSCHANLFEE = "mpos/getPosChanlFee";
    public static final String ADDRESSOPERA = "mpos/addressOpera";

    //获取落地商户支持渠道信息
    public static final String GETCHANNELINFO = "income/getChannelInfo";
    public static final String MERCHINCOME = "income/merchIncome";
    public static final String GETAUTHINCOMEINFO = "income/getAuthIncomeInfo";
    public static final String INCOMEBINDCARD = "income/incomeBindCard";

    //合伙人信息
    public static final String PARTNERLOGIN = "register/partnerLogin";
    public static final String  COUNTACCTINFO= "partner/countAcctInfo";
    public static final String  COUNTTEAMINFO= "partner/countTeamInfo";
    public static final String  COUNTTRANSINFO= "partner/countTransInfo";
    public static final String  GETTRANSINFOCOUNT= "partner/getTransInfoCount";
    public static final String  GETTRANSINFOCOUNTDTL= "partner/getTransInfoCountDtl";
    public static final String  GETTEAMINFOCOUNT= "partner/getTeamInfoCount";
    public static final String  GETTEAMINFOCOUNTDTL= "partner/getTeamInfoCountDtl";



    public static final String APP_ID = "wx33707aaf76db1f7f";//微信appids

    public static String getTopbranchno() {
        return TOPBRANCHNO;
    }
}
