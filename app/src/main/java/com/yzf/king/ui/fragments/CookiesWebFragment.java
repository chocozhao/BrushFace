package com.yzf.king.ui.fragments;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebConfig;
import com.just.agentweb.AgentWebSettings;
import com.just.agentweb.ChromeClientCallbackManager;
import com.just.agentweb.DefaultMsgConfig;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.DownLoadResultListener;
import com.just.agentweb.FragmentKeyDown;
import com.just.agentweb.MiddleWareWebChromeBase;
import com.just.agentweb.MiddleWareWebClientBase;
import com.just.agentweb.PermissionInterceptor;
import com.just.agentweb.WebDefaultSettingsManager;
import com.yzf.king.R;
import com.yzf.king.kit.AgentWeb.AndroidInterface;
import com.yzf.king.kit.AgentWeb.MiddleWareChromeClient;
import com.yzf.king.kit.AgentWeb.MiddlewareWebViewClient;
import com.yzf.king.kit.AgentWeb.UIController;
import com.yzf.king.kit.AppConfig;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;

import java.net.URLEncoder;
import java.util.HashMap;

import me.jessyan.autosize.internal.CancelAdapt;

/**
 * Created by cenxiaozhong on 2017/5/15.
 * <p>
 * <p>
 * source code  https://github.com/Justson/AgentWeb
 */

public class CookiesWebFragment extends Fragment implements FragmentKeyDown, CancelAdapt {

    private ImageView mBackImageView;
    private View mLineView;
    private ImageView mFinishImageView;
    private TextView mTitleTextView;
    protected AgentWeb mAgentWeb;
    public static final String URL_KEY = "url_key";
    private ImageView mMoreImageView;
    private PopupMenu mPopupMenu;
    private Gson mGson = new Gson(); //用于方便打印测试
    public static final String TAG = "金管家";

    private MiddleWareWebClientBase mWebClient;
    private MiddleWareWebChromeBase mMiddleWareWebChrome;

