package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.ActivityManager;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoResults;
import com.yzf.king.present.PShopDtl;
import com.yzf.king.ui.fragments.HomeFragment;
import com.yzf.king.ui.fragments.ShopFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

public class ShopFinishActivity extends XActivity<PShopDtl> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.shop_define_bt)
    Button shopDefineBt;
    @BindView(R.id.shop_finish_tv)
    TextView shopFinishTv;
    GetMachinApplyInfoResults.DataBean dataBean;
    String date;

    @Override
    public void initData(Bundle savedInstanceState) {
        date = getIntent().getStringExtra("date");
        dataBean = (GetMachinApplyInfoResults.DataBean) getIntent().getSerializableExtra("dataBean");
        initView();
        if (dataBean!=null) {
            getP().shopConfirm(AppUser.getInstance().getMerchId(), dataBean.getOrderId(), AppUser.getInstance().getTermId(), date, "SUCCESS");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_finish;
    }

    @Override
    public PShopDtl newP() {
        return new PShopDtl();
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
        title.setText("铺货结果");
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
            ActivityManager.getInstance().finishAllActivityExceptOne(MainActivity.class);
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


    @OnClick({R.id.shop_define_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shop_define_bt:
                ActivityManager.getInstance().finishAllActivityExceptOne(MainActivity.class);
                break;
            default:
        }
    }

    public void showResult(String message) {
        shopFinishTv.setText(message);
    }
}
