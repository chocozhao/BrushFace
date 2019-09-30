package com.yzf.king.ui.activitys;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.xw.repo.XEditText;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.utils.GetImagePath;
import com.yzf.king.kit.utils.PermissionPageUtils;
import com.yzf.king.kit.utils.fileUtill;
import com.yzf.king.model.ChooseItem;
import com.yzf.king.model.servicesmodels.GetUrlResult;
import com.yzf.king.model.servicesmodels.UploadPhotosResult;
import com.yzf.king.present.PUploadBankCard;
import com.yzf.king.widget.BottomDialog;
import com.yzf.king.widget.StateButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.imageloader.ILoader;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import rx.functions.Action1;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


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
 * ClassName：UploadIdCardActivity
 * Description: 上传储蓄卡图片界面
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/3/22 21:04
 * Modified By：
 * Fixtime：2017/3/22 21:04
 * FixDescription：
 */
public class UploadBankCardActivity extends XActivity<PUploadBankCard> {

    private static final int BANKFRONT = 2;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ulbankcard_zm_iv)
    ImageView ulbankcardZmIv;
    @BindView(R.id.ulbankcard_zm_tv)
    TextView ulbankcardZmTv;
    @BindView(R.id.ulbankcard_no_et)
    XEditText ulbankcardNoEt;
    @BindView(R.id.ulbankcard_phone_et)
    XEditText ulbankcardPhoneEt;
    @BindView(R.id.ulbankcard_confirm_bt)
    StateButton ulbankcardConfirmBt;

    private String[] imgPath = new String[]{null};
    private String[] imgFlag = new String[]{null};

    private int mCount;
    private String mMobile;
    private String mFilePath;
    private String rootPath;
    private String mFilename;

    public static final String IDNO = "idNo";
    public static final String NAME = "name";
    private String idNo;
    private String name;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        mMobile = AppUser.getInstance().getPhone();
        idNo = getIntent().getStringExtra(IDNO);
        name = getIntent().getStringExtra(NAME);
    }

    @Override
    protected void onResume() {
        super.onResume();
        rootPath = context.getExternalFilesDir("") + File.separator + "Pictures/";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_upload_bankcard;
    }

    @Override
    public PUploadBankCard newP() {
        return new PUploadBankCard();
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
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left_black_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title.setText("身份认证");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

//    @Override
//    public int getOptionsMenuId() {
//        return R.menu.menu_limits;
//    }

    /**
     * 标题栏监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                break;
            case R.id.menu_text:
                List<GetUrlResult.DataBean> list = AppUser.getInstance().getUrlBean();
                String target = null;//http://h5.yiyoupay.net/apply/html/creditApplication.html
                if (list != null && list.size() > 0) {
                    for (GetUrlResult.DataBean dataBean : list) {
                        if (dataBean.getType().equals("supVerifyUrl")) {
                            target = dataBean.getUrl();
                            break;
                        }
                    }
                }
                if (!AppTools.isEmpty(target)) {
                    AgenWebViewActivity.launch(context, target, null);
                } else {
                    showToast("暂无数据");
                }
                break;
                default:
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * activity跳转
     */
    public void JumpActivity(Class<?> activity, boolean isfinish) {
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
    public void JumpActivity(Class<?> activity, boolean isfinish, String idNo, String name) {
        Router.newIntent(context)
                .to(activity)
                .launch();
        if (isfinish) {
            Router.pop(context);
        }
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
     * 显示双按钮对话框
     *
     * @param msg
     * @param callback
     */
    public void showNoticeDialog(String msg, MaterialDialog.SingleButtonCallback callback) {
        getvDelegate().showNoticeDialog(msg, callback);
    }

    /**
     * 显示错误信息
     *
     * @param error
     */
    public void showError(NetError error) {
        getvDelegate().showError(error);
    }


    @OnClick({R.id.ulbankcard_zm_iv, R.id.ulbankcard_confirm_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ulbankcard_zm_iv:
                mCount = BANKFRONT;
                choosePicture(mMobile + "_BANKFRONT");
                break;
            case R.id.ulbankcard_confirm_bt:
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
                    String cardNo = ulbankcardNoEt.getTextEx();
                    String phoneNo = ulbankcardPhoneEt.getTextEx();
                    if (AppTools.isEmpty(cardNo)) {
                        showToast("银行卡号为空");
                        return;
                    }
                    if (cardNo.length() < 12) {
                        showToast("银行卡号格式不正确");
                        return;
                    }
                    if (AppTools.isEmpty(phoneNo)) {
                        showToast("预留手机号为空");
                        return;
                    }
                    getvDelegate().showLoading();
                    getP().cardOperate(AppUser.getInstance().getMerchId(), "0", cardNo, name, phoneNo, idNo);
                }
                break;
        }
    }

    public void setBankCardInfo(UploadPhotosResult.DataBean.BankCardBean bankCardInfo) {
        ulbankcardNoEt.setText(bankCardInfo.getCardNo().trim().replace(" ", ""));
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
            case "02":
                if (success.equals("success")) {
                    ILFactory.getLoader().loadImageSizekipMemoryCache( imgPath[0],ulbankcardZmIv);
                    imgFlag[0] = "00";
                    ulbankcardZmTv.setVisibility(View.GONE);
                } else if (success.equals("fail")) {
                    ILFactory.getLoader().loadImage( R.mipmap.loading_fail_img,ulbankcardZmIv);
                    imgFlag[0] = "01";
                    ulbankcardZmTv.setVisibility(View.VISIBLE);
                    ulbankcardZmTv.setText(message);
                } else if (success.equals("error")) {
                    ILFactory.getLoader().loadImage( R.mipmap.loading_fail_img,ulbankcardZmIv);
                    imgFlag[0] = "01";
                    ulbankcardZmTv.setVisibility(View.VISIBLE);
                    ulbankcardZmTv.setText(message);
                }
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
                                    case BANKFRONT:
                                        if (!context.isFinishing()) {
                                            ILFactory.getLoader().loadGif( "file:///android_asset/loading.gif",ulbankcardZmIv);
                                        }
                                        imgPath[0] = file.getAbsolutePath();
//                                        getvDelegate().showLoading();
                                        getP().uploadPhoto(file, "02", AppUser.getInstance().getMerchId());
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
                                        case BANKFRONT:
                                            if (!context.isFinishing()) {
                                                ILFactory.getLoader().loadGif( "file:///android_asset/loading.gif",ulbankcardZmIv);
                                            }
                                            imgPath[0] = file.getAbsolutePath();
                                            getP().uploadPhoto(file, "02", AppUser.getInstance().getMerchId());
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
                                case BANKFRONT:
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
