package com.yzf.king.present;


import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.GetAddApplyInfoResults;
import com.yzf.king.model.servicesmodels.GetUploadFileResult;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.MerchApplyPhotoActivity;
import com.yzf.king.ui.activitys.ShopSoundPhotoActivity;

import java.io.File;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * ClaseName：PMerchApplyPhoto
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/26 10:49
 * Modified By：
 * Fixtime：2019/8/26 10:49
 * FixDescription：
 **/

public class PMerchApplyPhoto extends XPresent<MerchApplyPhotoActivity> {

    /**
     * 上传图片
     */
    public void uploadFile(String merchId, String shopId, String fileType, String merchType, String sortId, String imgDesc, String imgKey, File file, int mCount) {
        // 创建 RequestBody，用于封装 请求RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part pfile =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        String version = Version.UPLOADFILE.version();
        Api.getAPPService().uploadFile(pfile, merchId, shopId, fileType, merchType, sortId, imgDesc, imgKey, null, version)
                .compose(XApi.<GetUploadFileResult>getApiTransformer())
                .compose(XApi.<GetUploadFileResult>getScheduler())
                .subscribe(new ApiSubcriber<GetUploadFileResult>() {
                    @Override
                    public void onNext(GetUploadFileResult result) {
                        if (result.getCode() == ResultCode.SUCCESS.code()) {
                            getV().showphotos(fileType, "success", result.getMessage());
                            if ("05".equals(fileType)) {
                                getV().setInfo(result.getData());
                            } else if ("23".equals(fileType)) {
                                switch (mCount) {
                                    case ShopSoundPhotoActivity.STORE:
                                        getV().setStoreInfo(result.getData());
                                        break;
                                    case ShopSoundPhotoActivity.STOREHEAD:
                                        getV().setStoreHeadInfo(result.getData());
                                        break;
                                    default:
                                        break;
                                }
                            } else if ("24".equals(fileType)) {
                                switch (mCount) {
                                    case ShopSoundPhotoActivity.STOREONE:
                                        getV().setStoreOneInfo(result.getData());
                                        break;
                                    case ShopSoundPhotoActivity.STORETWO:
                                        getV().setStoreTwoInfo(result.getData());
                                        break;
                                    case ShopSoundPhotoActivity.STORETHREE:
                                        getV().setStoreThreeInfo(result.getData());
                                        break;
                                    default:
                                        break;
                                }
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
    public void addApplyInfo(String merchId,String shopId,String applyType, String status, String idFrontUrl,
                             String idBackUrl,String idNo, String idName,String idnoExpDate,
                             String settBankName,String settBankNo,String settName,String settCardNo,String settPhone,
                             String alipayId,String zizhiUrl,String shopUrl,String outShopUr1,String outShopUr2,
                             String outShopUr3,String inShopUrl,String fatherId,String bindType,String type,
                             String ranges, String businessUrl, String licenseNo,String shopName, String lisExpDate,String provinceName,
                             String cityName,String provinceCode,String cityCode,String zoneName,String addressDtl,
                             String mailId,String phone,String zoneCode,String name) {
        String version = Version.ADDAPPLYINFO.version();
        Api.getAPPService().addApplyInfo(merchId,shopId,applyType, status,idFrontUrl,idBackUrl,idNo,idName,
                idnoExpDate,settBankName, settBankNo, settName, settCardNo, settPhone,
                alipayId,zizhiUrl,shopUrl, outShopUr1,outShopUr2,outShopUr3,inShopUrl,fatherId,bindType,
                type,ranges,businessUrl,licenseNo,shopName,lisExpDate, provinceName, cityName, provinceCode, cityCode, zoneName,
                addressDtl,mailId,phone,zoneCode,name,version)
                .compose(XApi.<GetAddApplyInfoResults>getApiTransformer())
                .compose(XApi.<GetAddApplyInfoResults>getScheduler())
                .compose(getV().<GetAddApplyInfoResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<GetAddApplyInfoResults>() {
                    @Override
                    public void onNext(GetAddApplyInfoResults results) {
                        if (results.getCode() == ResultCode.SUCCESS.code()) {
//                            getV().setInfo(results.getData());
                            if ("1".equals(bindType)) {
                                getV().toMian();
                            }else {
                                getV().toMerchApplySupplier();
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
