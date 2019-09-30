package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.yzf.king.R;
import com.yzf.king.adapter.MyShopAdapter;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetMyShopInfoResult;
import com.yzf.king.present.PMyShop;
import com.yzf.king.widget.MultipleStatusView;
import com.yzf.king.widget.MyDividerItemDecoration;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class MyShopActivity extends XActivity<PMyShop> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.myshop_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.my_shop_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.my_shop_multiplestatusview)
    MultipleStatusView multiplestatusview;
    @BindView(R.id.my_shop_count_tv)
    TextView myShopCountTv;
    private MyShopAdapter adapter;
    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    private String beginTime;
    private String endTime;
    private int day;
    private String transDevice;
    String type = "1";

    @Override
    public void initData(Bundle savedInstanceState) {
        transDevice = getIntent().getStringExtra("transDevice");
        useEventBus(true);
        initView();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }
        getP().getShopInfo(AppUser.getInstance().getMerchId(), type, 1, pageSize, beginTime, endTime);
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        initAdapter();
        multiplestatusview.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multiplestatusview.showLoading();
                getP().getShopInfo(AppUser.getInstance().getMerchId(), type, 1, pageSize, beginTime, endTime);

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
        title.setText("我的店铺");
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


    public void initAdapter() {
        if (adapter == null) {
            adapter = new MyShopAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetMyShopInfoResult.DataBean.DataListBean, MyShopAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetMyShopInfoResult.DataBean.DataListBean item, int tag, MyShopAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case MyShopAdapter.TAG_VIEW:
                            if ("transDevice".equals(transDevice)) {
                                Router.newIntent(context)
                                        .to(TransActivity.class)
                                        .putString("shopId",item.getShopId())
                                        .putString("transDevice", "transDevice")
                                        .launch();
                            } else {
                                Router.newIntent(context)
                                        .to(ShopManageDtlActivity.class)
                                        .putSerializable("dataBean", item)
                                        .putString("myShopType", type)
                                        .launch();
                            }
                            break;
                        default:
                    }
                }
            });
        }
        swiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.theme));
        recyclerview.verticalLayoutManager(context);
        recyclerview.addItemDecoration(new MyDividerItemDecoration(context, MyDividerItemDecoration.HORIZONTAL_LIST));
        recyclerview.setAdapter(adapter);
        recyclerview.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                getP().getShopInfo(AppUser.getInstance().getMerchId(), type, 1, pageSize, beginTime, endTime);

            }

            @Override
            public void onLoadMore(int page) {
                getP().getShopInfo(AppUser.getInstance().getMerchId(), type, page, pageSize, beginTime, endTime);

            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getShopInfo(AppUser.getInstance().getMerchId(), type, 1, pageSize, beginTime, endTime);

            }
        });
    }

    public void refresh() {
        getP().getShopInfo(AppUser.getInstance().getMerchId(), type, 1, pageSize, beginTime, endTime);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_shop;
    }

    @Override
    public PMyShop newP() {
        return new PMyShop();
    }

    public void JumpActivity(Class<?> activity) {
        Router.newIntent(context)
                .to(activity)
                .launch();
    }

    public void JumpActivity(Class<?> activity, String serviceId) {
        Router.newIntent(context)
                .to(activity)
                .putString("serviceId", serviceId)
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
        multiplestatusview.showError(getvDelegate().getErrorType(error));
    }

    public void setAdapter(GetMyShopInfoResult dataBean, int page) {
        if (dataBean != null) {
            myShopCountTv.setText("店铺总数：" + dataBean.getData().getCount());
        }
        swiperefreshlayout.setRefreshing(false);
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
        multiplestatusview.showContent();
        if (page > 1) {
            adapter.addData(dataBean.getData().getDataList());
        } else {
            adapter.setData(dataBean.getData().getDataList());
        }
        if (adapter.getItemCount() < 1) {
            multiplestatusview.showEmpty("暂无数据");
            return;
        }
        if (dataBean.getData().getDataList().size() < pageSize) {
            //当条数少于默认条数时，调整最大页数
            recyclerview.setPage(page, page);
            recyclerview.removeAllFootView();
        } else {
            //必须设置setPage，否则上拉加载更多会无效
            recyclerview.setPage(page, MAX_PAGE);
        }

    }

}
