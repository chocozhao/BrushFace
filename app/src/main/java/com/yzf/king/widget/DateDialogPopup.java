package com.yzf.king.widget;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.widget.CalendarView.CalendarView;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;

/**
 * ClaseName：DateDialogPopup
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/9/6 20:30
 * Modified By：
 * Fixtime：2017/9/6 20:30
 * FixDescription：
 */

public class DateDialogPopup extends BasePopupWindow implements View.OnClickListener {

    private static DateDialogPopup.IPopupCallBack iPopupCallBack;
    private CalendarView mCalendarView;
    private TextView mTextSelectMonth;
    private ImageButton mLastMonthView;
    private ImageButton mNextMonthView;
    private List<String> mDatas = new ArrayList<>();

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_select_last_month:
                mCalendarView.setLastMonth();
                mTextSelectMonth.setText(mCalendarView.getDate());
                break;
            case R.id.img_select_next_month:
                mCalendarView.setNextMonth();
                mTextSelectMonth.setText(mCalendarView.getDate());
                break;
        }
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.datepopup);
    }

    public interface IPopupCallBack {
        void sent(List<String> list);
    }

    public static void setmPopupCallBack(DateDialogPopup.IPopupCallBack iPopupCallBack) {
        DateDialogPopup.iPopupCallBack = iPopupCallBack;
    }

    public DateDialogPopup(final Activity context, List<String> list) {
        super(context);
        mTextSelectMonth = (TextView) findViewById(R.id.txt_select_month);
        mLastMonthView = (ImageButton) findViewById(R.id.img_select_last_month);
        mNextMonthView = (ImageButton) findViewById(R.id.img_select_next_month);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mLastMonthView.setOnClickListener(this);
        mNextMonthView.setOnClickListener(this);
        mCalendarView.setOptionalDate(list);
        mTextSelectMonth.setText(mCalendarView.getDate());
        // 设置点击事件
        mCalendarView.setOnClickDate(new CalendarView.OnClickListener() {
            @Override
            public void onClickDateListener(int year, int month, int day) {
                // 获取已选择日期
                List<String> dates = mCalendarView.getSelectedDates();
                iPopupCallBack.sent(dates);
            }
        });
    }

    @Override
    protected Animation onCreateShowAnimation() {
        return getDefaultScaleAnimation(true);
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return getDefaultScaleAnimation(false);
    }

}