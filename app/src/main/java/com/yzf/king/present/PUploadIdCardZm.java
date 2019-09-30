package com.yzf.king.present;

import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.UploadPhotosResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.UploadIdCardZmActivity;

import java.io.File;

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

public class PUploadIdCardZm extends XPresent<UploadIdCardZmActivity> {


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
                            getV().showphotos(type, "success", uploadPhotosResult.getMessage());
                            if (type.equals("00")) {
                                getV().setIdCardInfo(uploadPhotosResult.getData().getIdBean());
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
}
