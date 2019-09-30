package com.yzf.king.ui.fragments;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.ui.activitys.ProxyActivity;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * ClaseName：ProxyFragment
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/6 10:00
 * Modified By：
 * Fixtime：2019/7/6 10:00
 * FixDescription：
 **/

public class ProxyFragment extends XFragment {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.proxy_define_bt)
    Button proxyDefineBt;
    @BindView(R.id.proxy_ll)
    LinearLayout proxyLl;
    Unbinder unbinder;
//     @BindView(R.id.tram_detail_multiplestatusview)
//        MultipleStatusView multiplestatusview;
    ProxyActivity proxyDtlFragment;
    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_proxy;
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
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        title.setText(R.string.proxy_name);
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
//            finish();
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


    @OnClick(R.id.proxy_define_bt)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.proxy_define_bt:
                JumpActivity(ProxyActivity.class);
                break;
            default:
        }


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
