package com.yzf.king.present;

/**
 * ClaseName：PMagneticCard
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/4/29 16:35
 * Modified By：
 * Fixtime：2019/4/29 16:35
 * FixDescription：
 **/

import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.BaseResults;
import com.yzf.king.model.servicesmodels.GetCardInfoResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.MagneticCardActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class PMagneticCard extends XPresent<MagneticCardActivity> {

    /**
     * 磁条卡绑卡申请
     *
     * @param
     */
    public void MagneticCard(GetCardInfoResult.DataBean dataBean) {
        if (AppTools.isEmpty(dataBean.getCardId())) {
            getV().showToast("银行卡号为空");
            return;
        }
        if (AppTools.isEmpty(dataBean.getName())) {
            getV().showToast("姓名为空");
            return;
        }
        if (AppTools.isEmpty(dataBean.getIdNo())) {
            getV().showToast("身份证号为空");
            return;
        }
        if (AppTools.isEmpty(dataBean.getPhone())) {
            getV().showToast("手机号为空");
            return;
        }if (!AppTools.isMobile(dataBean.getPhone())) {
            getV().showToast("手机号码格式不正确");
            return;
        }

        String version = Version.CARDOPERATE.version();
        final String orderId = AppTools.craeateOrderId();
        Api.getAPPService().cardOperate(AppUser.getInstance().getMerchId(), orderId, "6", dataBean.getCardId(), dataBean.getName(), dataBean.getPhone(), dataBean.getIdNo(), null, null, null, null, null, null, null, null, null, version)
                .compose(XApi.<BaseResults>getApiTransformer())
                .compose(XApi.<BaseResults>getScheduler())
                .compose(getV().<BaseResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<BaseResults>() {
                    @Override
                    public void onNext(BaseResults result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().setOriOrderId(orderId);
                            getV().showToast("验证码已发送");
                            getV().startTimer();
                        } else {
                            getV().showToast(result.getMessage());
                        }

                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });
    }
    /**
     * 磁条卡绑卡确认
     *
     * @param
     */
    public void MagneticCardConfirm(String merchId,String cardId,String name, String idNo,String phone,String smsCode,String origOrderId) {
        if (AppTools.isEmpty(cardId)) {
            getV().showToast("银行卡号为空");
            return;
        }
        if (AppTools.isEmpty(name)) {
            getV().showToast("姓名为空");
            return;
        }
        if (AppTools.isEmpty(idNo)) {
            getV().showToast("身份证号为空");
            return;
        }
        if (AppTools.isEmpty(phone)) {
            getV().showToast("手机号为空");
            return;
        }
        if (AppTools.isEmpty(smsCode)) {
            getV().showToast("验证码为空");
            return;
        }
        if (AppTools.isEmpty(origOrderId)) {
            getV().showToast("请先申请验证码");
            return;
        }
        String version = Version.CARDOPERATE.version();
        final String orderId = AppTools.craeateOrderId();
        Api.getAPPService().cardOperate(AppUser.getInstance().getMerchId(), orderId, "7", cardId, name, phone, idNo, null, null, null, null, null, null, null, smsCode, origOrderId, version)
                .compose(XApi.<BaseResults>getApiTransformer())
                .compose(XApi.<BaseResults>getScheduler())
                .compose(getV().<BaseResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<BaseResults>() {
                    @Override
                    public void onNext(BaseResults result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
//                            getV().JumpActivity(PosPayActivity.class,false);
                        } else {
                            getV().showToast(result.getMessage());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });
    }
}