package com.yzf.king.present;

import android.text.TextUtils;

import com.yzf.king.App;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.AesUtil;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.BaseResults;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.FindPasswordActivity;

import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.kit.Codec;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PFindPassword
 * Description：找回密码逻辑
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/3/21 14:33
 * Modified By：
 * Fixtime：2017/3/21 14:33
 * FixDescription：
 */

public class PFindPassword extends XPresent<FindPasswordActivity> {

    /**
     * 获取验证码
     *
     * @param phone
     */
    public void getSmsCode(String phone, String smsType, String merchId, String templateId) {
        if (TextUtils.isEmpty(phone)) {
            getV().showToast("请输入手机号");
            return;
        }
        // 判断是否是合法的手机号码
        if (!AppTools.isMobile(phone)) {
            getV().showToast("手机号码格式不正确");
            return;
        }
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

    /**
     * 找回密码
     *
     * @param phone
     * @param password
     * @param code
     */
    public void findpassword(final String phone, String password, String code) {
        if (TextUtils.isEmpty(phone)) {
            getV().showToast("请输入手机号");
            return;
        }
        // 判断是否是合法的手机号码
        if (!AppTools.isMobile(phone)) {
            getV().showToast("手机号码格式不正确");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            getV().showToast("请输入验证码");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            getV().showToast("请输入密码");
            return;
        }
        if (password.length() < 6) {
            getV().showToast("6~20位登录密码");
            return;
        }
        String savePwd = password;
        password = Codec.MD5.getMD5(password + Codec.SECRET, 1);
        savePwd = AesUtil.encrypt(savePwd);
        final String finalPassword = savePwd;
        String version = Version.FORGOTPWD.version();
        Api.getAPPService().forgotPwd(phone, code, password, version)
                .compose(XApi.<BaseResults>getApiTransformer())
                .compose(XApi.<BaseResults>getScheduler())
                .compose(getV().<BaseResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<BaseResults>() {
                    @Override
                    public void onNext(BaseResults results) {
                        getV().showToast(results.getMessage());
                        if (results.getCode() == ResultCode.SUCCESS.code()) {//找回密码成功后，更新本地账号密码，跳转到登陆页面
                            SharedPref.getInstance(App.getContext()).putString("merchId", phone);
                            SharedPref.getInstance(App.getContext()).putString("password", finalPassword);
                            getV().finish();
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });
    }
}
