package com.yzf.king.ui.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xw.repo.XEditText;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.present.PMerchApplySupplier;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

public class MerchApplySupplierActivity extends XActivity<PMerchApplySupplier> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.merch_apply_supplier_yes_cb)
    CheckBox merchApplySupplierYesCb;
    @BindView(R.id.merch_apply_supplier_account_tv)
    TextView merchApplySupplierAccountTv;
    @BindView(R.id.merch_apply_supplier_no_cb)
    CheckBox merchApplySupplierNoCb;
    @BindView(R.id.merch_apply_supplier_define_bt)
    Button merchApplySupplierDefineBt;
    @BindView(R.id.merch_apply_supplier_account_rl)
    RelativeLayout merchApplySupplierAccountRl;

    // 每页10行
    private int pageSize = 10;
    protected static final int MAX_PAGE = 30;
    String beginTime;
    String endTime;
    private String merchId;
    private String applyType;
    private String shopId;

    @Override
    public void initData(Bundle savedInstanceState) {
        applyType = getIntent().getStringExtra("applyType");
        shopId = getIntent().getStringExtra("shopId");
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_merch_apply_supplier;
    }

    @Override
    public PMerchApplySupplier newP() {
        return new PMerchApplySupplier();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        merchApplySupplierYesCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    merchApplySupplierAccountRl.setVisibility(View.VISIBLE);
                    merchApplySupplierNoCb.setChecked(false);
                    merchApplySupplierYesCb.setChecked(true);
                } else {
                    merchApplySupplierAccountRl.setVisibility(View.GONE);
                    merchApplySupplierNoCb.setChecked(true);
                    merchApplySupplierYesCb.setChecked(false);
                }
            }
        });
        merchApplySupplierNoCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    merchApplySupplierNoCb.setChecked(true);
                    merchApplySupplierYesCb.setChecked(false);
                } else {
                    merchApplySupplierNoCb.setChecked(false);
                    merchApplySupplierYesCb.setChecked(true);
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
        title.setText("选择供应商");
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


    @OnClick({R.id.merch_apply_supplier_account_rl, R.id.merch_apply_supplier_define_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.merch_apply_supplier_account_rl:
                //从FirstActivity跳转到SecondActivity的Intent（意图）
                Intent intent = new Intent(context, MerchApplySupplierDtlActivity.class);
                //执行Intent ★使用startActivityForResult来启动
                startActivityForResult(intent, 0);
                break;
            case R.id.merch_apply_supplier_define_bt:
                //applyType 1：支付宝    applyType 2：微信
                if (merchApplySupplierNoCb.isChecked() == false && merchApplySupplierYesCb.isChecked() ==false) {
                    showToast("请选择设备供应商类型");
                    return;
                }
                if ( merchApplySupplierYesCb.isChecked() ==true&& AppTools.isEmpty(merchId)) {
                    showToast("请选择设备供应商");
                    return;
                }

                if ("1".equals(applyType)) {
                    getP().addApplyInfo(AppUser.getInstance().getMerchId(), shopId, "0", "4", null, null, null,
                            null, null, null, null, null, null, null, null, null,
                            null, null, null, null, null, merchId, "0");
                } else if ("2".equals(applyType)) {
                    getP().addApplyInfo(AppUser.getInstance().getMerchId(), shopId, "1", "4", null, null, null,
                            null, null, null, null, null, null, null, null, null,
                            null, null, null, null, null, merchId, "0");
                }
                break;
            default:
                break;
        }
    }

    /**
     * 复写onActivityResult方法
     * 当SecondActivity页面关闭时，接收SecondActiviy页面传递过来的数据。
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            Bundle bundle = data.getExtras();
            String phone = bundle.getString("phone");
            String name = bundle.getString("name");
            //获取下来的是merchId,addApplyInfo需要传的fatherId即是这个merchId
            merchId = bundle.getString("merchId");
            merchApplySupplierAccountTv.setText(name + ":" + phone);
        }
    }
}
