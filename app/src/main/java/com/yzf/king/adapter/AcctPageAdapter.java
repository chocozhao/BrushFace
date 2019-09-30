package com.yzf.king.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.model.CardItem;
import com.yzf.king.model.servicesmodels.GetAcctInfoResult;
import com.yzf.king.widget.StateButton;

import java.util.ArrayList;
import java.util.List;

public class AcctPageAdapter extends PagerAdapter implements CardAdapter {
    private List<View> views;
    private List<CardItem> mData;
    private float mBaseElevation;

    public static final int WITHDRAW = 0;
    public static final int PROFIT = 1;

    private AcctPageAdapter.OnMyItemClickListener listener;

    public void setOnMyItemClickListener(AcctPageAdapter.OnMyItemClickListener listener) {
        this.listener = listener;

    }

    public interface OnMyItemClickListener {
        void myClick(View v, CardItem item, int tag);
    }

    public AcctPageAdapter() {
        mData = new ArrayList<>();
        views = new ArrayList<>();
    }

    public void addCardItem(CardItem item) {
        views.add(null);
        mData.add(item);
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return null;
    }


    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public View getViewAt(int position) {
        return views.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.adapter_account, container, false);
        container.addView(view);
        bind(mData.get(position), view);
        LinearLayout cardView = (LinearLayout) view.findViewById(R.id.profit_ll);
        views.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        views.set(position, null);
    }

    private void bind(final CardItem item, View view) {
        GetAcctInfoResult.DataBeanX.DataBean dataBean = (GetAcctInfoResult.DataBeanX.DataBean) item.getData();
        TextView profitTypeTv = (TextView) view.findViewById(R.id.profit_type_tv);
        TextView profitWithdrawTv = (TextView) view.findViewById(R.id.profit_withdraw_tv);
        StateButton profitWithdrawBt = (StateButton) view.findViewById(R.id.profit_withdraw_bt);
        Button profitProfitBt = (Button) view.findViewById(R.id.profit_profit_bt);
        TextView profitAvlTv = (TextView) view.findViewById(R.id.profit_avl_tv);
        TextView profitFznTv = (TextView) view.findViewById(R.id.profit_fzn_tv);
        TextView profitTotalTv = (TextView) view.findViewById(R.id.profit_total_tv);
        if ("01".equals(dataBean.getAcctType())) {
            profitTypeTv.setText("会员分润");
        }
        if ("03".equals(dataBean.getAcctType())) {
            profitTypeTv.setText("商户分润");
        }
        profitWithdrawTv.setText(dataBean.getAvlbAmt());
        profitAvlTv.setText(dataBean.getAvlbAmt());
        profitFznTv.setText(dataBean.getFrozenAmt());
        profitTotalTv.setText(dataBean.getTotalAmt());
        profitWithdrawBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.myClick(view, item, WITHDRAW);
                }

            }
        });
        profitProfitBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.myClick(view, item, PROFIT);
                }
            }
        });
    }

}
