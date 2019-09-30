package com.yzf.king.ui.activitys;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetTranDtlResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.kit.Kits;
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
 * ClassName：TransDetailActivity
 * Description: 订单详情页面
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/5/18 11:50
 * Modified By：
 * Fixtime：2017/5/18 11:50
 * FixDescription：
 */
public class TransDetailActivity extends XActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.transdetail_type_tv)
    TextView transdetailTypeTv;
    @BindView(R.id.transdetail_state_tv)
    TextView transdetailStateTv;
    @BindView(R.id.transdetail_fee_tv)
    TextView transdetailFeeTv;

    @BindView(R.id.transdetail_time_tv)
    TextView transdetailTimeTv;
    @BindView(R.id.transdetail_merchid_tv)
    TextView transdetailMerchidTv;
    @BindView(R.id.transdetail_orderid_tv)
    TextView transdetailOrderidTv;
    @BindView(R.id.transdetail_amt_tv)
    TextView transdetailAmtTv;
    @BindView(R.id.transdetail_type_iv)
    ImageView transdetailTypeIv;
    @BindView(R.id.transdetail_orderamt_tv)
    TextView transdetailOrderamtTv;
    @BindView(R.id.transdetail_result_tv)
    TextView transdetailResultTv;
    @BindView(R.id.transdetail_remark_tv)
    TextView transdetailRemarkTv;
    @BindView(R.id.transdetail_remark_ll)
    LinearLayout transdetailRemarkLl;
    private GetTranDtlResult.DataBean dataBean;


    @Override
    public Object newP() {
        return null;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        dataBean = (GetTranDtlResult.DataBean) getIntent().getSerializableExtra("dataBean");
        setOrderDetail();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_trans_detail;
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
        title.setText("交易详情");
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

    private void setOrderDetail() {
        String merchantNo = dataBean.getMerchId();
        String orderId = dataBean.getOrderId();
//        String payType = dataBean.getServicesName();
        String orderState = dataBean.getRespDesc();
        String respCode = dataBean.getRespCode();
//        String fee = dataBean.getMerchFee();
        String tradeTime = Kits.Date.getYmdhms(dataBean.getInsertTime());
//        String tradeMoney = dataBean.getTransAmt();
        transdetailMerchidTv.setText(merchantNo);
        transdetailOrderidTv.setText(orderId);
//        transdetailTypeTv.setText(payType);
        if ("0000".equals(respCode)) {
            orderState = "交易成功";
        } else if ("0100".equals(respCode)) {
            orderState = "交易处理中";
            transdetailStateTv.setTextColor(getResources().getColor(R.color.text_yellow));
        } else {
            orderState = "交易失败";
            transdetailStateTv.setTextColor(getResources().getColor(R.color.text_red));
        }

        transdetailStateTv.setText(orderState);
//        transdetailFeeTv.setText("￥" +fee);
        transdetailTimeTv.setText(tradeTime);
//        transdetailAmtTv.setText("￥" + tradeMoney);

        transdetailResultTv.setText(orderState);
        if (!"0000".equals(dataBean.getRespCode())) {
            transdetailRemarkLl.setVisibility(View.VISIBLE);
            transdetailRemarkTv.setText(dataBean.getRespDesc());
        } else {
            transdetailRemarkLl.setVisibility(View.GONE);
        }
//        transdetailOrderamtTv.setText("￥" + tradeMoney);

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



}
