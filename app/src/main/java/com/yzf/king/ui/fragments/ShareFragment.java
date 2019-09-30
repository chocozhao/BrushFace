package com.yzf.king.ui.fragments;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.allenliu.versionchecklib.callback.OnCancelListener;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.CustomVersionDialogListener;
import com.allenliu.versionchecklib.v2.callback.ForceUpdateListener;
import com.bumptech.glide.Glide;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.XPopupImageLoader;
import com.yzf.king.R;
import com.yzf.king.adapter.MyPagerAdapter;
import com.yzf.king.kit.AppKit;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.utils.PermissionPageUtils;
import com.yzf.king.model.servicesmodels.CheckVersionResults;
import com.yzf.king.model.servicesmodels.GetShareImgResult;
import com.yzf.king.model.servicesmodels.GetTeamInfoResult;
import com.yzf.king.present.PShare;
import com.yzf.king.ui.activitys.PhotoActivity;
import com.yzf.king.ui.activitys.TeamManageActivity;
import com.yzf.king.widget.MultipleStatusView;
import com.yzf.king.widget.PopView.NotificatonUpPopup;
import com.yzf.king.widget.ScalePageTransformer;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.event.IEvent;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.kit.SystemClockUtil;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import rx.functions.Action1;

/**
 * 　　┏┓　　　┏┓+ +
 * 　┏┛┻━━━┛┻┓ + +
 * 　┃　　　　　　　┃
 * 　┃　　　━　　　┃ ++ + + +
 * ████━████ ┃+
 * 　┃　　　　　　　┃ +
 * 　┃　　　┻　　　┃
 * 　┃　　　　　　　┃ + +
 * 　┗━┓　　　┏━┛
 * 　　　┃　　　┃
 * 　　　┃　　　┃ + + + +
 * 　　　┃　　　┃
 * 　　　┃　　　┃ +  神兽镇楼
 * 　　　┃　　　┃    代码无bug
 * 　　　┃　　　┃　　+
 * 　　　┃　 　　┗━━━┓ + +
 * 　　　┃ 　　　　　　　┣┓
 * 　　　┃ 　　　　　　　┏┛
 * 　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　┃┫┫　┃┫┫
 * 　　　　┗┻┛　┗┻┛+ + + +
 * <p>
 * ClassName：ShareFragment
 * Description: 分享页面
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/5/18 12:01
 * Modified By：
 * Fixtime：2017/5/18 12:01
 * FixDescription：
 */

public class ShareFragment extends XFragment<PShare> {