    public static CookiesWebFragment getInstance(Bundle bundle) {

        CookiesWebFragment mAgentWebFragment = new CookiesWebFragment();
        if (bundle != null) {
            mAgentWebFragment.setArguments(bundle);
        }
        return mAgentWebFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_agentweb, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAgentWeb = AgentWeb.with(this)//
                .setAgentWebParent((LinearLayout) view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))//传入AgentWeb的父控件。
                .setIndicatorColorWithHeight(-1, 3)//设置进度条颜色与高度，-1为默认值，高度为2，单位为dp。
                .setAgentWebWebSettings(getSettings())//设置 AgentWebSettings。
                .setWebViewClient(mWebViewClient)//WebViewClient ， 与 WebView 使用一致 ，但是请勿获取WebView调用setWebViewClient(xx)方法了,会覆盖AgentWeb DefaultWebClient,同时相应的中间件也会失效。
                .setWebChromeClient(mWebChromeClient) //WebChromeClient
                .setPermissionInterceptor(mPermissionInterceptor) //权限拦截 2.0.0 加入。
                .setReceivedTitleCallback(mCallback)//标题回调。
                .setSecurityType(AgentWeb.SecurityType.strict) //严格模式 Android 4.2.2 以下会放弃注入对象 ，使用AgentWebView没影响。
                .addDownLoadResultListener(mDownLoadResultListener) //下载回调
                .setAgentWebUIController(new UIController(getActivity())) //自定义UI  AgentWeb3.0.0 加入。
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1) //参数1是错误显示的布局，参数2点击刷新控件ID -1表示点击整个布局都刷新， AgentWeb 3.0.0 加入。
                .useMiddleWareWebChrome(getMiddleWareWebChrome()) //设置WebChromeClient中间件，支持多个WebChromeClient，AgentWeb 3.0.0 加入。
                .useMiddleWareWebClient(getMiddleWareWebClient()) //设置WebViewClient中间件，支持多个WebViewClient， AgentWeb 3.0.0 加入。
                .openParallelDownload()//打开并行下载 , 默认串行下载。
                .setNotifyIcon(R.drawable.ic_file_download_black_24dp) //下载通知图标。
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他页面时，弹窗质询用户前往其他应用 AgentWeb 3.0.0 加入。
                .interceptUnkownScheme() //拦截找不到相关页面的Scheme AgentWeb 3.0.0 加入。
                .createAgentWeb()//创建AgentWeb。
                .ready()//设置 WebSettings。
                .go(getUrl()); //WebView载入该url地址的页面并显示。

        //Javascript 调 Java
        //Android 端 ， AndroidInterface 是一个注入类 ，里面有一个无参数方法：callAndroid
        if (mAgentWeb != null) {
            mAgentWeb.getJsInterfaceHolder().addJavaObject("android", new AndroidInterface(mAgentWeb, this.getActivity()));

        }
        toCleanWebCache(false);

        if (!isUrl(getUrl())) {
            WebView mWebView = mAgentWeb.getWebCreator().get();
            mWebView.loadDataWithBaseURL(null, getUrl(), "text/html", "utf-8", null);
        }
        initView(view);


        DefaultMsgConfig.DownLoadMsgConfig mDownLoadMsgConfig = mAgentWeb.getDefaultMsgConfig().getDownLoadMsgConfig();
        //  mDownLoadMsgConfig.setCancel("放弃");  // 修改下载提示信息，这里可以语言切换

        //AgentWeb 没有把WebView的功能全面覆盖 ，所有某些设置 AgentWeb 没有提供 ， 请从WebView方面入手设置。
        mAgentWeb.getWebCreator().get().setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        //mAgentWeb.getWebCreator().get()  获取WebView .


    }

    /**
     * 判断字符串是否为URL
     *
     * @param urls
     * @return true:是URL、false:不是URL
     */
    public static boolean isUrl(String urls) {
        if (urls == null) {
            return false;
        }
        urls = urls.toLowerCase();
        if (urls.startsWith("http") || urls.startsWith("www") || urls.startsWith("ftp")
                || urls.endsWith("com") || urls.endsWith("cn") || urls.endsWith("net")
                || urls.endsWith("net") || urls.endsWith("org") || urls.endsWith("gov")
                || urls.endsWith("edu") || urls.endsWith("info") || urls.endsWith("co")
                || urls.endsWith("name") || urls.endsWith("pro") || urls.endsWith("club")) {
            return true;
        }
        return false;
    }


    protected PermissionInterceptor mPermissionInterceptor = new PermissionInterceptor() {

        //AgentWeb 在触发某些敏感的 Action 时候会回调该方法， 比如定位触发 。
        //例如 https//:www.baidu.com 该 Url 需要定位权限， 返回false ，如果版本大于等于23 ， agentWeb 会动态申请权限 ，true 该Url对应页面请求定位失败。
        //该方法是每次都会优先触发的 ， 开发者可以做一些敏感权限拦截 。
        @Override
        public boolean intercept(String url, String[] permissions, String action) {
            Log.i(TAG, "url:" + url + "  permission:" + mGson.toJson(permissions) + " action:" + action);
            return false;
        }
    };


    /**
     * 下载文件完成后，回调文件的绝对路径 ，DownLoadResultListener只会在触发文件下载回调 ， 如果文件存在，并且完整 ，
     * AgentWeb则默认打开它。
     */
    protected DownLoadResultListener mDownLoadResultListener = new DownLoadResultListener() {
        //下载成功
        @Override
        public void success(String path) {
            //do you work
        }

        //下载失败
        @Override
        public void error(String path, String resUrl, String cause, Throwable e) {
            //do you work
        }
    };

    public AgentWebSettings getSettings() {
        return WebDefaultSettingsManager.getInstance();
    }

    /**
     * 页面空白，请检查scheme是否加上， scheme://host:port/path?query 。
     *
     * @return url
     */
    public String getUrl() {
        String target = "";
        if (TextUtils.isEmpty(target = this.getArguments().getString(URL_KEY))) {
            target = "http://www.baidu.com/";
        }
        return target;
    }

    protected ChromeClientCallbackManager.ReceivedTitleCallback mCallback = new ChromeClientCallbackManager.ReceivedTitleCallback() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (mTitleTextView != null && !TextUtils.isEmpty(title)) {
                if (title.length() > 10) {
                    title = title.substring(0, 10).concat("...");
                }
            }
            mTitleTextView.setText(title);

        }
    };
    protected WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //  super.onProgressChanged(view, newProgress);
            Log.i(TAG, "onProgressChanged:" + newProgress + "  view:" + view);
        }
    };

    protected WebViewClient mWebViewClient = new WebViewClient() {

        private HashMap<String, Long> timer = new HashMap<>();

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return shouldOverrideUrlLoading(view, request.getUrl() + "");
        }


        //
        @Override
        public boolean shouldOverrideUrlLoading(final WebView view, String url) {

            Log.i(TAG, "view:" + new Gson().toJson(view.getHitTestResult()));
            Log.i(TAG, "mWebViewClient shouldOverrideUrlLoading:" + url);
            //intent:// scheme的处理 如果返回false ， 则交给 DefaultWebClient 处理 ， 默认会打开该Activity  ， 如果Activity不存在则跳到应用市场上去.  true 表示拦截
            //例如优酷视频播放 ，intent://play?...package=com.youku.phone;end;
            //优酷想唤起自己应用播放该视频 ， 下面拦截地址返回 true  则会在应用内 H5 播放 ，禁止优酷唤起播放该视频， 如果返回 false ， DefaultWebClient  会根据intent 协议处理 该地址 ， 首先匹配该应用存不存在 ，如果存在 ， 唤起该应用播放 ， 如果不存在 ， 则跳到应用市场下载该应用 .
            if (url.startsWith("intent://") && url.contains("com.youku.phone"))
                return true;
            /*else if (isAlipay(view, url))   //1.2.5开始不用调用该方法了 ，只要引入支付宝sdk即可 ， DefaultWebClient 默认会处理相应url调起支付宝
                return true;*/


            return false;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            Log.i(TAG, "url:" + url + " onPageStarted  target:" + getUrl());
            timer.put(url, System.currentTimeMillis());
            if (url.equals(getUrl())) {
                pageNavigator(View.GONE);
            } else {
                pageNavigator(View.VISIBLE);
            }

        }

        //2972 1483|3005 1536|2868 1785| 2889 1523| 2912 1537|2941 1628|2925 1561|2864 1669|2953 1508|2932 1693|
        //2926.1 1592.3

        //2731 1749|1234 1808|2203 1230|1648 1752|
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (url.equals(getUrl())) {
                pageNavigator(View.GONE);
            } else {
                pageNavigator(View.VISIBLE);
            }
            if (timer.get(url) != null) {
                long overTime = System.currentTimeMillis();
                Long startTime = timer.get(url);
                Log.i(TAG, "onPageFinished  page url:" + url + "  used time:" + (overTime - startTime));
            }
            //网易136邮箱
            if (url.contains("mail.163.com") || url.contains("126.com")) {
                String cookies = AgentWebConfig.getCookiesByUrl(url);
                Log.i(TAG, "onPageFinished" + url + "      cookies:" + cookies);
                if (!AppTools.isEmpty(cookies)) {
                    String[] a = cookies.split(";");
                    String cookieInfo = null;
                    String mailId = null;
                    String type = "3";
                    for (String s : a) {
                        if (s != null && s.trim().startsWith("Coremail=")) {
                            s = s.trim();
                            cookieInfo = s.substring(9, s.length());
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("Coremail", cookieInfo);
                            cookieInfo = jsonObject.toJSONString();
                        }
                        if (s != null && s.trim().startsWith("mail_uid=")) {
                            s = s.trim();
                            mailId = s.substring(9, s.length());
                        }
                    }
                    if (cookieInfo != null && cookieInfo.length() > 10) {
                        if (mAgentWeb != null) {
                            cookieInfo = URLEncoder.encode(cookieInfo);
                            mAgentWeb.getLoader().loadUrl(AppConfig.H5_URL + "VueKing/#/waitResult?cookieInfo=" + cookieInfo + "&mailId=" + mailId + "&type=" + type + "&merchId=" + AppUser.getInstance().getMerchId());
                        }
                    }
                }
            }
            //网易126邮箱
            if (url.contains("mail.163.com") || url.contains("126.com")) {
                String cookies = AgentWebConfig.getCookiesByUrl(url);
                Log.i(TAG, "onPageFinished" + url + "      cookies:" + cookies);
                if (!AppTools.isEmpty(cookies)) {
                    String[] a = cookies.split(";");
                    String cookieInfo = null;
                    String mailId = null;
                    String type = "2";
                    for (String s : a) {
                        if (s != null && s.trim().startsWith("Coremail=")) {
                            s = s.trim();
                            cookieInfo = s.substring(9, s.length());
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("Coremail", cookieInfo);
                            cookieInfo = jsonObject.toJSONString();
                        }
                        if (s != null && s.trim().startsWith("mail_uid=")) {
                            s = s.trim();
                            mailId = s.substring(9, s.length());
                        }
                    }
                    if (cookieInfo != null && cookieInfo.length() > 10) {
                        if (mAgentWeb != null) {
                            cookieInfo = URLEncoder.encode(cookieInfo);
                            mAgentWeb.getLoader().loadUrl(AppConfig.H5_URL + "VueKing/#/waitResult?cookieInfo=" + cookieInfo + "&mailId=" + mailId + "&type=" + type + "&merchId=" + AppUser.getInstance().getMerchId());
                        }
                    }
                }
            }
            //qq邮箱
            if (url.contains("mail.qq.com")) {
                String cookies = AgentWebConfig.getCookiesByUrl(url);
                Log.i(TAG, "onPageFinished" + url + "      cookies:" + cookies);
                if (!AppTools.isEmpty(cookies)) {
                    String[] a = cookies.split(";");
                    String cookieInfo = null;
                    String mailId = null;
                    String sid = null;
                    if (url.contains("sid")) {
                        sid = url.substring(url.indexOf("sid=") + 4, url.indexOf("&"));
                    }
                    String type = "1";
                    for (String s : a) {
                        if (s != null && s.trim().startsWith("sid=")) {
                            s = s.trim();
                            cookieInfo = s.substring(4, s.length());
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("sid", cookieInfo);
                            cookieInfo = jsonObject.toJSONString();
                        }
                        if (s != null && s.trim().startsWith("qqmail_alias=")) {
                            s = s.trim();
                            mailId = s.substring(10, s.length());
                        }
                    }
                    if (cookieInfo != null && sid != null && cookieInfo.length() > 10) {
                        if (mAgentWeb != null) {
                            cookieInfo = URLEncoder.encode(cookieInfo);
                            mAgentWeb.getLoader().loadUrl(AppConfig.H5_URL + "VueKing/#/waitResult?cookieInfo=" + cookieInfo + "&mailId=" + mailId + "&type=" + type + "&sid=" + sid + "&merchId=" + AppUser.getInstance().getMerchId());
                        }
                    }
                }
            }

            //新浪邮箱
            if (url.contains("m0.mail.sina.cn")) {
                String cookies = AgentWebConfig.getCookiesByUrl(url);
                Log.i(TAG, "onPageFinished" + url + "      cookies:" + cookies);
                if (!AppTools.isEmpty(cookies)) {
                    String[] a = cookies.split(";");
                    String cookieInfo = null;
                    String mailId = null;
                    String type = "4";
                    JSONObject object = new JSONObject();
                    for (String s : a) {
                        if (s != null && s.trim().startsWith("SWEBAPPSESSID=")) {
                            s = s.trim();
                            s = s.substring(14, s.length());
                            object.put("SWEBAPPSESSID", s);
                        }
                        if (s != null && s.trim().startsWith("SUB=")) {
                            s = s.trim();
                            s = s.substring(4, s.length());
                            object.put("SUB", s);
                        }
                        if (s != null && s.trim().startsWith("freeName=")) {
                            s = s.trim();
                            mailId = s.substring(9, s.length());
                            object.put("freeName", s);
                        }
                    }
                    cookieInfo = object.toJSONString();
                    if (cookieInfo != null && cookieInfo.length() > 10) {
                        if (mAgentWeb != null) {
                            cookieInfo = URLEncoder.encode(cookieInfo);
                            mAgentWeb.getLoader().loadUrl(AppConfig.H5_URL + "VueKing/#/waitResult?cookieInfo=" + cookieInfo + "&mailId=" + mailId + "&type=" + type + "&merchId=" + AppUser.getInstance().getMerchId());
                        }
                    }
                }
            }
            //中移动139邮箱
            if (url.contains("html5.mail.10086.cn")) {
                String cookies = AgentWebConfig.getCookiesByUrl(url);
                Log.i(TAG, "onPageFinished" + url + "      cookies:" + cookies);
                if (!AppTools.isEmpty(cookies)) {
                    String[] a = cookies.split(";");
                    String cookieInfo = null;
                    String mailId = null;
                    String type = "5";
                    JSONObject object = new JSONObject();
                    for (String s : a) {
                        if (s != null && s.trim().startsWith("RMKEY=")) {
                            s = s.trim();
                            s = s.substring(6, s.length());
                            object.put("RMKEY", s);
                        }
                        if (s != null && s.trim().startsWith("Os_SSo_Sid=")) {
                            s = s.trim();
                            s = s.substring(11, s.length());
                            object.put("Os_SSo_Sid", s);
                        }
                        if (s != null && s.trim().startsWith("Login_UserNumber=")) {
                            s = s.trim();
                            s = s.substring(17, s.length());
                            object.put("Login_UserNumber", s);
                        }
                    }
                    cookieInfo = object.toJSONString();
                    if (cookieInfo != null && cookieInfo.length() > 10) {
                        if (mAgentWeb != null) {
                            cookieInfo = URLEncoder.encode(cookieInfo);
                            mAgentWeb.getLoader().loadUrl(AppConfig.H5_URL + "VueKing/#/waitResult?cookieInfo=" + cookieInfo + "&mailId=" + mailId + "&type=" + type + "&merchId=" + AppUser.getInstance().getMerchId());
                        }
                    }
                }
            }

            //搜狐闪电邮箱(暂时未使用)
            if (url.contains("mail.sohu.com")) {
                String cookies = AgentWebConfig.getCookiesByUrl(url);
                Log.i(TAG, "onPageFinished" + url + "      cookies:" + cookies);
                if (!AppTools.isEmpty(cookies)) {
                    String[] a = cookies.split(";");
                    String cookieInfo = null;
                    String mailId = null;
                    String type = "3";
                    for (String s : a) {
                        if (s != null && s.trim().startsWith("RMKEY=")) {
                            s = s.trim();
                            cookieInfo = s.substring(9, s.length());
                        }
                        if (s != null && s.trim().startsWith("Os_SSo_Sid=")) {
                            s = s.trim();
                            mailId = s.substring(9, s.length());
                        }
                        if (s != null && s.trim().startsWith("Login_UserNumbe=")) {
                            s = s.trim();
                            mailId = s.substring(9, s.length());
                        }
                    }
                    if (cookieInfo != null) {
                        if (mAgentWeb != null) {
                            cookieInfo = URLEncoder.encode(cookieInfo);
                            mAgentWeb.getLoader().loadUrl(AppConfig.H5_URL + "VueKing/#/waitResult?cookieInfo=" + cookieInfo + "&mailId=" + mailId + "&type=" + type + "&merchId=" + AppUser.getInstance().getMerchId());
                        }
                    }
                }
            }
            //阿里邮箱个人版(暂时未使用)
            if (url.contains("mail.aliyun.com")) {
                String cookies = AgentWebConfig.getCookiesByUrl(url);
                Log.i(TAG, "onPageFinished" + url + "      cookies:" + cookies);
                if (!AppTools.isEmpty(cookies)) {
                    String[] a = cookies.split(";");
                    String cookieInfo = null;
                    String mailId = null;
                    String type = "3";
                    for (String s : a) {
                        if (s != null && s.trim().startsWith("Coremail=")) {
                            s = s.trim();
                            cookieInfo = s.substring(9, s.length());
                        }
                        if (s != null && s.trim().startsWith("mail_uid=")) {
                            s = s.trim();
                            mailId = s.substring(9, s.length());
                        }
                    }
                    if (cookieInfo != null && cookieInfo.length() > 10) {
                        if (mAgentWeb != null) {
                            cookieInfo = URLEncoder.encode(cookieInfo);
                            mAgentWeb.getLoader().loadUrl(AppConfig.H5_URL + "VueKing/#/waitResult?cookieInfo=" + cookieInfo + "&mailId=" + mailId + "&type=" + type + "&merchId=" + AppUser.getInstance().getMerchId());
                        }
                    }
                }
            }
            //网易yeah邮箱(暂时未使用)
            if (url.contains("www.yeah.net")) {
                String cookies = AgentWebConfig.getCookiesByUrl(url);
                Log.i(TAG, "onPageFinished" + url + "      cookies:" + cookies);
                if (!AppTools.isEmpty(cookies)) {
                    String[] a = cookies.split(";");
                    String cookieInfo = null;
                    String mailId = null;
                    String type = "3";
                    for (String s : a) {
                        if (s != null && s.trim().startsWith("Coremail=")) {
                            s = s.trim();
                            cookieInfo = s.substring(9, s.length());
                        }
                        if (s != null && s.trim().startsWith("mail_uid=")) {
                            s = s.trim();
                            mailId = s.substring(9, s.length());
                        }
                    }
                    if (cookieInfo != null && cookieInfo.length() > 10) {
                        if (mAgentWeb != null) {
                            cookieInfo = URLEncoder.encode(cookieInfo);
                            mAgentWeb.getLoader().loadUrl(AppConfig.H5_URL + "VueKing/#/waitResult?cookieInfo=" + cookieInfo + "&mailId=" + mailId + "&type=" + type + "&merchId=" + AppUser.getInstance().getMerchId());
                        }
                    }
                }
            }

            //微软hotmail邮箱(暂时未使用)
            if (url.contains("outlook.live.com")) {
                String cookies = AgentWebConfig.getCookiesByUrl(url);
                Log.i(TAG, "onPageFinished" + url + "      cookies:" + cookies);
                if (!AppTools.isEmpty(cookies)) {
                    String[] a = cookies.split(";");
                    String cookieInfo = null;
                    String mailId = null;
                    String type = "3";
                    for (String s : a) {
                        if (s != null && s.trim().startsWith("Coremail=")) {
                            s = s.trim();
                            cookieInfo = s.substring(9, s.length());
                        }
                        if (s != null && s.trim().startsWith("mail_uid=")) {
                            s = s.trim();
                            mailId = s.substring(9, s.length());
                        }
                    }
                    if (cookieInfo != null && cookieInfo.length() > 10) {
                        if (mAgentWeb != null) {
                            cookieInfo = URLEncoder.encode(cookieInfo);
                            mAgentWeb.getLoader().loadUrl(AppConfig.H5_URL + "VueKing/#/waitResult?cookieInfo=" + cookieInfo + "&mailId=" + mailId + "&type=" + type + "&merchId=" + AppUser.getInstance().getMerchId());
                        }
                    }
                }
            }

        }
        /*错误页回调该方法 ， 如果重写了该方法， 上面传入了布局将不会显示 ， 交由开发者实现，注意参数对齐。*/
       /* public void onMainFrameError(AgentWebUIController agentWebUIController, WebView view, int errorCode, String description, String failingUrl) {
            Log.i(TAG, "AgentWebFragment onMainFrameError");
            agentWebUIController.onMainFrameError(view,errorCode,description,failingUrl);
        }*/

        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            super.onReceivedHttpError(view, request, errorResponse);

            Log.i(TAG, "onReceivedHttpError:" + 3 + "  request:" + mGson.toJson(request) + "  errorResponse:" + mGson.toJson(errorResponse));
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);

            Log.i(TAG, "onReceivedError:" + errorCode + "  description:" + description + "  errorResponse:" + failingUrl);
        }
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        mAgentWeb.uploadFileResult(requestCode, resultCode, data); //2.0.0开始 废弃该api ，没有api代替 ,使用 ActionActivity 绕过该方法 ,降低使用门槛
    }

    protected void initView(View view) {
        mBackImageView = (ImageView) view.findViewById(R.id.iv_back);
        mLineView = view.findViewById(R.id.view_line);
        mFinishImageView = (ImageView) view.findViewById(R.id.iv_finish);
        mTitleTextView = (TextView) view.findViewById(R.id.toolbar_title);
        mBackImageView.setOnClickListener(mOnClickListener);
        mFinishImageView.setOnClickListener(mOnClickListener);
        mMoreImageView = (ImageView) view.findViewById(R.id.iv_more);
        mMoreImageView.setOnClickListener(mOnClickListener);
        pageNavigator(View.GONE);
    }


    private void pageNavigator(int tag) {
        mFinishImageView.setVisibility(tag);
        mLineView.setVisibility(tag);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_back:
                    if (!mAgentWeb.back())// true表示AgentWeb处理了该事件
                    {
                        CookiesWebFragment.this.getActivity().finish();
                    }
                    break;
                case R.id.iv_finish:
                    CookiesWebFragment.this.getActivity().finish();
                    break;
                case R.id.iv_more:
                    showPoPup(v);
                    break;
                default:
                    break;
            }
        }


    };


    /**
     * 打开浏览器
     *
     * @param targetUrl 外部浏览器打开的地址
     */
    private void openBrowser(String targetUrl) {
        if (TextUtils.isEmpty(targetUrl) || targetUrl.startsWith("file://")) {
            Toast.makeText(this.getContext(), targetUrl + " 该链接无法使用浏览器打开。", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri mUri = Uri.parse(targetUrl);
        intent.setData(mUri);
        startActivity(intent);


    }


    /**
     * 显示更多菜单
     *
     * @param view 菜单依附在该View下面
     */
    private void showPoPup(View view) {
        if (mPopupMenu == null) {
            mPopupMenu = new PopupMenu(this.getActivity(), view);
            mPopupMenu.inflate(R.menu.toolbar_menu);
            mPopupMenu.setOnMenuItemClickListener(mOnMenuItemClickListener);
        }
        mPopupMenu.show();
    }

    //菜单事件
    private PopupMenu.OnMenuItemClickListener mOnMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()) {

                case R.id.refresh:
                    if (mAgentWeb != null) {
                        mAgentWeb.getLoader().reload(); //刷新
                    }
                    return true;

                case R.id.copy:
                    if (mAgentWeb != null) {
                        toCopy(CookiesWebFragment.this.getContext(), mAgentWeb.getWebCreator().get().getUrl());
                    }
                    return true;
                case R.id.default_browser:
                    if (mAgentWeb != null) {
                        openBrowser(mAgentWeb.getWebCreator().get().getUrl());
                    }
                    return true;
                case R.id.default_clean:
                    toCleanWebCache(true);
                    return true;
                case R.id.error_website:
                    loadErrorWebSite();
                    return true;
                default:
                    return false;
            }

        }
    };

    //这里用于测试错误页的显示
    private void loadErrorWebSite() {
        if (mAgentWeb != null) {
            mAgentWeb.getLoader().loadUrl("http://www.unkownwebsiteblog.me");
        }
    }

    //清除 WebView 缓存
    private void toCleanWebCache(boolean show) {

        if (this.mAgentWeb != null) {

            //清理所有跟WebView相关的缓存 ，数据库， 历史记录 等。
            this.mAgentWeb.clearWebCache();
            if (show) {
                Toast.makeText(getActivity(), "已清理缓存", Toast.LENGTH_SHORT).show();
            }
            //清空所有 AgentWeb 硬盘缓存，包括 WebView 的缓存 , AgentWeb 下载的图片 ，视频 ，apk 等文件。
//            AgentWebConfig.clearDiskCache(this.getContext());
        }

    }


    //复制字符串
    private void toCopy(Context context, String text) {

        ClipboardManager mClipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        mClipboardManager.setPrimaryClip(ClipData.newPlainText(null, text));

    }


    @Override
    public void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();//恢复
        super.onResume();
    }

    @Override
    public void onPause() {
        mAgentWeb.getWebLifeCycle().onPause(); //暂停应用内所有WebView ， 调用mWebView.resumeTimers(); 恢复。
        super.onPause();
    }

    @Override
    public boolean onFragmentKeyDown(int keyCode, KeyEvent event) {
        return mAgentWeb.handleKeyEvent(keyCode, event);
    }

    @Override
    public void onDestroyView() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroyView();
    }

    //
    protected MiddleWareWebClientBase getMiddleWareWebClient() {
        return this.mWebClient = new MiddlewareWebViewClient();
    }

    protected MiddleWareWebChromeBase getMiddleWareWebChrome() {
        return this.mMiddleWareWebChrome = new MiddleWareChromeClient();
    }
}
