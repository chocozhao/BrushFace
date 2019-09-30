package com.yzf.king.present;

import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetBenefitDtlResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.fragments.TransDtlFragment;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PWithDraw
 * Description：提现逻辑
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/5/8 14:52
 * Modified By：
 * Fixtime：2017/5/8 14:52
 * FixDescription：
 */

public class PTransDtl extends XPresent<TransDtlFragment> {

    public void getBenefitDtl(String merchId, String benefitType, String transType, String shopId, String termId,final int page, int pageSize, String beginTime, String endTime) {
        String version = Version.GETBENEFITDTL.version();
        Api.getAPPService().getBenefitDtl(merchId,benefitType,transType, shopId, termId, page, pageSize, beginTime, endTime, version)
                .compose(XApi.<GetBenefitDtlResult>getApiTransformer())
                .compose(XApi.<GetBenefitDtlResult>getScheduler())
                .compose(getV().<GetBenefitDtlResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetBenefitDtlResult>() {
                    @Override
                    public void onNext(GetBenefitDtlResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().setAdapter(result.getData(), page);
                        } else {
                            getV().showErrorView(result.getMessage());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showErrorView(error);
                    }

                });
    }

}
