package com.yzf.king.ui.fragments;


import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.yzf.king.App;
import com.yzf.king.R;
import com.yzf.king.adapter.ShopAdapter;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.utils.PermissionPageUtils;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoResults;
import com.yzf.king.present.PShop;
import com.yzf.king.ui.activitys.MerchPickActivity;
import com.yzf.king.ui.activitys.RecommendActivity;
import com.yzf.king.ui.activitys.ShopAroundActivity;
import com.yzf.king.ui.activitys.ShopContractActivity;
import com.yzf.king.widget.MultipleStatusView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;
import rx.functions.Action1;

import static java.lang.String.valueOf;

/**
 * ClaseName：ShopFragment
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/6 10:01
 * Modified By：
 * Fixtime：2019/7/6 10:01
 * FixDescription：
 **/

public class ShopFragment extends XFragment<PShop> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.shop_location_tv)
    TextView shopLocationTv;
    @BindView(R.id.shop_store_ll)
    LinearLayout shopStoreLl;
    @BindView(R.id.shop_recommend_ll)
    LinearLayout shopRecommendLl;
    @BindView(R.id.shop_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.shop_multiplestatusview)
    MultipleStatusView multiplestatusview;
    @BindView(R.id.shop_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.shop_search_sv)
    SearchView shopSearchSV;

    // 每页10行
    private int pageSize = 10;
    protected static final int MAX_PAGE = 30;
    private String beginTime;
    private String endTime;
    private String city = null;
    GetMachinApplyInfoResults.DataBean dataBean;
    ShopAdapter adapter;

    public LocationClient mLocationClient = null;
    private ShopLocationListenner myListener = new ShopLocationListenner();

    private boolean flag = true;
    @Override
    public void initData(Bundle savedInstanceState) {
//        city = AppUser.getInstance().getCity();
//        if (AppTools.isEmpty(city)) {
//            AppUser.getInstance().setCity("广州");
//            city = "广州";
//        }
        initView();
        initAdapter();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }
