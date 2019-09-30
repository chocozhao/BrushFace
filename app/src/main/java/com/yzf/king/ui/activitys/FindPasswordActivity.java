package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.xw.repo.XEditText;
import com.yzf.king.R;
import com.yzf.king.kit.AppUser;
import com.yzf.king.present.PFindPassword;
import com.yzf.king.widget.StateButton;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
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
 * ClassName：FindPasswordActivity
 * Description: 找回密码界面
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/3/21 14:33
 * Modified By：
 * Fixtime：2017/3/21 14:33
 * FixDescription：
 */
public class FindPasswordActivity extends XActivity<PFindPassword> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.findpwd_phone_et)
    XEditText findpwdPhoneEt;
    @BindView(R.id.findpwd_code_bt)
    StateButton findpwdCodeBt;
    @BindView(R.id.findpwd_code_et)
    XEditText findpwdCodeEt;
    @BindView(R.id.findpwd_pwd_et)
    XEditText findpwdPwdEt;
    @BindView(R.id.findpwd_confirm_bt)
    StateButton findpwdConfirmBt;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_find_password;
    }

    @Override
    public PFindPassword newP() {
        return new PFindPassword();
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
        title.setText("找回密码");
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

    @OnClick({R.id.findpwd_code_bt, R.id.findpwd_confirm_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.findpwd_code_bt:
                getP().getSmsCode(findpwdPhoneEt.getTextEx(), "2", AppUser.getInstance().getMerchId(), "1");
                break;
            case R.id.findpwd_confirm_bt:
                getvDelegate().showLoading();
                getP().findpassword(findpwdPhoneEt.getTextEx(), findpwdPwdEt.getTextEx(), findpwdCodeEt.getTextEx());
                break;
        }
    }

    public void startTimer() {
        getvDelegate().dismissLoading();
        findpwdCodeBt.setEnabled(false);
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
        findpwdCodeBt.setEnabled(true);
        mTime.cancel();
        findpwdCodeBt.setText("验证码");
    }

    /**
     * 定时器
     */
    private Timer mTime;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            int m = (Integer) msg.obj;
            findpwdCodeBt.setText(m / 1000 + "s重新获取");
            if (m / 1000 == 0) {
                findpwdCodeBt.setEnabled(true);
                mTime.cancel();
                findpwdCodeBt.setText("验证码");
            }
        }

    };
}
