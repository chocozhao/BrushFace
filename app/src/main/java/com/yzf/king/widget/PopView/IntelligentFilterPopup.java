package com.yzf.king.widget.PopView;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.lxj.xpopup.impl.PartShadowPopupView;
import com.yzf.king.R;

import cn.droidlover.xdroidmvp.log.Logger;

/**
 * ClaseName：IntelligentFilterPopup
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/29 16:24
 * Modified By：
 * Fixtime：2019/7/29 16:24
 * FixDescription：
 **/

public class IntelligentFilterPopup extends PartShadowPopupView {


    private String intelligentType;
    private String type;

    public IntelligentFilterPopup(@NonNull Context context) {
        super(context);
    }

    private static CheckCallBack iCheckCallBack;


    public interface CheckCallBack {
        void IntelligentFilterPopupSent(String intelligentType,String type);
    }

    public static void setCallback(CheckCallBack callBack) {
        iCheckCallBack = callBack;
    }


    @Override
    protected int getImplLayoutId() {
        return R.layout.pop_shop_around_filter;
    }

    TextView pop_shop_around_sort;
    TextView pop_shop_around_recent;
    TextView pop_shop_around_highest;


    @Override
    protected void onCreate() {
        super.onCreate();
        pop_shop_around_sort = findViewById(R.id.pop_shop_around_sort);
        pop_shop_around_recent = findViewById(R.id.pop_shop_around_recent);
        pop_shop_around_highest = findViewById(R.id.pop_shop_around_highest);
        findViewById(R.id.pop_shop_around_sort).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "1";
                intelligentType = (String) pop_shop_around_sort.getText();
                iCheckCallBack.IntelligentFilterPopupSent(intelligentType,type);
                dismiss();
            }
        });
        findViewById(R.id.pop_shop_around_recent).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "distance";
                intelligentType = (String) pop_shop_around_recent.getText();
                iCheckCallBack.IntelligentFilterPopupSent(intelligentType,type);
                dismiss();
            }
        });
        findViewById(R.id.pop_shop_around_highest).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "price";
                intelligentType = (String) pop_shop_around_highest.getText();
                iCheckCallBack.IntelligentFilterPopupSent(intelligentType,type);
                dismiss();
            }
        });

    }

    @Override
    protected void onShow() {
        super.onShow();
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
    }
}
