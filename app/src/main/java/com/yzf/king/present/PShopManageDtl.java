package com.yzf.king.present;


import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetShopInfoDtlResult;
import com.yzf.king.model.servicesmodels.GetShopInfoDtlResults;
import com.yzf.king.model.servicesmodels.GetSpreadShopInfoRestult;
import com.yzf.king.model.servicesmodels.GetSunMerchInfoListResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.ShopManageDtlActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PShopManageDtl
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/17 20:15
 * Modified By：
 * Fixtime：2019/7/17 20:15
 * FixDescription：
 **/

public class PShopManageDtl extends XPresent<ShopManageDtlActivity> {

    /**
     * 设备TermInfoListBean
     * @param merchId
     * @param shopId
     */
    public void getShopInfoDtl(String merchId,String shopId) {
        String version = Version.GETSHOPINFODTLINFO.version();
        Api.getAPPService().getShopInfoDtl(merchId, shopId,version)
                .compose(XApi.<GetShopInfoDtlResult>getApiTransformer())
                .compose(XApi.<GetShopInfoDtlResult>getScheduler())
                .compose(getV().<GetShopInfoDtlResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetShopInfoDtlResult>() {
                    @Override
                    public void onNext(GetShopInfoDtlResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (result.getData() != null) {
                                getV().setAdapter(result.getData());
                            } else {
                                getV().showToast(result.getMessage());
                            }
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
     * 签约信息SubMerchListBean
     * @param merchId
     * @param shopId
     */
    public void getShopInfoDtls(String merchId,String shopId) {
        String version = Version.GETSHOPINFODTLINFO.version();
        Api.getAPPService().getShopInfoDtls(merchId, shopId,version)
                .compose(XApi.<GetShopInfoDtlResults>getApiTransformer())
                .compose(XApi.<GetShopInfoDtlResults>getScheduler())
                .compose(getV().<GetShopInfoDtlResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetShopInfoDtlResults>() {
                    @Override
                    public void onNext(GetShopInfoDtlResults result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (result.getData() != null) {
                                getV().setAdapters(result.getData());
                            } else {
                                getV().showToast(result.getMessage());
                            }
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
     * 补交资料SubListBean
     * @param merchId
     * @param shopId
     */

    public void getSunMerchInfoList(String merchId, String shopId) {
        String version = Version.GETSHOPINFO.version();
        Api.getAPPService().getSunMerchInfoList(merchId, shopId, version)
                .compose(XApi.<GetSunMerchInfoListResult>getApiTransformer())
                .compose(XApi.<GetSunMerchInfoListResult>getScheduler())
                .compose(getV().<GetSunMerchInfoListResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetSunMerchInfoListResult>() {
                    @Override
                    public void onNext(GetSunMerchInfoListResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().setSunMerchInfoListAdapter(result.getData());
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
