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
import com.yzf.king.ui.fragments.ProfitFilterFragment;
import com.yzf.king.ui.fragments.ProfitFragment;

import java.lang.reflect.Field;
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
 * ClassName：ProfitActivity
 * Description: 分润
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/5/18 11:43
 * Modified By：
 * Fixtime：2017/5/18 11:43
 * FixDescription：
 */
public class ProfitActivity extends XActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.profit_tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.profit_viewPager)
    ViewPager viewPager;
    @BindView(R.id.profit_amt_tv)
    TextView profitAmtTv;
    @BindView(R.id.profit_date_tv)
    TextView profitDateTv;

    XFragmentAdapter adapter;
    List<Fragment> fragmentList = new ArrayList<>();
    String[] titles = {"7天", "一个月", "更多筛选"};
    public static final String TYPE = "type";
    public static final String TRANSTYPE = "transType";
    public static final String TITLE = "title";
    int position = 0;
    String type;
    String transType;
    String ptitle;
    JSONObject a;
    JSONObject b;
    JSONObject c;


    @Override
    public void initData(Bundle savedInstanceState) {
        useEventBus(true);
        type = getIntent().getStringExtra(TYPE);
        transType = getIntent().getStringExtra(TRANSTYPE);
        ptitle = getIntent().getStringExtra(TITLE);
        initView();
        fragmentList.clear();
        fragmentList.add(ProfitFragment.newInstance(-7, type,transType));
        fragmentList.add(ProfitFragment.newInstance(-30, type,transType));
        fragmentList.add(ProfitFilterFragment.newInstance(0, type,transType));
        if (adapter == null) {
            adapter = new XFragmentAdapter(getSupportFragmentManager(), fragmentList, titles);
        }
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
                switch (position) {
                    case 0:
                        if (a != null) {
                            profitAmtTv.setText("￥" + a.getString("amt"));
                            profitDateTv.setText(a.getString("date"));
                        } else {
                            profitAmtTv.setText("￥0.00");
                            profitDateTv.setText("");
                        }
                        break;
                    case 1:
                        if (b != null) {
                            profitAmtTv.setText("￥" + b.getString("amt"));
                            profitDateTv.setText(b.getString("date"));
                        } else {
                            profitAmtTv.setText("￥0.00");
                            profitDateTv.setText("");
                        }
                        break;
                    case 2:
                        if (c != null) {
                            profitAmtTv.setText("￥" + c.getString("amt"));
                            profitDateTv.setText(c.getString("date"));
                        } else {
                            profitAmtTv.setText("￥0.00");
                            profitDateTv.setText("");
                        }
                        break;
                    default:
                        profitAmtTv.setText("￥0.00");
                        profitDateTv.setText("");
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
                                Logger.i("click_profit_filter");
                                if (c != null) {
                                    profitAmtTv.setText("￥" + c.getString("amt"));
                                    profitDateTv.setText(c.getString("date"));
                                } else {
                                    profitAmtTv.setText("￥0.00");
                                    profitDateTv.setText("");
                                }
                                IBus.IEvent iEvent = new IEvent();
                                iEvent.setId("click_profit_filter");
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
        return R.layout.activity_profit;
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
        title.setText(ptitle);
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
                        if (iEvent.getId().equals("set_profit")) {
                            JSONObject jsonObject = (JSONObject) iEvent.getObject();
                            int day = jsonObject.getIntValue("day");
                            if (day == -7) {
                                a = jsonObject;
                                if (position == 0) {
                                    if (a != null) {
                                        profitAmtTv.setText("￥" + a.getString("amt"));
                                        profitDateTv.setText(a.getString("date"));
                                    } else {
                                        profitAmtTv.setText("￥0.00");
                                        profitDateTv.setText("");
                                    }
                                }
                            }
                            if (day == -30) {
                                b = jsonObject;
                                if (position == 1) {
                                    if (b != null) {
                                        profitAmtTv.setText("￥" + b.getString("amt"));
                                        profitDateTv.setText(b.getString("date"));
                                    } else {
                                        profitAmtTv.setText("￥0.00");
                                        profitDateTv.setText("");
                                    }
                                }
                            }
                            if (day == 0) {
                                c = jsonObject;
                                if (position == 2) {
                                    if (c != null) {
                                        profitAmtTv.setText("￥" + c.getString("amt"));
                                        profitDateTv.setText(c.getString("date"));
                                    } else {
                                        profitAmtTv.setText("￥0.00");
                                        profitDateTv.setText("");
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