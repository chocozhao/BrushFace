package com.yzf.king.net;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Looper;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yzf.king.App;
import com.yzf.king.kit.ActivityManager;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.BaseResults;
import com.yzf.king.ui.activitys.LoginActivity;

import java.io.IOException;
import java.nio.charset.Charset;

import cn.droidlover.xdroidmvp.router.Router;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class TokenInterceptor implements Interceptor {
    private static Long sLastclick = 0L;
    private static final Long FILTER_TIMEM = 2000L;

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // request the entire body.
        Buffer buffer = source.buffer();
        String responseBodyString = buffer.clone().readString(Charset.forName("UTF-8"));
        // 对返回code统一拦截
        if (responseBodyString != null) {
            try {
                Gson gson = new Gson();
                BaseResults results = gson.fromJson(responseBodyString, BaseResults.class);
                if (results.getCode() == ResultCode.TOKENfAIL.code()) {
                    if (System.currentTimeMillis() - sLastclick >= FILTER_TIMEM) {
                        sLastclick = System.currentTimeMillis();
                        Looper.prepare();
                        AlertDialog.Builder builder = new AlertDialog.Builder(App.getContext(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
                        builder.setTitle("提示")/*设置dialog的title*/
                                .setMessage("距离上次登录时间过长，请重新登录")/*设置dialog的内容*/
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {/*设置dialog确认按钮的点击事件*/
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Router.newIntent(ActivityManager.getInstance().getTopActivity())
                                                .to(LoginActivity.class)
                                                .launch();
                                        ActivityManager.getInstance().finishAllActivityExceptOne(LoginActivity.class);
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.setCanceledOnTouchOutside(false);
                        alertDialog.setCancelable(false);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            alertDialog.getWindow().setType((WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY - 1));
                        } else {
                            alertDialog.getWindow().setType((WindowManager.LayoutParams.TYPE_TOAST));
                        }
                        alertDialog.show();
                        Looper.loop();
                    }
                }
            } catch (Exception e) {
                try {
                    Toast.makeText(App.getContext(), "距离上次登录时间过长，请重新登录", Toast.LENGTH_LONG);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }
        return response;
    }
}