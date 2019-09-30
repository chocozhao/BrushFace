package com.yzf.king.present;

import com.google.gson.Gson;
import com.yzf.king.App;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.GetDeviceId;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.AesUtil;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.BaseResults;
import com.yzf.king.model.servicesmodels.GetAcctInfoResult;
import com.yzf.king.model.servicesmodels.GetUrlResult;
import com.yzf.king.model.servicesmodels.LoginResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.AcctInfoActivity;
import com.yzf.king.ui.activitys.LoginActivity;
import com.yzf.king.ui.activitys.MainActivity;
import com.yzf.king.ui.activitys.SplashActivity;

import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.kit.Codec;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.ToastType;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClassName：SplashActivity
 * Description:登陆逻辑处理 通过单例获取手机号码、token、Status(状态码)、id、Name、Level、用户标志。
 * Author：JensenWei
 * QQ:
 * Createtime：2018/8/24 14:39
 * Modified By：
 * Fixtime：2018/8/24 14:39
 * FixDescription：
 */
public class PSplash extends XPresent<SplashActivity> {
    public void login(final String phone, String password, String code, String type, String latitude, String longitude, String city) {
        String savePwd = password;
        password=AesUtil.decrypt(savePwd);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(password);
        stringBuilder.append(Codec.SECRET);
        String deviceId = GetDeviceId.getDeviceId(getV().getApplicationContext());
        Api.getAPPService().login(phone, Codec.MD5.getMD5(stringBuilder.toString(), 1), code, type, latitude, longitude, city, deviceId, Version.LOGIN.version())
                .compose(XApi.<LoginResult>getApiTransformer())//异常处理交换
                .compose(XApi.<LoginResult>getScheduler())//线程切换
                .compose(getV().<LoginResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<LoginResult>() {
                    public void onNext(LoginResult loginResult) {
                        if (loginResult.getCode() == ResultCode.SUCCESS.code()) {
                            if (AppTools.isEmpty(AppUser.getInstance().getPhone()) || !phone.equals(AppUser.getInstance().getPhone())) {//判断是否和拿到的手机号一样
                                SharedPref.getInstance(App.getContext()).putBoolean("showMsg", false);
                            }
                            AppUser.getInstance().setPhone(phone);
                            AppUser.getInstance().setToken(loginResult.getData().getToken());
                            AppUser.getInstance().setStatus(loginResult.getData().getMerchStatus());
                            AppUser.getInstance().setMerchId(loginResult.getData().getMerchId());
                            AppUser.getInstance().setMerchName(loginResult.getData().getMerchName());
                            AppUser.getInstance().setMerchLevel(loginResult.getData().getMerchLevel());
                            AppUser.getInstance().setUsedFlag(loginResult.getData().getUsedFlag());
                            AppUser.getInstance().setServicePhone(loginResult.getData().getServicePhone());
                            AppUser.getInstance().setMerchlevelname(loginResult.getData().getMerchlevelname());
                            AppUser.getInstance().setMerchlevelname2(loginResult.getData().getMerchlevelname2());
                            AppUser.getInstance().setBranchlevelname(loginResult.getData().getBranchlevelname());
                            AppUser.getInstance().setShoplevelname(loginResult.getData().getShoplevelname());
                            AppUser.getInstance().setIsAgent(loginResult.getData().getIsAgent());
                            SharedPref.getInstance(App.getContext()).putString("phone", phone);
                            getUrl(loginResult.getData().getMerchId());
                            getV().JumpActivity(MainActivity.class, true);//登陆成功跳转金管家页面
                            getV().showToast(loginResult.getMessage());
                        } else {
                            getV().showToast("自动登录失败：" + loginResult.getMessage());
                            getV().JumpActivity(LoginActivity.class, true);//登陆失败跳转登陆页面
                        }

                    }

                    protected void onFail(NetError netError) {
                        getV().showToast("自动登录失败");
                        getV().JumpActivity(LoginActivity.class, true);
                    }
                });
    }

    public void getUrl(String merchId) {
        String version = Version.GETURL.version();
        Api.getAPPService().getUrl(merchId, null, version)
                .compose(XApi.<GetUrlResult>getApiTransformer())
                .compose(XApi.<GetUrlResult>getScheduler())
                .subscribe(new ApiSubcriber<GetUrlResult>() {
                    @Override
                    public void onNext(GetUrlResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (result.getData() != null && result.getData().size() > 0) {
                                Gson gson = new Gson();
                                AppUser.getInstance().setUrlInfo(gson.toJson(result.getData()));
                            }

                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });
    }

}