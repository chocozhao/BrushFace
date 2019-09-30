package com.yzf.king.ui.fragments;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.sunfusheng.marqueeview.MarqueeView;
import com.yzf.king.R;
import com.yzf.king.adapter.HolderView.AdHolderView;
import com.yzf.king.adapter.HomeAdapter;
import com.yzf.king.adapter.HomeProductAdapter;
import com.yzf.king.kit.AppConfig;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.db.NoticeDao;
import com.yzf.king.kit.db.TNotice;
import com.yzf.king.kit.utils.PermissionPageUtils;
import com.yzf.king.model.servicesmodels.GetGoodsInfoResults;
import com.yzf.king.model.servicesmodels.GetNoticeResult;
import com.yzf.king.model.servicesmodels.GetShareImgResult;
import com.yzf.king.model.servicesmodels.GetUrlResult;
import com.yzf.king.model.servicesmodels.RefreshResult;
import com.yzf.king.present.PHome;
import com.yzf.king.ui.activitys.AcctInfoActivity;
import com.yzf.king.ui.activitys.AgenWebViewActivity;
import com.yzf.king.ui.activitys.MsgTypeActivity;
import com.yzf.king.ui.activitys.MyDeviceActivity;
import com.yzf.king.ui.activitys.OrderManageActivity;
import com.yzf.king.ui.activitys.TeamDeviceActivity;
import com.yzf.king.ui.activitys.WeChatCustomerActivity;
import com.yzf.king.ui.activitys.WeChatPublicActivity;
import com.yzf.king.widget.PopView.NotificatonPopup;
import com.yzf.king.widget.StateButton;
import com.yzf.king.widget.TitlePopupViews.ActionItem;
import com.yzf.king.widget.TitlePopupViews.TitlePopup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.event.IEvent;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;
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
 * ClassName：HomeFragment
 * Description: 金管家页面
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/5/18 12:01
 * Modified By：
 * Fixtime：2017/5/18 12:01
 * FixDescription：
 */

public class HomeFragment extends XFragment<PHome> implements OnItemClickListener {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.kefu_iv)
    ImageView kefuIv;
    @BindView(R.id.home_profitamt_tv)
    TextView homeProfitamtTv;
    @BindView(R.id.home_income_tv)
    TextView homeIncomeTv;
    @BindView(R.id.home_profit_ll)
    LinearLayout homeProfitLl;
    @BindView(R.id.home_recyclerView)
    XRecyclerView recyclerview;
    @BindView(R.id.home_priduct_rv)
    XRecyclerView priductRecyclerView;
    @BindView(R.id.home_banner_iv)
    ImageView homeBannerIv;
    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    @BindView(R.id.home_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.marqueeViewLl)
    LinearLayout marqueeViewLl;
    @BindView(R.id.home_message)
    ImageView homeMessage;
    @BindView(R.id.home_mypoint_tv)
    TextView homeMypointTv;
    @BindView(R.id.home_teampoint_tv)
    TextView homeTeampointTv;
    boolean first = false;

    HomeAdapter adapter;
    HomeProductAdapter productAdapter;

    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    String beginTime;
    String endTime;

    private List<GetShareImgResult.DataBean> bannerList;
    private GetShareImgResult.DataBean dataBean;
    // 定义标题栏弹窗按钮
    private TitlePopup titlePopup;

    @Override
    public void initData(Bundle savedInstanceState) {
        useEventBus(true);
        initView();
        initAdapter();
//        initTitlePopup();
//        initListData();
        getP().getAcctInfo(AppUser.getInstance().getMerchId());
        getP().getDynamicNews(AppUser.getInstance().getMerchId());
        getP().getGoodsInfo(AppUser.getInstance().getMerchId());
        getP().getNotice(AppUser.getInstance().getMerchId());
        getRxPermissions()//获取内、外部存储权限、位置权限、联系人权限
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) {
                            //TODO 许可
//                            FissionFragment.load();
                            setBanner(null);
                        } else {
                            //TODO 未许可
//                                                    showToast("权限未获取");
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
                });

