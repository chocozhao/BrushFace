package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppConfig;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetMyShopInfoResult;
import com.yzf.king.model.servicesmodels.GetShopInfoDtlResult;
import com.yzf.king.model.servicesmodels.GetUrlResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

public class ShopManageDetailActivity extends XActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.shopmanagedetail_shopname_tv)
    TextView shopmanagedetailShopnameTv;
    @BindView(R.id.shopmanagedetail_shopId_tv)
    TextView shopmanagedetailShopIdTv;
    @BindView(R.id.shopmanagedetail_name_tv)
    TextView shopmanagedetailNameTv;
    @BindView(R.id.shopmanagedetail_phone_tv)
    TextView shopmanagedetailPhoneTv;
    @BindView(R.id.shopmanagedetail_status_tv)
    TextView shopmanagedetailStatusTv;
    @BindView(R.id.shopmanagedetail_date_tv)
    TextView shopmanagedetailDateTv;
    @BindView(R.id.shopmanagedetail_address_tv)
    TextView shopmanagedetailAddressTv;
    @BindView(R.id.shopmanagedetail_receipt_tv)
    TextView shopmanagedetailReceiptTv;
    @BindView(R.id.shopmanagedetail_receipt_ll)
    LinearLayout shopmanagedetailReceiptLl;
    @BindView(R.id.shopmanagedetail_mail_tv)
    TextView shopmanagedetailMailTv;
    @BindView(R.id.shopmanagedetail_mail_ll)
    LinearLayout shopmanagedetailMailLl;

    GetShopInfoDtlResult.DataBean shopDtlDataBean;
    GetMyShopInfoResult.DataBean.DataListBean myShopDataBean;
    String type;
    String myShopType;


    @Override
    public void initData(Bundle savedInstanceState) {
        shopDtlDataBean = (GetShopInfoDtlResult.DataBean) getIntent().getSerializableExtra("shopDtlDataBean");
        myShopDataBean = (GetMyShopInfoResult.DataBean.DataListBean) getIntent().getSerializableExtra("myShopDataBean");
        type = getIntent().getStringExtra("type");
        myShopType = getIntent().getStringExtra("myShopType");
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_manage_detail;
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

        if (shopDtlDataBean != null) {
            shopmanagedetailShopnameTv.setText(shopDtlDataBean.getShopName());
            shopmanagedetailShopIdTv.setText(shopDtlDataBean.getShopId());
            shopmanagedetailNameTv.setText(shopDtlDataBean.getName());
            shopmanagedetailPhoneTv.setText(shopDtlDataBean.getPhone());
            shopmanagedetailDateTv.setText(Kits.Date.getYmdhms(shopDtlDataBean.getInstrtTime()));
            if (!AppTools.isEmpty(shopDtlDataBean.getProvinceName()) || !AppTools.isEmpty(shopDtlDataBean.getCityName()) || !AppTools.isEmpty(shopDtlDataBean.getAddress())) {
                shopmanagedetailAddressTv.setText(shopDtlDataBean.getProvinceName() + shopDtlDataBean.getCityName() + shopDtlDataBean.getAddress());
            } else {
                shopmanagedetailAddressTv.setText("");
            }

            switch (shopDtlDataBean.getStatus()) {
                case 0:
                    shopmanagedetailStatusTv.setText("注册中");
                    break;
                case 1:
                    shopmanagedetailStatusTv.setText("待审核");
                    break;
                case 2:
                    shopmanagedetailStatusTv.setText("审核不通过");
                    break;
                case 3:
                    shopmanagedetailStatusTv.setText("审核通过");
                    break;
//                case 4:
//                    shopmanagedetailStatusTv.setText("已完成");
//                    break;
//                case 5:
//                    shopmanagedetailStatusTv.setText("审核失败");
//
//                    break;
//                case 6:
//                    shopmanagedetailStatusTv.setText("装机失败");
//                    break;
                default:
                    shopmanagedetailStatusTv.setText("无");
                    break;
            }
        }

        if (myShopDataBean != null) {
            if ("2".equals(type) || "3".equals(type)) {
                shopmanagedetailReceiptTv.setText(setAcctno(myShopDataBean.getAlipayId()));
                shopmanagedetailMailTv.setText(setAcctno(myShopDataBean.getMailId()));
            } else if ("1".equals(myShopType)) {
                shopmanagedetailReceiptTv.setText(myShopDataBean.getAlipayId());
                shopmanagedetailMailTv.setText(myShopDataBean.getMailId());
            } else {
                shopmanagedetailMailLl.setVisibility(View.GONE);
                shopmanagedetailReceiptLl.setVisibility(View.GONE);
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
        title.setText("店铺信息");
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


    private String setAcctno(String acctNo) {
        if (!AppTools.isEmpty(acctNo)) {
            acctNo = acctNo.substring(0, 3) + "****" + acctNo.substring(acctNo.length() - 4, acctNo.length());

        }
        return acctNo;
    }


}
