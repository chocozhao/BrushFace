package com.yzf.king.present;

import com.google.gson.Gson;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.BaseResults;
import com.yzf.king.model.servicesmodels.GetCardInfoResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.BankCardActivity;
import com.yzf.king.ui.activitys.BankCardDetailActivity;

import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.event.IBus;
import cn.droidlover.xdroidmvp.event.IEvent;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PBankCard
 * Description：银行卡管理逻辑
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/4/1 11:50
 * Modified By：
 * Fixtime：2017/4/1 11:50
 * FixDescription：
 */

public class PBankCardDetail extends XPresent<BankCardDetailActivity> {

    public void deleteBankCard(String merchId, String cardId, String bindType) {
        String version = Version.CARDOPERATE.version();
        Api.getAPPService().cardOperate(merchId, AppTools.craeateOrderId(), bindType, cardId, null, null, null, null, null, null, null, null, null, null, null, null, version)
                .compose(XApi.<BaseResults>getApiTransformer())
                .compose(XApi.<BaseResults>getScheduler())
                .compose(getV().<BaseResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<BaseResults>() {
                    @Override
                    public void onNext(BaseResults results) {
                        if (results.getCode() == ResultCode.SUCCESS.code()) {
                            IBus.IEvent iEvent = new IEvent();
                            iEvent.setId("refresh_card");//交易成功后发消息去更新金额
                            BusProvider.getBus().post(iEvent);
                            getV().finish(results.getMessage());
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

}
