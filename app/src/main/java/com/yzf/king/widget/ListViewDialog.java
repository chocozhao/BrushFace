package com.yzf.king.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetCardInfoResult;

import java.util.List;

import cn.droidlover.xdroidmvp.imageloader.ILFactory;

/**
 * ClaseName：ListViewDialog
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/3/7 9:38
 * Modified By：
 * Fixtime：2017/3/7 9:38
 * FixDescription：
 */

public class ListViewDialog extends Dialog {

    private static ICardNoCallBack mCardNoCallBack;
    private final Context mContext;
    private ListView mListView;
    private TextView title;
    private List<GetCardInfoResult.DataBean> datalist;
    private Xdapter adapter;
    private String cardNo;

    public ListViewDialog(Context context, List<GetCardInfoResult.DataBean> datalist) {
        super(context);
        mContext = context;
        this.datalist = datalist;
        initView();
        initListView();
    }

    public interface ICardNoCallBack {
        void getCardno(String cardNo);

        void getCardBean(GetCardInfoResult.DataBean dataBean);
    }

    public static void setmCardNOCallBack(ICardNoCallBack mCardNoCallBack) {
        ListViewDialog.mCardNoCallBack = mCardNoCallBack;
    }

    public void setTitle(String name) {
        if (!AppTools.isEmpty(name)) {
            title.setText(name);
        }
    }

    private void initView() {
        View contentView = View.inflate(mContext, R.layout.content_dialog, null);
        mListView = (ListView) contentView.findViewById(R.id.content_dialog_lv);
        title = (TextView) contentView.findViewById(R.id.content_dialog_title);
        setContentView(contentView);
    }

    private void initListView() {
        adapter = new ListViewDialog.Xdapter(mContext, datalist);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus) {
            return;
        }
        setHeight();
    }

    private void setHeight() {
        Window window = getWindow();
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        WindowManager.LayoutParams attributes = window != null ? window.getAttributes() : null;
        if (window.getDecorView().getHeight() >= (int) (displayMetrics.heightPixels * 0.6)) {
            attributes.height = (int) (displayMetrics.heightPixels * 0.6);
        }
       /* window.setGravity(Gravity.BOTTOM);
        window.setBackgroundDrawableResource(android.R.color.background_light);*/
        window.setAttributes(attributes);
    }

    public class Xdapter extends BaseAdapter {
        private Context context;
        private List<GetCardInfoResult.DataBean> mData;

        public Xdapter(Context context, List<GetCardInfoResult.DataBean> mData) {
            this.context = context;
            this.mData = mData;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            if (mData != null) {
                return mData.size();
            }
            return 0;

        }

        private void setData(List<GetCardInfoResult.DataBean> mData) {
            this.mData = mData;
            notifyDataSetChanged();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ListViewDialog.Xdapter.ViewHolder mHolder;
            if (null == convertView) {
                convertView = LayoutInflater.from(context).inflate(R.layout.whitecardno_list_item, null);
                mHolder = new ListViewDialog.Xdapter.ViewHolder();
                mHolder.tx = (TextView) convertView.findViewById(R.id.cardNo_tx);
                mHolder.iv = (ImageView) convertView.findViewById(R.id.cardNo_iv);
                mHolder.whitecardno_ll = (LinearLayout) convertView.findViewById(R.id.whitecardno_ll);
                convertView.setTag(mHolder);
            } else {
                mHolder = (ListViewDialog.Xdapter.ViewHolder) convertView.getTag();
            }
            GetCardInfoResult.DataBean dataBean = mData.get(position);
            mHolder.tx.setText(dataBean.getBankName() + "(" + dataBean.getCardId().substring(dataBean.getCardId().length() - 4, dataBean.getCardId().length()) + ")");
            int id = getImgId("b" + mData.get(position).getBankCode());
            if (id > 0) {
                ILFactory.getLoader().loadCircleImage(id,mHolder.iv);
            }else {
                ILFactory.getLoader().loadCircleImage(getImgId("bank"),mHolder.iv);
            }
            mHolder.whitecardno_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardNo = mData.get(position).getCardId();
                    mCardNoCallBack.getCardno(cardNo);
                    mCardNoCallBack.getCardBean(mData.get(position));
                }
            });
            return convertView;
        }

        class ViewHolder {
            private TextView tx;
            private ImageView iv;
            private LinearLayout whitecardno_ll;
        }

    }


    private int getImgId(String imgName) {
        int id = -1;
        try {
            id = mContext.getResources().getIdentifier(imgName, "mipmap", mContext.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
}
