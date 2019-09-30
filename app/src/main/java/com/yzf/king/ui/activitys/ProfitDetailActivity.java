package com.yzf.king.ui.activitys;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetProfitDtlResult;
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
 * ClassName：profitdetailActivity
 * Description: 订单详情页面
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/5/18 11:50
 * Modified By：
 * Fixtime：2017/5/18 11:50
 * FixDescription：
 */
public class ProfitDetailActivity extends XActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.profitdetail_type_tv)
    TextView profitdetailTypeTv;
    @BindView(R.id.profitdetail_state_tv)
    TextView profitdetailStateTv;
    @BindView(R.id.profitdetail_merchname_tv)
    TextView profitdetailMerchNameTv;
    @BindView(R.id.profitdetail_time_tv)
    TextView profitdetailTimeTv;
    @BindView(R.id.profitdetail_merchid_tv)
    TextView profitdetailMerchidTv;
    @BindView(R.id.profitdetail_orderid_tv)
    TextView profitdetailOrderidTv;
    @BindView(R.id.profitdetail_amt_tv)
    TextView profitdetailAmtTv;
    @BindView(R.id.profitdetail_type_iv)
    ImageView profitdetailTypeIv;
    @BindView(R.id.profitdetail_orderamt_tv)
    TextView profitdetailOrderamtTv;
    @BindView(R.id.profitdetail_result_tv)
    TextView profitdetailResultTv;

    private GetProfitDtlResult.DataBean.DataDtlBean dataBean;


    @Override
    public Object newP() {
        return null;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        dataBean = (GetProfitDtlResult.DataBean.DataDtlBean) getIntent().getSerializableExtra("dataBean");
        setOrderDetail();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_profit_detail;
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
        title.setText("分润详情");
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
//        String orderId = dataBean.getOrderId();
        String payType = dataBean.getBusinessName();
        String orderState = "已到账";
       /* String respCode = dataBean.getRespCode();
        String fee = dataBean.getMerchFee();*/
        String tradeTime = Kits.Date.getYmdhms(dataBean.getInsertTime());
        String tradeMoney = dataBean.getProfitAmt();
        profitdetailMerchidTv.setText(AppTools.formatPhone(merchantNo));
//        profitdetailOrderidTv.setText(orderId);
        profitdetailTypeTv.setText(payType);
        /*if ("0000".equals(respCode)) {
            orderState = "交易成功";
        } else if ("0100".equals(respCode)) {
            orderState = "交易处理中";
            profitdetailStateTv.setTextColor(getResources().getColor(R.color.text_yellow));
        } else {
            orderState = "交易失败";
            profitdetailStateTv.setTextColor(getResources().getColor(R.color.text_red));
        }*/
        profitdetailMerchNameTv.setText(AppTools.formatName(dataBean.getMerchName()));
        profitdetailStateTv.setText(orderState);
        profitdetailTimeTv.setText(tradeTime);
        profitdetailAmtTv.setText("￥" + tradeMoney);

        profitdetailResultTv.setText(orderState);

        profitdetailOrderamtTv.setText("￥" + tradeMoney);

        switch (dataBean.getBusinessId()) {
            case "01":
                profitdetailTypeIv.setImageResource(R.mipmap.trans_icon);//提现
                break;
            case "02":
                profitdetailTypeIv.setImageResource(R.mipmap.trans_icon);//提现
                break;
            case "03":
                profitdetailTypeIv.setImageResource(R.mipmap.trans_mpos);//MPOS收益
                break;
            case "04":
                profitdetailTypeIv.setImageResource(R.mipmap.trans_wx);//微信
                break;
            case "06":
                profitdetailTypeIv.setImageResource(R.mipmap.trans_zfb);//支付宝
                break;
            default:
                profitdetailTypeIv.setImageResource(R.mipmap.trans_icon);
                break;
        }
    }


}
