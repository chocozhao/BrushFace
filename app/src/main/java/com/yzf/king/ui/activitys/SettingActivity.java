package com.yzf.king.ui.activitys;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.allenliu.versionchecklib.callback.OnCancelListener;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.CustomVersionDialogListener;
import com.allenliu.versionchecklib.v2.callback.ForceUpdateListener;
import com.yzf.king.R;
import com.yzf.king.kit.ActivityManager;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.utils.PermissionPageUtils;
import com.yzf.king.model.servicesmodels.CheckVersionResults;
import com.yzf.king.model.servicesmodels.GetUrlResult;
import com.yzf.king.present.PSetting;
import com.yzf.king.widget.PopView.NotificatonUpPopup;
import com.yzf.king.widget.StateButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import rx.functions.Action1;

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
 * ClassName：MerchInfoActivity
 * Description:设置
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/2/20 19:52
 * Modified By：
 * Fixtime：2019/2/20 19:52
 * FixDescription：
 */
public class SettingActivity extends XActivity<PSetting> {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.setting_phone_ll)
    LinearLayout settingPhoneLl;
    @BindView(R.id.setting_pwd_ll)
    LinearLayout settingPwdLl;
    @BindView(R.id.setting_version_tv)
    TextView settingVersionTv;
    @BindView(R.id.setting_about_ll)
    LinearLayout settingAboutLl;
    @BindView(R.id.setting_version_ll)

    LinearLayout settingVersionLl;
    @BindView(R.id.setting_exit_bt)
    StateButton settingExitBt;
    @BindView(R.id.setting_settcard_ll)
    LinearLayout settingSettcardLl;
    @BindView(R.id.setting_verify_ll)
    LinearLayout settingVerifyLl;

    DownloadBuilder builder;


    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public PSetting newP() {
        return new PSetting();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        String version = Kits.Package.getVersionName(context);
        settingVersionTv.setText(version);
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
        title.setText("设置");
    }

    /**
     * 标题栏监听
     *
     * @param item
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
        getvDelegate().dismissLoading();
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
     * 显示双按钮对话框
     *
     * @String msg
     * @MaterialDialog.SingleButtonCallback callback
     */
    public void showNoticeDialog(String msg, MaterialDialog.SingleButtonCallback callback) {
        getvDelegate().showNoticeDialog(msg, callback);
    }

    /**
     * 显示双按钮对话框
     *
     * @param msg
     * @param
     */
    public void showDialog(String title, String msg, String positiveText, String negativeText, MaterialDialog.SingleButtonCallback singleButtonCallback, Boolean cancle) {
        getvDelegate().showDialog(title, msg, positiveText, negativeText, singleButtonCallback, cancle);
    }

    /**
     * 显示错误信息
     *
     * @NetError error
     */
    public void showError(NetError error) {
        getvDelegate().showError(error);
    }


    @OnClick({R.id.setting_phone_ll, R.id.setting_pwd_ll, R.id.setting_about_ll,
            R.id.setting_exit_bt, R.id.setting_version_ll, R.id.setting_settcard_ll, R.id.setting_verify_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_phone_ll:
                showToast("暂未开放");
                break;
            case R.id.setting_pwd_ll:
                JumpActivity(ChangePwdActivity.class);
                break;
            case R.id.setting_version_ll:
                checkUpdate(true);
                break;
            case R.id.setting_about_ll:
                List<GetUrlResult.DataBean> list = AppUser.getInstance().getUrlBean();
                String target = null;
                if (list != null && list.size() > 0) {
                    for (GetUrlResult.DataBean dataBean : list) {
                        if (dataBean.getType().equals("aboutUsUrl")) {
                            target = dataBean.getUrl();
                            break;
                        }
                    }
                }
                if (!AppTools.isEmpty(target)) {
                    AgenWebViewActivity.launch(context, target, null);
                } else {
                    showToast("暂未开放");
                }
                break;
            case R.id.setting_exit_bt:
                showNoticeDialog("是否退出登陆？", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            JumpActivity(LoginActivity.class, true);
//                            ActivityManager.getInstance().finishActivity(MainActivity.class);
                            ActivityManager.getInstance().finishAllActivityExceptOne(LoginActivity.class);
                        }
                    }
                });
                break;
            case R.id.setting_settcard_ll:
                if (getP().verifyStatus(AppUser.getInstance().getStatus())) {
                    JumpActivity(SetteInfoActivity.class);
                }
                break;
            case R.id.setting_verify_ll:
                getP().toVerify(AppUser.getInstance().getStatus());
                break;
                default:break;
        }
    }

    public void update(final CheckVersionResults results) {
        getvDelegate().dismissLoading();
        getRxPermissions()
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) {
                            if (!context.isFinishing()) {
                                builder = AllenVersionChecker
                                        .getInstance()
                                        .downloadOnly(crateUIData(results))
                                        .setCustomVersionDialogListener(createCustomDialogTwo(results))
                                        .setForceRedownload(true) //默认false
                                        .setShowNotification(false);
//                                builder.setCustomVersionDialogListener(createCustomDialogTwo());
                                if ("1".equals(results.getData().getUpdateFlag())) {//强制更新
                                    builder.setForceUpdateListener(new ForceUpdateListener() {
                                        @Override
                                        public void onShouldForceUpdate() {
                                        }
                                    });
                                }
                                builder.setOnCancelListener(new OnCancelListener() {
                                    @Override
                                    public void onCancel() {
//                                        showToast("取消");
                                    }
                                });
                                builder.excuteMission(context);
                            } else {
                                //TODO 未许可
//                                            getvDelegate().toastShort("权限未获取");
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
                    }
                });

    }

    private UIData crateUIData(final CheckVersionResults results) {

        UIData uiData = UIData.create();
        uiData.setTitle("版本更新");
        uiData.setDownloadUrl(results.getData().getLinkUrl());
        String centent = results.getData().getUpdateDesc();
        if (!AppTools.isEmpty(centent)) {
            centent = centent.replace("\\n", "\n");
        }
        uiData.setContent(centent);
        return uiData;

    }

    private CustomVersionDialogListener createCustomDialogTwo(final CheckVersionResults results) {
        return new CustomVersionDialogListener() {
            @Override
            public Dialog getCustomVersionDialog(Context context, UIData versionBundle) {
                NotificatonUpPopup notificatonPopup = new NotificatonUpPopup(context, R.layout.pop_notification_up);
                notificatonPopup.showDialog();
                TextView popNotificationAppVersionTv = notificatonPopup.findViewById(R.id.pop_notification_AppVersion_tv);
                TextView popNotificationContextTv = notificatonPopup.findViewById(R.id.pop_notification_context_tv);
                popNotificationAppVersionTv.setText("v"+results.getData().getAppVersion());
                popNotificationContextTv.setText(versionBundle.getContent());
                if ("1".equals(results.getData().getUpdateFlag())) {
                    notificatonPopup.setCanceledOnTouchOutside(false);
                    notificatonPopup.setCancelable(false);
                }else {
                    notificatonPopup.setCanceledOnTouchOutside(true);
                    notificatonPopup.setCancelable(true);
                }
                return notificatonPopup;
            }
        };
    }


    /**
     * 检查更新
     *
     * @param
     */
    private void checkUpdate(boolean show) {
        if (show) {
            getvDelegate().showLoading("检查更新中....");
        }
        getP().checkUpdate(show);

    }

}
