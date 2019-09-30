package com.yzf.king.ui.activitys;

/**
 * ClaseName：TransStatActivity
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/13 19:50
 * Modified By：
 * Fixtime：2019/5/13 19:50
 * FixDescription：
 **/

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.yzf.king.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

public class TransStatActivity extends XActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.trans_tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.trans_viewPager)
    ViewPager viewPager;

    XFragmentAdapter adapter;
    List<Fragment> fragmentList = new ArrayList<>();
    String[] titles = {"日交易数据", "月交易数据"};
    public static final String TYPE = "type";
    public static final String TITLE = "title";
    public static final String TRANSTYPE = "transType";
    String type;
    String transType;
    String ptitle;
    @Override
    public void initData(Bundle savedInstanceState) {
        useEventBus(true);
        type = getIntent().getStringExtra(TYPE);
        ptitle = getIntent().getStringExtra(TITLE);
        transType = getIntent().getStringExtra(TRANSTYPE);
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_trans_stat;
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
        fragmentList.clear();
//        fragmentList.add(TransStatDayFragment.newInstance(type,transType));
//        fragmentList.add(TransStatMonthFragment.newInstance(type,transType));
        if (adapter == null) {
            adapter = new XFragmentAdapter(getSupportFragmentManager(), fragmentList, titles);
        }
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);
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
        title.setText(ptitle);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public int getOptionsMenuId() {
        return R.menu.menu_filter;
    }
    /**
     * 标题栏监听
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                break;
            case R.id.menu_filter:
                break;
                default:break;
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
     * @param msg
     */
    public void showToast(String msg) {
        getvDelegate().toastShort(msg);
    }

    /**
     * 显示单按钮对话框
     * @param msg
     */
    public void showErrorDialog(String msg) {
        getvDelegate().showErrorDialog(msg);
    }

    /**
     * 显示错误信息
     * @param error
     */
    public void showError(NetError error) {
        getvDelegate().showError(error);
    }

//    public void showEmptyView(String msg) {
//        multiplestatusview.showEmpty(msg);
//    }
//
//    public void showErrorView(String msg) {
//        multiplestatusview.showError(msg);
//    }
//
//    public void showErrorView(NetError error) {
//        multiplestatusview.showError(getvDelegate().getErrorType(error));
//    }


}
