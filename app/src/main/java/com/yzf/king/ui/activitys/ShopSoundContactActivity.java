package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xw.repo.XEditText;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetSunMerchInfoListResult;
import com.yzf.king.present.PShopSoundContact;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

public class ShopSoundContactActivity extends XActivity<PShopSoundContact> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.shop_sound_contact_phone_tv)
    XEditText shopSoundContactPhoneTv;
    @BindView(R.id.shop_sound_contact_email_tv)
    XEditText shopSoundContactEmailTv;
    @BindView(R.id.shop_sound_contact_define_bt)
    Button shopSoundContactDefineBt;

    private GetSunMerchInfoListResult.DataBean.SubListBean subListBean;

    @Override
    public void initData(Bundle savedInstanceState) {
        subListBean = (GetSunMerchInfoListResult.DataBean.SubListBean) getIntent().getSerializableExtra("subListBean");
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_sound_contact;
    }

    @Override
    public PShopSoundContact newP() {
        return new PShopSoundContact();
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
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_black_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title.setText("店铺申请");
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


    @OnClick(R.id.shop_sound_contact_define_bt)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shop_sound_contact_define_bt:
                if (!AppTools.isMobile(shopSoundContactPhoneTv.getTextEx())) {
                    showToast("手机格式不正确");
                    return;
                }
                if (!AppTools.isEmail(shopSoundContactEmailTv.getTextEx())) {
                    showToast("邮箱格式不正确");
                    return;
                }
                if (AppTools.isEmpty(shopSoundContactPhoneTv.getTextEx())) {
                    showToast("请输入法人电话");
                    return;
                }
                if (AppTools.isEmpty(shopSoundContactEmailTv.getTextEx())) {
                    showToast("请输入法人邮箱");
                    return;
                }
                getP().addShopInfo(AppUser.getInstance().getMerchId(), subListBean.getShopId(), subListBean.getChannelCode(), "6", shopSoundContactPhoneTv.getTextEx(), shopSoundContactEmailTv.getTextEx());
                break;
            default:
                break;
        }
    }
}