//        getP().verifyStatus(AppUser.getInstance().getStatus());

    }

    /**
     * 初始化界面
     */
    private void initView() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        title.setText(getResources().getString(R.string.home_title));

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public PHome newP() {
        return new PHome();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            context.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        String currTime = Kits.Date.getyyyyMMddHHmmss().substring(8, 12);
        if (currTime.compareTo("0900") < 0 || currTime.compareTo("2000") > 0) {
            marqueeView.stopFlipping();
        }

        getP().refresh(AppUser.getInstance().getMerchId());
        /*if (first) {
            getP().refresh(AppUser.getInstance().getMerchId());
        } else {
            first = true;
        }*/
    }

    @Override
    public void bindEvent() {
        BusProvider.getBus().toObservable(IEvent.class)
                .subscribe(new Action1<IEvent>() {
                    @Override
                    public void call(IEvent iEvent) {
                        //TODO 事件处理
                        if (iEvent.getId().equals("merch_msg")) {
                            getP().refresh(AppUser.getInstance().getMerchId());
                        }
                        if (iEvent.getId().equals("trade_msg")) {
                            getP().refresh(AppUser.getInstance().getMerchId());
                        }
                        if (iEvent.getId().equals("profit_msg")) {
                            getP().refresh(AppUser.getInstance().getMerchId());
                        }
                    }
                });
    }

    /**
     * 将跳转的activity抽出来
     *
     * @param activity
     * @param finish
     */
    public void JumpActivity(Class<?> activity, boolean finish) {
        getvDelegate().dismissLoading();
        Router.newIntent(context)
                .to(activity)
                .launch();
        if (finish) {
            Router.pop(context);
        }
    }

    /**
     * 将跳转的activity抽出来
     *
     * @param activity
     */
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
     * 显示双按钮对话框
     *
     * @param msg
     * @param
     */
    public void showDialog(String title, String msg, String positiveText, String negativeText, MaterialDialog.SingleButtonCallback singleButtonCallback, Boolean cancle) {
        getvDelegate().showDialog(title, msg, positiveText, negativeText, singleButtonCallback, cancle);
    }

    public void showError(NetError error) {
        getvDelegate().showError(error);
    }

    /**
     * 点击判断处理
     *
     * @param view
     */
    @OnClick({R.id.kefu_iv, R.id.home_profit_ll, R.id.home_message,
            R.id.home_mydevice_rl, R.id.home_teamdevice_rl, R.id.home_detail_rl,
            R.id.home_guide_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.kefu_iv:
                new XPopup.Builder(context)
                        .watchView(view)
                        .asBottomList("", new String[]{String.valueOf(Html.fromHtml("客服微信:<font color='#008AFF'>adfhk66</font>")),
                                        String.valueOf(Html.fromHtml("微信公众号:<font color='#008AFF'>乐收刷脸支付</font>")),
                                        String.valueOf(Html.fromHtml("客服电话:<font color='#008AFF'>"+AppUser.getInstance().getServicePhone()+"</font>"))},
                                new OnSelectListener() {
                                    @Override
                                    public void onSelect(int position, String text) {
                                        switch (position) {
                                            case 0:
                                                JumpActivity(WeChatCustomerActivity.class);
                                                break;
                                            case 1:
                                                JumpActivity(WeChatPublicActivity.class);
                                                break;
                                            case 2:
                                                getRxPermissions()
                                                        .request(Manifest.permission.CALL_PHONE)
                                                        .subscribe(new Action1<Boolean>() {
                                                            @Override
                                                            public void call(Boolean granted) {
                                                                if (granted) {
                                                                    //TODO 许可
                                                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + AppUser.getInstance().getServicePhone()));
                                                                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                                                        return;
                                                                    }
                                                                    context.startActivity(intent);
                                                                } else {
                                                                    //TODO 未许可
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
                                                        });
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                })
                        .show();
                break;
            case R.id.home_profit_ll:
                JumpActivity(AcctInfoActivity.class);
                break;
            case R.id.home_message:
                //跳转消息
                JumpActivity(MsgTypeActivity.class);
                break;
            case R.id.home_mydevice_rl:
                JumpActivity(MyDeviceActivity.class);
                break;
            case R.id.home_teamdevice_rl:
                JumpActivity(TeamDeviceActivity.class);
                break;
            case R.id.home_detail_rl:
                JumpActivity(OrderManageActivity.class);
                break;
            case R.id.home_guide_rl:
                List<GetUrlResult.DataBean> list = AppUser.getInstance().getUrlBean();
                String target = null;
                if (list != null && list.size() > 0) {
                    for (GetUrlResult.DataBean dataBean : list) {
                        if (dataBean.getType().equals("agentHelpUrl")) {
                            target = dataBean.getUrl();
                            break;
                        }
                    }
                }
                if (!AppTools.isEmpty(target)) {
                    AgenWebViewActivity.launch(context, target, null);
                } else {
                    showToast("暂未开放");
                }
                break;
            default:
        }
    }

    public void initAdapter() {
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().refresh(AppUser.getInstance().getMerchId());
            }
        });

        //最新产品recyclerview
        priductRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        if (productAdapter == null) {
            productAdapter = new HomeProductAdapter(context);
            productAdapter.setRecItemClick(new RecyclerItemCallback<GetGoodsInfoResults.DataBean, HomeProductAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetGoodsInfoResults.DataBean item, int tag, HomeProductAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case HomeProductAdapter.TAG_VIEW:
                            List<GetGoodsInfoResults.DataBean> list = Collections.singletonList(item);
                            String target = null;//http://h5.yiyoupay.net/link/html/directions.html?topBranchNo=20000029
                            if (list != null && list.size() > 0) {
                                for (GetGoodsInfoResults.DataBean dataBean : list) {
                                    if (dataBean.getActionUrl() != null && !"".equals(dataBean.getActionUrl())) {
                                        target = dataBean.getActionUrl() + "&merchId=" + AppUser.getInstance().getMerchId() + "&token=" + AppUser.getInstance().getToken();
                                        break;
                                    } else {
                                        showToast("即将上线,敬请期待！");
                                    }
                                }
                            }
                            if (!AppTools.isEmpty(target)) {
                                AgenWebViewActivity.launch(context, target, null);
                            } else {
                                showToast("即将上线,敬请期待！");
                            }
                            break;
                        default:
                    }
                }
            });
        }
        priductRecyclerView.setAdapter(productAdapter);
    }


    /**
     * 从acctinfo实体类获取总收益数据信息展示
     */
    public void setInfo() {
        homeIncomeTv.setText(AppUser.getInstance().getAcctInfoBean().getData().get(0).getAvlbAmt() + "元");
        homeProfitamtTv.setText(AppTools.formatL2Y(AppUser.getInstance().getAcctInfoBean().getSumAmt()) + "元");
    }

    /**
     * 刷新获得收益数据展示
     *
     * @param data
     */
    public void setInfo(RefreshResult.DataBean data) {
        homeIncomeTv.setText(AppTools.formatL2Y(data.getAcctBal()) + "元");
        homeProfitamtTv.setText(AppTools.formatL2Y(data.getSumAmt()) + "元");
        if ("0".equals(data.getMyTermNum())) {
            homeMypointTv.setVisibility(View.GONE);
        } else {
            homeMypointTv.setText(data.getMyTermNum());
        }
        if ("0".equals(data.getTeamTermNum())) {
            homeTeampointTv.setVisibility(View.GONE);
        } else {
            homeTeampointTv.setText(data.getTeamTermNum());
        }

    }

    /**
     * 获取产品信息展示
     *
     * @param data
     */
    public void setInfo(List<GetGoodsInfoResults.DataBean> data) {
        if (data != null) {
            productAdapter.setData(data);
        }
        if (data.size() < pageSize) {
            //当条数少于默认条数时，调整最大页数
            recyclerview.setPage(1, 1);
            recyclerview.removeAllFootView();
        } else {
            //必须设置setPage，否则上拉加载更多会无效
            recyclerview.setPage(1, MAX_PAGE);
        }
    }


    public void dismissLoading() {
        getvDelegate().dismissLoading();
    }

    /**
     * 轮播图
     *
     * @param getBannerListResult
     */
    public void setBanner(List<GetShareImgResult.DataBean> getBannerListResult) {
        bannerList = new ArrayList<>();
        if (getBannerListResult != null && getBannerListResult.size() > 0) {
            bannerList.addAll(getBannerListResult);
        } else {

            dataBean = new GetShareImgResult.DataBean();
            dataBean.setResourceId(R.mipmap.banner4);
            String target1 = null;
            target1 = AppConfig.H5_URL + "VueKing/#/gotoloan?merchId=" + AppUser.getInstance().getMerchId() + "&token=" + AppUser.getInstance().getToken();
            dataBean.setH5Url(target1);
            bannerList.add(dataBean);

            dataBean = new GetShareImgResult.DataBean();
            dataBean.setResourceId(R.mipmap.banner3);
            String target2 = null;
            target2 = AppConfig.H5_URL + "VueKing/#/upGrade?merchId=" + AppUser.getInstance().getMerchId() + "&token=" + AppUser.getInstance().getToken();
            dataBean.setH5Url(target2);
            bannerList.add(dataBean);
        }

        showBanner();
    }

    /**
     * 轮播图展示
     */
    private void showBanner() {
        if (bannerList.size() > 0) {
            convenientBanner.setPages(
                    new CBViewHolderCreator() {
                        @Override
                        public Holder createHolder(View itemView) {
                            return new AdHolderView(itemView);
                        }

                        @Override
                        public int getLayoutId() {
                            return R.layout.item_images;
                        }
                    }, bannerList)
                    //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
//                    .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                    //设置指示器的方向
                    //                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                                    .setOnPageChangeListener(this)//监听翻页事件
                    .setOnItemClickListener(this);
        }
        convenientBanner.startTurning(5500);//轮播时间
    }

    @Override
    public void onItemClick(int position) {
        if (bannerList.size() > 0 && bannerList.get(position) != null && !AppTools.isEmpty(bannerList.get(position).getH5Url())) {
            AgenWebViewActivity.launch(context, bannerList.get(position).getH5Url(), "");
        }

    }

    public void toWebView() {
//        AgenWebViewActivity.launch(context, AppConfig.H5_URL + "king/#/upGrade?merchId=" + AppUser.getInstance().getMerchId() + "&token=" + AppUser.getInstance().getToken(), "");
        List<GetUrlResult.DataBean> list = AppUser.getInstance().getUrlBean();
        String target = null;//http://h5.yiyoupay.net/link/html/directions.html?topBranchNo=20000029
        if (list != null && list.size() > 0) {
            for (GetUrlResult.DataBean dataBean : list) {
                if (dataBean.getType().equals("upgradeUrl")) {
                    target = dataBean.getUrl();
                    break;
                }
            }
        }
        if (!AppTools.isEmpty(target)) {
            AgenWebViewActivity.launch(context, target, null);
        } else {
            showToast("暂未开放");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        marqueeView.startFlipping();//解决跑马灯重影问题
    }

    @Override
    public void onStop() {
        super.onStop();
        marqueeView.stopFlipping();//解决跑马灯重影问题
    }

    public void refreshed() {
        swiperefreshlayout.setRefreshing(false);
    }


    /**
     * 初始化数据
     */
    private void initListData() {
        // 给标题栏弹窗添加子类
        titlePopup.addAction(new ActionItem(context, "客服微信", R.mipmap.home_icon_wx));
        titlePopup.addAction(new ActionItem(context, "微信公众号", R.mipmap.home_icon_public));
        titlePopup.addAction(new ActionItem(context, "客服电话", R.mipmap.home_icon_phone));
    }

    private void initTitlePopup() {
        // 实例化标题栏弹窗
        titlePopup = new TitlePopup(context, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        Dialog dialog = new Dialog(context);
        Window window = dialog.getWindow();
        //设置Dialog背景色
        window.setBackgroundDrawableResource(R.color.theme);
        titlePopup.setItemOnClickListener(new TitlePopup.OnItemOnClickListener() {
            @Override
            public void onItemClick(ActionItem item, int position) {
                switch (position) {
                    case 0:
                        JumpActivity(WeChatCustomerActivity.class);
                        break;
                    case 1:
                        JumpActivity(WeChatPublicActivity.class);
                        break;
                    case 2:
                        getRxPermissions()
                                .request(Manifest.permission.CALL_PHONE)
                                .subscribe(new Action1<Boolean>() {
                                    @Override
                                    public void call(Boolean granted) {
                                        if (granted) {
                                            //TODO 许可
                                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + AppUser.getInstance().getServicePhone()));
                                            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                                return;
                                            }
                                            context.startActivity(intent);
                                        } else {
                                            //TODO 未许可
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
                                });
                        break;
                    default:
                        break;
                }
            }
        });

    }


    /**
     * 跑马灯数据展示
     *
     * @param msgList
     */
    public void setMarqueeView(final List<String> msgList) {
        if (msgList.size() > 0) {
            List<SpannableString> info = new ArrayList<>();
            for (String s : msgList) {
                SpannableString spannableString = new SpannableString("#喜报#  " + s);
                ForegroundColorSpan span = new ForegroundColorSpan(getResources().getColor(R.color.bg_blue));
                spannableString.setSpan(span, 0, 4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);//设置跑马灯的宽度
                info.add(spannableString);
            }
            marqueeViewLl.setVisibility(View.VISIBLE);
            marqueeView.startWithList(info);
            marqueeView.startWithList(info, R.anim.anim_bottom_in, R.anim.anim_top_out);
            marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                @Override
                public void onItemClick(int position, TextView textView) {
                    showErrorDialog(msgList.get(position));
                }
            });
            String currTime = Kits.Date.getyyyyMMddHHmmss().substring(8, 12);
            if (currTime.compareTo("0900") < 0 || currTime.compareTo("2000") > 0) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            marqueeView.stopFlipping();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 2000);

            }
        }
    }

    /**
     * 推送通知
     *
     * @param getNoticeResult
     */
    public void showNotice(GetNoticeResult getNoticeResult) {
        HomeFragment homeFragment = this;
        if (getNoticeResult.getData().size() > 0) {
            GetNoticeResult.DataBean dataBean = getNoticeResult.getData().get(0);
            //1.如果根据代理商编号判断系统消息
            if (AppConfig.TOPBRANCHNO.equals(dataBean.getMerchId()) || "00000000".equals(dataBean.getMerchId())) {
                TNotice tNotice = new TNotice();
                tNotice.setMerchId(AppUser.getInstance().getMerchId());
                tNotice.setNoticeId(dataBean.getId());
                tNotice = new NoticeDao().get(tNotice);
                if (tNotice == null) {//数据库中无数据
                    //1.1根据tyep类型显示怎样的图片
                    if (dataBean.getType() != 3) {
//                           根据1.1.1判断是否需要强制提醒(2强制提醒  1不强制提醒)  需要(不做数据刷新)
                        if (dataBean.getShowFlag() == 2) {
                            //1.1.1.1判断url是否为null(链接为null时,不需要链接,显示确认按钮）
                            NotificatonPopup notificatonPopup = new NotificatonPopup(context, R.layout.pop_notification);
                            notificatonPopup.showDialog();
                            TextView popNotificationTitleTv = notificatonPopup.findViewById(R.id.pop_notification_title_tv);
                            TextView popNotificationContextTv = notificatonPopup.findViewById(R.id.pop_notification_context_tv);
                            LinearLayout popNotificationBgLl = notificatonPopup.findViewById(R.id.pop_notification_bg_ll);
                            StateButton popNotificationBt = notificatonPopup.findViewById(R.id.pop_notification_bt);
                            popNotificationTitleTv.setText(dataBean.getTitle());
                            popNotificationContextTv.setText(dataBean.getContent());
                            ImageView pop_notification_cancel_iv = notificatonPopup.findViewById(R.id.pop_notification_cancel_iv);
                            pop_notification_cancel_iv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    notificatonPopup.dismiss();
                                }
                            });
                            //加载背景图
                            if (!AppTools.isEmpty(dataBean.getAdd1())) {
                                Glide.with(context)
                                        .load(dataBean.getAdd1())
                                        .into(new SimpleTarget<Drawable>() {
                                            @Override
                                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                                popNotificationBgLl.setBackground(resource);
                                            }
                                        });
                            } else {
                                Glide.with(context)
                                        .load(R.mipmap.pop_notification_black)
                                        .into(new SimpleTarget<Drawable>() {
                                            @Override
                                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                                popNotificationBgLl.setBackground(resource);
                                            }
                                        });
                            }

                            if (!AppTools.isEmpty(dataBean.getUrl())) {
                                popNotificationBt.setText("查看详情");
                                popNotificationBt.setNormalBackgroundColor(Color.parseColor(dataBean.getAdd2()));
                                popNotificationBt.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        AgenWebViewActivity.launch(HomeFragment.this.context, dataBean.getUrl(), "");
                                        notificatonPopup.dismiss();
                                    }
                                });
                            } else {
                                popNotificationBt.setText("知道了");
                                if (!AppTools.isEmpty(dataBean.getAdd2())) {
                                    popNotificationBt.setNormalBackgroundColor(Color.parseColor(dataBean.getAdd2()));
                                } else {
                                    popNotificationBt.setNormalBackgroundColor(Color.parseColor(String.valueOf(R.color.bg_black)));
                                }
                                popNotificationBt.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        notificatonPopup.dismiss();
                                    }
                                });

                            }
                        } else {//不需要强制提醒 (需要数据刷新)
                            NotificatonPopup notificatonPopup = new NotificatonPopup(context, R.layout.pop_notification);
                            notificatonPopup.showDialog();
                            TextView popNotificationTitleTv = notificatonPopup.findViewById(R.id.pop_notification_title_tv);
                            TextView popNotificationContextTv = notificatonPopup.findViewById(R.id.pop_notification_context_tv);
                            LinearLayout popNotificationBgLl = notificatonPopup.findViewById(R.id.pop_notification_bg_ll);
                            StateButton popNotificationBt = notificatonPopup.findViewById(R.id.pop_notification_bt);
                            ImageView pop_notification_cancel_iv = notificatonPopup.findViewById(R.id.pop_notification_cancel_iv);
                            popNotificationTitleTv.setText(dataBean.getTitle());
                            popNotificationContextTv.setText(dataBean.getContent());
                            pop_notification_cancel_iv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    notificatonPopup.dismiss();
                                }
                            });
                            //加载背景图
                            if (!AppTools.isEmpty(dataBean.getAdd1())) {
                                Glide.with(context)
                                        .load(dataBean.getAdd1())
                                        .into(new SimpleTarget<Drawable>() {
                                            @Override
                                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                                popNotificationBgLl.setBackground(resource);
                                            }
                                        });
                            } else {
                                Glide.with(context)
                                        .load(R.mipmap.pop_notification_black)
                                        .into(new SimpleTarget<Drawable>() {
                                            @Override
                                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                                popNotificationBgLl.setBackground(resource);
                                            }
                                        });
                            }
                            if (!AppTools.isEmpty(dataBean.getUrl())) {
                                popNotificationBt.setText("查看详情");
                                popNotificationBt.setNormalBackgroundColor(Color.parseColor(dataBean.getAdd2()));
                                TNotice finalTNotice = tNotice;
                                popNotificationBt.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        AgenWebViewActivity.launch(HomeFragment.this.context, dataBean.getUrl(), "");
                                        notificatonPopup.dismiss();
                                        TNotice tNotice = new TNotice();
                                        tNotice.setMerchId(AppUser.getInstance().getMerchId());
                                        tNotice.setNoticeId(dataBean.getId());
                                        tNotice.setContent(dataBean.getContent());
                                        tNotice.setTitle(dataBean.getTitle());
                                        tNotice.setOrderId(dataBean.getOrderId());
                                        tNotice.setType(dataBean.getType());
                                        tNotice.setStatus(dataBean.getStatus());
                                        tNotice.setAmt(dataBean.getAmt());
                                        tNotice.setUrl(dataBean.getUrl());
                                        tNotice.setShowFlag(dataBean.getShowFlag());
                                        tNotice.setReadFlag(1);
                                        tNotice.setRemark(dataBean.getRemark());
                                        new NoticeDao().saveOrUpdate(tNotice);
                                    }
                                });
                            } else {
                                popNotificationBt.setText("知道了");
                                if (!AppTools.isEmpty(dataBean.getAdd2())) {
                                    popNotificationBt.setNormalBackgroundColor(Color.parseColor(dataBean.getAdd2()));
                                } else {
                                    popNotificationBt.setNormalBackgroundColor(Color.parseColor(String.valueOf(R.color.bg_black)));
                                }
                                TNotice finalTNotice1 = tNotice;
                                popNotificationBt.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        notificatonPopup.dismiss();
                                        TNotice tNotice = new TNotice();
                                        tNotice.setMerchId(AppUser.getInstance().getMerchId());
                                        tNotice.setNoticeId(dataBean.getId());
                                        tNotice.setContent(dataBean.getContent());
                                        tNotice.setTitle(dataBean.getTitle());
                                        tNotice.setOrderId(dataBean.getOrderId());
                                        tNotice.setType(dataBean.getType());
                                        tNotice.setStatus(dataBean.getStatus());
                                        tNotice.setAmt(dataBean.getAmt());
                                        tNotice.setUrl(dataBean.getUrl());
                                        tNotice.setShowFlag(dataBean.getShowFlag());
                                        tNotice.setReadFlag(1);
                                        tNotice.setRemark(dataBean.getRemark());
                                        new NoticeDao().saveOrUpdate(tNotice);
                                    }
                                });

                            }

                        }
                    } else if (dataBean.getType() == 3) {
//                            1.2.1  判断是否需要强制提醒(2强制提醒  1不强制提醒)  需要(不做数据刷新)
                        if (dataBean.getShowFlag() == 2) {
                            NotificatonPopup notificatonPopup = new NotificatonPopup(context, R.layout.pop_notification);
                            notificatonPopup.showDialog();
                            TextView popNotificationTitleTv = notificatonPopup.findViewById(R.id.pop_notification_title_tv);
                            TextView popNotificationContextTv = notificatonPopup.findViewById(R.id.pop_notification_context_tv);
                            LinearLayout popNotificationBgLl = notificatonPopup.findViewById(R.id.pop_notification_bg_ll);
                            StateButton popNotificationBt = notificatonPopup.findViewById(R.id.pop_notification_bt);
                            ImageView pop_notification_cancel_iv = notificatonPopup.findViewById(R.id.pop_notification_cancel_iv);
                            pop_notification_cancel_iv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    notificatonPopup.dismiss();
                                }
                            });
                            if (!AppTools.isEmpty(dataBean.getUrl())) {
                                popNotificationBt.setVisibility(View.GONE);
                                if (!AppTools.isEmpty(dataBean.getAdd1())) {
                                    Glide.with(context)
                                            .load(dataBean.getAdd1())
                                            .into(new SimpleTarget<Drawable>() {
                                                @Override
                                                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                                    popNotificationBgLl.setBackground(resource);
                                                }
                                            });
                                } else {
                                    Glide.with(context)
                                            .load(R.mipmap.pop_notification_black)
                                            .into(new SimpleTarget<Drawable>() {
                                                @Override
                                                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                                    popNotificationBgLl.setBackground(resource);
                                                }
                                            });
                                }
                                popNotificationBgLl.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        AgenWebViewActivity.launch(HomeFragment.this.context, dataBean.getUrl(), "");
                                        notificatonPopup.dismiss();
                                    }
                                });
                            } else {
                                popNotificationBt.setVisibility(View.GONE);
                                popNotificationContextTv.setText("暂无活动");

                            }
