package com.yzf.king.present;


import com.google.gson.Gson;
import com.yzf.king.kit.ActivityManager;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.BaseResults;
import com.yzf.king.model.servicesmodels.GetCardOperateResult;
import com.yzf.king.model.servicesmodels.GetMerchInfoResult;
import com.yzf.king.model.servicesmodels.UploadPhotosResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.SetteInfoActivity;
import com.yzf.king.ui.activitys.UploadBankCardActivity;
import com.yzf.king.ui.activitys.UploadBankCardDiyActivity;
import com.yzf.king.ui.activitys.UploadFaceActivity;

import java.io.File;

import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * ClaseName：PUploadPhotos
 * Description：上传图片逻辑
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/3/21 17:00
 * Modified By：
 * Fixtime：2017/3/21 17:00
 * FixDescription：
 */

public class PUploadBankCardDiy extends XPresent<UploadBankCardDiyActivity> {


    /**
     * 上传图片
     *
     * @param file
     * @param type
     * @param merchId
     */
    public void uploadPhoto(File file, final String type, String merchId) {
        // 创建 RequestBody，用于封装 请求RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part pfile =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        String version = Version.UPLOADFILE.version();
        Api.getAPPService().uploadPhoto(pfile, type, merchId, version)
                .compose(XApi.<UploadPhotosResult>getApiTransformer())
                .compose(XApi.<UploadPhotosResult>getScheduler())
                .subscribe(new ApiSubcriber<UploadPhotosResult>() {
                    @Override
                    public void onNext(UploadPhotosResult uploadPhotosResult) {
                        if (uploadPhotosResult.getCode() == ResultCode.SUCCESS.code()) {
                            if ("1".equals(uploadPhotosResult.getData().getBankCardBean().getBankType())) {
                                getV().showphotos(type, "success", uploadPhotosResult.getMessage());
                                if (type.equals("02")) {
                                    getV().setBankCardInfo(uploadPhotosResult.getData().getBankCardBean());
                                }
                            } else {
                                getV().showToast("请选择储蓄卡上传");
                                getV().showphotos(type, "fail", "请选择储蓄卡上传");
                            }

                        } else {
                            getV().showphotos(type, "fail", uploadPhotosResult.getMessage());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showphotos(type, "error", "上传失败，请重试");
                    }

                });
    }

    /**
     * 上传资料
     *
     * @param merchId
     * @param bindType
     * @param cardId
     * @param name
     * @param phone
     * @param idNo
     */
    public void cardOperate(final String merchId, String bindType, String cardId, String name, String phone, String idNo) {
        String version = Version.CARDOPERATE.version();
        Api.getAPPService().getcardOperate(merchId, AppTools.craeateOrderId(), bindType, cardId, name, phone, idNo, null, null, null, null, null, null, null, null, null, version)
                .compose(XApi.<GetCardOperateResult>getApiTransformer())
                .compose(XApi.<GetCardOperateResult>getScheduler())
                .compose(getV().<GetCardOperateResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetCardOperateResult>() {
                    @Override
                    public void onNext(GetCardOperateResult results) {
                        if (results.getCode() == ResultCode.SUCCESS.code()) {
//                            getMerchInfo(merchId);
                            GetMerchInfoResult.DataBean dataBean = AppUser.getInstance().getMerchInfoBean();
                            dataBean.setSettleCardNo(results.getData().getObject().getCardId());
                            dataBean.setSettleBankName(results.getData().getObject().getBankName());
                            Gson gson = new Gson();
                            AppUser.getInstance().setMerchInfo(gson.toJson(dataBean));
                            AppUser.getInstance().setStatus("00");
                            getV().showToast(results.getMessage());
                            ActivityManager.getInstance().finishActivity(SetteInfoActivity.class);
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

    public void getMerchInfo(String merchId) {
        if (AppTools.isEmpty(merchId)) {
            getV().showToast("商户号为空");
            return;
        }
        String version = Version.GETMERCHINFO.version();
        Api.getAPPService().getMerchInfo(merchId, version)
                .compose(XApi.<GetMerchInfoResult>getApiTransformer())
                .compose(XApi.<GetMerchInfoResult>getScheduler())
                .compose(getV().<GetMerchInfoResult>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetMerchInfoResult>() {
                    @Override
                    public void onNext(GetMerchInfoResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            Gson gson = new Gson();
                            AppUser.getInstance().setMerchInfo(gson.toJson(result.getData()));
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });
    }
}
