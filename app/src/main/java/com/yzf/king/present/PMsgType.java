package com.yzf.king.present;
/**
 * ClaseName：PMsgType
 * Description：
 * Author：
 * QQ:
 * Createtime：2019/4/24 13:41
 * Modified By：
 * Fixtime：2019/4/24 13:41
 * FixDescription：
 **/

import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.BaseResults;
import com.yzf.king.model.servicesmodels.GetPushMsgCountResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.MsgTypeActivity;

import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class PMsgType extends XPresent<MsgTypeActivity> {

    public void getPushMsgCount(String merchId) {
        String version = Version.GETPUSHMSGCOUNT.version();
        Api.getAPPService().getPushMsgCount(merchId, version)
                .compose(XApi.<GetPushMsgCountResult>getApiTransformer())//异常转换
                .compose(XApi.<GetPushMsgCountResult>getScheduler())//线程变换
                .compose(getV().<GetPushMsgCountResult>bindToLifecycle())//绑定生命周期，防止内存泄漏
                .subscribe(new ApiSubcriber<GetPushMsgCountResult>() {
                    @Override
                    public void onNext(GetPushMsgCountResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (result.getData() != null) {
                                getV().setAdapter(result.getData());
                            } else {
                                getV().showErrorView(result.getMessage());
                            }
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


    public void updateMsg(String merchId) {
            String version = Version.UPDATEPUSHMSG.version();
            Api.getAPPService().updatePushMsg(merchId, null,"1", version)
                    .compose(XApi.<BaseResults>getApiTransformer())
                    .compose(XApi.<BaseResults>getScheduler())
                    .compose(getV().<BaseResults>bindToLifecycle())
                    .subscribe(new ApiSubcriber<BaseResults>() {
                        @Override
                        public void onNext(BaseResults baseResults) {
                            if (baseResults.getCode() == ResultCode.SUCCESS.code()) {
                                getV().refresh();
                                Logger.i("修改状态成功");
                            }
                        }

                        @Override
                        protected void onFail(NetError error) {
                            Logger.i("修改状态失败");
                        }

                    });
        }

}