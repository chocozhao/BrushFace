package com.yzf.king.present;


import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.BaseResults;
import com.yzf.king.model.servicesmodels.GetAddShopInfoResults;
import com.yzf.king.model.servicesmodels.GetUploadFileResult;
import com.yzf.king.model.servicesmodels.UploadPhotosResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.ShopSoundPermitActivity;
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
 * ClaseName：PShopSoundPermit
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/20 11:08
 * Modified By：
 * Fixtime：2019/8/20 11:08
 * FixDescription：
 **/

public class PShopSoundPermit extends XPresent<ShopSoundPermitActivity> {

    /**
     * 上传图片
     *
     */
    public void uploadFile(String merchId,String shopId,String fileType,String merchType,String sortId,String imgDesc,String imgKey,File file) {
        // 创建 RequestBody，用于封装 请求RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part pfile =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        String version = Version.UPLOADFILE.version();
        Api.getAPPService().uploadFile(pfile,merchId,shopId,fileType,merchType,sortId,imgDesc,imgKey,null, version)
                .compose(XApi.<GetUploadFileResult>getApiTransformer())
                .compose(XApi.<GetUploadFileResult>getScheduler())
                .subscribe(new ApiSubcriber<GetUploadFileResult>() {
                    @Override
                    public void onNext(GetUploadFileResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().showphotos(fileType, "success", result.getMessage());
                            if ("04".equals(fileType)) {
                                getV().setIdInfo(result.getData());
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
     *
     */
    public void addShopInfo(String merchId,String shopId,String transType, String status, String type, String idName,String businessUrl, String licenseNo,String shopName,String lisExpDate) {
        String version = Version.ADDSHOPINFO.version();
        Api.getAPPService().addShopInfo(merchId,shopId,transType,status,null, type,businessUrl,licenseNo,shopName,lisExpDate,
                null, null, null, null, null, null,
                null, null, null, null, null, null,
                null, null,null, null,null,null,
                idName,null,null,null,null,null,
                null,null,version)
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
