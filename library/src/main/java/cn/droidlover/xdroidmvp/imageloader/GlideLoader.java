package cn.droidlover.xdroidmvp.imageloader;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.qiyukf.unicorn.api.ImageLoaderListener;
import com.qiyukf.unicorn.api.UnicornImageLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.droidlover.xdroidmvp.R;
import cn.droidlover.xdroidmvp.log.Logger;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * ClaseName：${NAME}
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2016/11/28 10:32
 * Modified By：
 * Fixtime：2016/11/28 10:32
 * FixDescription：
 */

public class GlideLoader implements ILoader, UnicornImageLoader {
    private Context context;

    @Override
    public void init(Context context) {
        this.context = context.getApplicationContext();

    }

    public final int placeholderSoWhite = R.color.ysf_white;
    public final int errorSoWhite = R.mipmap.empty_photo;

    /*
     *加载图片(默认)
     */
    @Override
    public <T> void loadImage(T url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .fitCenter()
//                .placeholder(placeholderSoWhite) //占位图
                .error(errorSoWhite)       //错误图
                // .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Activity activity = getActivityFromView(imageView);
        if (activity != null && !activity.isFinishing()) {
            Glide.with(activity).load(url).apply(options).into(imageView);
        } else if (context != null) {
            Glide.with(context).load(url).apply(options).into(imageView);
        }
    }

    /**
     * 指定图片大小;使用override()方法指定了一个图片的尺寸。
     * Glide现在只会将图片加载成width*height像素的尺寸，而不会管你的ImageView的大小是多少了。
     * 如果你想加载一张图片的原始尺寸的话，可以使用Target.SIZE_ORIGINAL关键字----override(Target.SIZE_ORIGINAL)
     *
     * @param url
     * @param imageView
     * @param width
     * @param height
     */
    @Override
    public <T> void loadImageSize(T url, ImageView imageView, int width, int height) {
        RequestOptions options = new RequestOptions()
                .fitCenter()
//                .placeholder(placeholderSoWhite) //占位图
                .error(errorSoWhite)       //错误图
                .override(width, height)
                // .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Activity activity = getActivityFromView(imageView);
        if (activity != null && !activity.isFinishing()) {
            Glide.with(activity).load(url).apply(options).into(imageView);
        }  else if (context != null) {
            Glide.with(context).load(url).apply(options).into(imageView);
        }
    }


    /**
     * 禁用内存缓存功能
     * diskCacheStrategy()方法基本上就是Glide硬盘缓存功能的一切，它可以接收五种参数：
     * <p>
     * DiskCacheStrategy.NONE： 表示不缓存任何内容。
     * DiskCacheStrategy.DATA： 表示只缓存原始图片。
     * DiskCacheStrategy.RESOURCE： 表示只缓存转换过后的图片。
     * DiskCacheStrategy.ALL ： 表示既缓存原始图片，也缓存转换过后的图片。
     * DiskCacheStrategy.AUTOMATIC： 表示让Glide根据图片资源智能地选择使用哪一种缓存策略（默认选项）。
     */

