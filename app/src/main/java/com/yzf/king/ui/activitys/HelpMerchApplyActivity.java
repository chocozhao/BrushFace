package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.xw.repo.XEditText;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetUrlResult;
import com.yzf.king.model.servicesmodels.RegisterResult;
import com.yzf.king.present.PHelpMerchApply;
import com.yzf.king.widget.StateButton;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

public class HelpMerchApplyActivity extends XActivity<PHelpMerchApply> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.help_merch_apply_name_et)
    XEditText helpMerchApplyNameEt;
    @BindView(R.id.help_merch_apply_phone_et)
    XEditText helpMerchApplyPhoneEt;
    @BindView(R.id.help_merch_apply_pwd_et)
    XEditText helpMerchApplyPwdEt;
    @BindView(R.id.help_merch_apply_getcode_et)
    XEditText helpMerchApplyGetcodeEt;
    @BindView(R.id.help_merch_apply_getcode_bt)
    StateButton helpMerchApplyGetcodeBt;
    @BindView(R.id.help_merch_apply_cb)
    CheckBox helpMerchApplyCb;
    @BindView(R.id.help_merch_apply_agreement_tv)
    TextView helpMerchApplyAgreementTv;
    @BindView(R.id.help_merch_apply_zfb_bt)
    StateButton helpMerchApplyZfbBt;
    @BindView(R.id.help_merch_apply_wx_bt)
    StateButton helpMerchApplyWxBt;

    RegisterResult.DataBean dataBean = new RegisterResult.DataBean();
    @Override
    public void initData(Bundle savedInstanceState) {
        initView();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_help_merch_apply;
    }

    @Override
    public PHelpMerchApply newP() {
        return new PHelpMerchApply();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        helpMerchApplyZfbBt.setUnableBackgroundColor(getResources().getColor(R.color.text_tip));
        helpMerchApplyWxBt.setUnableBackgroundColor(getResources().getColor(R.color.text_tip));
        helpMerchApplyCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    helpMerchApplyZfbBt.setEnabled(true);
                    helpMerchApplyWxBt.setEnabled(true);
                } else {
                    helpMerchApplyZfbBt.setEnabled(false);
                    helpMerchApplyWxBt.setEnabled(false);

                }
            }
        });
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
        title.setText("帮助商家申请");
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
     * 获取接口数据
     * @param data
     */
    public void setInfo(RegisterResult.DataBean data) {
        if (data != null) {
            dataBean.setMerchId(data.getMerchId());

        }

    }

    @OnClick({R.id.help_merch_apply_zfb_bt, R.id.help_merch_apply_wx_bt, R.id.help_merch_apply_agreement_tv,
    R.id.help_merch_apply_getcode_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.help_merch_apply_zfb_bt:
                if (!helpMerchApplyCb.isChecked()) {
                    showToast("请先阅读并同意协议");
                    return;
                }
                if (AppTools.isEmpty(helpMerchApplyPhoneEt.getTextEx())) {
                    showToast("商家手机号为空,请重新输入");
                    return;
                }
                if (AppTools.isEmpty(helpMerchApplyPwdEt.getTextEx())) {
                    showToast("密码为空,请重新输入");
                    return;
                }
                if (AppTools.isEmpty(helpMerchApplyGetcodeEt.getTextEx())) {
                    showToast("验证码为空,请重新输入");
                    return;
                }
                getP().regist(helpMerchApplyPhoneEt.getTextEx(),helpMerchApplyPwdEt.getTextEx(),helpMerchApplyGetcodeEt.getTextEx(),
                        AppUser.getInstance().getPhone(),"0","1");
                break;
            case R.id.help_merch_apply_wx_bt:
                if (!helpMerchApplyCb.isChecked()) {
                    showToast("请先阅读并同意协议");
                    return;
                }
                if (AppTools.isEmpty(helpMerchApplyPhoneEt.getTextEx())) {
                    showToast("商家手机号为空,请重新输入");
                    return;
                }
                if (AppTools.isEmpty(helpMerchApplyPwdEt.getTextEx())) {
                    showToast("密码为空,请重新输入");
                    return;
                }
                if (AppTools.isEmpty(helpMerchApplyGetcodeEt.getTextEx())) {
                    showToast("验证码为空,请重新输入");
                    return;
                }
                getP().regist(helpMerchApplyPhoneEt.getTextEx(),helpMerchApplyPwdEt.getTextEx(),helpMerchApplyGetcodeEt.getTextEx(),
                        AppUser.getInstance().getPhone(),"0","2");
                break;
            case R.id.help_merch_apply_agreement_tv:
                List<GetUrlResult.DataBean> list2 = AppUser.getInstance().getUrlBean();
                String target2 = null;//http://h5.yiyoupay.net/link/html/directions.html?topBranchNo=20000029
                if (list2 != null && list2.size() > 0) {
                    for (GetUrlResult.DataBean dataBean : list2) {
                        if (dataBean.getType().equals("agencyUrl")) {
                            Logger.i(target2);
                            target2 = dataBean.getUrl();
                            break;
                        }
                    }
                }
                if (!AppTools.isEmpty(target2)) {
                    AgenWebViewActivity.launch(context, target2, null);
                } else {
                    showToast("暂未开放");
                }
                break;
            case R.id.help_merch_apply_getcode_bt:
                if (AppTools.isEmpty(helpMerchApplyPhoneEt.getTextEx())) {
                    showToast("手机号为空，请输入手机号");
                    return;
                }
                getP().getCode(helpMerchApplyPhoneEt.getTextEx(),"0",AppUser.getInstance().getMerchId(),"1");
                break;
            default:
                break;
        }
    }
    public void startTimer() {
        getvDelegate().dismissLoading();
        helpMerchApplyGetcodeBt.setEnabled(false);
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

    /**
     * 定时器
     */
    private Timer mTime;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            int m = (Integer) msg.obj;
            helpMerchApplyGetcodeBt.setText(m / 1000 + "s重新获取");
            if (m / 1000 == 0) {
                helpMerchApplyGetcodeBt.setEnabled(true);
                mTime.cancel();
                helpMerchApplyGetcodeBt.setText("验证码");
            }
        }

    };


    /**
     * 根据applyType跳转微信或者支付宝
     * @param applyType
     */
    public void toMerchApplyPermit(String applyType) {
        Router.newIntent(context)
                .to(MerchApplyPermitActivity.class)
                .putString("applyType",applyType)
                .launch();
    }
}
