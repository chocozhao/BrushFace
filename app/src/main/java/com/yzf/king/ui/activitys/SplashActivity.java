package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.ImageView;

import com.yzf.king.R;
import com.yzf.king.kit.AppConfig;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.db.ParamDao;
import com.yzf.king.present.PSplash;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.mvp.ToastType;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.jpush.android.api.JPushInterface;

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
 * ClassName：SplashActivity
 * Description:开屏页
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2018/8/24 14:39
 * Modified By：
 * Fixtime：2018/8/24 14:39
 * FixDescription：
 */
public class SplashActivity extends XActivity<PSplash> {
    /**
     * Handler
     */
    private static Handler handlerdelay = new Handler();
    @BindView(R.id.splash_iv)
    ImageView splashIv;

    private int versionCode = 0;


    @Override
    public void initData(Bundle savedInstanceState) {
        versionCode = SharedPref.getInstance(context).getInt("versionCode", 0);
        initView();
        initDB();//数据初始化
        if (AppConfig.PUSH) {
            JPushInterface.setDebugMode(AppConfig.DEV);
            JPushInterface.init(this);
        }
        int tempVersionCode = Kits.Package.getVersionCode(context);//获取版本号
        if (tempVersionCode > versionCode) {//判断是否是第一次登陆，从而跳转引导页
            SharedPref.getInstance(context).putInt("versionCode", tempVersionCode);
            Router.newIntent(context)
                    .to(GuideActivity.class)
                    .data(new Bundle())
                    .launch();
            Router.pop(context);
        } else {//否则获取手机号码和密码
            String phone = SharedPref.getInstance(this.context).getString("phone", "");
            String password = SharedPref.getInstance(context).getString("password", "");
            if (!AppTools.isEmpty(phone) && !AppTools.isEmpty(password)) {//如果手机号码密码不为空，调用P层的登陆逻辑处理判断登陆成功还是失败
                getvDelegate().showLoading("登录中....");
                getP().login(phone, password, null, "0", null, null, null);
            } else {
                handlerdelay.postDelayed(this.jumpRunnable, 2000);//否则开启一个子线程跳转登陆页面
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public PSplash newP() {
        return new PSplash();
    }

    /**
     * 初始化界面
     */
    private void initView() {
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

    public void showToast(String msg, ToastType type) {
        getvDelegate().toast(msg, type);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

    private void initDB() {
        new ParamDao().initDbBeforeLogin();
    }

    public void onBackPressed() {
        super.onBackPressed();
        handlerdelay.removeCallbacks(this.jumpRunnable);
    }

    @Override
    protected void onDestroy() {
        handlerdelay.removeCallbacks(this.jumpRunnable);
        super.onDestroy();
    }

    private final Runnable jumpRunnable = new Runnable() {
        public void run() {
            Router.newIntent(context)
                    .to(LoginActivity.class)
                    .data(new Bundle())
                    .launch();
            Router.pop(context);
        }
    };


}
