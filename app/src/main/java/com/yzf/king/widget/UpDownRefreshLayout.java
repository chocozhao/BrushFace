package com.yzf.king.widget;

/**
 * ClaseName：UpDownRefreshLayout
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/4/18 10:51
 * Modified By：
 * Fixtime：2019/4/18 10:51
 * FixDescription：
 */

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * 自定义上拉下拉的控件
 */
public class UpDownRefreshLayout extends SwipeRefreshLayout {

    private int   mTouchSlop; //触发移动事件的最短距离
    private int   mDownY;
    private int   mDownX;

    public UpDownRefreshLayout(Context context) {
        super(context);
        initRefreshLayout(context);
    }

    public UpDownRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initRefreshLayout(context);
    }

    /**
     * 初始化刷新控件
     */
    private void initRefreshLayout(Context pContext) {
        //得到触发移动事件的最短距离
        mTouchSlop = ViewConfiguration.get(pContext).getScaledTouchSlop();

        //其他内容省略
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        int _action = ev.getAction();
        switch (_action) {
            case MotionEvent.ACTION_DOWN:
                mDownY = (int) ev.getY();
                mDownX = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int curX = (int) ev.getX();
                int curY = (int) ev.getY();
                int absX = Math.abs(curX - mDownX);
                int absY = Math.abs(curY - mDownY);

                if (absX < absY) {      //如果垂直移动
                    //执行你想要的操作，
                } else if (absX > absY) {   //左右划的时候不拦截，触摸事件直接交给子View处理
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
                //内容省略
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
