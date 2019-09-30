package com.yzf.king.ui.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.just.agentweb.FragmentKeyDown;
import com.yzf.king.R;
import com.yzf.king.ui.fragments.AgentWebFragment;
import com.yzf.king.ui.fragments.CookiesWebFragment;

import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;

/**
 *
 */
public class CookieWebViewActivity extends XActivity {
    private FrameLayout mFrameLayout;
    private FragmentManager mFragmentManager;
    private CookiesWebFragment mAgentWebFragment;
    public static final String PARAM_URL = "url";
    public static final String PARAM_DESC = "desc";
    String url;
    String desc;


    @Override
    public void initData(Bundle savedInstanceState) {
        url = getIntent().getStringExtra(PARAM_URL);
        desc = getIntent().getStringExtra(PARAM_DESC);
        mFrameLayout = (FrameLayout) this.findViewById(R.id.container_framelayout);
        mFragmentManager = this.getSupportFragmentManager();

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        Bundle mBundle;
        ft.add(R.id.container_framelayout, mAgentWebFragment = CookiesWebFragment.getInstance(mBundle = new Bundle()), CookiesWebFragment.class.getName());
        mBundle.putString(AgentWebFragment.URL_KEY, url);
        ft.commit();
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this).statusBarDarkFont(true, 0.2f)
                .keyboardEnable(true)
                .navigationBarColor(cn.droidlover.xdroidmvp.R.color.alert_default_text_color);
        mImmersionBar.init();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_agen_web_view;
    }

    @Override
    public Object newP() {
        return null;
    }

    public static void launch(Activity activity, String url, String desc) {
        Router.newIntent(activity)
                .to(CookieWebViewActivity.class)
                .putString(PARAM_URL, url)
                .putString(PARAM_DESC, desc)
                .launch();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //一定要保证 mAentWebFragemnt 回调
        mAgentWebFragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        CookiesWebFragment mAgentWebFragment = this.mAgentWebFragment;
        if (mAgentWebFragment != null) {
            FragmentKeyDown mFragmentKeyDown = mAgentWebFragment;
            if (mFragmentKeyDown.onFragmentKeyDown(keyCode, event))
                return true;
            else
                return super.onKeyDown(keyCode, event);
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
