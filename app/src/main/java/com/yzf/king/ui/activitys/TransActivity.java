package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.yzf.king.R;
import com.yzf.king.model.servicesmodels.GetTeamTermInfoResult;
import com.yzf.king.ui.fragments.TransFilterFragment;
import com.yzf.king.ui.fragments.TransFragment;

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

/**
 * 　　┏┓　　　┏┓+ +
 * 　┏┛┻━━━┛┻┓ + +
 * 　┃　　　　　　　┃
 * 　┃　　　━　　　┃ ++ + + +
 * ████━████ ┃+
 * 　┃　　　　　　　┃ +
 * 　┃　　　┻　　　┃
 * 　┃　　　　　　　┃ + +
 * 　┗━┓　　　┏━┛
 * 　　　┃　　　┃
 * 　　　┃　　　┃ + + + +
 * 　　　┃　　　┃
 * 　　　┃　　　┃ +  神兽镇楼
 * 　　　┃　　　┃    代码无bug
 * 　　　┃　　　┃　　+
 * 　　　┃　 　　┗━━━┓ + +
 * 　　　┃ 　　　　　　　┣┓
 * 　　　┃ 　　　　　　　┏┛
 * 　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　┃┫┫　┃┫┫
 * 　　　　┗┻┛　┗┻┛+ + + +
 * <p>
 * ClassName：MainActivity
 * Description: 交易明细主页面
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/5/18 11:43
 * Modified By：
 * Fixtime：2017/5/18 11:43
 * FixDescription：
 */
public class TransActivity extends XActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.trans_tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.trans_viewPager)
    ViewPager viewPager;
    @BindView(R.id.trans_amt_tv)
    TextView transAmtTv;
    @BindView(R.id.trans_date_tv)
    TextView transDateTv;

    XFragmentAdapter adapter;
    List<Fragment> fragmentList = new ArrayList<>();
    String[] titles = {"7天", "一个月", "更多筛选"};
    public static final String TYPE = "type";
    int position = 0;
    JSONObject a;
    JSONObject b;
    JSONObject c;
    private String transDevice;
    private String termId;
    private String shopId;
    @Override
    public void initData(Bundle savedInstanceState) {
        useEventBus(true);
        transDevice = getIntent().getStringExtra("transDevice");
        termId = getIntent().getStringExtra("termId");
        shopId = getIntent().getStringExtra("shopId");
        initView();
        fragmentList.clear();
        fragmentList.add(TransFragment.newInstance(-7,transDevice,termId,shopId));
        fragmentList.add(TransFragment.newInstance(-30,transDevice,termId,shopId));
        fragmentList.add(TransFilterFragment.newInstance(0,transDevice,termId,shopId));
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
                            transAmtTv.setText("￥" + a.getString("amt"));
                            transDateTv.setText(a.getString("date"));
                        } else {
                            transAmtTv.setText("￥0.00");
                            transDateTv.setText("");
                        }
                        break;
                    case 1:
                        if (b != null) {
                            transAmtTv.setText("￥" + b.getString("amt"));
                            transDateTv.setText(b.getString("date"));
                        } else {
                            transAmtTv.setText("￥0.00");
                            transDateTv.setText("");
                        }
                        break;
                    case 2:
                        View view = tabLayout.getTabAt(2).view;
                        view.setTag(2);
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int pos = (int) view.getTag();
                                if (pos == 2) {
                                    Logger.i("click_trans_filter");
                                    if (c != null) {
                                        transAmtTv.setText("￥" + c.getString("amt"));
                                        transDateTv.setText(c.getString("date"));
                                    } else {
                                        transAmtTv.setText("￥0.00");
                                        transDateTv.setText("");
                                    }
                                    IBus.IEvent iEvent = new IEvent();
                                    iEvent.setId("click_trans_filter");
                                    BusProvider.getBus().post(iEvent);
                                }
                            }
                        });
                        if (c != null) {
                            transAmtTv.setText("￥" + c.getString("amt"));
                            transDateTv.setText(c.getString("date"));
                        } else {
                            transAmtTv.setText("￥0.00");
                            transDateTv.setText("");
                        }
                        IBus.IEvent iEvent = new IEvent();
                        iEvent.setId("show_trans_filter");
                        BusProvider.getBus().post(iEvent);
                        break;
                    default:
                        transAmtTv.setText("￥0.00");
                        transDateTv.setText("");
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
                                Logger.i("click_trans_filter");
                                if (c != null) {
                                    transAmtTv.setText("￥" + c.getString("amt"));
                                    transDateTv.setText(c.getString("date"));
                                } else {
                                    transAmtTv.setText("￥0.00");
                                    transDateTv.setText("");
                                }
                                IBus.IEvent iEvent = new IEvent();
                                iEvent.setId("click_trans_filter");
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
        return R.layout.activity_trans;
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
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left_white_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if ("transDevice".equals(transDevice)) {
            title.setText("交易明细");
        } else {
            title.setText("收益明细");
        }
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
                        if (iEvent.getId().equals("set_trans")) {
                            JSONObject jsonObject = (JSONObject) iEvent.getObject();
                            int day = jsonObject.getIntValue("day");
                            if (day == -7) {
                                a = jsonObject;
                                if (position == 0) {
                                    if (a != null) {
                                        transAmtTv.setText("￥" + a.getString("amt"));
                                        transDateTv.setText(a.getString("date"));
                                    } else {
                                        transAmtTv.setText("￥0.00");
                                        transDateTv.setText("");
                                    }
                                }
                            }
                            if (day == -30) {
                                b = jsonObject;
                                if (position == 1) {
                                    if (b != null) {
                                        transAmtTv.setText("￥" + b.getString("amt"));
                                        transDateTv.setText(b.getString("date"));
                                    } else {
                                        transAmtTv.setText("￥0.00");
                                        transDateTv.setText("");
                                    }
                                }
                            }
                            if (day == 0) {
                                c = jsonObject;
                                if (position == 2) {
                                    if (c != null) {
                                        transAmtTv.setText("￥" + c.getString("amt"));
                                        transDateTv.setText(c.getString("date"));
                                    } else {
                                        transAmtTv.setText("￥0.00");
                                        transDateTv.setText("");
                                    }
                                }
                            }


                        }
                    }
                });
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