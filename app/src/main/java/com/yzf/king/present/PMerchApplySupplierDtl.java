package com.yzf.king.present;


import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetAgentInfoResults;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.MerchApplySupplierDtlActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PMerchApplySupplierDtl
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/27 15:36
 * Modified By：
 * Fixtime：2019/8/27 15:36
 * FixDescription：
 **/

public class PMerchApplySupplierDtl extends XPresent<MerchApplySupplierDtlActivity> {

    /**
     * 供应商列表
     */
    public void getAgentInfo(String phone,int page,int pageSize,String beginTime,String endTime) {
        String version = Version.GETMERCHINFO.version();
        Api.getAPPService().getAgentInfo(phone,page,pageSize,beginTime,endTime,version)
                .compose(XApi.<GetAgentInfoResults>getApiTransformer())
                .compose(XApi.<GetAgentInfoResults>getScheduler())
                .compose(getV().<GetAgentInfoResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetAgentInfoResults>() {
                    @Override
                    public void onNext(GetAgentInfoResults result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().setAdapter(result.getData(),page);
//                            getV().JumpActivity(MerchApplyPermitActivity.class);
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
