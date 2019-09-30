package com.yzf.king.present;

import com.google.gson.Gson;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.AddressResult;
import com.yzf.king.model.servicesmodels.GetMposIncomeResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.AddAddressActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * 　　┏┓　　　┏┓+ +
 * 　┏┛┻━━━┛┻┓ + +
 * 　┃　　　　　　　┃
 * 　┃　　　━　　　┃ ++ + + +
 * ████━████ ┃+
 * 　┃　　　　　　　┃ +
 * 　┃　　　┻　　　┃
 * 　┃　　　　　　　┃ + +
 * 　┗━┓　　　┏━┛
 * 　　　┃　　　┃
 * 　　　┃　　　┃ + + + +
 * 　　　┃　　　┃
 * 　　　┃　　　┃ +  神兽镇楼
 * 　　　┃　　　┃    代码无bug
 * 　　　┃　　　┃　　+
 * 　　　┃　 　　┗━━━┓ + +
 * 　　　┃ 　　　　　　　┣┓
 * 　　　┃ 　　　　　　　┏┛
 * 　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　┃┫┫　┃┫┫
 * 　　　　┗┻┛　┗┻┛+ + + +
 * <p>
 * ClassName：PMerchFee
 * Description: 商户费率逻辑
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/3/20 9:18
 * Modified By：
 * Fixtime：2017/3/20 9:18
 * FixDescription：
 */
public class PAddAddress extends XPresent<AddAddressActivity> {


    /**
     * 获取商户费率
     *
     * @param
     */
    public void addressOpera(String merchId, final String type, String addressType, final String name, final String phone, final String address, final String addressDtl, String zipCode, String id) {
        String version = Version.ADDRESSOPERA.version();
        Api.getAPPService().addressOpera(merchId, type, addressType, name, phone, address, addressDtl, zipCode, id, version)
                .compose(XApi.<AddressResult>getApiTransformer())
                .compose(XApi.<AddressResult>getScheduler())
                .compose(getV().<AddressResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<AddressResult>() {
                    @Override
                    public void onNext(AddressResult results) {
                        if (results.getCode() == ResultCode.SUCCESS.code()) {
                            if ("0".equals(type)) {//新增
                                GetMposIncomeResult.DataBean.MposApplyRecordBean dataBean = AppUser.getInstance().getMposApplyBean();
                                if (dataBean == null) {
                                    dataBean = new GetMposIncomeResult.DataBean.MposApplyRecordBean();
                                    dataBean.setAddressDtl(address + addressDtl);
                                    dataBean.setAddressName(name);
                                    dataBean.setAddressPhone(phone);
                                    Gson gson = new Gson();
                                    AppUser.getInstance().setMposApplyInfo(gson.toJson(dataBean));
                                } else {
                                    dataBean.setAddressDtl(address + addressDtl);
                                    dataBean.setAddressName(name);
                                    dataBean.setAddressPhone(phone);
                                    Gson gson = new Gson();
                                    AppUser.getInstance().setMposApplyInfo(gson.toJson(dataBean));
                                }
                                getV().finish(results.getMessage());

                            }
                            if ("1".equals(type)) {//修改
                                GetMposIncomeResult.DataBean.MposApplyRecordBean dataBean = AppUser.getInstance().getMposApplyBean();
                                dataBean.setAddressDtl(address + addressDtl);
                                dataBean.setAddressName(name);
                                dataBean.setAddressPhone(phone);
                                Gson gson = new Gson();
                                AppUser.getInstance().setMposApplyInfo(gson.toJson(dataBean));
                                getV().finish(results.getMessage());
                                getV().finish(results.getMessage());
                            }
                            if ("2".equals(type)) {//删除

                            }

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