    MyPagerAdapter adapter;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    //    @BindView(R.id.share_iv)
//    ImageView shareIv;
    @BindView(R.id.share_merch_level2_rate)
    TextView shareMerchLevel2Rate;
    @BindView(R.id.share_merch_level3_rate)
    TextView shareMerchLevel3Rate;
    @BindView(R.id.share_merch_level4_rate)
    TextView shareMerchLevel4Rate;
    @BindView(R.id.share_merch_level2_head)
    ImageView shareMerchLevel2Head;
    @BindView(R.id.share_merch_level3_num)
    TextView shareMerchLevel3Num;
    @BindView(R.id.share_merch_level3_progress)
    ProgressBar shareMerchLevel3Progress;
    @BindView(R.id.share_merch_level3_head)
    ImageView shareMerchLevel3Head;
    @BindView(R.id.share_merch_level4_num)
    TextView shareMerchLevel4Num;
    @BindView(R.id.share_merch_level4_progress)
    ProgressBar shareMerchLevel4Progress;
    @BindView(R.id.share_merch_level4_head)
    ImageView shareMerchLevel4Head;
    @BindView(R.id.share_merch_ll)
    LinearLayout shareMerchLl;
    @BindView(R.id.share_agent_level11_rate)
    TextView shareAgentLevel11Rate;
    @BindView(R.id.share_agent_level12_rate)
    TextView shareAgentLevel12Rate;
    @BindView(R.id.share_agent_level13_rate)
    TextView shareAgentLevel13Rate;
    @BindView(R.id.share_agent_level14_rate)
    TextView shareAgentLevel14Rate;
    @BindView(R.id.share_agent_level11_head)
    ImageView shareAgentLevel11Head;
    @BindView(R.id.share_agent_level12_num)
    TextView shareAgentLevel12Num;
    @BindView(R.id.share_agent_level12_progress)
    ProgressBar shareAgentLevel12Progress;
    @BindView(R.id.share_agent_level12_head)
    ImageView shareAgentLevel12Head;
    @BindView(R.id.share_agent_level13_num)
    TextView shareAgentLevel13Num;
    @BindView(R.id.share_agent_level13_progress)
    ProgressBar shareAgentLevel13Progress;
    @BindView(R.id.share_agent_level13_head)
    ImageView shareAgentLevel13Head;
    @BindView(R.id.share_agent_level14_num)
    TextView shareAgentLevel14Num;
    @BindView(R.id.share_agent_level14_progress)
    ProgressBar shareAgentLevel14Progress;
    @BindView(R.id.share_agent_level14_head)
    ImageView shareAgentLevel14Head;
    @BindView(R.id.share_agent_ll)
    LinearLayout shareAgentLl;
    @BindView(R.id.share_upgrade_tip)
    TextView shareUpgradeTip;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.share_wx_ll)
    LinearLayout shareWxLl;
    @BindView(R.id.share_time_ll)
    LinearLayout shareTimeLl;
    @BindView(R.id.share_face_ll)
    LinearLayout shareFaceLl;
    @BindView(R.id.share_picture_ll)
    LinearLayout sharePictureLl;
    @BindView(R.id.share_multiplestatusview)
    MultipleStatusView multiplestatusview;
    @BindView(R.id.share_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;

    String remark = null;
    @BindView(R.id.share_team_rl)
    RelativeLayout shareTeamRl;
    @BindView(R.id.share_team_tv)
    TextView shareTeamTv;

    GetTeamInfoResult.DataBean TeamDataBean = new GetTeamInfoResult.DataBean();
    @Override
    public void initData(Bundle savedInstanceState) {
        useEventBus(true);
        initView();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }
        getP().getShareImg(AppUser.getInstance().getMerchId());
        getP().getTeamInfo(AppUser.getInstance().getMerchId());
        checkUpdate(false);
    }

    @Override
    public void onResume() {
        super.onResume();
//        getP().getTeamInfo(AppUser.getInstance().getMerchId());
    }

    /**
     * 初始化界面
     */
    private void initView() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        title.setText("分享");
        swiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.theme));
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getTeamInfo(AppUser.getInstance().getMerchId());
                getP().getShareImg(AppUser.getInstance().getMerchId());
            }
        });
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_share;
    }

    @Override
    public PShare newP() {
        return new PShare();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                break;
            case R.id.menu_share:
                try {
                    GetShareImgResult.DataBean dataBean = adapter.getItem(viewPager.getCurrentItem());
                    String rootPath = context.getExternalFilesDir("") + File.separator + "Pictures/";
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
                        ILFactory.getLoader().downloadImage(context, dataBean.getImgPath(), file);
                    }
                    Uri uri = null;
                    if (Build.VERSION.SDK_INT >= 24) {
                        // 适配android7.0 ，不能直接访问原路径
                        uri = FileProvider.getUriForFile(context, AppKit.getpackageNames(context) + ".fileprovider", file);
                    } else {
                        uri = Uri.fromFile(file);
                    }
//                Logger.i(uri.toString());
                    AppKit.insertImage(context.getApplicationContext(), file);//图片插入相册
                    AppKit.shareImage(context, uri, "com.tencent.mm");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void bindEvent() {
        BusProvider.getBus().toObservable(IEvent.class)
                .subscribe(new Action1<IEvent>() {
                    @Override
                    public void call(IEvent iEvent) {
                        //TODO 事件处理
                        if (iEvent.getId().equals("merch_msg")) {
//                            getP().getTeamInfo(AppUser.getInstance().getMerchId());
                        }
                        if (iEvent.getId().equals("show_share1")) {
                            try {
                                GetShareImgResult.DataBean dataBean = adapter.getItem(viewPager.getCurrentItem());
                                String rootPath = context.getExternalFilesDir("") + File.separator + "Pictures/";
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
                                    ILFactory.getLoader().downloadImage(context, dataBean.getImgPath(), file);
                                } else {
                                    long lastTime = file.lastModified();
                                    long nowTime = SystemClockUtil.millisClock().now();
                                    Logger.i("lastTime-nowTime:" + Kits.Date.getYmdhmsS(lastTime) + "-" + Kits.Date.getYmdhmsS(nowTime));
                                    if (nowTime - lastTime > 1000 * 1 * 60 * 60)//1000 * 24 * 60 * 60
                                    {
                                        file.delete();
                                        Logger.i("delete:" + filePath);
                                        ILFactory.getLoader().downloadImage(context, dataBean.getImgPath(), file);
                                    }

                                }
                                Uri uri = null;
                                if (Build.VERSION.SDK_INT >= 24) {
                                    // 适配android7.0 ，不能直接访问原路径
                                    uri = FileProvider.getUriForFile(context, AppKit.getpackageNames(context) + ".fileprovider", file);
                                } else {
                                    uri = Uri.fromFile(file);
                                }
//                            AppKit.insertImage(context.getApplicationContext(), file);//图片插入相册
                                AppKit.shareImage(context, uri, "com.tencent.mm");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }


    public void JumpActivity(Class<?> activity, boolean finish) {
        Router.newIntent(context)
                .to(activity)
                .launch();
        if (finish) {
            Router.pop(context);
        }
    }

    public void JumpActivity(Class<?> activity) {
        Router.newIntent(context)
                .to(activity)
                .launch();
    }

    public void showToast(String msg) {
        getvDelegate().toastShort(msg);
    }


    /**
     * 显示单按钮对话框
     *
     * @param msg
     */
    public void showErrorDialog(String msg) {
        swiperefreshlayout.setRefreshing(false);
        getvDelegate().showErrorDialog(msg);
    }

    /**
     * 显示双按钮对话框
     *
     * @param msg
     * @param callback
     */
    public void showNoticeDialog(String msg, MaterialDialog.SingleButtonCallback callback) {
        getvDelegate().showNoticeDialog(msg, callback);
    }


    /**
     * 显示错误信息
     *
     * @param error
     */
    public void showError(NetError error) {
        swiperefreshlayout.setRefreshing(false);
        multiplestatusview.showError(getvDelegate().getErrorType(error));
    }

    public void showErrorView(String msg) {
        multiplestatusview.showError(msg);
    }

    public void showErrorView(NetError error) {
        multiplestatusview.showError(getvDelegate().getErrorType(error));
    }


    @OnClick({R.id.share_wx_ll, R.id.share_time_ll, R.id.share_face_ll, R.id.share_picture_ll,R.id.share_team_rl})
    public void onViewClicked(View view) {
        GetShareImgResult.DataBean dataBean = adapter.getItem(viewPager.getCurrentItem());
        String rootPath = context.getExternalFilesDir("") + File.separator + "Pictures/";
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
            ILFactory.getLoader().downloadImage(context, dataBean.getImgPath(), file);
        } else {
            long lastTime = file.lastModified();
            long nowTime = SystemClockUtil.millisClock().now();
            Logger.i("lastTime-nowTime:" + Kits.Date.getYmdhmsS(lastTime) + "-" + Kits.Date.getYmdhmsS(nowTime));
            if (nowTime - lastTime > 1000 * 1 * 60 * 60)//1000 * 24 * 60 * 60
            {
                file.delete();
                Logger.i("delete:" + filePath);
                ILFactory.getLoader().downloadImage(context, dataBean.getImgPath(), file);
            }

        }
        switch (view.getId()) {
//            case R.id.share_iv:
            case R.id.share_wx_ll:
            case R.id.share_time_ll:

                // 最后通知图库更新
                Uri uri = null;
                if (Build.VERSION.SDK_INT >= 24) {
                    // 适配android7.0 ，不能直接访问原路径
                    uri = FileProvider.getUriForFile(context, AppKit.getpackageNames(context) + ".fileprovider", file);
                } else {
                    uri = Uri.fromFile(file);
                }
//                Logger.i(uri.toString());
//                AppKit.insertImage(context.getApplicationContext(), file);//图片插入相册
                AppKit.shareImage(context, uri, "com.tencent.mm");
                break;
            case R.id.share_face_ll:
                PhotoActivity.launch(context, null, 0, file.getAbsolutePath());
                /*XPopup.get(getContext())
                        .asImageViewer(imageView, cardItem, new ImageLoader())
                        .show();*/
                break;
            case R.id.share_picture_ll:
                if (Build.VERSION.SDK_INT >= 24) {
                    // 适配android7.0 ，不能直接访问原路径
                    uri = FileProvider.getUriForFile(context, AppKit.getpackageNames(context) + ".fileprovider", file);
                } else {
                    uri = Uri.fromFile(file);
                }
                Logger.i(uri.toString());
                AppKit.insertImage(context.getApplicationContext(), file);//图片插入相册
                showToast("图片插入相册");
                break;
            case R.id.share_team_rl:
                Router.newIntent(context)
                        .to(TeamManageActivity.class)
                        .putString("count",TeamDataBean.getCount())
                        .launch();
                break;
            default:
                break;
        }
    }


    public void update(final CheckVersionResults results) {
        getvDelegate().dismissLoading();
        getRxPermissions()
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) {
                            //TODO 许可
                            if (!context.isFinishing()) {
                                DownloadBuilder builder = AllenVersionChecker
                                        .getInstance()
                                        .downloadOnly(crateUIData(results))
                                        .setCustomVersionDialogListener(createCustomDialogTwo(results))
                                        .setForceRedownload(true)//清本地的apk缓存
                                        .setShowNotification(false);
                                if ("1".equals(results.getData().getUpdateFlag())) {//强制更新
                                    builder.setForceUpdateListener(new ForceUpdateListener() {
                                        @Override
                                        public void onShouldForceUpdate() {
                                        }
                                    });
                                }
                                builder.setOnCancelListener(new OnCancelListener() {
                                    @Override
                                    public void onCancel() {
//                                        showToast("取消");
                                    }
                                });
                                builder.excuteMission(context);
                            } else {
                                //TODO 未许可
//                                            getvDelegate().toastShort("权限未获取");
                                showNoticeDialog("尚未获取权限，是否去开启权限?", new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        if (which == DialogAction.POSITIVE) {
                                            PermissionPageUtils permissionPageUtils = new PermissionPageUtils(context);
                                            permissionPageUtils.jumpPermissionPage();
                                        }
                                    }
                                });

                            }
                        }
                    }
                });

    }

    private UIData crateUIData(CheckVersionResults results) {
        UIData uiData = UIData.create();
        uiData.setTitle("版本更新");
        uiData.setDownloadUrl(results.getData().getLinkUrl());
        String centent = results.getData().getUpdateDesc();
        if (!AppTools.isEmpty(centent)) {
            centent = centent.replace("\\n", "\n");
        }
        uiData.setContent(centent);
        return uiData;
    }

    private CustomVersionDialogListener createCustomDialogTwo(final CheckVersionResults results) {
        return new CustomVersionDialogListener() {
            @Override
            public Dialog getCustomVersionDialog(Context context, UIData versionBundle) {
                NotificatonUpPopup notificatonPopup = new NotificatonUpPopup(context, R.layout.pop_notification_up);
                notificatonPopup.showDialog();
                TextView popNotificationAppVersionTv = notificatonPopup.findViewById(R.id.pop_notification_AppVersion_tv);
                TextView popNotificationContextTv = notificatonPopup.findViewById(R.id.pop_notification_context_tv);
                popNotificationAppVersionTv.setText("v" + results.getData().getAppVersion());
                popNotificationContextTv.setText(versionBundle.getContent());
                if ("1".equals(results.getData().getUpdateFlag())) {
                    notificatonPopup.setCanceledOnTouchOutside(false);
                    notificatonPopup.setCancelable(false);
                } else {
                    notificatonPopup.setCanceledOnTouchOutside(true);
                    notificatonPopup.setCancelable(true);
                }
                return notificatonPopup;
            }
        };
    }

    /**
     * 检查更新
     *
     * @param
     */
    private void checkUpdate(boolean show) {
        if (show) {
            getvDelegate().showLoading("检查更新中....");
        }
        getP().checkUpdate(show);
    }




    /**
     * 分享图片缓存、会员升级进度条
     */
    class ImageLoader implements XPopupImageLoader {

        @Override
        public void loadImage(int position, @NonNull Object uri, @NonNull ImageView imageView) {
            GetShareImgResult.DataBean item = (GetShareImgResult.DataBean) uri;
//            Glide.with(imageView).load(item.getImgPath()).apply(new RequestOptions().override(Target.SIZE_ORIGINAL)).into(imageView);
            ILFactory.getLoader().loadImage(item.getImgPath(), imageView);
        }

        //必须实现这个方法，返回uri对应的缓存文件，可参照下面的实现，内部保存图片会用到。
        @Override
        public File getImageFile(@NonNull Context context, @NonNull Object uri) {
            try {
                GetShareImgResult.DataBean item = (GetShareImgResult.DataBean) uri;
                return Glide.with(context).downloadOnly().load(item.getImgPath()).submit().get();
            } catch (Exception e) {
                Logger.e(e.toString());
            }
            return null;
        }
    }

    /**
     * 分享图展示信息
     *
     * @param data
     */
    public void setPageInfo(List<GetShareImgResult.DataBean> data) {
        swiperefreshlayout.setRefreshing(false);
        multiplestatusview.showContent();
        viewPager.setOffscreenPageLimit(3);
        adapter = new MyPagerAdapter(data, context);
        viewPager.setAdapter(adapter);
//        viewPager.setPageMargin(40);
        viewPager.setPageTransformer(true, new ScalePageTransformer());
        viewPager.setCurrentItem(1);
        adapter.setOnMyItemClickListener(new MyPagerAdapter.OnMyItemClickListener() {
            @Override
            public void myClick(View v, GetShareImgResult.DataBean item) {
                ImageView imageView = (ImageView) v;
                new XPopup.Builder(getContext())
                        .asImageViewer(imageView, item, new ImageLoader())
                        .show();
            }
        });
    }



    public void setAdapter(GetTeamInfoResult.DataBean data) {
        if (data != null) {
            shareTeamTv.setText(data.getCount());
            TeamDataBean.setCount(data.getCount());
        }
    }
    /**
     * 进度条
     *
     * @param data
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setProgress(GetTeamInfoResult.DataBean data) {
        swiperefreshlayout.setRefreshing(false);
        /*shareUpgradeTip.setVisibility(View.VISIBLE);
        shareUpgradeTip.setText("再直推" + data.getNextLevelContion() + "个会员可升级为" + data.getNextLevelName());
        GetTeamInfoResult.DataBean.LevelListBean bean2 = null;
        GetTeamInfoResult.DataBean.LevelListBean bean3 = null;
        GetTeamInfoResult.DataBean.LevelListBean bean4 = null;
        GetTeamInfoResult.DataBean.LevelListBean bean5 = null;
        int sumMerchNnm = data.getSubMerchNum();
        for (GetTeamInfoResult.DataBean.LevelListBean levelListBean : data.getLevelList()) {
            if (levelListBean.getLevelId() == 2) {
                bean2 = levelListBean;
            }
            if (levelListBean.getLevelId() == 3) {
                bean3 = levelListBean;
            }
            if (levelListBean.getLevelId() == 4) {
                bean4 = levelListBean;
            }
            if (levelListBean.getLevelId() == 5) {
                bean5 = levelListBean;
            }
        }

        if (bean2 == null || bean3 == null || bean4 == null) {
            showToast("数据处理有误");
            return;
        }
        shareMerchLl.setVisibility(View.VISIBLE);
        shareAgentLl.setVisibility(View.GONE);
        shareMerchLevel2Rate.setText(bean2.getFeeRate());

        shareMerchLevel3Rate.setText(bean3.getFeeRate());

        shareMerchLevel4Rate.setText(bean4.getFeeRate());

        if (data.getMerchLevel() == 2)//认证会员
        {
            shareMerchLevel3Num.setVisibility(View.VISIBLE);
            remark = bean2.getRemark();
            shareMerchLevel2Head.setImageResource(R.mipmap.level_2);
            shareMerchLevel3Head.setImageResource(R.mipmap.level_3_gray);
            shareMerchLevel4Head.setImageResource(R.mipmap.level_4_gray);
            shareMerchLevel2Rate.setBackground(getResources().getDrawable(R.drawable.line_bg_yellow));
            shareMerchLevel3Rate.setBackground(getResources().getDrawable(R.drawable.line_bg_gray));
            shareMerchLevel4Rate.setBackground(getResources().getDrawable(R.drawable.line_bg_gray));

            shareMerchLevel3Num.setText(data.getNowLevelCount() + "/" + data.getNextLevelCount());
            shareMerchLevel3Progress.setProgress(data.getNowLevelCount());
            shareMerchLevel3Progress.setMax(data.getNextLevelCount());
            shareMerchLevel4Progress.setProgress(0);
            shareMerchLevel4Progress.setMax(bean4.getContiton());

            shareMerchLevel2Rate.setTextColor(getResources().getColor(R.color.text_yellow));
        }
        if (data.getMerchLevel() == 3)//vip1
        {
            shareMerchLevel4Num.setVisibility(View.VISIBLE);
            remark = bean3.getRemark();
            shareMerchLevel2Head.setImageResource(R.mipmap.level_2);
            shareMerchLevel3Head.setImageResource(R.mipmap.level_3);
            shareMerchLevel4Head.setImageResource(R.mipmap.level_4_gray);
            shareMerchLevel2Rate.setBackground(getResources().getDrawable(R.drawable.line_bg_yellow));
            shareMerchLevel3Rate.setBackground(getResources().getDrawable(R.drawable.line_bg_yellow));
            shareMerchLevel4Rate.setBackground(getResources().getDrawable(R.drawable.line_bg_gray));

            shareMerchLevel4Num.setText(data.getNowLevelCount() + "/" + data.getNextLevelCount());
            shareMerchLevel3Progress.setProgress(bean3.getContiton());
            shareMerchLevel3Progress.setMax(bean3.getContiton());
            shareMerchLevel4Progress.setProgress(data.getNowLevelCount());
            shareMerchLevel4Progress.setMax(data.getNextLevelCount());

            shareMerchLevel2Rate.setTextColor(getResources().getColor(R.color.text_yellow));
            shareMerchLevel3Rate.setTextColor(getResources().getColor(R.color.text_yellow));
        }
        if (data.getMerchLevel() == 4)//VIP2
        {
            remark = bean3.getRemark();
            shareMerchLevel2Head.setImageResource(R.mipmap.level_2);
            shareMerchLevel3Head.setImageResource(R.mipmap.level_3);
            shareMerchLevel4Head.setImageResource(R.mipmap.level_4);
            shareMerchLevel2Rate.setBackground(getResources().getDrawable(R.drawable.line_bg_yellow));
            shareMerchLevel3Rate.setBackground(getResources().getDrawable(R.drawable.line_bg_yellow));
            shareMerchLevel4Rate.setBackground(getResources().getDrawable(R.drawable.line_bg_yellow));

            shareMerchLevel3Progress.setProgress(bean3.getContiton());
            shareMerchLevel3Progress.setMax(bean3.getContiton());
            shareMerchLevel4Progress.setProgress(bean4.getContiton());
            shareMerchLevel4Progress.setMax(bean4.getContiton());

            shareMerchLevel2Rate.setTextColor(getResources().getColor(R.color.text_yellow));
            shareMerchLevel3Rate.setTextColor(getResources().getColor(R.color.text_yellow));
            shareMerchLevel4Rate.setTextColor(getResources().getColor(R.color.text_yellow));

            shareUpgradeTip.setText("您已是最高黑卡商户等级，升级为合伙人可享受更多优惠");
            shareUpgradeTip.setTextSize(12);
        }
        if (data.getMerchLevel() == 5)//VIP2
        {
            remark = bean3.getRemark();
            shareMerchLevel2Head.setImageResource(R.mipmap.level_2);
            shareMerchLevel3Head.setImageResource(R.mipmap.level_3);
            shareMerchLevel4Head.setImageResource(R.mipmap.level_4);
            shareMerchLevel2Rate.setBackground(getResources().getDrawable(R.drawable.line_bg_yellow));
            shareMerchLevel3Rate.setBackground(getResources().getDrawable(R.drawable.line_bg_yellow));
            shareMerchLevel4Rate.setBackground(getResources().getDrawable(R.drawable.line_bg_yellow));

            shareMerchLevel3Progress.setProgress(bean3.getContiton());
            shareMerchLevel3Progress.setMax(bean3.getContiton());
            shareMerchLevel4Progress.setProgress(bean4.getContiton());
            shareMerchLevel4Progress.setMax(bean4.getContiton());

            shareMerchLevel2Rate.setTextColor(getResources().getColor(R.color.text_yellow));
            shareMerchLevel3Rate.setTextColor(getResources().getColor(R.color.text_yellow));
            shareMerchLevel4Rate.setTextColor(getResources().getColor(R.color.text_yellow));

            shareUpgradeTip.setText("您已是最高黑卡商户等级，升级为合伙人可享受更多优惠");
            shareUpgradeTip.setTextSize(12);
        }

    }*/
    }
}
