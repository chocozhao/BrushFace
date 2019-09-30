package com.yzf.king.ui.activitys;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.xw.repo.XEditText;
import com.yzf.king.App;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.utils.GetImagePath;
import com.yzf.king.kit.utils.PermissionPageUtils;
import com.yzf.king.kit.utils.fileUtill;
import com.yzf.king.model.ChooseItem;
import com.yzf.king.model.servicesmodels.GetSunMerchInfoListResult;
import com.yzf.king.model.servicesmodels.GetUploadFileResult;
import com.yzf.king.present.PShopSoundPermit;
import com.yzf.king.widget.BottomDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import rx.functions.Action1;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class ShopSoundPermitActivity extends XActivity<PShopSoundPermit> implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.shop_sound_permit_type_tv)
    TextView shopSoundPermitTypeTv;
    @BindView(R.id.shop_sound_permit_iv)
    ImageView shopSoundPermitIv;
    @BindView(R.id.shop_sound_permit_type_rl)
    RelativeLayout shopSoundPermitTypeRl;
    @BindView(R.id.shop_sound_permit_add_iv)
    ImageView shopSoundPermitAddIv;
    @BindView(R.id.shop_sound_permit_shopname_tv)
    XEditText shopSoundPermitShopnameTv;
    @BindView(R.id.shop_sound_permit_number_et)
    XEditText shopSoundPermitNumberEt;
    @BindView(R.id.shop_sound_permit_legal_person_tv)
    XEditText shopSoundPermitLegalPersonTv;
    @BindView(R.id.shop_sound_permit_data_tv)
    TextView shopSoundPermitDataTv;
    @BindView(R.id.shop_sound_permit_add_tv)
    TextView shopSoundPermitAddTv;
    private String type;
    private int mCount;
    private String mMobile;
    private String mFilePath;
    private String rootPath;
    private String mFilename;
    private String[] imgPath = new String[]{null};
    private String[] imgFlag = new String[]{null};
    private static final int IDCARDBACK = 1;
    private GetSunMerchInfoListResult.DataBean.SubListBean subListBean;
    private GetUploadFileResult.DataBean dataBean = new GetUploadFileResult.DataBean();
    private String date;

    @Override
    public void initData(Bundle savedInstanceState) {
        mMobile = AppUser.getInstance().getPhone();
        subListBean = (GetSunMerchInfoListResult.DataBean.SubListBean) getIntent().getSerializableExtra("subListBean");
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_sound_permit;
    }

    @Override
    public PShopSoundPermit newP() {
        return new PShopSoundPermit();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
    }


    @Override
    protected void onResume() {
        super.onResume();
        rootPath = context.getExternalFilesDir("") + File.separator + "Pictures/";
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


    @OnClick({R.id.shop_sound_permit_type_rl, R.id.shop_sound_permit_add_iv,R.id.shop_sound_permit_define_bt,
    R.id.shop_sound_permit_data_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shop_sound_permit_type_rl:
                new XPopup.Builder(context)
//                        .enableDrag(false)
                        .asBottomList("请选择", new String[]{"三证合一", "未三证合一"},
                                new OnSelectListener() {
                                    @Override
                                    public void onSelect(int position, String text) {
                                        switch (position) {
                                            case 0:
                                                type = "0";
                                                break;
                                            case 1:
                                                type = "1";
                                                break;
                                            default:
                                                break;
                                        }
                                        shopSoundPermitTypeTv.setText(text);
                                    }
                                })
                        .show();
                break;
            case R.id.shop_sound_permit_add_iv:
                mCount = IDCARDBACK;
                choosePicture(mMobile + "_IDCARDBACK");
                break;
            case R.id.shop_sound_permit_define_bt:
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
                    if (AppTools.isEmpty(shopSoundPermitTypeTv.getText().toString())) {
                        showToast("请选择营业执照类型");
                        return;
                    }

                    if (AppTools.isEmpty(shopSoundPermitShopnameTv.getTextEx())) {
                        showToast("请输入店铺名称");
                        return;
                    }
                    if (!AppTools.isEmpty(shopSoundPermitShopnameTv.getTextEx()) && shopSoundPermitShopnameTv.getTextEx().length() < 2) {
                        showToast("店铺名称格式不正确");

                        return;
                    }
                    if (AppTools.isEmpty(shopSoundPermitNumberEt.getTextEx())) {
                        showToast("请上传营业执照号");
                        return;
                    }
                    if (!AppTools.isEmpty(shopSoundPermitNumberEt.getTextEx()) && shopSoundPermitNumberEt.getTextEx().length() < 10) {
                        showToast("营业执照号格式不正确");
                        return;
                    }

                    if (AppTools.isEmpty(shopSoundPermitLegalPersonTv.getTextEx())) {
                        showToast("请上传法人姓名");
                        return;
                    }
                    if (!AppTools.isEmpty(shopSoundPermitLegalPersonTv.getTextEx()) && shopSoundPermitLegalPersonTv.getTextEx().length() < 2) {
                        showToast("法人姓名格式不正确");
                        return;
                    }

                    if (AppTools.isEmpty(shopSoundPermitDataTv.getText().toString())) {
                        showToast("请选择营业执照注册时间");
                        return;
                    }

                    getP().addShopInfo(AppUser.getInstance().getMerchId(),subListBean.getShopId(),subListBean.getChannelCode(),"1",type,
                            shopSoundPermitLegalPersonTv.getTextEx(),dataBean.getImgPath(),shopSoundPermitNumberEt.getTextEx(),shopSoundPermitShopnameTv.getTextEx(),date);
                }
                break;
            case R.id.shop_sound_permit_data_tv:
                Calendar calendarStart = Calendar.getInstance();
                DatePickerDialog datePickerDialogStart = DatePickerDialog.newInstance(
                        this,
                        calendarStart.get(Calendar.YEAR),
                        calendarStart.get(Calendar.MONTH),
                        calendarStart.get(Calendar.DAY_OF_MONTH)
                );
                FragmentManager fragmentManager = context.getFragmentManager();
                datePickerDialogStart.show(fragmentManager, "Datepickerdialog");
                break;
            default:
                break;
        }
    }

    /**
     * 时间选择器
     * @param view
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     */
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        monthOfYear = monthOfYear + 1;
        String month;
        if (monthOfYear < 10) {
            month = "0" + monthOfYear;
        } else {
            month = monthOfYear + "";
        }
        String day;
        if (dayOfMonth < 10) {
            day = "0" + dayOfMonth;
        } else {
            day = dayOfMonth + "";
        }
        date = year +"-"+ month +"-"+ day;
        shopSoundPermitDataTv.setText(date);
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
                                case IDCARDBACK:
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
                            intent.setAction(Intent.ACTION_PICK);//Pick an item from the data
                            intent.setType("image/*");//从所有图片中进行选择
                            startActivityForResult(intent, 2);

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

    public void setIdInfo(GetUploadFileResult.DataBean licenseBeanBean) {
        shopSoundPermitShopnameTv.setText(licenseBeanBean.getLicenseBean().getShopName());
        shopSoundPermitLegalPersonTv.setText(licenseBeanBean.getLicenseBean().getName());
        shopSoundPermitNumberEt.setText(licenseBeanBean.getLicenseBean().getLicenseNo().trim().replace(" ", ""));

        dataBean.setImgPath(licenseBeanBean.getImgPath());
        dataBean.setImgId(licenseBeanBean.getImgId());
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
            case "04":
                if ("success".equals(success)) {
                    ILFactory.getLoader().loadImageSizekipMemoryCache(imgPath[0], shopSoundPermitAddIv);
                    imgFlag[0] = "00";
                    shopSoundPermitAddTv.setVisibility(View.GONE);
                } else if ("fail".equals(success)) {
                    ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, shopSoundPermitAddIv);
                    imgFlag[0] = "01";
                    shopSoundPermitAddTv.setVisibility(View.VISIBLE);
                    shopSoundPermitAddTv.setText(message);
                } else if ("error".equals(success)) {
                    ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, shopSoundPermitAddIv);
                    imgFlag[0] = "01";
                    shopSoundPermitAddTv.setVisibility(View.VISIBLE);
                    shopSoundPermitAddTv.setText(message);
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
                        .ignoreBy(1024)                                  // 忽略不压缩图片的大小
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
                                    case IDCARDBACK:
                                        if (!context.isFinishing()) {
                                            ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", shopSoundPermitAddIv);
                                        }
                                        imgPath[0] = file.getAbsolutePath();
//                                        getvDelegate().showLoading();
                                        getP().uploadFile(AppUser.getInstance().getMerchId(), subListBean.getShopId(), "04", null,
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
        } else if (requestCode == 2 && resultCode != 0) {
            try {
                String picturePath = GetImagePath.getPath(context, data.getData());  //获取照片路径
                if (fileUtill.hasSdcard()) {
                    File file = new File(rootPath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    mFilePath = rootPath + mFilename;
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
                                        case IDCARDBACK:
                                            if (!context.isFinishing()) {
                                                ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", shopSoundPermitAddIv);
                                            }
                                            imgPath[0] = file.getAbsolutePath();
                                            getP().uploadFile(AppUser.getInstance().getMerchId(), subListBean.getShopId(), "04", null,
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
}
