package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnPageChangeListener;
import com.yzf.king.R;
import com.yzf.king.adapter.HolderView.LocalHolderView;
import com.yzf.king.widget.StateButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

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
 * ClassName：GuideActivity
 * Description:引导页
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2018/9/7 17:28
 * Modified By：
 * Fixtime：2018/9/7 17:28
 * FixDescription：
 */
public class GuideActivity extends XActivity {

    @BindView(R.id.gide_convenientbanner)
    ConvenientBanner convenientBanner;
    @BindView(R.id.gide_bt)
    StateButton gideBt;
    @BindView(R.id.relativeLayout)
    ConstraintLayout relativeLayout;
    private Handler handlerdelay = new Handler();
    private List<String> bannerList;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        bannerList = new ArrayList<>();
        bannerList.add("file:///android_asset/guide1.png");
        bannerList.add("file:///android_asset/guide2.png");
        bannerList.add("file:///android_asset/guide3.png");
        showBanner();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
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


    @OnClick(R.id.gide_bt)
    public void onViewClicked() {
        handlerdelay.removeCallbacks(jumpRunnable);
        Router.newIntent(context)
                .to(LoginActivity.class)
                .data(new Bundle())
                .launch();
        Router.pop(context);
    }

    private Runnable jumpRunnable = new Runnable() {
        public void run() {
            Router.newIntent(context)
                    .to(LoginActivity.class)
                    .data(new Bundle())
                    .launch();
            Router.pop(context);
        }
    };

    private void showBanner() {
        if (bannerList.size() > 0) {
            convenientBanner.setPages(new CBViewHolderCreator() {
                public int getLayoutId() {
                    return R.layout.item_images;
                }

                public Holder createHolder(View view) {
                    return new LocalHolderView(view);
                }
            }, bannerList)
                    .setPageIndicator(new int[]{R.mipmap.ic_page, R.mipmap.ic_page_focused})
                    .setOnPageChangeListener(new OnPageChangeListener() {
                        @Override
                        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                        }

                        @Override
                        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                        }

                        @Override
                        public void onPageSelected(int index) {
                            if (index == 2) {
                                gideBt.setVisibility(View.VISIBLE);
                            } else {
                                gideBt.setVisibility(View.GONE);
                            }

                        }
                    })
                    .setPointViewVisible(true);
        }
        handlerdelay.postDelayed(jumpRunnable, 7500);
    }

    // 开始自动翻页
    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        convenientBanner.startTurning(2500);
    }

    // 停止自动翻页
    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        convenientBanner.stopTurning();
    }

}
