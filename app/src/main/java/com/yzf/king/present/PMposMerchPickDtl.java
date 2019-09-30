package com.yzf.king.present;


import com.google.gson.Gson;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetChannelMerchResult;
import com.yzf.king.model.servicesmodels.GetLocationResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.MposMerchPickDtlActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PMposMerchPickDtl
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/6/4 19:35
 * Modified By：
 * Fixtime：2019/6/4 19:35
 * FixDescription：
 **/

public class PMposMerchPickDtl extends XPresent<MposMerchPickDtlActivity> {
    public void getLocationInfo(String merchid) {
        String version = Version.GETLOCATIONINFO.version();
        Api.getAPPService().getLocationInfo(merchid, version)
                .compose(XApi.<GetLocationResult>getApiTransformer())
                .compose(XApi.<GetLocationResult>getScheduler())
                .compose(getV().<GetLocationResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetLocationResult>() {
                    @Override
                    public void onNext(GetLocationResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            Gson gson = new Gson();
                            getV().setInfo(result.getData());
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

    public void getMposChannelMerchInfo(String merchid, String type, String city, String province, final int page, int pageSize,String channelId) {
        String version = Version.GETMPOSCHANNELMERCHINFO.version();
        Api.getAPPService().getMposChannelMerchInfo(merchid, type, city, province,page,pageSize,channelId,version)
                .compose(XApi.<GetChannelMerchResult>getApiTransformer())
                .compose(XApi.<GetChannelMerchResult>getScheduler())
                .compose(getV().<GetChannelMerchResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetChannelMerchResult>() {
                    @Override
                    public void onNext(GetChannelMerchResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (result.getData() != null && result.getData().size() > 0) {
                                getV().setAdapter(result.getData(),page);
                            } else {
                                getV().showToast("暂无商户，请更换行业类型后重试");
                                getV().finish();
                            }
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
}
