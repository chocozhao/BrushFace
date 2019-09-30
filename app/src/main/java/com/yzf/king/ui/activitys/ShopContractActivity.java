package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.yzf.king.R;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetLoopShopResult;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoResults;
import com.yzf.king.model.servicesmodels.GetTeamTermInfoResult;
import com.yzf.king.present.PShopContract;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

public class ShopContractActivity extends XActivity<PShopContract> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.shop_contract_cb)
    CheckBox shopContractCb;
    @BindView(R.id.shop_contract_bt)
    Button shopContractBt;
    GetMachinApplyInfoResults.DataBean dataBean;
    @Override
    public void initData(Bundle savedInstanceState) {
        dataBean = (GetMachinApplyInfoResults.DataBean) getIntent().getSerializableExtra("dataBean");
        initView();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_contract;
    }

    @Override
    public PShopContract newP() {
        return new PShopContract();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();

        shopContractCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    shopContractBt.setEnabled(true);
                    shopContractBt.setBackground(getResources().getDrawable(R.drawable.proxy_btn_bg));
                } else {
                    shopContractBt.setEnabled(false);
                    shopContractBt.setBackground(getResources().getDrawable(R.drawable.proxy_btn_blue_height));
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
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left_black_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title.setText("铺货合约");
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (shopContractCb.isChecked()) {
            shopContractBt.setEnabled(true);
            shopContractBt.setBackground(getResources().getDrawable(R.drawable.proxy_btn_bg));
        }else {
            shopContractBt.setEnabled(false);
            shopContractBt.setBackground(getResources().getDrawable(R.drawable.proxy_btn_blue_height));
        }
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




    @OnClick({R.id.shop_contract_cb, R.id.shop_contract_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shop_contract_cb:
                if (!shopContractCb.isChecked()) {
                    showToast("请先阅读并同意协议");
                    return;
                }
                break;
            case R.id.shop_contract_bt:
                getvDelegate().showNoticeDialog("抢单后，您的装机订单要在2个小时内完成铺货确认，否则您抢到的装机订单将将会自动失效", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            getP().LootShop(AppUser.getInstance().getMerchId(), dataBean.getOrderId());
                            AppUser.getInstance().setDeviceNum("");
                            Router.newIntent(context)
                                    .to(ShopDtlActivity.class)
                                    .putSerializable("dataBean",dataBean)
                                    .launch();
                        }
                    }
                });
                break;
            default:
        }
    }

    public void setAdapter(GetLoopShopResult.DataBean dataBean) {
        if (dataBean != null) {
//            data.setOrderId(dataBean.getOrderId());
        }

    }

}
