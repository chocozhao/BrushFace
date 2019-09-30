package com.yzf.king.widget.TitlePopupViews;


/**
 * ClaseName：TitlePopup
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/9/24 16:14
 * Modified By：
 * Fixtime：2019/9/24 16:14
 * FixDescription：
 **/

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yzf.king.R;

import java.util.ArrayList;

/**
 * @author yangyu
 * 功能描述：标题按钮上的弹窗（继承自PopupWindow）
 */
public class TitlePopup extends PopupWindow {
    private Context mContext;

    //列表弹窗的间隔
    protected final int LIST_PADDING = 16;

    //实例化一个矩形
    private Rect mRect = new Rect();

    //坐标的位置（x、y）
    private final int[] mLocation = new int[2];

    //屏幕的宽度和高度
    private int mScreenWidth;
    private int mScreenHeight;

    //判断是否需要添加或更新列表子类项
    private boolean mIsDirty;

    //位置不在中心
    private int popupGravity = Gravity.NO_GRAVITY;

    //弹窗子类项选中时的监听
    private OnItemOnClickListener mItemOnClickListener;

    //定义列表对象
    private ListView mListView;

    //定义弹窗子类项列表
    private ArrayList<ActionItem> mActionItems = new ArrayList<ActionItem>();

    public TitlePopup(Context context) {
        //设置布局的参数
        this(context, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    public TitlePopup(Context context, int width, int height) {
        this.mContext = context;

        //设置可以获得焦点
        setFocusable(true);
        //设置弹窗内可点击
        setTouchable(true);
        //设置弹窗外可点击
        setOutsideTouchable(true);

        //获得屏幕的宽度和高度
        mScreenWidth = getScreenWidth(mContext);
        mScreenHeight = getScreenHeight(mContext);

        //设置弹窗的宽度和高度
        setWidth(width);
        setHeight(height);
        setBackgroundDrawable(new BitmapDrawable());

        //设置弹窗的布局界面
        setContentView(LayoutInflater.from(mContext).inflate(R.layout.popup_title, null));

        initUI();
    }

    /**
     * 初始化弹窗列表
     */
    private void initUI() {
        mListView = (ListView) getContentView().findViewById(R.id.title_list);

        mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
                //点击子类项后，弹窗消失
                dismiss();

                if (mItemOnClickListener != null) {
                    mItemOnClickListener.onItemClick(mActionItems.get(index), index);
                }
            }
        });
    }

    /**
     * 显示弹窗列表界面
     */
    public void show(View view) {
        //获得点击屏幕的位置坐标
        view.getLocationOnScreen(mLocation);

        //设置矩形的大小
        mRect.set(mLocation[0], mLocation[1], mLocation[0] + view.getWidth(), mLocation[1] + view.getHeight());

        //判断是否需要添加或更新列表子类项
        if (mIsDirty) {
            populateActions();
        }

        //显示弹窗的位置
        showAtLocation(view, popupGravity, mScreenWidth - LIST_PADDING - (getWidth() / 2), mRect.bottom);

    }

    /**
     * 设置弹窗列表子项
     */
    private void populateActions() {
        mIsDirty = false;

        //设置列表的适配器
        mListView.setAdapter(new BaseAdapter() {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = null;

                if (convertView == null) {
                    textView = new TextView(mContext);
                    textView.setTextColor(mContext.getResources().getColor(R.color.home_title_gry));
                    textView.setTextSize(16);
                    //设置文本居中
                    textView.setGravity(Gravity.CENTER);
                    //设置文本域的范围
                    textView.setPadding(0, 10, 0, 10);
                    //设置文本在一行内显示（不换行）
                    textView.setSingleLine(true);
                } else {
                    textView = (TextView) convertView;
                }

                ActionItem item = mActionItems.get(position);

                //设置文本文字
                textView.setText(item.mTitle);
                //设置文字与图标的间隔
                textView.setCompoundDrawablePadding(10);
                //设置在文字的左边放一个图标
                textView.setCompoundDrawablesWithIntrinsicBounds(item.mDrawable, null, null, null);

                return textView;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public Object getItem(int position) {
                return mActionItems.get(position);
            }

            @Override
            public int getCount() {
                return mActionItems.size();
            }
        });
    }


    /**
     * 设置页面的透明度
     * @param bgAlpha 1表示不透明
     */
    public  void setBackgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        if (bgAlpha == 1) {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//此行代码主要是解决在华为手机上半透明效果无效的bug
        }
        activity.getWindow().setAttributes(lp);

    }

    /**
     * 添加子类项
     */
    public void addAction(ActionItem action) {
        if (action != null) {
            mActionItems.add(action);
            mIsDirty = true;
        }
    }

    /**
     * 清除子类项
     */
    public void cleanAction() {
        if (mActionItems.isEmpty()) {
            mActionItems.clear();
            mIsDirty = true;
        }
    }

    /**
     * 根据位置得到子类项
     */
    public ActionItem getAction(int position) {
        if (position < 0 || position > mActionItems.size()) {
            return null;
        }
        return mActionItems.get(position);
    }

    /**
     * 设置监听事件
     */
    public void setItemOnClickListener(OnItemOnClickListener onItemOnClickListener) {
        this.mItemOnClickListener = onItemOnClickListener;
    }


    /**
     * @author yangyu
     * 功能描述：弹窗子类项按钮监听事件
     */
    public static interface OnItemOnClickListener {
        public void onItemClick(ActionItem item, int position);
    }

    /**
     * 得到设备屏幕的宽度
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 得到设备屏幕的高度
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 得到设备的密度
     */
    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * 把密度转换为像素
     */
    public static int dip2px(Context context, float px) {
        final float scale = getScreenDensity(context);
        return (int) (px * scale + 0.5);
    }
}