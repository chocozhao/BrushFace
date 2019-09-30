package com.yzf.king.present;

import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetTranDtlResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.WithDrawDetailActivity;

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

public class PWithDrawDetail extends XPresent<WithDrawDetailActivity> {

    public void getTransDtl(String merchId, String transType, final int page, int pageSize) {
        String version = Version.GETTRANSDTL.version();
        Api.getAPPService().getTransDtl(merchId, null, transType, null, page, pageSize, null, null, version)
                .compose(XApi.<GetTranDtlResult>getApiTransformer())
                .compose(XApi.<GetTranDtlResult>getScheduler())
                .compose(getV().<GetTranDtlResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetTranDtlResult>() {
                    @Override
                    public void onNext(GetTranDtlResult result) {
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