//                           1.2.2  判断是否需要强制提醒(2强制提醒  1不强制提醒)
                        } else {//不需要强制提醒（做数据刷新）
                            NotificatonPopup notificatonPopup = new NotificatonPopup(context, R.layout.pop_notification);
                            notificatonPopup.showDialog();
                            TextView popNotificationTitleTv = notificatonPopup.findViewById(R.id.pop_notification_title_tv);
                            TextView popNotificationContextTv = notificatonPopup.findViewById(R.id.pop_notification_context_tv);
                            LinearLayout popNotificationBgLl = notificatonPopup.findViewById(R.id.pop_notification_bg_ll);
                            StateButton popNotificationBt = notificatonPopup.findViewById(R.id.pop_notification_bt);
//                            popNotificationTitleTv.setText(dataBean.getTitle());
//                            popNotificationContextTv.setText(dataBean.getContent());
                            ImageView pop_notification_cancel_iv = notificatonPopup.findViewById(R.id.pop_notification_cancel_iv);
                            pop_notification_cancel_iv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    notificatonPopup.dismiss();
                                }
                            });
                            if (!AppTools.isEmpty(dataBean.getUrl())) {
                                popNotificationBt.setVisibility(View.GONE);
                                if (!AppTools.isEmpty(dataBean.getAdd1())) {
                                    Glide.with(context)
                                            .load(dataBean.getAdd1())
                                            .into(new SimpleTarget<Drawable>() {
                                                @Override
                                                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                                    popNotificationBgLl.setBackground(resource);
                                                }
                                            });
                                } else {
                                    Glide.with(context)
                                            .load(R.mipmap.pop_notification_black)
                                            .into(new SimpleTarget<Drawable>() {
                                                @Override
                                                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                                    popNotificationBgLl.setBackground(resource);
                                                }
                                            });
                                }
                                popNotificationBt.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        AgenWebViewActivity.launch(HomeFragment.this.context, dataBean.getUrl(), "");
                                        notificatonPopup.dismiss();
                                        getP().updateNotice(dataBean);
                                    }
                                });
                            } else {
                                popNotificationBt.setVisibility(View.GONE);
                                popNotificationContextTv.setText("暂无活动");
                            }
                        }
                    }
                } else {//数据库中有数据
                    if (tNotice.getReadFlag() != 1)//未读
                    {
                        NotificatonPopup notificatonPopup = new NotificatonPopup(context, R.layout.pop_notification);
                        notificatonPopup.showDialog();
                        TextView popNotificationTitleTv = notificatonPopup.findViewById(R.id.pop_notification_title_tv);
                        TextView popNotificationContextTv = notificatonPopup.findViewById(R.id.pop_notification_context_tv);
                        LinearLayout popNotificationBgLl = notificatonPopup.findViewById(R.id.pop_notification_bg_ll);
                        StateButton popNotificationBt = notificatonPopup.findViewById(R.id.pop_notification_bt);
                        ImageView pop_notification_cancel_iv = notificatonPopup.findViewById(R.id.pop_notification_cancel_iv);
                        popNotificationTitleTv.setText(dataBean.getTitle());
                        popNotificationContextTv.setText(dataBean.getContent());
                        pop_notification_cancel_iv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                notificatonPopup.dismiss();
                            }
                        });
                        //加载背景图
                        if (!AppTools.isEmpty(dataBean.getAdd1())) {
                            Glide.with(context)
                                    .load(dataBean.getAdd1())
                                    .into(new SimpleTarget<Drawable>() {
                                        @Override
                                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                            popNotificationBgLl.setBackground(resource);
                                        }
                                    });
                        } else {
                            Glide.with(context)
                                    .load(R.mipmap.pop_notification_black)
                                    .into(new SimpleTarget<Drawable>() {
                                        @Override
                                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                            popNotificationBgLl.setBackground(resource);
                                        }
                                    });
                        }
                        if (!AppTools.isEmpty(dataBean.getUrl())) {
                            popNotificationBt.setText("查看详情");
                            popNotificationBt.setNormalBackgroundColor(Color.parseColor(dataBean.getAdd2()));
                            TNotice finalTNotice = tNotice;
                            popNotificationBt.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AgenWebViewActivity.launch(HomeFragment.this.context, dataBean.getUrl(), "");
                                    notificatonPopup.dismiss();
                                    TNotice tNotice = new TNotice();
                                    tNotice.setMerchId(AppUser.getInstance().getMerchId());
                                    tNotice.setNoticeId(dataBean.getId());
                                    tNotice.setContent(dataBean.getContent());
                                    tNotice.setTitle(dataBean.getTitle());
                                    tNotice.setOrderId(dataBean.getOrderId());
                                    tNotice.setType(dataBean.getType());
                                    tNotice.setStatus(dataBean.getStatus());
                                    tNotice.setAmt(dataBean.getAmt());
                                    tNotice.setUrl(dataBean.getUrl());
                                    tNotice.setShowFlag(dataBean.getShowFlag());
                                    tNotice.setReadFlag(1);
                                    tNotice.setRemark(dataBean.getRemark());
                                    new NoticeDao().saveOrUpdate(tNotice);
                                }
                            });
                        } else {
                            popNotificationBt.setText("知道了");
                            if (!AppTools.isEmpty(dataBean.getAdd2())) {
                                popNotificationBt.setNormalBackgroundColor(Color.parseColor(dataBean.getAdd2()));
                            } else {
                                popNotificationBt.setNormalBackgroundColor(Color.parseColor(String.valueOf(R.color.bg_black)));
                            }
                            TNotice finalTNotice1 = tNotice;
                            popNotificationBt.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    notificatonPopup.dismiss();
                                    TNotice tNotice = new TNotice();
                                    tNotice.setMerchId(AppUser.getInstance().getMerchId());
                                    tNotice.setNoticeId(dataBean.getId());
                                    tNotice.setContent(dataBean.getContent());
                                    tNotice.setTitle(dataBean.getTitle());
                                    tNotice.setOrderId(dataBean.getOrderId());
                                    tNotice.setType(dataBean.getType());
                                    tNotice.setStatus(dataBean.getStatus());
                                    tNotice.setAmt(dataBean.getAmt());
                                    tNotice.setUrl(dataBean.getUrl());
                                    tNotice.setShowFlag(dataBean.getShowFlag());
                                    tNotice.setReadFlag(1);
                                    tNotice.setRemark(dataBean.getRemark());
                                    new NoticeDao().saveOrUpdate(tNotice);
                                }
                            });
                        }
                    }
                }


                //2.否则无代理商编号则为普通通知
            } else {

                //2.1根据tyep类型显示类型
                if (dataBean.getType() != 3) {
//                       2.1.1 判断是否需要强制提醒(2强制提醒  1不强制提醒)  需要(不做数据刷新)
                    if (dataBean.getShowFlag() == 2) {
                        NotificatonPopup notificatonPopup = new NotificatonPopup(context, R.layout.pop_notification);
                        notificatonPopup.showDialog();
                        TextView popNotificationTitleTv = notificatonPopup.findViewById(R.id.pop_notification_title_tv);
                        TextView popNotificationContextTv = notificatonPopup.findViewById(R.id.pop_notification_context_tv);
                        LinearLayout popNotificationBgLl = notificatonPopup.findViewById(R.id.pop_notification_bg_ll);
                        StateButton popNotificationBt = notificatonPopup.findViewById(R.id.pop_notification_bt);
                        popNotificationTitleTv.setText(dataBean.getTitle());
                        popNotificationContextTv.setText(dataBean.getContent());
                        ImageView pop_notification_cancel_iv = notificatonPopup.findViewById(R.id.pop_notification_cancel_iv);
                        pop_notification_cancel_iv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                notificatonPopup.dismiss();
                            }
                        });
                        //加载背景图
                        if (!AppTools.isEmpty(dataBean.getAdd1())) {
                            Glide.with(context)
                                    .load(dataBean.getAdd1())
                                    .into(new SimpleTarget<Drawable>() {
                                        @Override
                                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                            popNotificationBgLl.setBackground(resource);
                                        }
                                    });
                        } else {
                            Glide.with(context)
                                    .load(R.mipmap.pop_notification_black)
                                    .into(new SimpleTarget<Drawable>() {
                                        @Override
                                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                            popNotificationBgLl.setBackground(resource);
                                        }
                                    });
                        }
                        if (!AppTools.isEmpty(dataBean.getUrl())) {
                            popNotificationBt.setText("查看详情");
                            popNotificationBt.setNormalBackgroundColor(Color.parseColor(dataBean.getAdd2()));
                            popNotificationBt.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AgenWebViewActivity.launch(HomeFragment.this.context, dataBean.getUrl(), "");
                                    notificatonPopup.dismiss();
                                }
                            });
                        } else {
                            popNotificationBt.setText("知道了");
                            if (!AppTools.isEmpty(dataBean.getAdd2())) {
                                popNotificationBt.setNormalBackgroundColor(Color.parseColor(dataBean.getAdd2()));
                            } else {
                                popNotificationBt.setNormalBackgroundColor(Color.parseColor(String.valueOf(R.color.bg_black)));
                            }
                            popNotificationBt.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    notificatonPopup.dismiss();
                                }

                            });

                        }
