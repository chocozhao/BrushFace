package com.yzf.king.kit;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import cn.droidlover.xdroidmvp.log.Logger;

/**
 * ClaseName：ActivityManager
 * Description：Activity管理类
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/4/10 11:52
 * Modified By：
 * Fixtime：2017/4/10 11:52
 * FixDescription：
 */

public class ActivityManager {


    /**
     * ActivityManager
     */
    private static ActivityManager MANAGER = null;

    /**
     * 维护Activity的list
     */
    private List<Activity> mActivitys = null;

    public List<Activity> getActivitys() {
        return mActivitys;
    }

    /**
     * Get Instance  *  * @return ActivityManager
     */
    public static ActivityManager getInstance() {
        if (MANAGER == null) {
            MANAGER = new ActivityManager();
        }
        return MANAGER;
    }

    private ActivityManager() {
        mActivitys = Collections.synchronizedList(new LinkedList<Activity>());
    }

    /**
     * @param activity 作用说明 ：添加一个activity到管理里
     */
    public void pushActivity(Activity activity) {
        mActivitys.add(activity);
        Logger.d("activityList:size:" + mActivitys.size());
    }

    /**
     * @param activity 作用说明 ：删除一个activity在管理里
     */
    public void popActivity(Activity activity) {
        mActivitys.remove(activity);
        Logger.d("activityList:size:" + mActivitys.size());
    }

    /**
     * get current Activity 获取当前Activity（栈中最后一个压入的）
     */
    public Activity currentActivity() {
        if (mActivitys == null || mActivitys.isEmpty()) {
            return null;
        }
        Activity activity = mActivitys.get(mActivitys.size() - 1);
        return activity;
    }

    /**
     * 结束当前Activity（栈中最后一个压入的）
     */
    public void finishCurrentActivity() {
        if (mActivitys == null || mActivitys.isEmpty()) {
            return;
        }
        Activity activity = mActivitys.get(mActivitys.size() - 1);
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (mActivitys == null || mActivitys.isEmpty()) {
            return;
        }
        if (activity != null) {
            mActivitys.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        if (mActivitys == null || mActivitys.isEmpty()) {
            return;
        }
        List<Activity> activities = new ArrayList<>();
        for (Activity activity : mActivitys) {
            if (activity.getClass().equals(cls)) {
                activities.add(activity);
            }
        }
        // 结束所有类名相同activity
        for (Activity activity : activities) {
            finishActivity(activity);
        }
//        mActivitys.removeAll(activities);
    }

    /**
     * 按照指定类名找到activity
     *
     * @param cls
     * @return
     */
    public Activity findActivity(Class<?> cls) {
        Activity targetActivity = null;
        if (mActivitys != null) {
            for (Activity activity : mActivitys) {
                if (activity.getClass().equals(cls)) {
                    targetActivity = activity;
                    break;
                }
            }
        }
        return targetActivity;
    }

    /**
     * @return 作用说明 ：获取当前最顶部activity的实例
     */
    public Activity getTopActivity() {
        Activity mBaseActivity;
        synchronized (mActivitys) {
            final int size = mActivitys.size() - 1;
            if (size < 0) {
                return null;
            }
            mBaseActivity = mActivitys.get(size);
        }
        return mBaseActivity;

    }

    /**
     * @return 作用说明 ：获取当前最顶部的acitivity 名字
     */
    public String getTopActivityName() {
        Activity mBaseActivity;
        synchronized (mActivitys) {
            final int size = mActivitys.size() - 1;
            if (size < 0) {
                return null;
            }
            mBaseActivity = mActivitys.get(size);
        }
        return mBaseActivity.getClass().getName();
    }



    /**
     * 结束除cls之外的所有activity
     *
     * @param cls
     */
    public void finishAllActivityExceptOne(Class<? extends Activity> cls) {
        if (mActivitys == null || mActivitys.isEmpty()) {
            return;
        }
        List<Activity> activities = new ArrayList<>();
        for (Activity activity : mActivitys) {
            if (!activity.getClass().equals(cls)) {
                activities.add(activity);
            }
        }
        // 结束所有类名相同activity
        for (Activity activity : activities) {
            finishActivity(activity);
        }
//        mActivitys.removeAll(activities);
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        if (mActivitys == null) {
            return;
        }
        for (Activity activity : mActivitys) {
            activity.finish();
        }
        mActivitys.clear();
    }

    /**
     * 退出应用程序
     */
    public void appExit() {
        try {
            Logger.e("app exit");
            finishAllActivity();
//            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 强行退出程序(异常)
     */
    public void appExceptionExit() {
        try {
            Logger.e("app exception exit");
            finishAllActivity();

            // 退出程序
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
