package com.yzf.king.ui.activitys;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.gyf.barlibrary.ImmersionBar;
import com.xw.repo.XEditText;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.utils.AesUtil;
import com.yzf.king.kit.utils.PermissionPageUtils;
import com.yzf.king.present.PLogin;
import com.yzf.king.widget.StateButton;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.mvp.ToastType;
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
 * ClassName：LoginActivity
 * Description:登陆页面
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2018/8/24 14:58
 * Modified By：
 * Fixtime：2018/8/24 14:58
 * FixDescription：
 */
public class LoginActivity extends XActivity<PLogin> {
    @BindView(R.id.login_phone_et)
    XEditText loginPhoneEt;
    @BindView(R.id.login_pwd_et)
    XEditText loginPwdEt;
    @BindView(R.id.login_forget_tv)
    TextView loginForgetTv;
    @BindView(R.id.login_bt)
    StateButton loginBt;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData(Bundle bundle) {
        initView();
        getRxPermissions()//获取内、外部存储权限
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) {
                            //TODO 许可
                        } else {
                            //TODO 未许可
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

    public PLogin newP() {
        return new PLogin();
    }

    private void initView() {
        String phone = SharedPref.getInstance(this.context).getString("phone", "");
        String password = SharedPref.getInstance(this.context).getString("password", "");
        String decrypt = null;
        if (!AppTools.isEmpty(password)) {
            decrypt = AesUtil.decrypt(password);
        }
        this.loginPhoneEt.setText(phone);
        this.loginPwdEt.setText(decrypt);
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

    public void showToast(String str) {
        getvDelegate().toastShort(str);
    }

    public void showToast(String str, ToastType type) {
        getvDelegate().toast(str, type);
    }

    public void showErrorDialog(String str) {
        getvDelegate().showErrorDialog(str);
    }

    public void showError(NetError netError) {
        getvDelegate().showError(netError);
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


    @OnClick({R.id.login_bt, R.id.login_forget_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_forget_tv:
                JumpActivity(FindPasswordActivity.class, false);
                break;
            case R.id.login_bt:
                String phone = loginPhoneEt.getTextEx();
                String pwd = loginPwdEt.getTextEx();
                if (AppTools.isEmpty(phone)) {
                    showToast(getString(R.string.input_phone));
                }
                if (!AppTools.isMobile(phone)) {
                    showToast(getString(R.string.phone_incorrect), ToastType.ERROR);
                }
                if (AppTools.isEmpty(pwd)) {
                    showToast(getString(R.string.input_pwd), ToastType.ERROR);
                }
                getvDelegate().showLoading();
                getP().login(phone, pwd, null, "0", null, null, null);
                break;
                default:
        }
    }

}