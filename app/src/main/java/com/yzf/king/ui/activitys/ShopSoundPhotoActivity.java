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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.utils.GetImagePath;
import com.yzf.king.kit.utils.PermissionPageUtils;
import com.yzf.king.kit.utils.fileUtill;
import com.yzf.king.model.ChooseItem;
import com.yzf.king.model.servicesmodels.BaseResults;
import com.yzf.king.model.servicesmodels.GetSunMerchInfoListResult;
import com.yzf.king.model.servicesmodels.GetUploadFileResult;
import com.yzf.king.present.PShopSoundPhoto;
import com.yzf.king.widget.BottomDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import rx.functions.Action1;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class ShopSoundPhotoActivity extends XActivity<PShopSoundPhoto> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.shop_sound_photo_add_iv)
    ImageView shopSoundPhotoAddIv;
    @BindView(R.id.shop_sound_photo_store_head_iv)
    ImageView shopSoundPhotoStoreHeadIv;
    @BindView(R.id.shop_sound_photo_store_iv)
    ImageView shopSoundPhotoStoreIv;
    @BindView(R.id.shop_sound_photo_store_one_iv)
    ImageView shopSoundPhotoStoreOneIv;
    @BindView(R.id.shop_sound_photo_store_two_iv)
    ImageView shopSoundPhotoStoreTwoIv;
    @BindView(R.id.shop_sound_photo_store_three_iv)
    ImageView shopSoundPhotoStoreThreeIv;
    @BindView(R.id.shop_sound_photo_type_tv)
    TextView shopSoundPhotoTypeTv;
    @BindView(R.id.shop_sound_photo_type_rl)
    RelativeLayout shopSoundPhotoTypeRl;
    @BindView(R.id.shop_sound_photo_add_tv)
    TextView shopSoundPhotoAddTv;
    @BindView(R.id.shop_sound_photo_store_tv)
    TextView shopSoundPhotoStoreTv;
    @BindView(R.id.shop_sound_photo_storenumber_tv)
    TextView shopSoundPhotoStorenumberTv;
    @BindView(R.id.shop_sound_photo_define_bt)
    Button shopSoundPhotoDefineBt;

    private int mCount;
    private String mMobile;
    private String mFilePath;
    private String rootPath;
    private String mFilename;
    private String[] imgPath = new String[]{null};
    private String[] imgFlag = new String[]{null};
    private String[] itemList = new String[]{null};
    public static final int ZIZHI = 1;
    public static final int STORE = 2;
    public static final int STOREHEAD = 3;
    public static final int STOREONE = 4;
    public static final int STORETWO = 5;
    public static final int STORETHREE = 6;
    private GetSunMerchInfoListResult.DataBean.SubListBean subListBean;
    private GetUploadFileResult.DataBean dataBeanInfo = new GetUploadFileResult.DataBean();
    private GetUploadFileResult.DataBean dataBeanStoreInfo = new GetUploadFileResult.DataBean();
    private GetUploadFileResult.DataBean dataBeanStoreHeadInfo = new GetUploadFileResult.DataBean();
    private GetUploadFileResult.DataBean dataBeanStoreOneInfo = new GetUploadFileResult.DataBean();
    private GetUploadFileResult.DataBean dataBeanStoreTwoInfo = new GetUploadFileResult.DataBean();
    private GetUploadFileResult.DataBean dataBeanStoreThreeInfo = new GetUploadFileResult.DataBean();
    private String payType;

    @Override
    public void initData(Bundle savedInstanceState) {
        mMobile = AppUser.getInstance().getPhone();
        subListBean = (GetSunMerchInfoListResult.DataBean.SubListBean) getIntent().getSerializableExtra("subListBean");
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_sound_photo;
    }

    @Override
    public PShopSoundPhoto newP() {
        return new PShopSoundPhoto();
    }

    @Override
    protected void onResume() {
        super.onResume();
        rootPath = context.getExternalFilesDir("") + File.separator + "Pictures/";
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        itemList = new String[]{"交通出行-旅馆/酒店/度假区",
                "交通出行-铁路客运",
                "交通出行-加油",
                "休闲娱乐-娱乐票务",
                "休闲娱乐-运动健身场馆",
                "休闲娱乐-俱乐部/休闲会所",
                "休闲娱乐-游艺厅/KTV/网吧",
                "休闲娱乐-美发/美容/美甲店",
                "休闲娱乐-酒吧",
                "居民生活-咨询/法律咨询/金融咨询等",
                "居民生活-婚庆/摄影",
                "居民生活-家政/维修服务",
                "居民生活-装饰/设计",
                "居民生活-搬家/回收",
                "居民生活-宠物医院",
                "线下零售-数码电器/电脑办公",
                "线下零售-家具建材/家居厨具",
                "线下零售-服饰箱包",
                "线下零售-运动户外",
                "线下零售-美妆个护",
                "线下零售-母婴用品/儿童玩具",
                "线下零售-批发业",
                "线下零售-钟表眼镜",
                "线下零售-便利店",
                "线下零售-食品生鲜",
                "餐饮-小吃/熟食",
                "餐饮-甜品饮品",
                "餐饮-烘焙糕点",
                "餐饮-西餐",
                "餐饮-餐饮",
                "其他-游戏",
                "其他-软件/建站/技术开发",
                "其他-网络推广/网络广告"};
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

    @OnClick({R.id.shop_sound_photo_type_rl, R.id.shop_sound_photo_add_iv, R.id.shop_sound_photo_store_head_iv,
            R.id.shop_sound_photo_store_iv, R.id.shop_sound_photo_store_one_iv, R.id.shop_sound_photo_store_two_iv,
            R.id.shop_sound_photo_store_three_iv, R.id.shop_sound_photo_define_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shop_sound_photo_type_rl:
                new XPopup.Builder(context)
//                        .enableDrag(false)
                        .asBottomList("请选择", itemList,
                                new OnSelectListener() {
                                    @Override
                                    public void onSelect(int position, String text) {
                                        shopSoundPhotoTypeTv.setText(text);
                                        payType = text;
                                    }
                                })
                        .show();
                break;
            case R.id.shop_sound_photo_add_iv:
                mCount = ZIZHI;
                choosePicture(mMobile + "_ZIZHI");
                break;
            case R.id.shop_sound_photo_store_head_iv:
                mCount = STOREHEAD;
                choosePicture(mMobile + "_STOREHEAD");
                break;
            case R.id.shop_sound_photo_store_iv:
                mCount = STORE;
                choosePicture(mMobile + "_STORE");
                break;
            case R.id.shop_sound_photo_store_one_iv:
                mCount = STOREONE;
                choosePicture(mMobile + "_STOREONE");
                break;
            case R.id.shop_sound_photo_store_two_iv:
                mCount = STORETWO;
                choosePicture(mMobile + "_STORETWO");
                break;
            case R.id.shop_sound_photo_store_three_iv:
                mCount = STORETHREE;
                choosePicture(mMobile + "_STORETHREE");
                break;
            case R.id.shop_sound_photo_define_bt:
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
                    if (AppTools.isEmpty(shopSoundPhotoTypeTv.getText().toString())) {
                        showToast("营业范围为空，请重新选择");
                        return;
                    }
                    getP().addShopInfo(AppUser.getInstance().getMerchId(),subListBean.getShopId(),subListBean.getChannelCode(),"7",
                            shopSoundPhotoTypeTv.getText().toString(),dataBeanInfo.getImgPath(),dataBeanStoreInfo.getImgPath(),dataBeanStoreHeadInfo.getImgPath(),
                            dataBeanStoreOneInfo.getImgPath(),dataBeanStoreTwoInfo.getImgPath(),dataBeanStoreThreeInfo.getImgPath());
                }
                break;
            default:
                break;
        }
    }

    /**
     * 获取资质数据
     *
     * @param licenseBeanBean
     */
    public void setInfo(GetUploadFileResult.DataBean licenseBeanBean) {
        dataBeanInfo.setImgPath(licenseBeanBean.getImgPath());
    }

    /**
     * 获取店内照数据
     *
     * @param data
     */
    public void setStoreInfo(GetUploadFileResult.DataBean data) {
        dataBeanStoreInfo.setImgPath(data.getImgPath());
    }

    public void setStoreHeadInfo(GetUploadFileResult.DataBean data) {
        dataBeanStoreHeadInfo.setImgPath(data.getImgPath());
    }
    /**
     * 获取店面照数据
     *
     * @param data
     */
    public void setStoreOneInfo(GetUploadFileResult.DataBean data) {
        dataBeanStoreOneInfo.setImgPath(data.getImgPath());
    }

    public void setStoreTwoInfo(GetUploadFileResult.DataBean data) {
        dataBeanStoreTwoInfo.setImgPath(data.getImgPath());
    }

    public void setStoreThreeInfo(GetUploadFileResult.DataBean data) {
        dataBeanStoreThreeInfo.setImgPath(data.getImgPath());
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
                            File file = new File(rootPath);
                            if (!file.exists()) {
                                file.mkdirs();
                            }
                            switch (mCount) {
                                case ZIZHI:
                                    CameraActivity.toCameraActivity(context, CameraActivity.TYPE_BANKCARD);
                                    break;
                                case STOREHEAD:
                                    CameraActivity.toCameraActivity(context, CameraActivity.TYPE_BANKCARD);
                                    break;
                                case STORE:
                                    CameraActivity.toCameraActivity(context, CameraActivity.TYPE_BANKCARD);
                                    break;
                                case STOREONE:
                                    CameraActivity.toCameraActivity(context, CameraActivity.TYPE_BANKCARD);
                                    break;
                                case STORETWO:
                                    CameraActivity.toCameraActivity(context, CameraActivity.TYPE_BANKCARD);
                                    break;
                                case STORETHREE:
                                    CameraActivity.toCameraActivity(context, CameraActivity.TYPE_BANKCARD);
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
                                case ZIZHI:
                                    intent.setAction(Intent.ACTION_PICK);//Pick an item from the data
                                    intent.setType("image/*");//从所有图片中进行选择
                                    startActivityForResult(intent, 1);
                                    break;
                                case STORE:
                                    intent.setAction(Intent.ACTION_PICK);//Pick an item from the data
                                    intent.setType("image/*");//从所有图片中进行选择
                                    startActivityForResult(intent, 2);
                                    break;
                                case STOREHEAD:
                                    intent.setAction(Intent.ACTION_PICK);//Pick an item from the data
                                    intent.setType("image/*");//从所有图片中进行选择
                                    startActivityForResult(intent, 3);
                                    break;
                                case STOREONE:
                                    intent.setAction(Intent.ACTION_PICK);//Pick an item from the data
                                    intent.setType("image/*");//从所有图片中进行选择
                                    startActivityForResult(intent, 4);
                                    break;
                                case STORETWO:
                                    intent.setAction(Intent.ACTION_PICK);//Pick an item from the data
                                    intent.setType("image/*");//从所有图片中进行选择
                                    startActivityForResult(intent, 5);
                                    break;
                                case STORETHREE:
                                    intent.setAction(Intent.ACTION_PICK);//Pick an item from the data
                                    intent.setType("image/*");//从所有图片中进行选择
                                    startActivityForResult(intent, 6);
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


    /**
     * 根据filename，成功或者失败，在相应的imageview显示对应的图片
     *
     * @param file_type 图片名
     * @param success   识别成功/识别失败/上传失败
     */
    public void showphotos(String file_type, String success, String message) {
        getvDelegate().dismissLoading();
        switch (file_type) {
            case "05":
                if ("success".equals(success)) {
                    ILFactory.getLoader().loadImageSizekipMemoryCache(imgPath[0], shopSoundPhotoAddIv);
                    imgFlag[0] = "00";
                    shopSoundPhotoAddTv.setVisibility(View.GONE);
                } else if ("fail".equals(success)) {
                    ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, shopSoundPhotoAddIv);
                    imgFlag[0] = "01";
                    shopSoundPhotoAddTv.setVisibility(View.VISIBLE);
                    shopSoundPhotoAddTv.setText(message);
                } else if ("error".equals(success)) {
                    ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, shopSoundPhotoAddIv);
                    imgFlag[0] = "01";
                    shopSoundPhotoAddTv.setVisibility(View.VISIBLE);
                    shopSoundPhotoAddTv.setText(message);
                }
                break;
            case "23":
                switch (mCount) {
                    case STORE:
                        if ("success".equals(success)) {
                            ILFactory.getLoader().loadImageSizekipMemoryCache(imgPath[0], shopSoundPhotoStoreIv);
                            imgFlag[0] = "00";
                            shopSoundPhotoStoreTv.setVisibility(View.GONE);
                        } else if ("fail".equals(success)) {
                            ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, shopSoundPhotoStoreIv);
                            imgFlag[0] = "01";
                            shopSoundPhotoStoreTv.setVisibility(View.VISIBLE);
                            shopSoundPhotoStoreTv.setText(message);
                        } else if ("error".equals(success)) {
                            ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, shopSoundPhotoStoreIv);
                            imgFlag[0] = "01";
                            shopSoundPhotoStoreTv.setVisibility(View.VISIBLE);
                            shopSoundPhotoStoreTv.setText(message);
                        }
                        break;
                    case STOREHEAD:
                        if ("success".equals(success)) {
                            ILFactory.getLoader().loadImageSizekipMemoryCache(imgPath[0], shopSoundPhotoStoreHeadIv);
                            imgFlag[0] = "00";
                            shopSoundPhotoStoreTv.setVisibility(View.GONE);
                        } else if ("fail".equals(success)) {
                            ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, shopSoundPhotoStoreHeadIv);
                            imgFlag[0] = "01";
                            shopSoundPhotoStoreTv.setVisibility(View.VISIBLE);
                            shopSoundPhotoStoreTv.setText(message);
                        } else if ("error".equals(success)) {
                            ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, shopSoundPhotoStoreHeadIv);
                            imgFlag[0] = "01";
                            shopSoundPhotoStoreTv.setVisibility(View.VISIBLE);
                            shopSoundPhotoStoreTv.setText(message);
                        }
                        break;
                    default:
                        break;
                }
            case "24":
                switch (mCount) {
                    case STOREONE:
                        if ("success".equals(success)) {
                            ILFactory.getLoader().loadImageSizekipMemoryCache(imgPath[0], shopSoundPhotoStoreOneIv);
                            imgFlag[0] = "00";
                            shopSoundPhotoStorenumberTv.setVisibility(View.GONE);
                        } else if ("fail".equals(success)) {
                            ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, shopSoundPhotoStoreOneIv);
                            imgFlag[0] = "01";
                            shopSoundPhotoStorenumberTv.setVisibility(View.VISIBLE);
                            shopSoundPhotoStorenumberTv.setText(message);
                        } else if ("error".equals(success)) {
                            ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, shopSoundPhotoStoreOneIv);
                            imgFlag[0] = "01";
                            shopSoundPhotoStorenumberTv.setVisibility(View.VISIBLE);
                            shopSoundPhotoStorenumberTv.setText(message);
                        }
                        break;
                    case STORETWO:
                        if ("success".equals(success)) {
                            ILFactory.getLoader().loadImageSizekipMemoryCache(imgPath[0], shopSoundPhotoStoreTwoIv);
                            imgFlag[0] = "00";
                            shopSoundPhotoStorenumberTv.setVisibility(View.GONE);
                        } else if ("fail".equals(success)) {
                            ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, shopSoundPhotoStoreTwoIv);
                            imgFlag[0] = "01";
                            shopSoundPhotoStorenumberTv.setVisibility(View.VISIBLE);
                            shopSoundPhotoStorenumberTv.setText(message);
                        } else if ("error".equals(success)) {
                            ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, shopSoundPhotoStoreTwoIv);
                            imgFlag[0] = "01";
                            shopSoundPhotoStorenumberTv.setVisibility(View.VISIBLE);
                            shopSoundPhotoStorenumberTv.setText(message);
                        }
                        break;
                    case STORETHREE:
                        if ("success".equals(success)) {
                            ILFactory.getLoader().loadImageSizekipMemoryCache(imgPath[0], shopSoundPhotoStoreThreeIv);
                            imgFlag[0] = "00";
                            shopSoundPhotoStorenumberTv.setVisibility(View.GONE);
                        } else if ("fail".equals(success)) {
                            ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, shopSoundPhotoStoreThreeIv);
                            imgFlag[0] = "01";
                            shopSoundPhotoStorenumberTv.setVisibility(View.VISIBLE);
                            shopSoundPhotoStorenumberTv.setText(message);
                        } else if ("error".equals(success)) {
                            ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, shopSoundPhotoStoreThreeIv);
                            imgFlag[0] = "01";
                            shopSoundPhotoStorenumberTv.setVisibility(View.VISIBLE);
                            shopSoundPhotoStorenumberTv.setText(message);
                        }
                        break;
                    default:
                        break;
                }
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
                        // 传人要压缩的图片列表
                        .load(mFilePath)
                        // 忽略不压缩图片的大小
                        .ignoreBy(100)
                        // 设置压缩后文件存储位置
                        .setTargetDir(rootPath)
                        //设置回调
                        .setCompressListener(new OnCompressListener() {
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
                                    case ZIZHI:
                                        if (!context.isFinishing()) {
                                            ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", shopSoundPhotoAddIv);
                                        }
                                        imgPath[0] = file.getAbsolutePath();
                                        getP().uploadFile(AppUser.getInstance().getMerchId(), subListBean.getShopId(), "05", null,
                                                null, null, null, file, 0);
                                        break;
                                    case STORE:
                                        if (!context.isFinishing()) {
                                            ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", shopSoundPhotoStoreIv);
                                        }
                                        imgPath[0] = file.getAbsolutePath();
                                        getP().uploadFile(AppUser.getInstance().getMerchId(), subListBean.getShopId(), "23", null,
                                                null, null, null, file,mCount);
                                        break;
                                    case STOREHEAD:
                                        if (!context.isFinishing()) {
                                            ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", shopSoundPhotoStoreHeadIv);
                                        }
                                        imgPath[0] = file.getAbsolutePath();
                                        getP().uploadFile(AppUser.getInstance().getMerchId(), subListBean.getShopId(), "23", null,
                                                null, null, null, file,mCount);
                                        break;
                                    case STOREONE:
                                        if (!context.isFinishing()) {
                                            ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", shopSoundPhotoStoreOneIv);
                                        }
                                        imgPath[0] = file.getAbsolutePath();
                                        getP().uploadFile(AppUser.getInstance().getMerchId(), subListBean.getShopId(), "24", null,
                                                null, null, null, file,mCount);
                                        break;
                                    case STORETWO:
                                        if (!context.isFinishing()) {
                                            ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", shopSoundPhotoStoreTwoIv);
                                        }
                                        imgPath[0] = file.getAbsolutePath();
                                        getP().uploadFile(AppUser.getInstance().getMerchId(), subListBean.getShopId(), "24", null,
                                                null, null, null, file,mCount);
                                        break;
                                    case STORETHREE:
                                        if (!context.isFinishing()) {
                                            ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", shopSoundPhotoStoreThreeIv);
                                        }
                                        imgPath[0] = file.getAbsolutePath();
                                        getP().uploadFile(AppUser.getInstance().getMerchId(), subListBean.getShopId(), "24", null,
                                                null, null, null, file,mCount);
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
        } else if (requestCode >= 1 && requestCode <= 6 && resultCode != 0) {
            try {
                String picturePath = GetImagePath.getPath(context, data.getData());  //获取照片路径
                if (fileUtill.hasSdcard()) {
                    File file = new File(rootPath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    mFilePath = rootPath + mFilename;
                    Luban.with(this)
                            // 传人要压缩的图片列表
                            .load(picturePath)
                            // 忽略不压缩图片的大小
                            .ignoreBy(100)
                            // 设置压缩后文件存储位置
                            .setTargetDir(rootPath)
                            //设置回调
                            .setCompressListener(new OnCompressListener() {
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
                                        case ZIZHI:
                                            if (!context.isFinishing()) {
                                                ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", shopSoundPhotoAddIv);
                                            }
                                            imgPath[0] = file.getAbsolutePath();
                                            getP().uploadFile(AppUser.getInstance().getMerchId(), subListBean.getShopId(), "05", null,
                                                    null, null, null, file,0);
                                            break;
                                        case STORE:
                                            if (!context.isFinishing()) {
                                                ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", shopSoundPhotoStoreIv);
                                            }
                                            imgPath[0] = file.getAbsolutePath();
                                            getP().uploadFile(AppUser.getInstance().getMerchId(), subListBean.getShopId(), "23", null,
                                                    null, null, null, file,mCount);
                                            break;
                                        case STOREHEAD:
                                            if (!context.isFinishing()) {
                                                ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", shopSoundPhotoStoreHeadIv);
                                            }
                                            imgPath[0] = file.getAbsolutePath();
                                            getP().uploadFile(AppUser.getInstance().getMerchId(), subListBean.getShopId(), "23", null,
                                                    null, null, null, file,mCount);
                                            break;
                                        case STOREONE:
                                            if (!context.isFinishing()) {
                                                ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", shopSoundPhotoStoreOneIv);
                                            }
                                            imgPath[0] = file.getAbsolutePath();
                                            getP().uploadFile(AppUser.getInstance().getMerchId(), subListBean.getShopId(), "24", null,
                                                    null, null, null, file,mCount);
                                            break;
                                        case STORETWO:
                                            if (!context.isFinishing()) {
                                                ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", shopSoundPhotoStoreTwoIv);
                                            }
                                            imgPath[0] = file.getAbsolutePath();
                                            getP().uploadFile(AppUser.getInstance().getMerchId(), subListBean.getShopId(), "24", null,
                                                    null, null, null, file,mCount);
                                            break;
                                        case STORETHREE:
                                            if (!context.isFinishing()) {
                                                ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", shopSoundPhotoStoreThreeIv);
                                            }
                                            imgPath[0] = file.getAbsolutePath();
                                            getP().uploadFile(AppUser.getInstance().getMerchId(), subListBean.getShopId(), "24", null,
                                                    null, null, null, file,mCount);
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



}
