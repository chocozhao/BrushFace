package com.yzf.king.ui.activitys;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.adapter.TransMyDeviceAdapter;
import com.yzf.king.ui.fragments.MyDeviceFragment;
import com.yzf.king.ui.fragments.ShopManageFragment;
import com.yzf.king.ui.fragments.TeamDeviceFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

public class TransDetailsActivity extends XActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.trans_details_tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.trans_details_viewPager)
    ViewPager viewPager;
    @BindView(R.id.right_text_tv)
    TextView myShopTv;
    XFragmentAdapter adapter;
    List<Fragment> fragmentList = new ArrayList<>();
    String[] titles = {"我的设备", "团队设备"};
    String type;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        fragmentList.clear();
        fragmentList.add(MyDeviceFragment.newInstance(""));
        fragmentList.add(TeamDeviceFragment.newInstance(""));
        if (adapter == null) {
            adapter = new XFragmentAdapter(getSupportFragmentManager(), fragmentList, titles);
        }
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_trans_details;
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
        title.setText("交易明细");
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


    @OnClick({R.id.right_text_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_text_tv:
                Router.newIntent(context)
                        .to(MyShopActivity.class)
                        .putString("transDevice","transDevice")
                        .launch();
                break;
            default:
        }
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
