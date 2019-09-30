package com.yzf.king.adapter;


import android.support.v7.widget.CardView;
import android.view.View;

public interface CardAdapter {

    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    View getViewAt(int position);

    int getCount();
}
