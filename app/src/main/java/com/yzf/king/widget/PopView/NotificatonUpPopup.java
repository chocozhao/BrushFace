package com.yzf.king.widget.PopView;


import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.CheckVersionResults;
import com.yzf.king.widget.StateButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ClaseName：NotificatonUpPopup
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/27 19:22
 * Modified By：
 * Fixtime：2019/5/27 19:22
 * FixDescription：
 **/

public class NotificatonUpPopup extends Dialog {

    private int res;

    public NotificatonUpPopup(@NonNull Context context, int res) {
        super(context);
        this.res = res;
        setContentView(res);
        setCanceledOnTouchOutside(false);
    }

    public void showDialog() {
        Window window = getWindow();
        //设置弹窗动画
        window.setWindowAnimations(R.style.pop_notificaton);
        //设置Dialog背景色
        window.setBackgroundDrawableResource(R.color.colorTransparent);
        WindowManager.LayoutParams wl = window.getAttributes();
        //设置弹窗位置
        wl.gravity = Gravity.CENTER;
        window.setAttributes(wl);
        show();
    }
}
