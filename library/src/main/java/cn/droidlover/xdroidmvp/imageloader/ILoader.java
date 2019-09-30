package cn.droidlover.xdroidmvp.imageloader;

import android.content.Context;
import android.widget.ImageView;

import java.io.File;

import cn.droidlover.xdroidmvp.XDroidConf;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * ClaseName：${NAME}
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2016/11/27 10:32
 * Modified By：
 * Fixtime：2016/11/27 10:32
 * FixDescription：
 */

public interface ILoader {

    void init(Context context);

    <T> void loadImage(T url, ImageView imageView);

    <T> void loadImageSize(T url, ImageView imageView, int width, int height);

    <T> void loadImageSizekipMemoryCache(T url, ImageView imageView);

    <T> void loadCircleImage(T url, ImageView imageView);

    <T> void preloadImage(T url);

    <T> void loadRoundCircleImage(T url, ImageView imageView,int radius);

    <T> void loadCustRoundCircleImage(T url, ImageView imageView,int radius, RoundedCornersTransformation.CornerType type);

    <T> void loadBlurImage(T url, ImageView imageView, int blur);

    <T> void loadBlackImage(T url, ImageView imageView);

    <T> void loadGif(T url, ImageView imageView);

    <T> void downloadImage(Context context,T url);

    <T> void downloadImage(Context context,T url,File target);


}
