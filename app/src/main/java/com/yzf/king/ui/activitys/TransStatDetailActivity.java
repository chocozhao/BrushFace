package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetTransInfoCountDtlResult;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * ClaseName：TransStatDayFragment
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/13 20:05
 * Modified By：
 * Fixtime：2019/5/13 20:05
 * FixDescription：
 **/
public class TransStatDetailActivity extends XActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
//    @BindView(R.id.tram_detail_multiplestatusview)
//    MultipleStatusView multiplestatusview;
    @BindView(R.id.transdetail_type_iv)
    ImageView transdetailTypeIv;
    @BindView(R.id.transdetail_type_tv)
    TextView transdetailTypeTv;
    @BindView(R.id.transstatdetail_amt_tv)
    TextView transstatdetailAmtTv;
    @BindView(R.id.transstatdetail_state_tv)
    TextView transstatdetailStateTv;
    @BindView(R.id.transstatdetail_fee_tv)
    TextView transstatdetailFeeTv;
    @BindView(R.id.transstatdetail_merchid_tv)
    TextView transstatdetailMerchidTv;
    @BindView(R.id.transstatdetail_orderid_tv)
    TextView transstatdetailOrderidTv;
    @BindView(R.id.transstatdetail_time_tv)
    TextView transstatdetailTimeTv;
    @BindView(R.id.transstatdetail_orderamt_tv)
    TextView transstatdetailOrderamtTv;
    @BindView(R.id.transstatdetail_result_tv)
    TextView transstatdetailResultTv;
    @BindView(R.id.transstatdetail_remark_tv)
    TextView transstatdetailRemarkTv;
    @BindView(R.id.transstatdetail_remark_ll)
    LinearLayout transstatdetailRemarkLl;


    GetTransInfoCountDtlResult.DataBean dataBean;
    @Override
    public void initData(Bundle savedInstanceState) {
        dataBean = (GetTransInfoCountDtlResult.DataBean) getIntent().getSerializableExtra("dataBean");
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_trans_stat_detail;
    }

    @Override
    public Object newP() {
        return null;
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        setOrderDetail();
    }

    private void setOrderDetail() {
        String merchantNo = dataBean.getMerchId();
        String orderId = dataBean.getOrderId();
        String payType = dataBean.getServicesName();
        String orderState = dataBean.getRespDesc();
        String respCode = dataBean.getRespCode();
        String fee = dataBean.getMerchFee();
        String tradeTime = Kits.Date.getYmdhms(dataBean.getInsertTime());
        String tradeMoney = dataBean.getTransAmt();
        transstatdetailMerchidTv.setText(merchantNo);
        transstatdetailOrderidTv.setText(orderId);
        transdetailTypeTv.setText(payType);
        if ("0000".equals(respCode)) {
            orderState = "交易成功";
        } else if ("0100".equals(respCode)) {
            orderState = "交易处理中";
            transstatdetailStateTv.setTextColor(getResources().getColor(R.color.text_yellow));
        } else {
            orderState = "交易失败";
            transstatdetailStateTv.setTextColor(getResources().getColor(R.color.text_red));
        }

        transstatdetailStateTv.setText(orderState);
        transstatdetailFeeTv.setText("￥" +fee);
        transstatdetailTimeTv.setText(tradeTime);
        transstatdetailAmtTv.setText("￥" + tradeMoney);

        transstatdetailResultTv.setText(orderState);
        if (!"0000".equals(dataBean.getRespCode())) {
            transstatdetailRemarkLl.setVisibility(View.VISIBLE);
            transstatdetailRemarkTv.setText(dataBean.getRespDesc());
        } else {
            transstatdetailRemarkLl.setVisibility(View.GONE);
        }
        transstatdetailOrderamtTv.setText("￥" + tradeMoney);

        switch (dataBean.getTransType()) {
            case "01":
            case "02":
                transdetailTypeIv.setImageResource(R.mipmap.trans_icon);//提现
                break;
            case "04":
                transdetailTypeIv.setImageResource(R.mipmap.trans_zfb);//支付宝
                break;
            case "05":
                transdetailTypeIv.setImageResource(R.mipmap.trans_wx);//微信
                break;
            case "43":
            case "46":
                transdetailTypeIv.setImageResource(R.mipmap.trans_upgrade);//升级
                break;
            case "61":
            case "63":
            case "70":
            case "72":
            case "90":
                transdetailTypeIv.setImageResource(R.mipmap.trans_consume);//消费
                break;
            case "71":
            case "73":
            case "91":
            case "97":
                transdetailTypeIv.setImageResource(R.mipmap.trans_repayment);//还款
                break;
            case "78":
            case "80":
                transdetailTypeIv.setImageResource(R.mipmap.trans_tx);//套现
                break;
            default:
                transdetailTypeIv.setImageResource(R.mipmap.trans_icon);
                break;
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
        title.setText("交易详情");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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


}
