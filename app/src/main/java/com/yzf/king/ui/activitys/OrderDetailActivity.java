package com.yzf.king.ui.activitys;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetTransDevicesResult;
import com.yzf.king.ui.fragments.OrderDetailFragment;
import com.yzf.king.ui.fragments.OrderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

public class OrderDetailActivity extends XActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.order_detail_tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.order_detail_viewPager)
    ViewPager viewPager;

    XFragmentAdapter adapter;
    List<Fragment> fragmentList = new ArrayList<>();
    String[] titles = { "已发货", "未发货"};
    int item;
    String id;
    GetTransDevicesResult.DataBean transDevicesdataBean;
    @Override
    public void initData(Bundle savedInstanceState) {
        item = (int) getIntent().getIntExtra("item",item);
        id = getIntent().getStringExtra("id");
        transDevicesdataBean = (GetTransDevicesResult.DataBean) getIntent().getSerializableExtra("transDevicesdataBean");
        initView();
        fragmentList.clear();
        fragmentList.add(OrderDetailFragment.newInstance(transDevicesdataBean,id,"1"));
        fragmentList.add(OrderDetailFragment.newInstance(transDevicesdataBean,id,"0"));
        if (adapter == null) {
            adapter = new XFragmentAdapter(getSupportFragmentManager(), fragmentList, titles);
        }
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setCurrentItem(item);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_detail;
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
