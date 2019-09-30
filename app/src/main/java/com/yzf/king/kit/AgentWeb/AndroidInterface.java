package com.yzf.king.kit.AgentWeb;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.just.agentweb.AgentWeb;
import com.yzf.king.R;
import com.yzf.king.kit.ActivityManager;
import com.yzf.king.kit.AppKit;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.utils.BitmapUtil;
import com.yzf.king.kit.utils.fileUtill;
import com.yzf.king.model.servicesmodels.QuickPayResult;
import com.yzf.king.ui.activitys.MerchApplyPermitActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;

import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.event.IBus;
import cn.droidlover.xdroidmvp.event.IEvent;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * Created by cenxiaozhong on 2017/5/14.
 * source CODE  https://github.com/Justson/AgentWeb
 */

public class AndroidInterface {

    private Handler deliver = new Handler(Looper.getMainLooper());
    private AgentWeb agent;
    private Context context;

    public AndroidInterface(AgentWeb agent, Context context) {
        this.agent = agent;
        this.context = context;
    }


    @JavascriptInterface
    public void callAndroid(final String jsonStr) {
        Logger.i("后台回调：" + jsonStr);
        if (!AppTools.isEmpty(jsonStr)) {
            Gson gson = new Gson();
            final QuickPayResult quickPayResult = gson.fromJson(jsonStr, QuickPayResult.class);
            if (context != null) {
                deliver.post(new Runnable() {
                    @Override
                    public void run() {
                        if (((Activity) context).hasWindowFocus()) {
                            try {
                                new MaterialDialog.Builder(context)
                                        .title("提示")
                                        .content(quickPayResult.getRespDesc())
                                        .positiveText("确定")
                                        .onAny(new MaterialDialog.SingleButtonCallback() {
                                            @Override
                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                if (which == DialogAction.POSITIVE) {
                                                    ActivityManager.getInstance().finishActivity(context.getClass());
                                                }
                                            }
                                        })
                                        .show();
                            } catch (Exception e) {
                                Toast.makeText(context.getApplicationContext(), quickPayResult.getRespDesc(), Toast.LENGTH_LONG);
                                Logger.e(e.toString());
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        }
    }

    @JavascriptInterface
    public void bindCardCallBack(final String jsonStr) {
        Logger.i("绑卡后台回调：" + jsonStr);
        if (context != null) {
            deliver.post(new Runnable() {
                @Override
                public void run() {
                    if (((Activity) context).hasWindowFocus()) {
                        ActivityManager.getInstance().finishActivity(context.getClass());
                    }
                }
            });
        }

    }


    @JavascriptInterface
    public void finshCallBack(final String jsonStr) {
        Logger.i("退出网页回调：" + jsonStr);
        if (context != null) {
            deliver.post(new Runnable() {
                @Override
                public void run() {
                    if (((Activity) context).hasWindowFocus()) {
                        IBus.IEvent iEvent = new IEvent();
                        iEvent.setId("home_refresh");
                        BusProvider.getBus().post(iEvent);
                        ActivityManager.getInstance().finishActivity(context.getClass());
                    }
                }
            });
        }

    }

    //账单回调
    @JavascriptInterface
    public void billCallBack(final String jsonStr) {
        Logger.i("退出网页回调：" + jsonStr);
        if (context != null) {
            deliver.post(new Runnable() {
                @Override
                public void run() {
                    if (((Activity) context).hasWindowFocus()) {
                        IBus.IEvent iEvent = new IEvent();
                        iEvent.setId("refresh_card");
                        BusProvider.getBus().post(iEvent);
                        ActivityManager.getInstance().finishActivity(context.getClass());
                    }
                }
            });
        }

    }

    @JavascriptInterface
    public void shareUrl(final String jsonStr) {
        Logger.i("分享链接回调：" + jsonStr);
        if (!AppTools.isEmpty(jsonStr)) {
            if (context != null) {
                deliver.post(new Runnable() {
                    @Override
                    public void run() {
                        if (((Activity) context).hasWindowFocus()) {
                            try {
                                Gson gson = new Gson();
                                final QuickPayResult quickPayResult = gson.fromJson(jsonStr, QuickPayResult.class);
                                String url = quickPayResult.getShareUrl();
                                if (AppTools.isEmpty(url)) {
                                    Toast.makeText(context.getApplicationContext(), "分享失败，请重试", Toast.LENGTH_SHORT);
                                    return;
                                }
                                AppKit.shareText(context, AppUser.getInstance().getMerchName() + "邀请您加入" + context.getResources().getString(R.string.app_name) + ":" + url);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(context.getApplicationContext(), "分享失败，请重试", Toast.LENGTH_SHORT);
                            }
                        }
                    }
                });
            }
        }
    }

    @JavascriptInterface
    public void shareImage(final String jsonStr) {
//        Logger.i("分享图片回调：" + jsonStr);
        if (!AppTools.isEmpty(jsonStr)) {
            if (context != null) {
                deliver.post(new Runnable() {
                    @Override
                    public void run() {
                        if (((Activity) context).hasWindowFocus()) {
                            try {
                                /*WebView webView = agent.getWebCreator().get();
                                Bitmap bitmap = BitmapUtil.getBitmapFromView(webView);
                                ByteArrayOutputStream by = new ByteArrayOutputStream();
                                assert bitmap != null;
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, by);
                                byte[] stream = by.toByteArray();*/
                                Gson gson = new Gson();
                                final QuickPayResult quickPayResult = gson.fromJson(jsonStr, QuickPayResult.class);
                                String shareImage = quickPayResult.getShareImage();
                                if (AppTools.isEmpty(shareImage)) {
                                    Toast.makeText(context.getApplicationContext(), "分享失败，请重试", Toast.LENGTH_SHORT);
                                    return;
                                }
//                                shareImage= URLDecoder.decode(shareImage,"UTF-8");
                                if (!AppTools.isEmpty(shareImage)) {
                                    shareImage = shareImage.substring(shareImage.indexOf(",") + 1, shareImage.length());
                                }
                                Bitmap bitmap = BitmapUtil.string2Bitmap(shareImage);
                                ByteArrayOutputStream by = new ByteArrayOutputStream();
                                assert bitmap != null;
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, by);
                                byte[] stream = by.toByteArray();


                                // 以时间戳命名图片
                                String imageName = AppUser.getInstance().getMerchId() + Kits.Random.getRandomCapitalLetters(6) + ".jpg";
                                String rootPath = context.getExternalFilesDir("") + File.separator + "Pictures/";
                                File file = fileUtill.write2Sdcard(stream, rootPath, imageName);
                                // 最后通知图库更新
                                Uri uri = null;
                                if (Build.VERSION.SDK_INT >= 24) {
                                    // 适配android7.0 ，不能直接访问原路径
                                    uri = FileProvider.getUriForFile(context, AppKit.getpackageNames(context) + ".fileprovider", file);
                                } else {
                                    uri = Uri.fromFile(file);
                                }
                                Logger.i(uri.toString());
                                AppKit.insertImage(context.getApplicationContext(), file);//图片插入相册
                                AppKit.shareImage(context, uri, "com.tencent.mm");
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(context.getApplicationContext(), "分享失败，请重试", Toast.LENGTH_SHORT);
                            }
                        }
                    }
                });
            }
        }
    }

    //商家申请回调
    @JavascriptInterface
    public void MerchApplyCallBack(final String jsonStr) {
        Logger.i("退出网页回调：" + jsonStr);
        if (context != null) {
            deliver.post(new Runnable() {
                @Override
                public void run() {
                    if (((Activity) context).hasWindowFocus()) {
                        String newJsonStr = jsonStr.substring(1,jsonStr.length()-1);
                        Router.newIntent((Activity) context)
                                .to(MerchApplyPermitActivity.class)
                                .putString("applyType",newJsonStr)
                                .launch();
                    }
                }
            });
        }

    }
}
