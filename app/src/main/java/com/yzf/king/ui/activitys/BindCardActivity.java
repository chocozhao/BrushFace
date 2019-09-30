package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xw.repo.XEditText;
import com.yzf.king.R;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetCardInfoResult;
import com.yzf.king.present.PBindCard;
import com.yzf.king.widget.StateButton;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.event.IEvent;
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
 * ClassName：BindCardActivity
 * Description: 绑卡页面
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/4/1 14:29
 * Modified By：
 * Fixtime：2017/4/1 14:29
 * FixDescription：
 */
public class BindCardActivity extends XActivity<PBindCard> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bindcard_cardno_tv)
    XEditText bindcardCardnoTv;
    @BindView(R.id.bindcard_cvn2_tv)
    XEditText bindcardCvn2Tv;
    @BindView(R.id.bindcard_expdate_tv)
    XEditText bindcardExpdateTv;
    @BindView(R.id.bindcard_bankname_tv)
    XEditText bindcardBanknameTv;
    @BindView(R.id.bindcard_statementday_tv)
    XEditText bindcardStatementdayTv;
    @BindView(R.id.bindcard_repaymentday_tv)
    XEditText bindcardRepaymentdayTv;
    @BindView(R.id.bindcard_phoneno_tv)
    XEditText bindcardPhonenoTv;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.bindcard_confirm_bt)
    StateButton bindcardConfirmBt;
    @BindView(R.id.bindcard_code_tv)
    XEditText bindcardCodeTv;
    @BindView(R.id.bindcard_code_ll)
    LinearLayout bindcardCodeLl;
    String ORIG_ORDER_ID;
    @BindView(R.id.bindcard_getcode_bt)
    StateButton bindcardGetcodeBt;

    GetCardInfoResult.DataBean cardBean = new GetCardInfoResult.DataBean();

    @Override
    public void initData(Bundle savedInstanceState) {
        cardBean = (GetCardInfoResult.DataBean) getIntent().getSerializableExtra("dataBean");
        useEventBus(true);
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_card;
    }

    @Override
    public PBindCard newP() {
        return new PBindCard();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        if (cardBean != null && cardBean.getBillDate() != null && cardBean.getRepaymentDate() != null) {
            bindcardStatementdayTv.setTextEx(cardBean.getBillDate());
            bindcardRepaymentdayTv.setTextEx(cardBean.getRepaymentDate());
        }
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
        title.setText("添加信用卡");
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

    public void finishActivity(String msg) {
        showToast(msg);
        finish();
    }

    @Override
    public void bindEvent() {
        BusProvider.getBus().toObservable(IEvent.class)
                .subscribe(new Action1<IEvent>() {
                    @Override
                    public void call(IEvent iEvent) {
                        //TODO 事件处理
                        if (iEvent.getId().equals("show_code")) {
                            ORIG_ORDER_ID = (String) iEvent.getObject();
                        }
                    }
                });
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


    @OnClick({R.id.bindcard_getcode_bt, R.id.bindcard_confirm_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bindcard_getcode_bt:
                getvDelegate().showLoading();
                GetCardInfoResult.DataBean dataBean = new GetCardInfoResult.DataBean();
                dataBean.setCardId(bindcardCardnoTv.getTextEx());
                dataBean.setCvv(bindcardCvn2Tv.getTextEx());
                dataBean.setValidDate(bindcardExpdateTv.getTextEx());
                dataBean.setPhone(bindcardPhonenoTv.getTextEx());
                dataBean.setBillDate(bindcardStatementdayTv.getTextEx());
                dataBean.setRepaymentDate(bindcardRepaymentdayTv.getTextEx());
                getP().BindCard(dataBean);
                break;
            case R.id.bindcard_confirm_bt:
                getvDelegate().showLoading();
                getP().BindCardConfirm(AppUser.getInstance().getMerchId(), bindcardCardnoTv.getTextEx(), bindcardCodeTv.getTextEx(), ORIG_ORDER_ID);
                break;
        }
    }

    public void startTimer() {
        getvDelegate().dismissLoading();
        bindcardGetcodeBt.setEnabled(false);
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
            bindcardGetcodeBt.setText(m / 1000 + "s重新获取");
            if (m / 1000 == 0) {
                bindcardGetcodeBt.setEnabled(true);
                mTime.cancel();
                bindcardGetcodeBt.setText("验证码");
            }
        }

    };

    public void setOriOrderId(String order_id) {
        ORIG_ORDER_ID = order_id;
    }
}
