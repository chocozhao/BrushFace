package com.yzf.king.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yzf.king.R;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.utils.ImageUtil;
import com.yzf.king.kit.utils.fileUtill;
import com.yzf.king.model.CardItem;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.droidlover.xdroidmvp.cache.DiskCache;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.log.Logger;

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<View> views;
    private List<CardItem> mData;
    private float mBaseElevation;
    Context context;

    private CardPagerAdapter.OnMyItemClickListener listener;

    public void setOnMyItemClickListener(CardPagerAdapter.OnMyItemClickListener listener) {
        this.listener = listener;

    }

    public interface OnMyItemClickListener {
        void myClick(View v, CardItem item);
    }

    public CardPagerAdapter(Context context) {
        this.context = context;
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
        views = new ArrayList<>();
    }

    public void addCardItem(CardItem item) {
        mViews.add(null);
        views.add(null);
        mData.add(item);
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
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
                .inflate(R.layout.adapter, container, false);
        container.addView(view);
        bind(mData.get(position), view);
        ConstraintLayout cardView = (ConstraintLayout) view.findViewById(R.id.bgRl);
        views.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
        views.set(position, null);
    }

    private void bind(final CardItem item, View view) {
        ConstraintLayout bg = (ConstraintLayout) view.findViewById(R.id.bgRl);
        ImageView bgIv = (ImageView) view.findViewById(R.id.bgIv);
        ImageView imageView = (ImageView) view.findViewById(R.id.contentIv);
        bgIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.myClick(view, item);
                }
            }
        });
        if (item.getImageResource() > 0) {
            String imageName = AppUser.getInstance().getPhone() + item.getName() + Kits.Package.getVersionCode(context) + ".jpg";
            String value = DiskCache.getInstance(context).get(imageName);
            File file = null;
            if (value != null) {
                file = new File(value);
                if (file.exists()) {
                    ILFactory.getLoader().loadImageSizekipMemoryCache(file, bgIv);
                } else {
                    try {
                        bgIv.setBackgroundResource(item.getImageResource());
                        Resources res = context.getResources();
                        Bitmap bitmap = BitmapFactory.decodeResource(res, item.getImageResource());
                        int width = bitmap.getWidth();
                        int height = width * 31 / 100;
                        Bitmap waterBitmap = CodeUtils.createImage(item.getConetent(), height, height, BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
                        imageView.setImageBitmap(waterBitmap);

                        Bitmap watermarkBitmap = ImageUtil.viewToBitmap(bg);
                        ByteArrayOutputStream by = new ByteArrayOutputStream();
                        assert watermarkBitmap != null;
                        watermarkBitmap.compress(Bitmap.CompressFormat.JPEG, 90, by);
                        byte[] stream = by.toByteArray();
                        String rootPath = context.getExternalFilesDir("") + File.separator + "Pictures/";
                        file = fileUtill.write2Sdcard(stream, rootPath, imageName);
                        Logger.i("filePath=" + file.getAbsolutePath());
                        DiskCache.getInstance(context).put(imageName, file.getAbsolutePath(), 60 * 1000 * 90);

                        bgIv.setBackgroundResource(0);
                        imageView.setImageBitmap(null);
                        ILFactory.getLoader().loadImageSizekipMemoryCache(file, bgIv);
                    } catch (Exception e) {
                        bgIv.setBackgroundResource(item.getImageResource());
                        Logger.e(e.toString());
                    }
                }
            } else {
                Logger.i("图片缓存不存在");
                try {
                    bgIv.setBackgroundResource(item.getImageResource());
                    Resources res = context.getResources();
                    Bitmap bitmap = BitmapFactory.decodeResource(res, item.getImageResource());
                    int width = bitmap.getWidth();
                    int height = width * 31/ 100;
                    Bitmap waterBitmap = CodeUtils.createImage(item.getConetent(), height, height, BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
                    imageView.setImageBitmap(waterBitmap);

                    Bitmap watermarkBitmap = ImageUtil.viewToBitmap(bg);
                    ByteArrayOutputStream by = new ByteArrayOutputStream();
                    assert watermarkBitmap != null;
                    watermarkBitmap.compress(Bitmap.CompressFormat.JPEG, 90, by);
                    byte[] stream = by.toByteArray();
                    String rootPath = context.getExternalFilesDir("") + File.separator + "Pictures/";
                    file = fileUtill.write2Sdcard(stream, rootPath, imageName);
                    Logger.i("filePath=" + file.getAbsolutePath());
                    DiskCache.getInstance(context).put(imageName, file.getAbsolutePath(), 60 * 1000 * 90);
                    bgIv.setBackgroundResource(0);
                    imageView.setImageBitmap(null);
                    ILFactory.getLoader().loadImageSizekipMemoryCache(file, bgIv);
                } catch (Exception e) {
                    bgIv.setBackgroundResource(item.getImageResource());
                   Logger.e(e.toString());
                }
            }
        }
        if (item.getUrl() != null) {
            ILFactory.getLoader().loadImageSizekipMemoryCache(item.getUrl(), bgIv);
        }
    }

}
