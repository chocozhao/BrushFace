package com.yzf.king.present;


import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetAddShopInfoResults;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.ShopSoundAddressActivity;
import com.yzf.king.ui.fragments.HomeFragment;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PShopSoundAddress
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/21 10:21
 * Modified By：
 * Fixtime：2019/8/21 10:21
 * FixDescription：
 **/

public class PShopSoundAddress extends XPresent<ShopSoundAddressActivity> {

    /**
     * 上传资料
     */
    public void addShopInfo(String merchId, String shopId, String transType, String status,
                            String provinceName,String cityName,String provinceCode,String cityCode,String zoneName,String addressDtl) {
        String version = Version.ADDSHOPINFO.version();
        Api.getAPPService().addShopInfo(merchId, shopId, transType, status, null, null,null, null, null, null,
                provinceName, cityName, provinceCode, cityCode, zoneName, addressDtl,
                null, null, null, null, null, null,
                null, null, null, null, null, null,
                null, null, null, null, null, null,
                null, null,version)
                .compose(XApi.<GetAddShopInfoResults>getApiTransformer())
                .compose(XApi.<GetAddShopInfoResults>getScheduler())
                .compose(getV().<GetAddShopInfoResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetAddShopInfoResults>() {
                    @Override
                    public void onNext(GetAddShopInfoResults results) {
                        if (results.getCode() == ResultCode.SUCCESS.code()) {
                            getV().showToast(results.getMessage());
                            getV().finish();
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
