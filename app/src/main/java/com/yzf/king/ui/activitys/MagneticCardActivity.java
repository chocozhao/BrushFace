package com.yzf.king.ui.activitys;

/**
 * ClaseName：MagneticCardActivity
 * Description： 磁条卡确认
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/4/29 14:00
 * Modified By：
 * Fixtime：2019/4/29 14:00
 * FixDescription：
 **/

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
import com.yzf.king.model.servicesmodels.GetCardInfoResult;
import com.yzf.king.present.PMagneticCard;
import com.yzf.king.widget.StateButton;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

public class MagneticCardActivity extends XActivity<PMagneticCard> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.magnetic_cardno_tv)
    XEditText magneticCardnoTv;
    @BindView(R.id.magneticcard_nameno_tv)
    XEditText magneticcardNameTv;
    @BindView(R.id.magneticcard_expdate_tv)
    XEditText magneticcardExpdateTv;
    @BindView(R.id.magneticcard_phoneno_tv)
    XEditText magneticcardPhonenoTv;
    @BindView(R.id.magneticcard_code_tv)
    XEditText magneticcardCodeTv;
    @BindView(R.id.magneticcard_getcode_bt)
    StateButton magneticcardGetcodeBt;
    @BindView(R.id.magneticcard_confirm_bt)
    StateButton magneticcardConfirmBt;

    String ORIG_ORDER_ID;
    String cardId;

    @Override
    public void initData(Bundle savedInstanceState) {
        cardId = getIntent().getStringExtra("cardId");
        initView();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_magnetic_card;
    }

    @Override
    public PMagneticCard newP() {
        return new PMagneticCard();
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
        title.setText("磁条卡确认");
        magneticCardnoTv.setTextEx(cardId);
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


    @OnClick({R.id.magneticcard_getcode_bt, R.id.magneticcard_confirm_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.magneticcard_getcode_bt:
                getvDelegate().showLoading();
                GetCardInfoResult.DataBean dataBean = new GetCardInfoResult.DataBean();
                dataBean.setCardId(magneticCardnoTv.getTextEx());
                dataBean.setName(magneticcardNameTv.getTextEx());
                dataBean.setIdNo(magneticcardExpdateTv.getTextEx());
                dataBean.setPhone(magneticcardPhonenoTv.getTextEx());
                getP().MagneticCard(dataBean);
                break;
            case R.id.magneticcard_confirm_bt:
                getvDelegate().showLoading();
                getP().MagneticCardConfirm(AppUser.getInstance().getMerchId(),magneticCardnoTv.getTextEx(),magneticcardNameTv.getTextEx(),
                        magneticcardExpdateTv.getTextEx(),magneticcardPhonenoTv.getTextEx(),magneticcardCodeTv.getTextEx(), ORIG_ORDER_ID);
                break;
        }
    }

    public void startTimer() {
        getvDelegate().dismissLoading();
        magneticcardGetcodeBt.setEnabled(false);
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
            magneticcardGetcodeBt.setText(m / 1000 + "s重新获取");
            if (m / 1000 == 0) {
                magneticcardGetcodeBt.setEnabled(true);
                mTime.cancel();
                magneticcardGetcodeBt.setText("验证码");
            }
        }

    };

//    public void toMagneticResul(GetCardInfoResult.DataBean data) {
//        getvDelegate().dismissLoading();
//        Router.newIntent(context)
//                .to(PosResultActivity.class)
//                .putSerializable("dataBean", data)
//                .putSerializable("cardId", cardId)
//                .launch();
//        Router.pop(context);
//    }
    public void finishActivity(String msg) {
        showToast(msg);
        finish();
    }

    public void setOriOrderId(String order_id) {
        ORIG_ORDER_ID = order_id;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
