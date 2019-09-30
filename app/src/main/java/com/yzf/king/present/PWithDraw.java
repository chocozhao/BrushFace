package com.yzf.king.present;

import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.BaseResults;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.WithDrawActivity;

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

public class PWithDraw extends XPresent<WithDrawActivity> {
    /**
     * 提现
     *
     * @param
     */
    public void withDraw(String merchId, String orderAmt, String type) {
        String version = Version.WITHDRAW.version();
//        orderAmt = AppTools.formatY2F(orderAmt);
//        if ("01".equals(type)) {
//            type = "0";
//        } else if ("03".equals(type)) {
//            type = "1";
//        } else {
//            getV().showToast("无效提现类型");
//            return;
//        }
        Api.getAPPService().withDraw(merchId, AppTools.craeateOrderId(), orderAmt, type, version)
                .compose(XApi.<BaseResults>getApiTransformer())
                .compose(XApi.<BaseResults>getScheduler())
                .compose(getV().<BaseResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<BaseResults>() {
                    @Override
                    public void onNext(BaseResults result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().finishActivity(result.getMessage());
                        } else if (result.getCode() == ResultCode.PROCESSING.code()) {
                            getV().showToast(result.getMessage());
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