//                            2.1.2 判断是否需要强制提醒(2强制提醒  1不强制提醒)
                    } else {//不需要强制提醒（做数据刷新）
                        NotificatonPopup notificatonPopup = new NotificatonPopup(context, R.layout.pop_notification);
                        notificatonPopup.showDialog();
                        TextView popNotificationTitleTv = notificatonPopup.findViewById(R.id.pop_notification_title_tv);
                        TextView popNotificationContextTv = notificatonPopup.findViewById(R.id.pop_notification_context_tv);
                        LinearLayout popNotificationBgLl = notificatonPopup.findViewById(R.id.pop_notification_bg_ll);
                        StateButton popNotificationBt = notificatonPopup.findViewById(R.id.pop_notification_bt);
                        popNotificationTitleTv.setText(dataBean.getTitle());
                        popNotificationContextTv.setText(dataBean.getContent());
                        ImageView pop_notification_cancel_iv = notificatonPopup.findViewById(R.id.pop_notification_cancel_iv);
                        pop_notification_cancel_iv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                notificatonPopup.dismiss();
                            }
                        });
                        //加载背景图
                        if (!AppTools.isEmpty(dataBean.getAdd1())) {
                            Glide.with(context)
                                    .load(dataBean.getAdd1())
                                    .into(new SimpleTarget<Drawable>() {
                                        @Override
                                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                            popNotificationBgLl.setBackground(resource);
                                        }
                                    });
                        } else {
                            Glide.with(context)
                                    .load(R.mipmap.pop_notification_black)
                                    .into(new SimpleTarget<Drawable>() {
                                        @Override
                                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                            popNotificationBgLl.setBackground(resource);
                                        }
                                    });
                        }
                        if (!AppTools.isEmpty(dataBean.getUrl())) {
                            popNotificationBt.setText("查看详情");
                            popNotificationBt.setNormalBackgroundColor(Color.parseColor(dataBean.getAdd2()));
                            popNotificationBt.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AgenWebViewActivity.launch(HomeFragment.this.context, dataBean.getUrl(), "");
                                    notificatonPopup.dismiss();
                                    getP().updateNotice(dataBean);

                                }
                            });
                        } else {
                            popNotificationBt.setText("知道了");
                            if (!AppTools.isEmpty(dataBean.getAdd2())) {
                                popNotificationBt.setNormalBackgroundColor(Color.parseColor(dataBean.getAdd2()));
                            } else {
                                popNotificationBt.setNormalBackgroundColor(Color.parseColor(String.valueOf(R.color.bg_black)));
                            }
                            popNotificationBt.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    notificatonPopup.dismiss();
                                    getP().updateNotice(dataBean);
                                }
                            });

                        }
                    }

                }
                //2.2否则根据tyep类型显示类型
                else if (dataBean.getType() == 3) {
//                        2.2.1 判断是否需要强制提醒(2强制提醒  1不强制提醒)  需要(不做数据刷新)
                    if (dataBean.getShowFlag() == 2) {
                        NotificatonPopup notificatonPopup = new NotificatonPopup(context, R.layout.pop_notification);
                        notificatonPopup.showDialog();
                        TextView popNotificationContextTv = notificatonPopup.findViewById(R.id.pop_notification_context_tv);
                        LinearLayout popNotificationBgLl = notificatonPopup.findViewById(R.id.pop_notification_bg_ll);
                        StateButton popNotificationBt = notificatonPopup.findViewById(R.id.pop_notification_bt);
                        ImageView pop_notification_cancel_iv = notificatonPopup.findViewById(R.id.pop_notification_cancel_iv);
                        pop_notification_cancel_iv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                notificatonPopup.dismiss();
                            }
                        });

                        if (!AppTools.isEmpty(dataBean.getUrl())) {
                            popNotificationBt.setVisibility(View.GONE);
                            if (!AppTools.isEmpty(dataBean.getAdd1())) {
                                Glide.with(context)
                                        .load(dataBean.getAdd1())
                                        .into(new SimpleTarget<Drawable>() {
                                            @Override
                                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                                popNotificationBgLl.setBackground(resource);
                                            }
                                        });
                            } else {
                                Glide.with(context)
                                        .load(R.mipmap.pop_notification_black)
                                        .into(new SimpleTarget<Drawable>() {
                                            @Override
                                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                                popNotificationBgLl.setBackground(resource);
                                            }
                                        });
                            }
                            popNotificationBgLl.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AgenWebViewActivity.launch(HomeFragment.this.context, dataBean.getUrl(), "");
                                    notificatonPopup.dismiss();
                                }
                            });

                        } else {
                            popNotificationBt.setVisibility(View.GONE);
                            popNotificationContextTv.setText("暂无活动");
                        }
