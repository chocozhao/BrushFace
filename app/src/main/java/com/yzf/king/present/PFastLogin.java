package com.yzf.king.present;

import com.yzf.king.App;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.GetDeviceId;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.BaseResults;
import com.yzf.king.model.servicesmodels.LoginResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.FastLoginActivity;
import com.yzf.king.ui.activitys.MainActivity;

import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class PFastLogin extends XPresent<FastLoginActivity> {

    public void login(final String phone, String password, String code, String type, String latitude, String longitude, String city) {
        String deviceId = GetDeviceId.getDeviceId(getV().getApplicationContext());
        Api.getAPPService().login(phone, password, code, type, latitude, longitude, city, deviceId, Version.LOGIN.version())
                .compose(XApi.<LoginResult>getApiTransformer())
                .compose(XApi.<LoginResult>getScheduler())
                .compose(getV().<LoginResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<LoginResult>() {
                    public void onNext(LoginResult loginResult) {
                        if (loginResult.getCode() == ResultCode.SUCCESS.code()) {
                            if (AppTools.isEmpty(AppUser.getInstance().getPhone()) || !phone.equals(AppUser.getInstance().getPhone())) {
                                SharedPref.getInstance(App.getContext()).putBoolean("showMsg", false);
                            }
                            AppUser.getInstance().setPhone(phone);
                            AppUser.getInstance().setToken(loginResult.getData().getToken());
                            AppUser.getInstance().setStatus(loginResult.getData().getMerchStatus());
                            AppUser.getInstance().setMerchId(loginResult.getData().getMerchId());
                            AppUser.getInstance().setMerchName(loginResult.getData().getMerchName());
                            AppUser.getInstance().setUsedFlag(loginResult.getData().getUsedFlag());
                            AppUser.getInstance().setServicePhone(loginResult.getData().getServicePhone());
                            AppUser.getInstance().setMerchlevelname(loginResult.getData().getMerchlevelname());
                            AppUser.getInstance().setMerchlevelname2(loginResult.getData().getMerchlevelname2());
                            AppUser.getInstance().setBranchlevelname(loginResult.getData().getBranchlevelname());
                            AppUser.getInstance().setShoplevelname(loginResult.getData().getShoplevelname());
                            getV().JumpActivity(MainActivity.class, true);
                        }
                        getV().showToast(loginResult.getMessage());
                    }

                    protected void onFail(NetError netError) {
                        getV().showError(netError);
                    }
                });
    }

    /**
     * 获取验证码
     *
     * @param phone
     */
    public void getCode(String phone, String smsType, String merchId, String templateId) {
        getV().startTimer();
        String version = Version.GETSMSCODE.version();
        Api.getAPPService().getSmsCode(phone, templateId, merchId, smsType, version)
                .compose(XApi.<BaseResults>getApiTransformer())
                .compose(XApi.<BaseResults>getScheduler())
                .compose(getV().<BaseResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<BaseResults>() {
                    @Override
                    public void onNext(BaseResults results) {
                        switch (results.getCode()) {
                            case 200:
                                getV().showToast(results.getMessage());
                                break;
                            case 445:
                                getV().showToast(results.getMessage());
                                getV().cancleTimer();
                                break;
                            default:
                                getV().showToast(results.getMessage());
                                break;
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });
    }


}