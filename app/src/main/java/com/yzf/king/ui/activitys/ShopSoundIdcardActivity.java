package com.yzf.king.ui.activitys;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.utils.GetImagePath;
import com.yzf.king.kit.utils.PermissionPageUtils;
import com.yzf.king.kit.utils.fileUtill;
import com.yzf.king.model.ChooseItem;
import com.yzf.king.model.servicesmodels.GetSunMerchInfoListResult;
import com.yzf.king.model.servicesmodels.GetUploadFileIdResult;
import com.yzf.king.model.servicesmodels.GetUploadFileResult;
import com.yzf.king.model.servicesmodels.UploadPhotosResult;
import com.yzf.king.present.PShopSoundIdcard;
import com.yzf.king.widget.BottomDialog;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import rx.functions.Action1;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class ShopSoundIdcardActivity extends XActivity<PShopSoundIdcard> {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.shop_sound_idcard_zm_iv)
    ImageView shopSoundIdcardZmIv;
    @BindView(R.id.shop_sound_idcard_fm_iv)
    ImageView shopSoundIdcardFmIv;
    @BindView(R.id.shop_sound_idcard_name_tv)
    TextView shopSoundIdcardNameTv;
    @BindView(R.id.shop_sound_idcard_number_tv)
    TextView shopSoundIdcardNumberTv;
    @BindView(R.id.shop_sound_idcard_define_bt)
    Button shopSoundAddressDefineBt;
    @BindView(R.id.shop_sound_idcard_tv)
    TextView shopSoundIdcardTv;

    private String[] imgPath = new String[]{null};
    private String[] imgFlag = new String[]{null};

    private int mCount;
    private String mMobile;
    private String mFilePath;
    private String rootPath;
    private String mFilename;
    private static final int IDCARDFRONTZM = 0;
    private static final int IDCARDFRONTFM = 1;
    private GetSunMerchInfoListResult.DataBean.SubListBean subListBean;
    private GetUploadFileIdResult.DataBean dataBeanZM = new GetUploadFileIdResult.DataBean();
    private GetUploadFileIdResult.DataBean dataBeanFM = new GetUploadFileIdResult.DataBean();
    private GetUploadFileIdResult.DataBean.IdBeanBean idBeanBean = new GetUploadFileIdResult.DataBean.IdBeanBean();
    private String idvalidity;

    @Override
    public void initData(Bundle savedInstanceState) {
        mMobile = AppUser.getInstance().getPhone();
        subListBean = (GetSunMerchInfoListResult.DataBean.SubListBean) getIntent().getSerializableExtra("subListBean");
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_sound_idcard;
    }

    @Override
    public PShopSoundIdcard newP() {
        return new PShopSoundIdcard();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
    }

    /**
     * 初始化Toolbar
     */
    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_black_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title.setText("店铺申请");
    }

    @Override
    protected void onResume() {
        super.onResume();
        rootPath = context.getExternalFilesDir("") + File.separator + "Pictures/";
    }

    /**
     * 标题栏监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * activity跳转
     */
    public void JumpActivity(Class<?> activity, boolean isfinish) {
        getvDelegate().dismissLoading();
        Router.newIntent(context)
                .to(activity)
                .launch();
        if (isfinish) {
            Router.pop(context);
        }
    }

    /**
     * activity跳转
     */
    public void JumpActivity(Class<?> activity) {
        Router.newIntent(context)
                .to(activity)
                .launch();
    }

    /**
     * 显示Toast
     *
     * @param msg
     */
    public void showToast(String msg) {
        getvDelegate().toastShort(msg);
    }

    /**
     * 显示单按钮对话框
     *
     * @param msg
     */
    public void showErrorDialog(String msg) {
        getvDelegate().showErrorDialog(msg);
    }

    /**
     * 显示错误信息
     *
     * @param error
     */
    public void showError(NetError error) {
        getvDelegate().showError(error);
    }

    /**
     * 显示双按钮对话框
     *
     * @param msg
     * @param callback
     */
    public void showNoticeDialog(String msg, MaterialDialog.SingleButtonCallback callback) {
        getvDelegate().showNoticeDialog(msg, callback);
    }

    @OnClick({R.id.shop_sound_idcard_zm_iv, R.id.shop_sound_idcard_fm_iv, R.id.shop_sound_idcard_define_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shop_sound_idcard_zm_iv:
                mCount = IDCARDFRONTZM;
                choosePicture(mMobile + "_IDCARDFRONTZM");
                break;
            case R.id.shop_sound_idcard_fm_iv:
                mCount = IDCARDFRONTFM;
                choosePicture(mMobile + "_IDCARDFRONTFM");
                break;
            case R.id.shop_sound_idcard_define_bt:
                boolean flag = false;
                for (int i = 0; i < imgFlag.length; i++) {
                    String temp = imgFlag[i];
                    if (AppTools.isEmpty(temp) || !temp.equals("00")) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    showToast("请重新提交上传失败的图片");
                } else {
                    if (AppTools.isEmpty(shopSoundIdcardNameTv.getText().toString())) {
                        showToast("请上传身份证姓名");
                        return;
                    }
                    if (AppTools.isEmpty(shopSoundIdcardNumberTv.getText().toString())) {
                        showToast("请上传身份证号");
                        return;
                    }
                    getP().addShopInfo(AppUser.getInstance().getMerchId(),subListBean.getShopId(),subListBean.getChannelCode(),"3",
                            shopSoundIdcardNameTv.getText().toString(), shopSoundIdcardNumberTv.getText().toString(),dataBeanZM.getImgPath(),
                            dataBeanFM.getImgPath(),idvalidity);
                }
                break;
            default:
                break;
        }
    }

    public void setIdCardInfoZM(GetUploadFileIdResult.DataBean idBean) {
        shopSoundIdcardNameTv.setText(idBean.getIdBean().getName().trim().replace(" ", ""));
        shopSoundIdcardNumberTv.setText(idBean.getIdBean().getIdNo().trim().replace(" ", ""));

        dataBeanZM.setImgPath(idBean.getImgPath());

    }

    public void setIdCardInfoFM(GetUploadFileIdResult.DataBean idBean) {
        dataBeanFM.setImgPath(idBean.getImgPath());

        idBeanBean.setEffectDate(idBean.getIdBean().getEffectDate());
        idBeanBean.setInvalidDate(idBean.getIdBean().getInvalidDate());

        idvalidity = Kits.Date.formatTime(idBean.getIdBean().getEffectDate(),"yyyymmdd","yyyy-mm-dd") + ","
                + Kits.Date.formatTime(idBean.getIdBean().getInvalidDate(),"yyyymmdd","yyyy-mm-dd");
    }


    /**
     * 根据filename，成功或者失败，在相应的imageview显示对应的图片
     *
     * @param file_type 图片名
     * @param success   识别成功/识别失败/上传失败
     */
    public void showphotos(String file_type, String success, String message) {
        getvDelegate().dismissLoading();
        switch (file_type) {
            case "21":
                if (success.equals("success")) {
                    ILFactory.getLoader().loadImageSizekipMemoryCache(imgPath[0], shopSoundIdcardZmIv);
                    imgFlag[0] = "00";
                    shopSoundIdcardTv.setVisibility(View.GONE);
                } else if (success.equals("fail")) {
                    ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, shopSoundIdcardZmIv);
                    imgFlag[0] = "01";
                    shopSoundIdcardTv.setVisibility(View.VISIBLE);
                    shopSoundIdcardTv.setText(message);
                } else if (success.equals("error")) {
                    ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, shopSoundIdcardZmIv);
                    imgFlag[0] = "01";
                    shopSoundIdcardTv.setVisibility(View.VISIBLE);
                    shopSoundIdcardTv.setText(message);
                }
                break;
            case "22":
                if (success.equals("success")) {
                    ILFactory.getLoader().loadImageSizekipMemoryCache(imgPath[0], shopSoundIdcardFmIv);
                    imgFlag[0] = "00";
                    shopSoundIdcardTv.setVisibility(View.GONE);
                } else if (success.equals("fail")) {
                    ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, shopSoundIdcardFmIv);
                    imgFlag[0] = "01";
                    shopSoundIdcardTv.setVisibility(View.VISIBLE);
                    shopSoundIdcardTv.setText(message);
                } else if (success.equals("error")) {
                    ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, shopSoundIdcardFmIv);
                    imgFlag[0] = "01";
                    shopSoundIdcardTv.setVisibility(View.VISIBLE);
                    shopSoundIdcardTv.setText(message);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CameraActivity.REQUEST_CODE && resultCode == CameraActivity.RESULT_CODE) {
            if (fileUtill.hasSdcard()) {
//                mFilePath = rootPath + mFilename;
                File file = new File(rootPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                mFilePath = data.getExtras().getString("path");
                Luban.with(this)
                        .load(mFilePath)                                   // 传人要压缩的图片列表
                        .ignoreBy(100)                                  // 忽略不压缩图片的大小
                        .setTargetDir(rootPath)                        // 设置压缩后文件存储位置
                        .setCompressListener(new OnCompressListener() { //设置回调
                            @Override
                            public void onStart() {
                                // TODO 压缩开始前调用，可以在方法内启动 loading UI
                            }

                            @Override
                            public void onSuccess(File file) {
                                // TODO 压缩成功后调用，返回压缩后的图片文件
                                File delPath = new File(mFilePath);
                                fileUtill.delDir(delPath);
                                switch (mCount) {
                                    case IDCARDFRONTZM:
                                        if (!context.isFinishing()) {
                                            ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", shopSoundIdcardZmIv);
                                        }
                                        imgPath[0] = file.getAbsolutePath();
                                        getP().uploadFileId(AppUser.getInstance().getMerchId(), subListBean.getShopId(), "21", null,
                                                null, null, null, file);
                                        break;
                                    case IDCARDFRONTFM:
                                        if (!context.isFinishing()) {
                                            ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", shopSoundIdcardFmIv);
                                        }
                                        imgPath[0] = file.getAbsolutePath();
                                        getP().uploadFileId(AppUser.getInstance().getMerchId(), subListBean.getShopId(), "22", null,
                                                null, null, null, file);
                                        break;
                                    default:
                                        break;
                                }
                                file.deleteOnExit();
                            }

                            @Override
                            public void onError(Throwable e) {
                                // TODO 当压缩过程出现问题时调用
                                showToast("图片处理出错，请重试");
                            }
                        }).launch();    //启动压缩

            } else {
                showToast("请检查是否有外置存储卡");
            }
        } else if (requestCode == 2 && resultCode != 0 || requestCode == 3 && resultCode != 0) {
            try {
                String picturePath = GetImagePath.getPath(context, data.getData());  //获取照片路径
                if (fileUtill.hasSdcard()) {
                    mFilePath = rootPath + mFilename;
                    File file = new File(rootPath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    Luban.with(this)
                            .load(picturePath)                                   // 传人要压缩的图片列表
                            .ignoreBy(100)                                  // 忽略不压缩图片的大小
                            .setTargetDir(rootPath)                        // 设置压缩后文件存储位置
                            .setCompressListener(new OnCompressListener() { //设置回调
                                @Override
                                public void onStart() {
                                    // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                }

                                @Override
                                public void onSuccess(File file) {
                                    // TODO 压缩成功后调用，返回压缩后的图片文件
                                    File delPath = new File(mFilePath);
                                    fileUtill.delDir(delPath);
                                    switch (mCount) {
                                        case IDCARDFRONTZM:
                                            if (!context.isFinishing()) {
                                                ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", shopSoundIdcardZmIv);
                                            }
                                            imgPath[0] = file.getAbsolutePath();
                                            getP().uploadFileId(AppUser.getInstance().getMerchId(), subListBean.getShopId(), "21", null,
                                                    null, null, null, file);
                                            break;
                                        case IDCARDFRONTFM:
                                            if (!context.isFinishing()) {
                                                ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", shopSoundIdcardFmIv);
                                            }
                                            imgPath[0] = file.getAbsolutePath();
                                            getP().uploadFileId(AppUser.getInstance().getMerchId(), subListBean.getShopId(), "22", null,
                                                    null, null, null, file);
                                            break;
                                        default:
                                            break;
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    // TODO 当压缩过程出现问题时调用
                                    showToast("图片处理出错，请重试");
                                }
                            }).launch();    //启动压缩
                }
            } catch (Exception e) {
                // TODO Auto-generatedcatch block
                e.printStackTrace();
            }
        }

    }

    /**
     * 底部对话框
     *
     * @param fileName 图片名称
     */
    private void choosePicture(final String fileName) {
        BottomDialog bottomDailog = new BottomDialog(this);
        ArrayList<ChooseItem> imgArray = new ArrayList<>();
        imgArray.add(new ChooseItem("拍照", null));
        imgArray.add(new ChooseItem("相册", null));
        bottomDailog.showAlert(null, imgArray, new BottomDialog.OnAlertSelectId() {

            @Override
            public void onClick(int whichButton) {
                if (whichButton == 0) {
                    takePhoto(fileName);
                } else if (whichButton == 1) {
                    gallery(fileName);
                }
            }
        });
    }

    /**
     * 拍照选取图片
     *
     * @param fileName
     */
    private void takePhoto(final String fileName) {
        getRxPermissions()
                .request(Manifest.permission.CAMERA)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) {
                            //TODO 许可
                            String path = rootPath;
                            File dir = new File(path);
                            if (!dir.exists()) {
                                dir.mkdirs();
                            }
                            switch (mCount) {
                                case IDCARDFRONTZM:
                                    CameraActivity.toCameraActivity(context, CameraActivity.TYPE_IDCARD_FRONT);
                                    break;
                                case IDCARDFRONTFM:
                                    CameraActivity.toCameraActivity(context, CameraActivity.TYPE_IDCARD_FRONT);
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            //TODO 未许可
//                            showToast("权限未获取");
                            showNoticeDialog("尚未获取权限，是否去开启权限?", new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    PermissionPageUtils permissionPageUtils = new PermissionPageUtils(context);
                                    permissionPageUtils.jumpPermissionPage();
                                }

                            });
                        }
                    }
                });

    }

    /**
     * 从相册选取图片
     *
     * @param fileName 图片名称
     */
    private void gallery(final String fileName) {
        getRxPermissions()
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) {
                            //TODO 许可
                            Intent intent = new Intent();
                            mFilename = fileName + ".jpg";
                            String path = rootPath;
                            File dir = new File(path);
                            if (!dir.exists()) {
                                dir.mkdirs();
                            }
                            File file = new File(rootPath,
                                    mFilename);
                            if (file.exists()) {
                                file.delete();
                            }
                            switch (mCount) {
                                case IDCARDFRONTZM:
                                    intent.setAction(Intent.ACTION_PICK);//Pick an item from the data
                                    intent.setType("image/*");//从所有图片中进行选择
                                    startActivityForResult(intent, 2);
                                    break;
                                case IDCARDFRONTFM:
                                    intent.setAction(Intent.ACTION_PICK);//Pick an item from the data
                                    intent.setType("image/*");//从所有图片中进行选择
                                    startActivityForResult(intent, 3);
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            //TODO 未许可
//                            showToast("权限未获取");
                            showNoticeDialog("尚未获取权限，是否去开启权限?", new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    if (which == DialogAction.POSITIVE) {
                                        PermissionPageUtils permissionPageUtils = new PermissionPageUtils(context);
                                        permissionPageUtils.jumpPermissionPage();
                                    }
                                }
                            });
                        }
                    }
                });
    }

}
