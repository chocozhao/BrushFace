package com.yzf.king.ui.activitys;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;

import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFUserInfo;
import com.tapadoo.alerter.Alerter;
import com.yzf.king.App;
import com.yzf.king.R;
import com.yzf.king.kit.ActivityManager;
import com.yzf.king.kit.AppConfig;
import com.yzf.king.kit.AppUser;
import com.yzf.king.ui.fragments.HomeFragment;
import com.yzf.king.ui.fragments.NewsFragment;
import com.yzf.king.ui.fragments.PersonFragment;
import com.yzf.king.ui.fragments.ProxyFragment;
import com.yzf.king.ui.fragments.ShareFragment;
import com.yzf.king.ui.fragments.ShopFragment;
import com.yzf.king.widget.CustomViewPager;
import com.yzf.king.widget.SpecialTab;
import com.yzf.king.widget.SpecialTabRound;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;
import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.event.IBus;
import cn.droidlover.xdroidmvp.event.IEvent;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.jpush.android.api.JPushInterface;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import rx.functions.Action1;

public class MainActivity extends XActivity {
    XFragmentAdapter adapter;
    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.tabLayout)
    PageNavigationView tabLayout;
    private long lastTime = 0;

    List<Fragment> fragmentList = new ArrayList<>();
    String[] strArr = new String[]{App.getContext().getString(R.string.home_title), App.getContext().getString(R.string.proxy_name), "铺货", "分享", "我的"};
    int index_normal;
    int index_press;

    @Override
    public void initData(Bundle savedInstanceState) {
        useEventBus(true);
        initView();
        if (AppConfig.O_SINGLE) {
            index_normal = R.mipmap.index_proxy_normal;
            index_press = R.mipmap.index_proxy_press;
        } else {
            index_normal = R.mipmap.index_group_normal;
            index_press = R.mipmap.index_group_press;
        }
        initNavigationBar();


        if (AppConfig.PUSH) {//极光推送
            JPushInterface.setAlias(context, Kits.Random.getRandom(90000000), AppUser.getInstance().getMerchId());
            Set<String> tags = new HashSet<>();
            tags.add(AppConfig.TOPBRANCHNO);
            JPushInterface.setTags(context, Kits.Random.getRandom(90000000), tags);
        }
        YSFUserInfo userInfo = new YSFUserInfo();
        userInfo.userId = AppUser.getInstance().getMerchId();
        userInfo.data = "[{\"key\":\"real_name\", \"value\":" + AppUser.getInstance().getMerchName() + "},{\"key\":\"mobile_phone\", \"value\":" + AppUser.getInstance().getPhone() + ", \"hidden\":true}]";
        Unicorn.setUserInfo(userInfo);

       /* if (!checkNotificationPermission(context)) {
            goNLPermission(context);
        }*/


    }

    private void initNavigationBar() {
        fragmentList.clear();
        fragmentList.add(new HomeFragment());
        if (AppConfig.O_SINGLE) {
            fragmentList.add(new ProxyFragment());
        } else {
            fragmentList.add(new NewsFragment());
        }
        fragmentList.add(new ShopFragment());
        fragmentList.add(new ShareFragment());
        fragmentList.add(new PersonFragment());
        if (this.adapter == null) {
            this.adapter = new XFragmentAdapter(getSupportFragmentManager(), this.fragmentList, strArr);
        }
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(strArr.length);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 1:
                        IBus.IEvent iEvent = new IEvent();
                        iEvent.setId("show_new");
                        BusProvider.getBus().post(iEvent);
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //底部导航栏突起
        if (this.adapter == null) {
            this.adapter = new XFragmentAdapter(getSupportFragmentManager(), this.fragmentList, strArr);
        }
        this.viewPager.setAdapter(this.adapter);
        this.viewPager.setOffscreenPageLimit(strArr.length);
        this.tabLayout.custom().
                addItem(newItem(R.mipmap.index_home_normal, R.mipmap.index_home_press, App.getContext().getString(R.string.home_title)))
                .addItem(newItem(index_normal, index_press, App.getContext().getString(R.string.proxy_name)))
                .addItem(newRoundItem(R.mipmap.index_shop, R.mipmap.index_shop, "铺货"))
                .addItem(newItem(R.mipmap.index_fission_normal, R.mipmap.index_fission_press, "分享"))
                .addItem(newItem(R.mipmap.index_person_normal, R.mipmap.index_person_press, "我的"))
                .build()
                .setupWithViewPager(this.viewPager);
    }

    public static void goNLPermission(Context context) {
        try {
            Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查通知使用权
     */
    public static boolean checkNotificationPermission(Context context) {
        String pkg = context.getPackageName();
        String flat = Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        boolean enabled = flat != null && flat.contains(pkg);
        return enabled;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
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
       /* setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title.setText("标题");*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SharedPref.getInstance(this.context).getBoolean("showMsg", false)) {
            getvDelegate().showPushAlerter(null, SharedPref.getInstance(this.context).getString("msg", ""), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    JumpActivity(MsgActivity.class, false);
                    Alerter.hide();
                }
            });
        }
    }

    @Override
    public void bindEvent() {
        BusProvider.getBus().toObservable(IEvent.class)
                .subscribe(new Action1<IEvent>() {
                    @Override
                    public void call(IEvent iEvent) {
                        //TODO 事件处理
                        if (iEvent.getId().equals("show_msg")) {
                            String msg = SharedPref.getInstance(context).getString("msg", "");
                            getvDelegate().showPushAlerter(null, msg, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    JumpActivity(MsgActivity.class, false);
                                    Alerter.hide();
                                }
                            });
                        }
                        if (iEvent.getId().equals("to_tab")) {
                            int position = (int) iEvent.getObject();
                            viewPager.setCurrentItem(position, false);
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

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - this.lastTime < 2000) {
            if (ActivityManager.getInstance().getActivitys().size() > 0) {
                ActivityManager.getInstance().appExit();
            } else {
                Process.killProcess(Process.myPid());
            }
            return;
        }
        this.lastTime = System.currentTimeMillis();
        getvDelegate().toastShort(getString(R.string.press_again_exit));
    }

    /**
     * 正常tab
     */
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        SpecialTab specialTab = new SpecialTab(this);
        specialTab.initialize(drawable, checkedDrawable, text);
        specialTab.setTextDefaultColor(getResources().getColor(R.color.text_6));
        specialTab.setTextCheckedColor(getResources().getColor(R.color.text_theme));
        return specialTab;
    }

    /**
     * 圆形tab
     */
    private BaseTabItem newRoundItem(int drawable, int checkedDrawable, String text) {
        SpecialTabRound specialTabRound = new SpecialTabRound(this);
        specialTabRound.initialize(drawable, checkedDrawable, text);
        specialTabRound.setTextDefaultColor(getResources().getColor(R.color.text_6));
        specialTabRound.setTextCheckedColor(getResources().getColor(R.color.text_theme));
        return specialTabRound;
    }

}
