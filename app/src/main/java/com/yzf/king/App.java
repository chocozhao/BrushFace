package com.yzf.king;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.multidex.MultiDex;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.qiyukf.unicorn.api.StatusBarNotificationConfig;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFOptions;
import com.taobao.sophix.SophixManager;
import com.yzf.king.kit.ActivityManager;
import com.yzf.king.kit.AppConfig;
import com.yzf.king.kit.db.DBManager;
import com.yzf.king.net.SignInterceptor;
import com.yzf.king.net.TokenInterceptor;
import com.yzf.king.ui.activitys.MainActivity;
import com.zxy.recovery.callback.RecoveryCallback;
import com.zxy.recovery.core.Recovery;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import cn.droidlover.xdroidmvp.imageloader.GlideLoader;
import cn.droidlover.xdroidmvp.log.LogLevel;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.NetProvider;
import cn.droidlover.xdroidmvp.net.RequestHandler;
import cn.droidlover.xdroidmvp.net.XApi;
import es.dmoral.toasty.Toasty;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * ClaseName：${NAME}
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2016/12/31 10:32
 * Modified By：
 * Fixtime：2016/12/31 10:32
 * FixDescription：
 */

public class App extends Application {
    private static Context context;
    public static Application instance;
    private boolean flag = true;
    public interface BehaviourListener {
        void handleBehaviour(int code, String msg, int handlePatchVersion);
    }

    public static BehaviourListener behaviourListener = null;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        instance = this;
        GlideLoader glideLoader = new GlideLoader();
        glideLoader.init(context);
       /* if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);*/

        // 注册activity监听器
        registerActivityListener();

        Unicorn.init(this, AppConfig.QIYU_KEY, options(), glideLoader);

        if (AppConfig.DEV) {
            Logger.init(getString(R.string.app_name)).hideThreadInfo().methodCount(2).logLevel(LogLevel.FULL);//logger日志初始化
        } else {
            Logger.init(getString(R.string.app_name)).hideThreadInfo().methodCount(2).logLevel(LogLevel.NONE);//logger日志初始化
        }

        Recovery.getInstance()//crash崩溃监听处理
                .debug(AppConfig.DEV)
                .recoverInBackground(false)
                .recoverStack(true)
                .mainPage(MainActivity.class)
                .recoverEnabled(true)
                .callback(new MyCrashCallback())
                .silent(!AppConfig.DEV, Recovery.SilentMode.RECOVER_ACTIVITY_STACK)
                .init(this);

        DBManager.initialize(context);//初始化数据库

        Toasty.Config.getInstance()
                .setTextSize(14) //toast字体大小
                .allowQueue(false)
                .apply();


        XApi.registerProvider(new NetProvider() {//网络初始化

            @Override
            public Interceptor[] configInterceptors() {
                List<Interceptor> interceptorList = new ArrayList<>();
                interceptorList.add(new SignInterceptor());
                interceptorList.add(new TokenInterceptor());
                return interceptorList.toArray(new Interceptor[]{});
            }

            @Override
            public void configHttps(OkHttpClient.Builder builder) {

            }

            @Override
            public CookieJar configCookie() {
                return null;
            }

            @Override
            public RequestHandler configHandler() {
                return null;
            }

            @Override
            public long configConnectTimeoutMills() {
                return 0;
            }

            @Override
            public long configReadTimeoutMills() {
                return 0;
            }

            @Override
            public boolean configLogEnable() {
                return true;
            }

            @Override
            public boolean handleError(NetError error) {
                return false;
            }
        });

        //android P 非SDK兼用提示限制
        closeAndroidPDialog();
        //百度地图初始化
        initLocation();

        /**
         *  queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
         *  阿里热修复Sophix初始化
         */
        if (flag) {
            SophixManager.getInstance().queryAndLoadNewPatch();
            flag = false;
        }

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    /**
     * 设置 app 字体不随系统字体设置改变
     */

    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        if (resources.getConfiguration().fontScale != 1) { //fontScale不为1，需要强制设置为1
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置成默认值，即fontScale为1
            resources.updateConfiguration(newConfig, resources.getDisplayMetrics());
        }
        return resources;
    }

    // 如果返回值为null，则全部使用默认参数。
    private YSFOptions options() {
        YSFOptions options = new YSFOptions();
        options.statusBarNotificationConfig = new StatusBarNotificationConfig();
        return options;
    }


    public static Context getContext() {
        return context;
    }

    private void registerActivityListener() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    /**
                     *  监听到 Activity创建事件 将该 Activity 加入list
                     */
                    ActivityManager.getInstance().pushActivity(activity);
                    /**
                     * 栈顶元素名称
                     */
                    Logger.d("TopActivityName:" + ActivityManager.getInstance().getTopActivityName());
                }

                @Override
                public void onActivityStarted(Activity activity) {

                }

                @Override
                public void onActivityResumed(Activity activity) {

                }

                @Override
                public void onActivityPaused(Activity activity) {

                }

                @Override
                public void onActivityStopped(Activity activity) {

                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                }

                @Override
                public void onActivityDestroyed(Activity activity) {
                    if (null == ActivityManager.getInstance().getActivitys() && ActivityManager.getInstance().getActivitys().isEmpty()) {
                        return;
                    }
                    if (ActivityManager.getInstance().getActivitys().contains(activity)) {
                        /**  * 监听到 Activity销毁事件 将该Activity 从list中移除  */
                        ActivityManager.getInstance().popActivity(activity);
                    }
                }
            });
        }
    }

    static final class MyCrashCallback implements RecoveryCallback {
        @Override
        public void stackTrace(String exceptionMessage) {
            Logger.e("exceptionMessage:" + exceptionMessage);
        }

        @Override
        public void cause(String cause) {
            Logger.e("cause:" + cause);
        }

        @Override
        public void exception(String exceptionType, String throwClassName, String throwMethodName, int throwLineNumber) {
            Logger.e("exceptionClassName:" + exceptionType);
            Logger.e("throwClassName:" + throwClassName);
            Logger.e("throwMethodName:" + throwMethodName);
            Logger.e("throwLineNumber:" + throwLineNumber);
        }

        @Override
        public void throwable(Throwable throwable) {

        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    //android P 非SDK兼用提示限制
    private void closeAndroidPDialog() {
        try {
            Class aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //百度地图初始化
    private void initLocation() {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(getContext());
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }

}
