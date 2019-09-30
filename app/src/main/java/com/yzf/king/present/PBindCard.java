package com.yzf.king.present;

import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.BaseResults;
import com.yzf.king.model.servicesmodels.GetCardInfoResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.BindCardActivity;

import java.math.BigDecimal;

import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.event.IBus;
import cn.droidlover.xdroidmvp.event.IEvent;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PBindCard
 * Description：绑卡逻辑
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/4/1 14:31
 * Modified By：
 * Fixtime：2017/4/1 14:31
 * FixDescription：
 */

public class PBindCard extends XPresent<BindCardActivity> {
    /**
     * 绑定银行卡
     *
     * @param dataBean
     */
    public void BindCard(GetCardInfoResult.DataBean dataBean) {
        if (AppTools.isEmpty(dataBean.getCardId())) {
            getV().showToast("银行卡号为空");
            return;
        }
        if (AppTools.isEmpty(dataBean.getCvv())) {
            getV().showToast("CVN2为空");
            return;
        }
        if (AppTools.isEmpty(dataBean.getValidDate())) {
            getV().showToast("卡有效期为空");
            return;
        }
        if (AppTools.isEmpty(dataBean.getBillDate())) {
            getV().showToast("账单日为空");
            return;
        }
        if (AppTools.isEmpty(dataBean.getRepaymentDate())) {
            getV().showToast("还款日为空");
            return;
        }
        BigDecimal add3 = new BigDecimal(dataBean.getBillDate());
        if (add3.compareTo(new BigDecimal(31)) > 0) {
            getV().showToast("账单日不能大于31号");
            return;
        }
        BigDecimal add4 = new BigDecimal(dataBean.getRepaymentDate());
        if (add4.compareTo(new BigDecimal(31)) > 0) {
            getV().showToast("还款日不能大于31号");
            return;
        }
        if (AppTools.isEmpty(dataBean.getPhone())) {
            getV().showToast("银行预留手机号为空");
            return;
        }
        if (!AppTools.isMobile(dataBean.getPhone())) {
            getV().showToast("手机号码格式不正确");
            return;
        }
        String version = Version.CARDOPERATE.version();
        final String orderId = AppTools.craeateOrderId();
        Api.getAPPService().cardOperate(AppUser.getInstance().getMerchId(), orderId, "2", dataBean.getCardId(), null, dataBean.getPhone(), null, dataBean.getCvv(), dataBean.getValidDate(), dataBean.getBillDate(), dataBean.getRepaymentDate(), null, AppTools.formatY2F(dataBean.getLimitAmt()), null, null, null, version)
                .compose(XApi.<BaseResults>getApiTransformer())
                .compose(XApi.<BaseResults>getScheduler())
                .compose(getV().<BaseResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<BaseResults>() {
                    @Override
                    public void onNext(BaseResults results) {
                        if (results.getCode() == ResultCode.SUCCESS.code()) {
                            getV().setOriOrderId(orderId);
                            getV().showToast("验证码已发送");
                            getV().startTimer();
                        } else {
                            getV().showToast(results.getMessage());
                        }

                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });
    }

    /**
     * 快捷绑卡确认
     *
     * @param merchId
     */
    public void BindCardConfirm(String merchId, String cardId, String smsCode, String origOrderId) {
        if (AppTools.isEmpty(merchId)) {
            getV().showToast("商户号为空");
            return;
        }
        if (AppTools.isEmpty(cardId)) {
            getV().showToast("银行卡号为空");
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
        Api.getAPPService().cardOperate(AppUser.getInstance().getMerchId(), orderId, "3", cardId, null, null, null, null, null, null, null, null, null, null, smsCode, origOrderId, version)
                .compose(XApi.<BaseResults>getApiTransformer())
                .compose(XApi.<BaseResults>getScheduler())
                .compose(getV().<BaseResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<BaseResults>() {
                    @Override
                    public void onNext(BaseResults result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            IBus.IEvent iEvent = new IEvent();
                            iEvent.setId("refresh_card");//交易成功后发消息去更新金额
                            BusProvider.getBus().post(iEvent);
                            getV().finishActivity(result.getMessage());
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
