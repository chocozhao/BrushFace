package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetMyTermResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

public class AvailableDtlActivity extends XActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.available_dtl_title_tv)
    TextView availableDtlTitleTv;
    @BindView(R.id.available_dtl_package_tv)
    TextView availableDtlPackageTv;
    @BindView(R.id.available_dtl_date_tv)
    TextView availableDtlDateTv;
    @BindView(R.id.available_dtl_address_tv)
    TextView availableDtlAddressTv;
    @BindView(R.id.available_dtl_modelname_tv)
    TextView availableDtlModelnameTv;
    @BindView(R.id.available_dtl_sn_tv)
    TextView availableDtlSnTv;
    @BindView(R.id.available_dtl_activate_tv)
    TextView availableDtlActivateTv;
    @BindView(R.id.available_dtl_term_tv)
    TextView availableDtlTermTv;
    GetMyTermResult.DataBean.DataListBean dataListBean;
    @BindView(R.id.available_dtl_hear_iv)
    ImageView availableDtlHearIv;

    @Override
    public void initData(Bundle savedInstanceState) {
        dataListBean = (GetMyTermResult.DataBean.DataListBean) getIntent().getSerializableExtra("dataListBean");
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_available_dtl;
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
        if (dataListBean != null) {
            if (!AppTools.isEmpty(dataListBean.getLogoUrl())) {
                ILFactory.getLoader().loadImage(dataListBean.getLogoUrl(), availableDtlHearIv);
            } else {
                ILFactory.getLoader().loadImage(R.mipmap.logo, availableDtlHearIv);
            }
            availableDtlTitleTv.setText(dataListBean.getModelName());
            availableDtlPackageTv.setText(dataListBean.getMealName());
            availableDtlDateTv.setText(Kits.Date.getYmdhms(dataListBean.getInsertTime()));
            availableDtlModelnameTv.setText(dataListBean.getModelName());
            availableDtlSnTv.setText(dataListBean.getTermSn());
            availableDtlActivateTv.setText(dataListBean.getActivCode());
            availableDtlTermTv.setText(dataListBean.getTermId());
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
        title.setText(dataListBean.getModelName());
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
