package com.yzf.king.ui.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lxj.xpopup.XPopup;
import com.yzf.king.R;
import com.yzf.king.adapter.ShopAroundAdapter;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetShopAroundResult;
import com.yzf.king.present.PShopAround;
import com.yzf.king.widget.MultipleStatusView;
import com.yzf.king.widget.PopView.IntelligentFilterPopup;
import com.yzf.king.widget.PopView.TypeFilterPopup;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;


public class ShopAroundActivity extends XActivity<PShopAround> implements TypeFilterPopup.CheckCallBack
        , IntelligentFilterPopup.CheckCallBack {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.shop_around_multiplestatusview)
    MultipleStatusView multiplestatusview;
    @BindView(R.id.shop_around_recyclerView)
    XRecyclerView recyclerview;
    @BindView(R.id.shop_around_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.shop_around_hotel_tv)
    TextView shopAroundHotelTv;
    @BindView(R.id.shop_around_filter_tv)
    TextView shopAroundFilterTv;
    @BindView(R.id.shop_around_address_tv)
    TextView shopAroundAddressTv;
    @BindView(R.id.shop_around_search_sv)
    SearchView shopAroundSearchSv;

    // 每页10行
    private int pageSize = 10;
    protected static final int MAX_PAGE = 30;
    String beginTime;
    String endTime;
    ShopAroundAdapter adapter;
    private TypeFilterPopup typeFilterPopup;
    private IntelligentFilterPopup intelligentFilterPopup;
    String location = null;
    private String city = AppUser.getInstance().getCity();
    private String key = "餐厅";
    private String intelligentType = "default";

    @Override
    public void initData(Bundle savedInstanceState) {
        if (!AppTools.isEmpty(AppUser.getInstance().getLatitude())) {
            location = AppUser.getInstance().getLatitude() + "," + AppUser.getInstance().getLongitude();
        }
        initView();
        initAdapter();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_around;
    }

    @Override
    public PShopAround newP() {
        return new PShopAround();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        shopAroundAddressTv.setText(AppUser.getInstance().getCity());
        multiplestatusview.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multiplestatusview.showLoading();
                if (city != null) {
                    if (city.equals(AppUser.getInstance().getCity())) {
                        getP().getShopAround(AppUser.getInstance().getMerchId(), key, location, null,
                                AppUser.getInstance().getCity(), null, "default", "1", 1, pageSize);
                    } else {
                        getP().getShopAround(AppUser.getInstance().getMerchId(), key, null, city,
                                AppUser.getInstance().getCity(), null, "default", "1", 1, pageSize);
                    }
                }
            }
        });
        //搜索框监听获取
        shopAroundSearchSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (city != null) {
                    if (city.equals(AppUser.getInstance().getCity())) {
                        getP().getShopAround(AppUser.getInstance().getMerchId(), key, location, null,
                                AppUser.getInstance().getCity(), null, "default", "1", 1, pageSize);
                    } else {
                        getP().getShopAround(AppUser.getInstance().getMerchId(), key, null, city,
                                AppUser.getInstance().getCity(), null, "default", "1", 1, pageSize);
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void initAdapter() {
        if (adapter == null) {
            adapter = new ShopAroundAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetShopAroundResult.DataBean, ShopAroundAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetShopAroundResult.DataBean item, int tag, ShopAroundAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case ShopAroundAdapter.TAG_VIEW:
                            if (item.getDetail_info().getDetail_url() != null) {
                                AgenWebViewActivity.launch(context, item.getDetail_info().getDetail_url(), null);
                            }
                            break;
                        default:
                            break;
                    }
                }
            });
        }
        swiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.theme));
        recyclerview.verticalLayoutManager(this);
        recyclerview.setAdapter(adapter);
        recyclerview.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                if (city != null) {
                    if (city.equals(AppUser.getInstance().getCity())) {
                        getP().getShopAround(AppUser.getInstance().getMerchId(), key, location, null,
                                AppUser.getInstance().getCity(), null, "default", "1", 1, pageSize);
                    } else {
                        getP().getShopAround(AppUser.getInstance().getMerchId(), key, null, city,
                                AppUser.getInstance().getCity(), null, "default", "1", 1, pageSize);
                    }
                }
            }

            @Override
            public void onLoadMore(int page) {
                if (city != null) {
                    if (city.equals(AppUser.getInstance().getCity())) {
                        getP().getShopAround(AppUser.getInstance().getMerchId(), key, location, null,
                                AppUser.getInstance().getCity(), null, "default", "1", page, pageSize);
                    } else {
                        getP().getShopAround(AppUser.getInstance().getMerchId(), key, null, city,
                                AppUser.getInstance().getCity(), null, "default", "1", page, pageSize);
                    }
                }
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (city != null) {
                    if (city.equals(AppUser.getInstance().getCity())) {
                        getP().getShopAround(AppUser.getInstance().getMerchId(), key, location, null,
                                AppUser.getInstance().getCity(), null, "default", "1", 1, pageSize);
                    } else {
                        getP().getShopAround(AppUser.getInstance().getMerchId(), key, null, city,
                                AppUser.getInstance().getCity(), null, "default", "1", 1, pageSize);
                    }
                }
            }
        });
    }

    /**
     * 初始化Toolbar
     */
    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left_black_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title.setText("附近门店");
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
            finish();
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
        swiperefreshlayout.setRefreshing(false);
        multiplestatusview.showEmpty(msg);
    }

    public void showErrorView(String msg) {
        swiperefreshlayout.setRefreshing(false);
        multiplestatusview.showError(msg);
    }

    public void showErrorView(NetError error) {
        swiperefreshlayout.setRefreshing(false);
        multiplestatusview.showError(getvDelegate().getErrorType(error));
    }


    public void setAdapter(GetShopAroundResult data, int page) {
        swiperefreshlayout.setRefreshing(false);
        if (data == null) {
            if (page > 1) {
                //当条数少于默认条数时，调整最大页数
                recyclerview.setPage(page, page);
                recyclerview.removeAllFootView();
            } else {
                multiplestatusview.showEmpty("暂无数据");
            }
            return;
        }
        multiplestatusview.showContent();
        if (page > 1) {
            adapter.addData(data.getData());
        } else {
            adapter.setData(data.getData());
        }
        if (adapter.getItemCount() < 1) {
            multiplestatusview.showEmpty("暂无数据");
            return;
        }
        if (data.getData().size() < pageSize) {
            //当条数少于默认条数时，调整最大页数
            recyclerview.setPage(page, page);
            recyclerview.removeAllFootView();
        } else {
            //必须设置setPage，否则上拉加载更多会无效
            recyclerview.setPage(page, MAX_PAGE);
        }
    }

    @OnClick({R.id.shop_around_hotel_tv, R.id.shop_around_filter_tv, R.id.shop_around_address_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shop_around_hotel_tv:
                TypeFilterPopup(view);
                break;
            case R.id.shop_around_filter_tv:
                IntelligentFilterPopup(view);
                break;
            case R.id.shop_around_address_tv:
                //从FirstActivity跳转到SecondActivity的Intent（意图）
                Intent intent = new Intent(context, MerchPickActivity.class);
                //执行Intent ★使用startActivityForResult来启动
                startActivityForResult(intent, 0);
                break;
            default:
                break;
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
            shopAroundAddressTv.setText(param);
            city = (String) shopAroundAddressTv.getText();
        }
    }

    /**
     * 智能筛选
     *
     * @param v
     */
    private void IntelligentFilterPopup(final View v) {
        if (intelligentFilterPopup == null) {
            intelligentFilterPopup = (IntelligentFilterPopup) new XPopup.Builder(context)
                    .atView(v)
                    .autoOpenSoftInput(true)
                    .asCustom(new IntelligentFilterPopup(context));
        }
        intelligentFilterPopup.show();
    }

    /**
     * 查询类型选择
     *
     * @param v
     */
    private void TypeFilterPopup(final View v) {
        if (typeFilterPopup == null) {
            typeFilterPopup = (TypeFilterPopup) new XPopup.Builder(context)
                    .atView(v)
                    .autoOpenSoftInput(true)
                    .asCustom(new TypeFilterPopup(context));
        }
        typeFilterPopup.show();
    }

    /**
     * 类型筛选回调
     *
     * @param type
     */
    @Override
    public void TypeFilterPopupSent(String type) {
        key = type;
        if (city != null) {
            if (city.equals(AppUser.getInstance().getCity())) {
                getP().getShopAround(AppUser.getInstance().getMerchId(), key, location, null,
                        AppUser.getInstance().getCity(), null, "default", "1", 1, pageSize);
            } else {
                getP().getShopAround(AppUser.getInstance().getMerchId(), key, null, city,
                        AppUser.getInstance().getCity(), null, "default", "1", 1, pageSize);
            }
        }
        shopAroundHotelTv.setText(type);
    }


    @Override
    public void onResume() {
        super.onResume();
        TypeFilterPopup.setCallback(this);
        IntelligentFilterPopup.setCallback(this);
        if (!AppTools.isEmpty(location)) {
            if (city != null) {
                if (city.equals(AppUser.getInstance().getCity())) {
                    getP().getShopAround(AppUser.getInstance().getMerchId(), key, location, null,
                            AppUser.getInstance().getCity(), null, "default", "1", 1, pageSize);
                } else {
                    getP().getShopAround(AppUser.getInstance().getMerchId(), key, null, city,
                            AppUser.getInstance().getCity(), null, "default", "1", 1, pageSize);
                }
            }
        } else {
            getP().getShopAround(AppUser.getInstance().getMerchId(), key, null, city,
                    AppUser.getInstance().getCity(), null, "default", "1", 1, pageSize);
        }

    }

    /**
     * 智能筛选回调
     *
     * @param intelligentType
     */
    @Override
    public void IntelligentFilterPopupSent(String intelligentType, String type) {
        this.intelligentType = intelligentType;
        if (city != null) {
            if (city.equals(AppUser.getInstance().getCity())) {
                getP().getShopAround(AppUser.getInstance().getMerchId(), key, location, null,
                        AppUser.getInstance().getCity(), null, type, "1", 1, pageSize);
            } else {
                getP().getShopAround(AppUser.getInstance().getMerchId(), key, null, city,
                        AppUser.getInstance().getCity(), null, type, "1", 1, pageSize);
            }
        }
        shopAroundFilterTv.setText(intelligentType);
    }

}