//        getP().getMachinApplyInfo(AppUser.getInstance().getMerchId(), "4", null, "0", city, null,1, pageSize, beginTime, endTime);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!AppTools.isEmpty(city)) {
            getP().getMachinApplyInfo(AppUser.getInstance().getMerchId(), "4", null, "0", city, null,1, pageSize, beginTime, endTime);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    public PShop newP() {
        return new PShop();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        //搜索框
        initSearch();
        //城市显示
        shopLocationTv.setText(city);
        multiplestatusview.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multiplestatusview.showLoading();
                getP().getMachinApplyInfo(AppUser.getInstance().getMerchId(), "4", null, "0", city, null,1, pageSize, beginTime, endTime);
            }
        });
        getRxPermissions()//获取位置权限
                .request(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) {
                            //TODO 许可
                            initLocation();
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
    }

    /**
     * 初始化Toolbar
     */
    private void initToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        title.setText("铺货");
    }

    /**
     * 适配器
     */
    private void initAdapter() {
        recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        if (adapter == null) {
            adapter = new ShopAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetMachinApplyInfoResults.DataBean, ShopAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetMachinApplyInfoResults.DataBean item, int tag, ShopAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case ShopAdapter.TAG_VIEW:
                            Router.newIntent(context)
                                    .to(ShopContractActivity.class)
                                    .putSerializable("dataBean", item)
                                    .launch();
                            break;
                        default:
                    }
                }
            });
        }
        recyclerview.verticalLayoutManager(context);
        recyclerview.setAdapter(adapter);
        swiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.theme));
        recyclerview.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                getP().getMachinApplyInfo(AppUser.getInstance().getMerchId(), "4", null, "0", city,null, 1, pageSize, beginTime, endTime);
            }

            @Override
            public void onLoadMore(int page) {
                getP().getMachinApplyInfo(AppUser.getInstance().getMerchId(), "4", null, "0", city,null, page, pageSize, beginTime, endTime);
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getMachinApplyInfo(AppUser.getInstance().getMerchId(), "4", null, "0", city, null,1, pageSize, beginTime, endTime);
            }
        });

    }

    /**
     * 点击判断处理
     *
     * @param view
     */
    @OnClick({R.id.shop_recommend_ll, R.id.shop_store_ll, R.id.shop_location_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shop_recommend_ll:
                JumpActivity(RecommendActivity.class);
                break;
            case R.id.shop_store_ll:
                Router.newIntent(context)
                        .to(ShopAroundActivity.class)
                        .launch();
//                showToast("即将上线");
                break;
            case R.id.shop_location_tv:
                //从FirstActivity跳转到SecondActivity的Intent（意图）
                Intent intent = new Intent(context, MerchPickActivity.class);
                //执行Intent ★使用startActivityForResult来启动
                startActivityForResult(intent, 0);
                break;
            default:
        }
    }


    /**
     * 复写onActivityResult方法
     * 当SecondActivity页面关闭时，接收SecondActiviy页面传递过来的数据。
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            Bundle bundle = data.getExtras();
            String param = bundle.getString("param");
            Logger.i("param=" + param);
            shopLocationTv.setText(param);
            city = (String) shopLocationTv.getText();
        }
    }

    /**
     * 标题栏监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
//            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * activity跳转
     */
    public void JumpActivity(Class<?> activity, boolean isfinish) {
        getvDelegate().dismissLoading();
        Router.newIntent(context)
                .to(activity)
                .launch();
        if (isfinish) {
            Router.pop(context);
        }
    }

    /**
     * activity跳转
     */
    public void JumpActivity(Class<?> activity) {
        Router.newIntent(context)
                .to(activity)
                .launch();
    }

    /**
     * 显示Toast
     *
     * @param msg
     */
    public void showToast(String msg) {
        getvDelegate().toastShort(msg);
    }

    /**
     * 显示单按钮对话框
     *
     * @param msg
     */
    public void showErrorDialog(String msg) {
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
        getvDelegate().showError(error);
    }


    public void showEmptyView(String msg) {
        multiplestatusview.showEmpty(msg);
    }

    public void showErrorView(String msg) {
        multiplestatusview.showError(msg);
    }

    public void showErrorView(NetError error) {
        swiperefreshlayout.setRefreshing(false);
        multiplestatusview.showError(getvDelegate().getErrorType(error));
    }

    public void setAdapter(List<GetMachinApplyInfoResults.DataBean> dataBean, int page) {
        swiperefreshlayout.setRefreshing(false);
        multiplestatusview.showContent();
        if (dataBean == null) {
            if (page > 1) {
                //当条数少于默认条数时，调整最大页数
                recyclerview.setPage(page, page);
                recyclerview.removeAllFootView();
            } else {
                multiplestatusview.showEmpty("暂无数据");
            }
            return;
        }
        if (page > 1) {
            adapter.addData(dataBean);
        } else {
            adapter.setData(dataBean);
        }
        if (adapter.getItemCount() < 1) {
            multiplestatusview.showEmpty("暂无数据");
            return;
        }
        if (dataBean.size() < pageSize) {
            //当条数少于默认条数时，调整最大页数
            recyclerview.setPage(page, page);
            recyclerview.removeAllFootView();
        } else {
            //必须设置setPage，否则上拉加载更多会无效
            recyclerview.setPage(page, MAX_PAGE);
        }

    }

    /**
     * 百度地图监听
     */
    public class ShopLocationListenner extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            city = location.getCity().replace("市", "");
            shopLocationTv.setText(location.getCity().replace("市", ""));
            AppUser.getInstance().setCity(location.getCity().replace("市", ""));
            if (flag) {
                getP().getMachinApplyInfo(AppUser.getInstance().getMerchId(), "4", null, "0", city, null,1, pageSize, beginTime, endTime);
                flag = false;
            }
            //获取纬度信息
            AppUser.getInstance().setLatitude(valueOf(location.getLatitude()));
            //获取经度信息
            AppUser.getInstance().setLongitude(valueOf(location.getLongitude()));
        }
    }


    /**
     * 百度地图定位
     * 设置相关参数
     */
    private void initLocation() {
        mLocationClient = new LocationClient(getContext());
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        //可选，默认gcj02，设置返回的定位结果坐标系
        option.setCoorType("bd09ll");
        int span = 1000;
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setScanSpan(span);
        //可选，设置是否需要地址信息，默认不需要
        option.setIsNeedAddress(true);
        //可选，默认false,设置是否使用gps
        option.setOpenGps(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setLocationNotify(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIsNeedLocationPoiList(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setIgnoreKillProcess(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        option.setEnableSimulateGps(false);

        mLocationClient.setLocOption(option);
        mLocationClient.start();

    }

    @Override
    public void onDestroy() {
        mLocationClient.stop();
        super.onDestroy();
    }

    /**
     * 搜索框初始化
     */
    public void initSearch() {
        shopSearchSV.setSubmitButtonEnabled(true);
        shopSearchSV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!AppTools.isEmpty(newText)) {
                    getP().getMachinApplyInfo(AppUser.getInstance().getMerchId(), "4", null, "0", city, newText,1, pageSize, beginTime, endTime);
                }
                return false;
            }
        });
    }
}

