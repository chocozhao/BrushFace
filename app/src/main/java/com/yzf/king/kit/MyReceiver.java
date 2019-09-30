package com.yzf.king.kit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tapadoo.alerter.Alerter;
import com.yzf.king.App;
import com.yzf.king.model.PushExtras;
import com.yzf.king.ui.activitys.MainActivity;
import com.yzf.king.ui.activitys.MsgActivity;
import com.yzf.king.ui.activitys.SplashActivity;

import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.event.IBus;
import cn.droidlover.xdroidmvp.event.IEvent;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.router.Router;
import cn.jpush.android.api.JPushInterface;

/**
 * ClaseName：MyReceiver
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2018/2/5 17:55
 * Modified By：
 * Fixtime：2018/2/5 17:55
 * FixDescription：
 */
public class MyReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Logger.d("[MyReceiver] onReceive - " + intent.getAction());
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Logger.d("[MyReceiver] 接收Registration Id : " + regId);
            //send the Registration Id to your server...
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Logger.d("[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            try {
                String content = bundle.getString(JPushInterface.EXTRA_ALERT);
                String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
                if (!AppTools.isEmpty(extra) && extra.startsWith("{")) {
                    PushExtras pushExtras = new PushExtras();
                    Gson gson = new Gson();
                    pushExtras = gson.fromJson(extra, PushExtras.class);
                    if (pushExtras.getExtras() != null && !AppTools.isEmpty(pushExtras.getExtras().getMsgContent())) {
                        content = pushExtras.getExtras().getMsgContent();
                    }
                    if ("00".equals(pushExtras.getExtras().getMsgType()))//系统消息
                    {
                        IBus.IEvent iEvent = new IEvent();
                        iEvent.setId("sys_msg");
                        BusProvider.getBus().post(iEvent);
                    }
                    if ("01".equals(pushExtras.getExtras().getMsgType()))//交易消息
                    {
                        IBus.IEvent iEvent = new IEvent();
                        iEvent.setId("trade_msg");
                        BusProvider.getBus().post(iEvent);
                    }
                    if ("02".equals(pushExtras.getExtras().getMsgType()))//规划消息
                    {
                        IBus.IEvent iEvent = new IEvent();
                        iEvent.setId("plan_msg");
                        BusProvider.getBus().post(iEvent);
                    }
                    if ("03".equals(pushExtras.getExtras().getMsgType()))//分润消息
                    {
                        IBus.IEvent iEvent = new IEvent();
                        iEvent.setId("profit_msg");
                        BusProvider.getBus().post(iEvent);
                    }
                    if ("04".equals(pushExtras.getExtras().getMsgType()))//商户消息
                    {
                        IBus.IEvent iEvent = new IEvent();
                        iEvent.setId("merch_msg");
                        BusProvider.getBus().post(iEvent);
                    }
                }
                SharedPref.getInstance(App.getContext()).putBoolean("showMsg", true);
                SharedPref.getInstance(App.getContext()).putString("msg", content);
                if (!Kits.Package.isApplicationInBackground(context)) {
                    IBus.IEvent iEvent = new IEvent();
                    iEvent.setId("show_msg");
                    BusProvider.getBus().post(iEvent);
                }
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Logger.d("[MyReceiver] 接收到推送下来的通知: " + bundle.getString(JPushInterface.EXTRA_ALERT) +
                    ", extras: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            try {
                String content = bundle.getString(JPushInterface.EXTRA_ALERT);
                String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
                if (!AppTools.isEmpty(extra) && extra.startsWith("{")) {
                    PushExtras pushExtras = new PushExtras();
                    Gson gson = new Gson();
                    pushExtras = gson.fromJson(extra, PushExtras.class);
                    if (pushExtras.getExtras() != null && !AppTools.isEmpty(pushExtras.getExtras().getMsgContent())) {
                        content = pushExtras.getExtras().getMsgContent();
                    }
                    if ("00".equals(pushExtras.getExtras().getMsgType()))//系统消息
                    {
                        IBus.IEvent iEvent = new IEvent();
                        iEvent.setId("sys_msg");
                        BusProvider.getBus().post(iEvent);
                    }
                    if ("01".equals(pushExtras.getExtras().getMsgType()))//交易消息
                    {
                        IBus.IEvent iEvent = new IEvent();
                        iEvent.setId("trade_msg");
                        BusProvider.getBus().post(iEvent);
                    }
                    if ("02".equals(pushExtras.getExtras().getMsgType()))//规划消息
                    {
                        IBus.IEvent iEvent = new IEvent();
                        iEvent.setId("plan_msg");
                        BusProvider.getBus().post(iEvent);
                    }
                    if ("03".equals(pushExtras.getExtras().getMsgType()))//分润消息
                    {
                        IBus.IEvent iEvent = new IEvent();
                        iEvent.setId("profit_msg");
                        BusProvider.getBus().post(iEvent);
                    }
                    if ("04".equals(pushExtras.getExtras().getMsgType()))//商户消息
                    {
                        IBus.IEvent iEvent = new IEvent();
                        iEvent.setId("merch_msg");
                        BusProvider.getBus().post(iEvent);
                    }
                }
                SharedPref.getInstance(App.getContext()).putBoolean("showMsg", true);
                SharedPref.getInstance(App.getContext()).putString("msg", content);
                if (!Kits.Package.isApplicationInBackground(context)) {
                    IBus.IEvent iEvent = new IEvent();
                    iEvent.setId("show_msg");
                    BusProvider.getBus().post(iEvent);
                    String msg = bundle.getString(JPushInterface.EXTRA_ALERT);
                    Alerter.create(ActivityManager.getInstance().getTopActivity())
                            .setText(msg)
                            .enableSwipeToDismiss()
                            .setDuration(4000)
                            .setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Router.newIntent(ActivityManager.getInstance().getTopActivity())
                                            .to(MsgActivity.class)
                                            .launch();
                                    Alerter.hide();
                                }
                            })
                            .show();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Logger.d("[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Logger.d("[MyReceiver] 用户点击打开了通知");
            //打开自定义的Activity
//            List<TPusgMsg> list=AppUser.getInstance().getPusgMsgDao().getAll();
            if (Kits.Package.isApplicationInBackground(context)) {
                if (ActivityManager.getInstance().findActivity(MainActivity.class) == null) {
                    Intent i = new Intent(context, SplashActivity.class);
                    i.putExtras(bundle);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                } else {
                    Intent i = new Intent(context, ActivityManager.getInstance().getTopActivity().getClass());
                    i.putExtras(bundle);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                    context.startActivity(i);
                }
            } else {
                String msg = SharedPref.getInstance(context).getString("msg", "");
                try {
                    Alerter.create(ActivityManager.getInstance().getTopActivity())
                            .setText(msg)
                            .enableSwipeToDismiss()
                            .setDuration(4000)
                            .setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Router.newIntent(ActivityManager.getInstance().getTopActivity())
                                            .to(MsgActivity.class)
                                            .launch();
                                    Alerter.hide();
                                }
                            })
                            .show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            Logger.d("[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            Logger.d("[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
        } else {
            Logger.d("[MyReceiver] Unhandled intent - " + intent.getAction());
        }
    }

}
