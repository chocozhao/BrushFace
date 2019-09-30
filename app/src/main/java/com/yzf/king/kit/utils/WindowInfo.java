package com.yzf.king.kit.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 此类用于显示屏幕信息
 */
public class WindowInfo {
    private Context context = null;
    private WindowManager wm = null;
    private Rect rect = null;

    public WindowInfo(Context context) {
        this.context = context;
        this.wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
    }

    /**
     * 屏幕宽度
     */
    public int getWidth() {
        return wm.getDefaultDisplay().getWidth();
    }

    /**
     * 屏幕高度
     */
    public int getHeight() {
        return wm.getDefaultDisplay().getHeight();
    }

    /**
     * 状态栏高度
     */
    public int getStateBarHeight() {
        rect = new Rect();
        ((Activity) context).getWindow().getDecorView()
                .getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    /**
     * 标题栏高度(系统标题栏)
     */
    public int getTitleBarHeight() {
        rect = new Rect();
        ((Activity) context).getWindow().getDecorView()
                .getWindowVisibleDisplayFrame(rect);
        View view = ((Activity) context).getWindow().findViewById(
                Window.ID_ANDROID_CONTENT);
        return rect.height() - view.getHeight();
    }
}
