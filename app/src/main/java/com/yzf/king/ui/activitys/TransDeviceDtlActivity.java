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

import com.alibaba.fastjson.JSONObject;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.ui.fragments.TransDeviceDtlFragment;
import com.yzf.king.ui.fragments.TransDtlFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.event.IBus;
import cn.droidlover.xdroidmvp.event.IEvent;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import rx.functions.Action1;

public class TransDeviceDtlActivity extends XActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.trans_device_dtl_tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.trans_device_dtl_viewPager)
    ViewPager viewPager;
    @BindView(R.id.trans_device_dtl_amt_tv)
    TextView transDeviceDtlAmtTv;
    @BindView(R.id.trans_device_dtl_date_tv)
    TextView transDeviceDtlDateTv;

    public final static String BEGINTIME = "beginTime";
    public final static String ENDTIME = "endTime";
    public static final String TYPE = "type";
    private String beginTime;
    private String endTime;
    XFragmentAdapter adapter;
    List<Fragment> fragmentList = new ArrayList<>();
    String[] titles = {"刷脸收款", "扫码收款"};
    int position = 0;
    String type;
    String status;
    String termId;
    String shopId;
    JSONObject a;
    JSONObject b;
    JSONObject c;


    @Override
    public void initData(Bundle savedInstanceState) {
        useEventBus(true);
        beginTime = getIntent().getStringExtra(BEGINTIME);
        endTime = getIntent().getStringExtra(ENDTIME);
        type = getIntent().getStringExtra("type");
        termId = getIntent().getStringExtra("termId");
        shopId = getIntent().getStringExtra("shopId");
        status = getIntent().getStringExtra("status");
        initView();
        fragmentList.clear();
        fragmentList.add(TransDeviceDtlFragment.newInstance(beginTime,endTime,"1",termId,shopId,status));
        fragmentList.add(TransDeviceDtlFragment.newInstance(beginTime,endTime,"0",termId,shopId,status));
        if (adapter == null) {
            adapter = new XFragmentAdapter(getSupportFragmentManager(), fragmentList, titles);
        }
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tabs) {
                position = tabs.getPosition();
                switch (position) {
                    case 0:
                        if (a != null) {
                            transDeviceDtlAmtTv.setText("￥" + a.getString("amt"));
                            transDeviceDtlDateTv.setText(a.getString("date"));
                        } else {
                            transDeviceDtlAmtTv.setText("￥0.00");
                            transDeviceDtlDateTv.setText("");
                        }
                        break;
                    case 1:
                        if (b != null) {
                            transDeviceDtlAmtTv.setText("￥" + b.getString("amt"));
                            transDeviceDtlDateTv.setText(b.getString("date"));
                        } else {
                            transDeviceDtlAmtTv.setText("￥0.00");
                            transDeviceDtlDateTv.setText("");
                        }
                        break;
                    default:
                        transDeviceDtlAmtTv.setText("￥0.00");
                        transDeviceDtlDateTv.setText("");
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                View tabView = tabLayout.getTabAt(i).view;
                if (tabView != null) {
                    tabView.setTag(i);
                    tabView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int pos = (int) view.getTag();
                            if (pos == 2) {
                                Logger.i("click_trans_device_dtl_filter");
                                if (c != null) {
                                    transDeviceDtlAmtTv.setText("￥" + c.getString("amt"));
                                    transDeviceDtlDateTv.setText(c.getString("date"));
                                } else {
                                    transDeviceDtlAmtTv.setText("￥0.00");
                                    transDeviceDtlDateTv.setText("");
                                }
                                IBus.IEvent iEvent = new IEvent();
                                iEvent.setId("click_trans_device_dtl_filter");
                                BusProvider.getBus().post(iEvent);
                            }
                        }
                    });
                }
            }
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_trans_device_dtl;
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
        if (!AppTools.isEmpty(type)) {
            transDeviceDtlAmtTv.setText("￥"+AppTools.formatF2Y(type));
            transDeviceDtlDateTv.setText(beginTime);
        }
    }

    /**
     * 初始化Toolbar
     */
    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left_white_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title.setText("交易明细详情");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void bindEvent() {
        BusProvider.getBus().toObservable(IEvent.class)
                .subscribe(new Action1<IEvent>() {
                    @Override
                    public void call(IEvent iEvent) {
                        //TODO 事件处理
                        if (iEvent.getId().equals("set_device_dtl_trans")) {
                            JSONObject jsonObject = (JSONObject) iEvent.getObject();
                            String type = jsonObject.getString("type");
                            if ("1".equals(type)) {
                                a = jsonObject;
                                if (position == 0) {
                                    if (a != null) {
                                        transDeviceDtlAmtTv.setText("￥" + a.getString("amt"));
                                        transDeviceDtlDateTv.setText(a.getString("date"));
                                    } else {
                                        transDeviceDtlAmtTv.setText("￥0.00");
                                        transDeviceDtlDateTv.setText("");
                                    }
                                }
                            }
                            if ("0".equals(type)) {
                                b = jsonObject;
                                if (position == 1) {
                                    if (b != null) {
                                        transDeviceDtlAmtTv.setText("￥" + b.getString("amt"));
                                        transDeviceDtlDateTv.setText(b.getString("date"));
                                    } else {
                                        transDeviceDtlAmtTv.setText("￥0.00");
                                        transDeviceDtlDateTv.setText("");
                                    }
                                }
                            }
                        }
                    }
                });
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
