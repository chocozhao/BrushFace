package com.yzf.king.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yzf.king.R;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetShareImgResult;

import java.io.File;
import java.util.List;

import cn.droidlover.xdroidmvp.imageloader.ILFactory;

/**
 * ClaseName：MyPagerAdapter
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/4/12 10:32
 * Modified By：
 * Fixtime：2019/4/12 10:32
 * FixDescription：
 */
public class MyPagerAdapter extends PagerAdapter {

    private List<GetShareImgResult.DataBean> mData;
    private Context mContext;
    private MyPagerAdapter.OnMyItemClickListener listener;

    public MyPagerAdapter(List<GetShareImgResult.DataBean> data, Context context) {
        mData = data;
        mContext = context;
    }

    public interface OnMyItemClickListener {
        void myClick(View v, GetShareImgResult.DataBean item);
    }

    public void setOnMyItemClickListener(MyPagerAdapter.OnMyItemClickListener listener) {
        this.listener = listener;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final GetShareImgResult.DataBean dataBean = mData.get(position);
        View inflate = LayoutInflater.from(container.getContext()).inflate(R.layout.cardviewpager_item, container, false);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.img_card_item);
        ILFactory.getLoader().loadImage(dataBean.getImgPath(), imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.myClick(view, dataBean);
                }
            }
        });
        container.addView(inflate);

        String rootPath = mContext.getExternalFilesDir("") + File.separator + "Pictures/";
        File rootFile = new File(rootPath);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }
        File file = null;
        String imgName = AppUser.getInstance().getMerchId() + dataBean.getImgName();
        if (!imgName.contains(".")) {
            imgName = imgName + ".jpg";
        }
        String filePath = rootPath + imgName;
        file = new File(filePath);
        if (!file.exists()) {
            ILFactory.getLoader().downloadImage(mContext, dataBean.getImgPath(), file);
        }
        return inflate;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    public GetShareImgResult.DataBean getItem(int position) {
        return mData.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(((View) object));
    }
}
