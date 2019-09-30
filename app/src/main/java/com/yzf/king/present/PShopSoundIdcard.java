package com.yzf.king.present;


import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetAddShopInfoResults;
import com.yzf.king.model.servicesmodels.GetUploadFileIdResult;
import com.yzf.king.model.servicesmodels.GetUploadFileResult;
import com.yzf.king.model.servicesmodels.UploadPhotosResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.ShopSoundIdcardActivity;
import com.yzf.king.ui.fragments.HomeFragment;

import java.io.File;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * ClaseName：PShopSoundIdcard
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/20 16:39
 * Modified By：
 * Fixtime：2019/8/20 16:39
 * FixDescription：
 **/

public class PShopSoundIdcard extends XPresent<ShopSoundIdcardActivity> {

    /**
     * 上传图片
     *
     */
    public void uploadFileId(String merchId,String shopId,String fileType,String merchType,String sortId,String imgDesc,String imgKey,File file) {
        // 创建 RequestBody，用于封装 请求RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part pfile =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        String version = Version.UPLOADFILE.version();
        Api.getAPPService().uploadFileId(pfile,merchId,shopId,fileType,merchType,sortId,imgDesc,imgKey,null, version)
                .compose(XApi.<GetUploadFileIdResult>getApiTransformer())
                .compose(XApi.<GetUploadFileIdResult>getScheduler())
                .subscribe(new ApiSubcriber<GetUploadFileIdResult>() {
                    @Override
                    public void onNext(GetUploadFileIdResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().showphotos(fileType, "success", result.getMessage());
                            if (fileType.equals("21")) {
                                getV().setIdCardInfoZM(result.getData());
                            } else if (fileType.equals("22")) {
                                getV().setIdCardInfoFM(result.getData());
                            }
                        } else {
                            getV().showphotos(fileType, "fail", result.getMessage());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showphotos(fileType, "error", "上传失败，请重试");
                    }

                });
    }

    /**
     * 上传资料
     */
    public void addShopInfo(String merchId, String shopId, String transType, String status, String idName, String idNo, String idFrontUrl, String idBackUrl,String idNoExpDate) {
        String version = Version.ADDSHOPINFO.version();
        Api.getAPPService().addShopInfo(merchId, shopId, transType, status, null, null,null, null, null, null,
                null, null, null, null, null, null,
                null, null, null, null, null, null,
                null, null, null, idFrontUrl, idBackUrl, idNo,
                idName, idNoExpDate, null, null, null, null,
                null, null, version)
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
