package com.yzf.king.widget;

import android.app.Dialog;
import android.content.Context;

/**
 * ClaseName：BaseDialog
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/5/28 16:17
 * Modified By：
 * Fixtime：2019/5/28 16:17
 * FixDescription：
 */
public class BaseDialog extends Dialog {
    private int res;

    public BaseDialog(Context context, int theme, int res) {
        super(context, theme);
        // TODO 自动生成的构造函数存根
        setContentView(res);
        this.res = res;
        setCanceledOnTouchOutside(false);
    }

}