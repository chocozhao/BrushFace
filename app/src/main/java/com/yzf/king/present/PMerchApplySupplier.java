package com.yzf.king.present;


import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetAddApplyInfoResults;
import com.yzf.king.model.servicesmodels.GetAgentInfoResults;
import com.yzf.king.model.servicesmodels.GetRecommendShopResults;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.MerchApplyFinshActivity;
import com.yzf.king.ui.activitys.MerchApplySupplierActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PMerchApplySupplier
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/27 14:54
 * Modified By：
 * Fixtime：2019/8/27 14:54
 * FixDescription：
 **/

public class PMerchApplySupplier extends XPresent<MerchApplySupplierActivity> {


    /**
     * 上传资料
     */
    public void addApplyInfo(String merchId, String shopId, String applyType, String status, String idFrontUrl,
                             String idBackUrl, String idNo, String idName, String idnoExpDate,
                             String settBankName, String settBankNo, String settName, String settCardNo, String settPhone,
                             String alipayId, String zizhiUrl, String shopUrl, String outShopUr1, String outShopUr2,
                             String outShopUr3, String inShopUrl, String fatherId,String bindType) {
        String version = Version.ADDSHOPINFO.version();
        Api.getAPPService().addApplyInfo(merchId, shopId, applyType, status, idFrontUrl, idBackUrl, idNo, idName,
                idnoExpDate, settBankName, settBankNo, settName, settCardNo, settPhone,
                alipayId, zizhiUrl, shopUrl, outShopUr1, outShopUr2, outShopUr3, inShopUrl, fatherId,bindType,null,
                null,null,null,null,null,null,null,
                null,null,null,null,null,null,null,null,version)
                .compose(XApi.<GetAddApplyInfoResults>getApiTransformer())
                .compose(XApi.<GetAddApplyInfoResults>getScheduler())
                .compose(getV().<GetAddApplyInfoResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetAddApplyInfoResults>() {
                    @Override
                    public void onNext(GetAddApplyInfoResults results) {
                        if (results.getCode() == ResultCode.SUCCESS.code()) {
                            getV().JumpActivity(MerchApplyFinshActivity.class,true);
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
