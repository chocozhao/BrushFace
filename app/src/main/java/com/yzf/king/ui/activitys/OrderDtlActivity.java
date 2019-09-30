package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetTransDevicesResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

public class OrderDtlActivity extends XActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.order_dtl_recriver_tv)
    TextView orderDtlRecriverTv;
    @BindView(R.id.order_dtl_phone_tv)
    TextView orderDtlPhoneTv;
    @BindView(R.id.order_dtl_address_tv)
    TextView orderDtlAddressTv;
    @BindView(R.id.order_dtl_hear_tv)
    ImageView orderDtlHearTv;
    @BindView(R.id.order_dtl_title_tv)
    TextView orderDtlTitleTv;
    @BindView(R.id.order_dtl_status_tv)
    TextView orderDtlStatusTv;
    @BindView(R.id.order_dtl_ordernumber_tv)
    TextView orderDtlOrdernumberTv;
    @BindView(R.id.order_dtl_number_tv)
    TextView orderDtlNumberTv;
    @BindView(R.id.order_dtl_freight_tv)
    TextView orderDtlFreightTv;
    @BindView(R.id.order_dtl_remark_tv)
    TextView orderDtlRemarkTv;

    GetTransDevicesResult.DataBean dataBean;

    @Override
    public void initData(Bundle savedInstanceState) {
        dataBean = (GetTransDevicesResult.DataBean) getIntent().getSerializableExtra("dataBean");
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_dtl;
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
        if (dataBean != null) {
            orderDtlRecriverTv.setText("收货人：" + dataBean.getName());
            orderDtlPhoneTv.setText(dataBean.getPhone());
            orderDtlAddressTv.setText("收货地址：" + dataBean.getAddress());
            orderDtlTitleTv.setText(dataBean.getGoodsName());
            orderDtlRemarkTv.setText(dataBean.getRemark());
            ILFactory.getLoader().loadImage(dataBean.getLogoUrl(), orderDtlHearTv);
            if (dataBean.getGiveNum() == 0) {
                orderDtlNumberTv.setText("数量：X" + dataBean.getOrderNum());
            } else {
                orderDtlNumberTv.setText("数量：X" + dataBean.getOrderNum() + "送" + dataBean.getGiveNum());
            }
            orderDtlFreightTv.setText("￥" + AppTools.formatF2Y(dataBean.getOrderAmt()));
            switch (dataBean.getStatus()) {
                case 0:
                    orderDtlStatusTv.setText("待付款");
                    break;
                case 1:
                    orderDtlStatusTv.setText("已付款");
                    break;
                default:
            }
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
        title.setText("订单详情");
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
