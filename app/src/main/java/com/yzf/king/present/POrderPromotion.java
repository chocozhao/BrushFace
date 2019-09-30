package com.yzf.king.present;


import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoResults;
import com.yzf.king.net.Api;
import com.yzf.king.ui.fragments.OrderManageFragment;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：POrderPromotion
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/13 16:45
 * Modified By：
 * Fixtime：2019/7/13 16:45
 * FixDescription：
 **/

public class POrderPromotion extends XPresent<OrderManageFragment> {

    public void getMachinApplyInfo(String merchId, String type, String status, String supStatus, String city, int page, int pageSize, String beginTime, String endTime) {
        String version = Version.GETTMACHINAPPLYINFO.version();
        Api.getAPPService().getMachinApplyInfo(merchId, type, status, supStatus, city,null, page, pageSize, beginTime, endTime, version)
                .compose(XApi.<GetMachinApplyInfoResults>getApiTransformer())
                .compose(XApi.<GetMachinApplyInfoResults>getScheduler())
                .compose(getV().<GetMachinApplyInfoResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetMachinApplyInfoResults>() {
                    @Override
                    public void onNext(GetMachinApplyInfoResults result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
//                                Gson gson  =  new Gson();
//                                AppUser.getInstance().setMachinApplyInfo(gson.toJson(result.getData()));
//                                AppUser.getInstance().setOrderId();
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
