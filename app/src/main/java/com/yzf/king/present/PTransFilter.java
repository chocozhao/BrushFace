package com.yzf.king.present;

import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetBenefitDailyResult;
import com.yzf.king.model.servicesmodels.GetOrdersDaily;
import com.yzf.king.model.servicesmodels.GetTranDtlResult;
import com.yzf.king.model.servicesmodels.GetTransResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.fragments.TransFilterFragment;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class PTransFilter extends XPresent<TransFilterFragment> {

    public void getBenefitDaily(String merchId, String benefitType,String transType,String shopId,String termId,final int page, int pageSize, String beginTime, String endTime) {
        String version = Version.GETBENEFITDAILY.version();
        Api.getAPPService().getBenefitDaily(merchId, benefitType,transType,shopId, termId,beginTime, endTime, version)
                .compose(XApi.<GetBenefitDailyResult>getApiTransformer())
                .compose(XApi.<GetBenefitDailyResult>getScheduler())
                .compose(getV().<GetBenefitDailyResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetBenefitDailyResult>() {
                    @Override
                    public void onNext(GetBenefitDailyResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().setAdapter(result.getData(), page);
                        } else {
                            getV().showErrorView(result.getMessage());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });
    }



    /**
     * 设备交易明细
     * @param merchId
     * @param transType
     * @param shopId
     * @param termId
     * @param page
     * @param pageSize
     * @param beginTime
     * @param endTime
     */
    public void getOrdersDaily(String merchId, String transType,String shopId,String termId,final int page, int pageSize, String beginTime, String endTime) {
        String version = Version.GETORDERSDAILY.version();
        Api.getAPPService().getOrdersDaily(merchId, transType,shopId, termId,beginTime, endTime, version)
                .compose(XApi.<GetOrdersDaily>getApiTransformer())
                .compose(XApi.<GetOrdersDaily>getScheduler())
                .compose(getV().<GetOrdersDaily>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetOrdersDaily>() {
                    @Override
                    public void onNext(GetOrdersDaily result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().setOrdersAdapter(result.getData(), page);
                        } else {
                            getV().showErrorView(result.getMessage());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });
    }

}