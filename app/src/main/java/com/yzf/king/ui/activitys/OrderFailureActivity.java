package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.ActivityManager;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetInstallResult;
import com.yzf.king.present.POrderFailur;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

public class OrderFailureActivity extends XActivity<POrderFailur> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.right_text_tv)
    TextView myShopTv;
    @BindView(R.id.order_failure_et)
    EditText orderFailureEt;
    String orderId;

    @Override
    public void initData(Bundle savedInstanceState) {
        orderId = getIntent().getStringExtra("orderId");
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_failure;
    }

    @Override
    public POrderFailur newP() {
        return new POrderFailur();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        myShopTv.setText("提交");
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
        title.setText("失败原因");
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



    @OnClick(R.id.right_text_tv)
    public void onViewClicked() {
        getP().installConfirm(AppUser.getInstance().getMerchId(),orderId,orderFailureEt.getText().toString(),"FAILSE");
    }

    public void setResult(GetInstallResult data) {
        showToast(data.getMessage());
    }

    public void backActivity() {
        ActivityManager.getInstance().finishAllActivityExceptOne(OrderShopActivity.class);
    }
}