    @Override
    public <T> void loadImageSizekipMemoryCache(T url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .error(errorSoWhite)       //错误图S
                .skipMemoryCache(true)//禁用掉Glide的内存缓存功能
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Activity activity = getActivityFromView(imageView);
        if (activity != null && !activity.isFinishing()) {
            Glide.with(activity).load(url).apply(options).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    Logger.i(e.toString());
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    return false;
                }
            }).into(imageView);
        } else {
            if (context != null) {
                Glide.with(context).load(url).apply(options).into(imageView);
            }
        }

    }


    /**
     * 加载圆形图片
     */
    @Override
    public <T> void loadCircleImage(T url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .circleCrop()//设置圆形
//                .placeholder(placeholderSoWhite)
                .error(errorSoWhite)
                //.priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Activity activity = getActivityFromView(imageView);
        if (activity != null && !activity.isFinishing()) {
            Glide.with(activity).load(url).apply(options).into(imageView);
        }  else if (context != null) {
            Glide.with(context).load(url).apply(options).into(imageView);
        }
    }

    /**
     * 预先加载图片
     * 在使用图片之前，预先把图片加载到缓存，调用了预加载之后，我们以后想再去加载这张图片就会非常快了，
     * 因为Glide会直接从缓存当中去读取图片并显示出来
     */
    @Override
    public <T> void preloadImage(T url) {
        Glide.with(context)
                .load(url)
                .preload();

    }

    /**
     * 加载圆角图片
     */
    @Override
    public <T> void loadRoundCircleImage(T url, ImageView imageView, int radius) {
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .circleCrop()//设置圆形
//                .placeholder(placeholderSoWhite)
                .error(errorSoWhite)
                //.priority(Priority.HIGH)
                .skipMemoryCache(true)
                .bitmapTransform(new RoundedCornersTransformation(radius, 0, RoundedCornersTransformation.CornerType.ALL))
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Activity activity = getActivityFromView(imageView);
        if (activity != null && !activity.isFinishing()) {
            Glide.with(activity).load(url).apply(options).into(imageView);
        }  else if (context != null) {
            Glide.with(context).load(url).apply(options).into(imageView);
        }

    }


    /**
     * 加载圆角图片-指定任意部分圆角（图片上、下、左、右四个角度任意定义）
     *
     * @param url
     * @param imageView
     * @param type
     */
    @Override
    public <T> void loadCustRoundCircleImage(T url, ImageView imageView, int radius, RoundedCornersTransformation.CornerType type) {
        RequestOptions options = new RequestOptions()
                .fitCenter()
//                .placeholder(placeholderSoWhite)
                .error(errorSoWhite)
                //.priority(Priority.HIGH)
                .skipMemoryCache(true)
                .bitmapTransform(new RoundedCornersTransformation(radius, 0, type))
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Activity activity = getActivityFromView(imageView);
        if (activity != null && !activity.isFinishing()) {
            Glide.with(activity).load(url).apply(options).into(imageView);
        } else if (context != null) {
            Glide.with(context).load(url).apply(options).into(imageView);
        }
    }


    /**
     * 加载模糊图片（自定义透明度）
     *
     * @param url
     * @param imageView
     * @param blur      模糊度，一般1-100够了，越大越模糊
     */
    @Override
    public <T> void loadBlurImage(T url, ImageView imageView, int blur) {
        RequestOptions options = new RequestOptions()
                .fitCenter()
//                .placeholder(placeholderSoWhite)
                .error(errorSoWhite)
                //.priority(Priority.HIGH)
                .bitmapTransform(new BlurTransformation(blur))
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Activity activity = getActivityFromView(imageView);
        if (activity != null && !activity.isFinishing()) {
            Glide.with(activity).load(url).apply(options).into(imageView);
        }  else if (context != null) {
            Glide.with(context).load(url).apply(options).into(imageView);
        }
    }

    /*
     *加载灰度(黑白)图片（自定义透明度）
     */
    @Override
    public <T> void loadBlackImage(T url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .fitCenter()
//                .placeholder(placeholderSoWhite)
                .error(errorSoWhite)
                //.priority(Priority.HIGH)
                .bitmapTransform(new GrayscaleTransformation())
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Activity activity = getActivityFromView(imageView);
        if (activity != null && !activity.isFinishing()) {
            Glide.with(activity).load(url).apply(options).into(imageView);
        }  else if (context != null) {
            Glide.with(context).load(url).apply(options).into(imageView);
        }
    }

    /**
     * Glide.with(this).asGif()    //强制指定加载动态图片
     * 如果加载的图片不是gif，则asGif()会报错， 当然，asGif()不写也是可以正常加载的。
     * 加入了一个asBitmap()方法，这个方法的意思就是说这里只允许加载静态图片，不需要Glide去帮我们自动进行图片格式的判断了。
     * 如果你传入的还是一张GIF图的话，Glide会展示这张GIF图的第一帧，而不会去播放它。
     *
     * @param url       例如：https://image.niwoxuexi.com/blog/content/5c0d4b1972-loading.gif
     * @param imageView
     */
    @Override
    public <T> void loadGif(T url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
//                .placeholder(placeholderSoWhite)
                .error(errorSoWhite);
        Activity activity = getActivityFromView(imageView);
        if (activity != null && !activity.isFinishing()) {
            Glide.with(activity)
                    .load(url)
                    .apply(options)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(imageView);

        } else if (context != null) {
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(imageView);
        }

    }


    public int dip2px(float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    @Override
    public <T> void downloadImage(final Context context, final T url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //T url = "http://www.guolin.tech/book.png";
                    FutureTarget<File> target = Glide.with(context)
                            .asFile()
                            .load(url)
                            .submit();
                    final File imageFile = target.get();
                    Log.d("logcat", "下载成功：" + imageFile.getPath());
                    /*runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, imageFile.getPath(), Toast.LENGTH_LONG).show();
                        }
                    });*/
                } catch (Exception e) {
                    Logger.e(e.toString());
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public <T> void downloadImage(final Context context, final T url, final File file) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //T url = "http://www.guolin.tech/book.png";
                    FutureTarget<File> target = Glide.with(context)
                            .asFile()
                            .load(url)
                            .submit();
                    File imageFile = target.get();
                    copy(imageFile, file);
                    Log.i("金管家", "下载成功：" + imageFile.getPath());
                } catch (Exception e) {
                    Logger.e(e.toString());
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Nullable
    @Override
    public Bitmap loadImageSync(String uri, int width, int height) {
        return null;
    }

    @Override
    public void loadImage(String uri, int width, int height, final ImageLoaderListener listener) {
        if (width <= 0 || height <= 0) {
            width = height = Integer.MIN_VALUE;
        }
        Glide.with(context).asBitmap().load(uri).into(new SimpleTarget<Bitmap>(width, height) {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                if (listener != null) {
                    listener.onLoadComplete(resource);
                }
            }
        });
    }

    /**
     * 复制文件
     *
     * @param source 输入文件
     * @param target 输出文件
     */
    public static void copy(File source, File target) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(source);
            fileOutputStream = new FileOutputStream(target);
            byte[] buffer = new byte[1024];
            while (fileInputStream.read(buffer) > 0) {
                fileOutputStream.write(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * try get host activity from view.
     * views hosted on floating window like dialog and toast will sure return null.
     *
     * @return host activity; or null if not available
     */
    public static Activity getActivityFromView(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }


}
