package com.yzf.king.present;

import com.yzf.king.App;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.AesUtil;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.BaseResults;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.ChangePwdActivity;

import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.kit.Codec;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PChangePwd
 * Description：修改密码逻辑
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/4/1 15:20
 * Modified By：
 * Fixtime：2017/4/1 15:20
 * FixDescription：
 */

public class PChangePwd extends XPresent<ChangePwdActivity> {
    /**
     * 修改密码
     *
     * @param merchId
     * @param oldPwd
     * @param newPwd
     * @param newPwds
     */
    public void changePassword(String merchId, String oldPwd, String newPwd, String newPwds) {
        if (AppTools.isEmpty(oldPwd)) {
            getV().showToast("请输入当前密码");
            return;
        }
        if (oldPwd.equals(newPwd)) {
            getV().showToast("旧密码和新密码一致，请重新修改");
            return;
        }
        if (AppTools.isEmpty(newPwd)) {
            getV().showToast("请输入新密码");
            return;
        }
        if (AppTools.isEmpty(newPwds)) {
            getV().showToast("请再次输入新密码");
            return;
        }
        if (!newPwd.equals(newPwds)) {
            getV().showToast("前后两次密码不一致");
            return;
        }
        if (!AppTools.isPassWord(newPwd)) {
            getV().showToast("请输入6-20位新密码");
            return;
        }
        String savePwd = newPwd;
        oldPwd = Codec.MD5.getMD5(oldPwd + Codec.SECRET, 1);
        newPwd = Codec.MD5.getMD5(newPwd + Codec.SECRET, 1);
        savePwd = AesUtil.encrypt(savePwd);
        final String finalSavePwd = savePwd;
        String version = Version.MODIFYPWD.version();
        Api.getAPPService().modifyPwd(merchId, oldPwd, newPwd, version)
                .compose(XApi.<BaseResults>getApiTransformer())
                .compose(XApi.<BaseResults>getScheduler())
                .compose(getV().<BaseResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<BaseResults>() {
                    @Override
                    public void onNext(BaseResults baseResults) {
                        if (baseResults.getCode() == ResultCode.SUCCESS.code()) {
                            SharedPref.getInstance(App.getContext()).putString("password", finalSavePwd);
                            getV().finishActivity(baseResults.getMessage());//修改密码成功后返回登陆页面
                        } else {
                            getV().showToast(baseResults.getMessage());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });


    }
}
