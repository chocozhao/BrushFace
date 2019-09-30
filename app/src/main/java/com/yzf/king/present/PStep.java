package com.yzf.king.present;

import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.BaseResults;
import com.yzf.king.model.servicesmodels.GetFeeInfoResult;
import com.yzf.king.model.servicesmodels.GetMposApplyRecordResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.StepActivity;

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

public class PStep extends XPresent<StepActivity> {

    /**
     * mpospay
     *
     * @param merchId
     */
    public void getMposApplyRecord(String merchId) {
        String version = Version.GETMPOSAPPLYRECORD.version();
        Api.getAPPService().getMposApplyRecord(merchId, version)
                .compose(XApi.<GetMposApplyRecordResult>getApiTransformer())
                .compose(XApi.<GetMposApplyRecordResult>getScheduler())
                .compose(getV().<GetMposApplyRecordResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetMposApplyRecordResult>() {
                    @Override
                    public void onNext(GetMposApplyRecordResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().setStep();
                        } else {
                            getV().showEmptyView(result.getMessage());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });
    }

    /**
     * 确认收货
     * mpospay
     *
     * @param merchId
     */
    public void receipt(String merchId) {
        String version = Version.RECEIPT.version();
        Api.getAPPService().receipt(merchId, version)
                .compose(XApi.<BaseResults>getApiTransformer())
                .compose(XApi.<BaseResults>getScheduler())
                .compose(getV().<BaseResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<BaseResults>() {
                    @Override
                    public void onNext(BaseResults result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().toPay();
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
     * 确认收货
     * mpospay
     *
     * @param merchId
     */
    public void getMposFee(String merchId) {
        String version = Version.RECEIPT.version();
        Api.getAPPService().getFeeInfo(merchId, "43", version)
                .compose(XApi.<GetFeeInfoResult>getApiTransformer())
                .compose(XApi.<GetFeeInfoResult>getScheduler())
                .compose(getV().<GetFeeInfoResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetFeeInfoResult>() {
                    @Override
                    public void onNext(GetFeeInfoResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code() && result.getData() != null && result.getData().size() > 0) {
                            getV().setFee(result.getData().get(0).getExternFee());
                            getV().setStep();
                        } else {
                            if (result.getMessage().contains("暂无数据")) {
                                getV().showEmptyView("找不到费率信息，请联系管理员");
                            } else {
                                getV().showEmptyView(result.getMessage());
                            }
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showErrorView(error);
                    }

                });
    }

}
