package com.yzf.king.present;


import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetLoopShopResult;
import com.yzf.king.model.servicesmodels.GetTeamTermInfoResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.ShopContractActivity;
import com.yzf.king.ui.fragments.ShopFragment;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * ClaseName：PShopContract
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/16 12:04
 * Modified By：
 * Fixtime：2019/7/16 12:04
 * FixDescription：
 **/

public class PShopContract extends XPresent<ShopContractActivity> {

    public void LootShop(String merchId,String orderId) {
        String version = Version.LOOTSHOP.version();
        Api.getAPPService().LootShop(merchId, orderId,version)
                .compose(XApi.<GetLoopShopResult>getApiTransformer())
                .compose(XApi.<GetLoopShopResult>getScheduler())
                .compose(getV().<GetLoopShopResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetLoopShopResult>() {
                    @Override
                    public void onNext(GetLoopShopResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            if (result.getData() != null) {
                                getV().setAdapter(result.getData());
                            }
                        } else {
                            if (result.getCode() == ResultCode.FAIL.code()) {
                                getV().getvDelegate().showErrorDialog(result.getMessage(), new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        if (which == DialogAction.POSITIVE) {
                                            getV().finish();
                                        }
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });
    }
}
