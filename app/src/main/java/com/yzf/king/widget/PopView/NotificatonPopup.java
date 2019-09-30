package com.yzf.king.widget.PopView;


import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lxj.xpopup.animator.PopupAnimator;
import com.lxj.xpopup.core.CenterPopupView;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetMerchInfoResult;

/**
 * ClaseName：NotificatonPopup
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/29 09:33
 * Modified By：
 * Fixtime：2019/5/29 09:33
 * FixDescription：
 **/

public class NotificatonPopup extends Dialog {

    private int res;

    public NotificatonPopup(@NonNull Context context, int res) {
        super(context);
        this.res = res;
        setContentView(res);
        setCanceledOnTouchOutside(true);
    }

    public NotificatonPopup(Context context) {
        super(context);

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