//                          2.2.2 判断是否需要强制提醒(2强制提醒  1不强制提醒)
                    } else {//不需要强制提醒（做数据刷新）
                        NotificatonPopup notificatonPopup = new NotificatonPopup(context, R.layout.pop_notification);
                        notificatonPopup.showDialog();
                        TextView popNotificationContextTv = notificatonPopup.findViewById(R.id.pop_notification_context_tv);
                        LinearLayout popNotificationBgLl = notificatonPopup.findViewById(R.id.pop_notification_bg_ll);
                        StateButton popNotificationBt = notificatonPopup.findViewById(R.id.pop_notification_bt);
                        ImageView pop_notification_cancel_iv = notificatonPopup.findViewById(R.id.pop_notification_cancel_iv);
                        pop_notification_cancel_iv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                notificatonPopup.dismiss();
                            }
                        });
                        if (!AppTools.isEmpty(dataBean.getUrl())) {
                            popNotificationBt.setVisibility(View.GONE);
                            if (!AppTools.isEmpty(dataBean.getAdd1())) {
                                Glide.with(context)
                                        .load(dataBean.getAdd1())
                                        .into(new SimpleTarget<Drawable>() {
                                            @Override
                                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                                popNotificationBgLl.setBackground(resource);
                                            }
                                        });
                            } else {
                                Glide.with(context)
                                        .load(R.mipmap.pop_notification_black)
                                        .into(new SimpleTarget<Drawable>() {
                                            @Override
                                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                                popNotificationBgLl.setBackground(resource);
                                            }
                                        });
                            }
                            popNotificationBgLl.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AgenWebViewActivity.launch(HomeFragment.this.context, dataBean.getUrl(), "");
                                    notificatonPopup.dismiss();
                                    getP().updateNotice(dataBean);
                                }
                            });
                        } else {
                            popNotificationBt.setVisibility(View.GONE);
                            popNotificationContextTv.setText("暂无活动");
                        }
                    }
                }
            }
        }
    }

}
