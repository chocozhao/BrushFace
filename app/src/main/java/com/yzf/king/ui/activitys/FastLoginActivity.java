package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.xw.repo.XEditText;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.present.PFastLogin;
import com.yzf.king.widget.StateButton;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

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
 * ClassName：FastLoginActivity
 * Description:
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2018/8/27 9:57
 * Modified By：
 * Fixtime：2018/8/27 9:57
 * FixDescription：
 */
public class FastLoginActivity extends XActivity<PFastLogin> {
    @BindView(R.id.fastlogin_regiest_tv)
    TextView fastloginRegiestTv;
    @BindView(R.id.fastlogin_phone_et)
    XEditText fastloginPhoneEt;
    @BindView(R.id.fastlogin_code_et)
    XEditText fastloginCodeEt;
    @BindView(R.id.fastlogin_code_tv)
    TextView fastloginCodeTv;
    @BindView(R.id.fastlogin_bt)
    StateButton fastloginBt;
    @BindView(R.id.fastlogin_pwd_tv)
    TextView fastloginPwdTv;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_fast_login;
    }

    @Override
    public PFastLogin newP() {
        return new PFastLogin();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        fastloginPhoneEt.setText(SharedPref.getInstance(this.context).getString("phone", ""));
    }

    /**
     * 初始化Toolbar
     */
    private void initToolbar() {
        /*setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title.setText("标题");*/
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

    @OnClick({R.id.fastlogin_code_tv, R.id.fastlogin_bt, R.id.fastlogin_regiest_tv, R.id.fastlogin_pwd_tv})
    public void onViewClicked(View view) {
        String phone = fastloginPhoneEt.getTextEx();
        switch (view.getId()) {
            case R.id.fastlogin_code_tv:
                if (AppTools.isEmpty(phone)) {
                    showToast(getString(R.string.input_phone));
                    return;
                }
                if (!AppTools.isMobile(phone)) {
                    showToast(getString(R.string.phone_incorrect));
                    return;
                }
                getvDelegate().showLoading();
                getP().getCode(phone, "0", AppUser.getInstance().getMerchId(), "1");
                break;
            case R.id.fastlogin_bt:
                String code = this.fastloginCodeEt.getTextEx();
                if (AppTools.isEmpty(phone)) {
                    showToast(getString(R.string.input_phone));
                    return;
                }
                if (!AppTools.isMobile(phone)) {
                    showToast(getString(R.string.phone_incorrect));
                    return;
                }
                if (AppTools.isEmpty(code)) {
                    showToast(getString(R.string.input_code));
                    return;
                }
                getvDelegate().showLoading();
                getP().login(phone, null, code, "1", null, null, null);
                break;
            case R.id.fastlogin_regiest_tv:
//                JumpActivity(RegisterActivity.class, false);
                break;
            case R.id.fastlogin_pwd_tv:
                JumpActivity(LoginActivity.class, true);
                break;
            default:break;
        }
    }


    public void startTimer() {
        getvDelegate().dismissLoading();
        fastloginCodeTv.setEnabled(false);
        mTime = new Timer();
        mTime.schedule(new TimerTask() {
            int time = 60000;

            @Override
            public void run() {
                time -= 1000;
                mHandler.sendMessage(mHandler.obtainMessage(1, time));
            }
        }, 1000, 1000);
    }

    public void cancleTimer() {
        fastloginCodeTv.setEnabled(true);
        mTime.cancel();
        fastloginCodeTv.setText(R.string.get_code);
    }

    /**
     * 定时器
     */
    private Timer mTime;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            int m = (Integer) msg.obj;
            fastloginCodeTv.setText(m / 1000 + "s重新获取");
            fastloginCodeTv.setTextColor(getResources().getColor(R.color.text_tip));
            if (m / 1000 == 0) {
                fastloginCodeTv.setEnabled(true);
                mTime.cancel();
                fastloginCodeTv.setText(R.string.get_code);
                fastloginCodeTv.setTextColor(getResources().getColor(R.color.text_theme));
            }
        }

    };


}
