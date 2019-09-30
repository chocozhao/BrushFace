package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yzf.king.R;
import com.yzf.king.adapter.AvailableAdapter;
import com.yzf.king.kit.ActivityManager;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetMyTermResult;
import com.yzf.king.model.servicesmodels.GetTermInfoResult;
import com.yzf.king.present.PAvailable;
import com.yzf.king.widget.MultipleStatusView;
import com.yzf.king.widget.MyDividerItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;
import es.dmoral.toasty.Toasty;

public class AvailableActivity extends XActivity<PAvailable> implements AvailableAdapter.CheckCallBack {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.available_multiplestatusview)
    MultipleStatusView multiplestatusview;
    @BindView(R.id.available_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.right_text_tv)
    TextView myShopTv;
    @BindView(R.id.available_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    private AvailableAdapter adapter;
    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    private String beginTime;
    private String endTime;
    public final static String TYPE = "type";
    String type;
    private ArrayList<GetMyTermResult.DataBean.DataListBean> deviceNum = new ArrayList();
    String shopmanage;
    GetTermInfoResult.DataBean.DataListBean data = new GetTermInfoResult.DataBean.DataListBean();
    int number = 0;

    @Override
    public void initData(Bundle savedInstanceState) {
        type = getIntent().getStringExtra(TYPE);
        shopmanage = getIntent().getStringExtra("shopmanage");
        number = getIntent().getIntExtra("number", 0);
        initView();
        initAdapter();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }
        getP().getMyTermInfo(AppUser.getInstance().getMerchId(), 1, pageSize,beginTime,endTime);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_available;
    }

    @Override
    public PAvailable newP() {
        return new PAvailable();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        if (AppTools.isEmpty(shopmanage)) {
            myShopTv.setVisibility(View.GONE);
        } else {
            myShopTv.setVisibility(View.VISIBLE);
            myShopTv.setText("提交");
        }
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
        title.setText("可用设备");
    }

    public void initAdapter() {
        if (adapter == null) {
            adapter = new AvailableAdapter(context, shopmanage, number);
            adapter.setRecItemClick(new RecyclerItemCallback<GetMyTermResult.DataBean.DataListBean, AvailableAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetMyTermResult.DataBean.DataListBean item, int tag, AvailableAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case AvailableAdapter.TAG_VIEW:
                            if (AppTools.isEmpty(shopmanage)) {
                                Router.newIntent(context)
                                        .to(AvailableDtlActivity.class)
                                        .putSerializable("dataListBean", item)
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
                getP().getMyTermInfo(AppUser.getInstance().getMerchId(), 1, pageSize,beginTime,endTime);
                deviceNum.clear();
            }

            @Override
            public void onLoadMore(int page) {
                getP().getMyTermInfo(AppUser.getInstance().getMerchId(), page, pageSize,beginTime,endTime);
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getMyTermInfo(AppUser.getInstance().getMerchId(), 1, pageSize,beginTime,endTime);
                deviceNum.clear();
            }
        });
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
     * 显示错误信息
     *
     * @param error
     */
    public void showError(NetError error) {
        getvDelegate().showError(error);
    }


    @OnClick(R.id.right_text_tv)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_text_tv:
                if (deviceNum.size() < number) {
                    Toasty.normal(context.getApplicationContext(), "您不足" + number + "台设备,请先购买", Toasty.LENGTH_LONG).show();
                    ActivityManager.getInstance().finishAllActivityExceptOne(MainActivity.class);
                    return;
                }
                String devices = "";
                String devicesId = "";
                for (GetMyTermResult.DataBean.DataListBean dataListBean : deviceNum) {
//                    devices = devices + dataListBean.getGoodsName() + ",";
                    devicesId = devicesId + dataListBean.getTermId() + ",";
                }
                if (!AppTools.isEmpty(devices) && devices.length() > 1) {
                    devices = devices.substring(0, devices.length() - 1);
                }
                if (!AppTools.isEmpty(devicesId) && devicesId.length() > 1) {
                    devicesId = devicesId.substring(0, devicesId.length() - 1);
                }
                AppUser.getInstance().setTermId(devicesId);
//                AppUser.getInstance().setDeviceNum(devices);
                AppUser.getInstance().setDeviceNum(deviceNum.get(0).getGoodsName());
                finish();
                break;
            default:
        }
    }

    @Override
    public void sent(ArrayList<GetMyTermResult.DataBean.DataListBean> list) {
        deviceNum = list;
    }

    @Override
    public void onResume() {
        super.onResume();
        AvailableAdapter.setCallback(this);
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


    public void setAdapter(GetMyTermResult data, int page) {
        if (data != null) {
//            myDeviceCountTv.setText("我的设备总数:" + data.getData().getCount());
//            myDeviceAvailableTv.setText(data.getData().getUseCount()+"");
//            myDeviceActivateTv.setText(data.getData().getActiveCount()+"");
//            myDeviceUnactivateTv.setText(data.getData().getUnactiveCount()+"");
//        }
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
                adapter.addData(data.getData().getDataList());
            } else {
                adapter.setData(data.getData().getDataList());
            }
            if (adapter.getItemCount() < 1) {
                multiplestatusview.showEmpty("暂无数据");
                return;
            }
            if (data.getData().getDataList().size() < pageSize) {
                //当条数少于默认条数时，调整最大页数
                recyclerview.setPage(page, page);
                recyclerview.removeAllFootView();
            } else {
                //必须设置setPage，否则上拉加载更多会无效
                recyclerview.setPage(page, MAX_PAGE);
            }
        }
    }
}
