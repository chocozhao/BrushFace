package com.yzf.king.present;


import com.google.gson.Gson;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.BaseResults;
import com.yzf.king.model.servicesmodels.GetMerchInfoResult;
import com.yzf.king.model.servicesmodels.RegisterResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.HelpMerchApplyActivity;
import com.yzf.king.ui.activitys.MerchApplyPermitActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PHelpMerchApply
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/26 17:56
 * Modified By：
 * Fixtime：2019/8/26 17:56
 * FixDescription：
 **/

public class PHelpMerchApply extends XPresent<HelpMerchApplyActivity> {
    /**
     * 帮助商家申请
     *
     */
    public void regist(String phone,String password,String smsCode,String fatherId,String merchName,String applyType) {
        String version = Version.GETMERCHINFO.version();
        Api.getAPPService().regist(phone,password,smsCode,fatherId, merchName,version)
                .compose(XApi.<RegisterResult>getApiTransformer())
                .compose(XApi.<RegisterResult>getScheduler())
                .compose(getV().<RegisterResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<RegisterResult>() {
                    @Override
                    public void onNext(RegisterResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().setInfo(result.getData());
                            getV().toMerchApplyPermit(applyType);
                        } else {
                            getV().showErrorDialog(result.getMessage());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
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
                                getV().startTimer();
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
