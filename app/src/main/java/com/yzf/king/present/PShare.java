package com.yzf.king.present;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.CheckVersionResults;
import com.yzf.king.model.servicesmodels.GetShareImgResult;
import com.yzf.king.model.servicesmodels.GetSubmerchInfoResult;
import com.yzf.king.model.servicesmodels.GetTeamInfoResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.fragments.ShareFragment;

import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class PShare extends XPresent<ShareFragment> {

    public void checkUpdate(final boolean show) {
        String version = Version.CHECKAPPVERSION.version();
        Api.getAPPService().checkAppVersion("Android", AppUser.getInstance().getMerchId(), null, version)
                .compose(XApi.<CheckVersionResults>getApiTransformer())
                .compose(XApi.<CheckVersionResults>getScheduler())
                .compose(getV().<CheckVersionResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<CheckVersionResults>() {
                    @Override
                    public void onNext(CheckVersionResults results) {
                        if (results.getCode() == ResultCode.SUCCESS.code()) {
                            int versionCode = 0;
                            if (!AppTools.isEmpty(results.getData().getVersionCode())) {
                                try {
                                    versionCode = Integer.parseInt(results.getData().getVersionCode());
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (Kits.Package.getVersionCode(getV().getContext()) < versionCode) {
                                getV().update(results);
                            } else {
                                if (show) {
                                    getV().showToast("暂无更新");
                                }
                            }
                        } else {
//                            getV().showToast(results.getMessage());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });
    }

    public void getShareImg(final String merchId) {
        if (AppTools.isEmpty(merchId)) {
            getV().showToast("商户号为空");
            return;
        }
        String version = Version.GETSHAREIMG.version();
        Api.getAPPService().getShareImg(merchId, "Android",version)
                .compose(XApi.<GetShareImgResult>getApiTransformer())
                .compose(XApi.<GetShareImgResult>getScheduler())
                .compose(getV().<GetShareImgResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetShareImgResult>() {
                    @Override
                    public void onNext(GetShareImgResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().setPageInfo(result.getData());
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


    public void getTeamInfo(String merchId) {
        String version = Version.GETTEAMINFO.version();
        Api.getAPPService().getTeamInfo(merchId,1,1,null,null, version)
                .compose(XApi.<GetTeamInfoResult>getApiTransformer())
                .compose(XApi.<GetTeamInfoResult>getScheduler())
                .compose(getV().<GetTeamInfoResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetTeamInfoResult>() {
                    @Override
                    public void onNext(GetTeamInfoResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
//                            getV().setProgress(result.getData());
                            getV().setAdapter(result.getData());
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