package com.yzf.king.ui.activitys;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzf.king.App;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetUrlResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * ClaseName：ProxyActivity
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/6 15:42
 * Modified By：
 * Fixtime：2019/7/6 15:42
 * FixDescription：
 **/

public class ProxyActivity extends XActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.proxy_upgrade_bt)
    Button proxyUpgradeBt;
    Unbinder unbinder;
    @BindView(R.id.proxy_ll)
    LinearLayout proxyLl;
    @BindView(R.id.proxy_contract_tv)
    TextView proxyContractTv;
    @BindView(R.id.proxy_cb)
    CheckBox proxyCb;


    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_proxy;
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
        proxyCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    proxyUpgradeBt.setEnabled(true);
                    proxyUpgradeBt.setBackground(getResources().getDrawable(R.drawable.proxy_btn_bg));
                    if ("2".equals(AppUser.getInstance().getMerchLevel())) {
                        proxyUpgradeBt.setEnabled(false);
                        proxyUpgradeBt.setBackground(getResources().getDrawable(R.drawable.proxy_btn_gray));
                    }
                } else {
                    proxyUpgradeBt.setEnabled(false);
                    proxyUpgradeBt.setBackground(getResources().getDrawable(R.drawable.proxy_btn_blue_height));
                    if ("2".equals(AppUser.getInstance().getMerchLevel())) {
                        proxyUpgradeBt.setEnabled(false);
                        proxyUpgradeBt.setBackground(getResources().getDrawable(R.drawable.proxy_btn_gray));
                    }
                }
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        if (proxyCb.isChecked()) {
            proxyUpgradeBt.setEnabled(true);
            proxyUpgradeBt.setBackground(getResources().getDrawable(R.drawable.proxy_btn_bg));
        }else {
            proxyUpgradeBt.setEnabled(false);
            proxyUpgradeBt.setBackground(getResources().getDrawable(R.drawable.proxy_btn_blue_height));
        }
        if ("2".equals(AppUser.getInstance().getMerchLevel())) {
            proxyUpgradeBt.setBackground(getResources().getDrawable(R.drawable.proxy_btn_gray));
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
        title.setText(R.string.proxy_name);
    }

    /**
     * 标题栏监听
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


    @OnClick({R.id.proxy_upgrade_bt, R.id.proxy_contract_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.proxy_upgrade_bt:
                if (!proxyCb.isChecked()) {
                    showToast("请先阅读并同意协议");
                    return;
                }
                if ("2".equals(AppUser.getInstance().getMerchLevel())) {
                    showErrorDialog(App.getContext().getString(R.string.proxy_upgrade_prompt));
                } else {
                    List<GetUrlResult.DataBean> list1 = AppUser.getInstance().getUrlBean();
                    String target1 = null;//http://h5.yiyoupay.net/link/html/directions.html?topBranchNo=20000029
                    if (list1 != null && list1.size() > 0) {
                        for (GetUrlResult.DataBean dataBean : list1) {
                            if (dataBean.getType().equals("upgradeUrl")) {
                                target1 = dataBean.getUrl();
                                break;
                            }
                        }
                    }
                    if (!AppTools.isEmpty(target1)) {
                        AgenWebViewActivity.launch(context, target1, null);
                    } else {
                        showToast("暂未开放");
                    }
                }
                break;
            case R.id.proxy_contract_tv:
                List<GetUrlResult.DataBean> list2 = AppUser.getInstance().getUrlBean();
                String target2 = null;//http://h5.yiyoupay.net/link/html/directions.html?topBranchNo=20000029
                if (list2 != null && list2.size() > 0) {
                    for (GetUrlResult.DataBean dataBean : list2) {
                        if (dataBean.getType().equals("agencyUrl")) {
                            Logger.i(target2);
                            target2 = dataBean.getUrl();
                            break;
                        }
                    }
                }
                if (!AppTools.isEmpty(target2)) {
                    AgenWebViewActivity.launch(context, target2, null);
                } else {
                    showToast("暂未开放");
                }
                break;
            default:
        }
    }

}
