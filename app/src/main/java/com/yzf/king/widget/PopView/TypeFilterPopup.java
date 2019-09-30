package com.yzf.king.widget.PopView;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.lxj.xpopup.impl.PartShadowPopupView;
import com.yzf.king.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ClaseName：CustomPartShadowPopupView
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/26 17:26
 * Modified By：
 * Fixtime：2019/7/26 17:26
 * FixDescription：
 **/

public class TypeFilterPopup extends PartShadowPopupView {


    private String type;


    public TypeFilterPopup(@NonNull Context context) {
        super(context);
    }

    private static CheckCallBack iCheckCallBack;


    public interface CheckCallBack {
        void TypeFilterPopupSent(String type);
    }

    public static void setCallback(CheckCallBack callBack) {
        iCheckCallBack = callBack;
    }


    @Override
    protected int getImplLayoutId() {
        return R.layout.pop_shop_around;
    }

    TextView pop_shop_around_hotel;
    TextView pop_shop_around_restaurant;
    TextView pop_shop_around_attractions;
    TextView pop_shop_around_hospital;
    TextView pop_shop_around_supermarket;
    TextView pop_shop_around_other;

    @Override
    protected void onCreate() {
        super.onCreate();
        pop_shop_around_hotel = findViewById(R.id.pop_shop_around_hotel);
        pop_shop_around_restaurant = findViewById(R.id.pop_shop_around_restaurant);
        pop_shop_around_attractions = findViewById(R.id.pop_shop_around_attractions);
        pop_shop_around_hospital = findViewById(R.id.pop_shop_around_hospital);
        pop_shop_around_supermarket = findViewById(R.id.pop_shop_around_supermarket);
        pop_shop_around_other = findViewById(R.id.pop_shop_around_other);
        findViewById(R.id.pop_shop_around_hotel).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                type = (String) pop_shop_around_hotel.getText();
                iCheckCallBack.TypeFilterPopupSent(type);
                dismiss();
            }
        });
        findViewById(R.id.pop_shop_around_restaurant).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                type = (String) pop_shop_around_restaurant.getText();
                iCheckCallBack.TypeFilterPopupSent(type);
                dismiss();
            }
        });
        findViewById(R.id.pop_shop_around_attractions).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                type = (String) pop_shop_around_attractions.getText();
                iCheckCallBack.TypeFilterPopupSent(type);
                dismiss();
            }
        });
        findViewById(R.id.pop_shop_around_hospital).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                type = (String) pop_shop_around_hospital.getText();
                iCheckCallBack.TypeFilterPopupSent(type);
                dismiss();
            }
        });
        findViewById(R.id.pop_shop_around_supermarket).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                type = (String) pop_shop_around_supermarket.getText();
                iCheckCallBack.TypeFilterPopupSent(type);
                dismiss();
            }
        });
        findViewById(R.id.pop_shop_around_other).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                type = (String) pop_shop_around_other.getText();
                iCheckCallBack.TypeFilterPopupSent(type);
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
