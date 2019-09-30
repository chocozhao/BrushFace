package com.yzf.king.ui.fragments;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.yzf.king.R;
import com.yzf.king.adapter.OrderAdapter;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.HomeTest;
import com.yzf.king.model.servicesmodels.GetTransDevicesResult;
import com.yzf.king.model.servicesmodels.GetTransResult;
import com.yzf.king.present.POrder;
import com.yzf.king.present.PTrans;
import com.yzf.king.ui.activitys.OrderDetailActivity;
import com.yzf.king.ui.activitys.OrderDtlActivity;
import com.yzf.king.ui.activitys.TransDtlActivity;
import com.yzf.king.widget.MultipleStatusView;
import com.yzf.king.widget.MyDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * ClaseName：OrderFragment
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/8 20:46
 * Modified By：
 * Fixtime：2019/7/8 20:46
 * FixDescription：
 **/

public class OrderFragment extends XFragment<POrder> {
    @BindView(R.id.order_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.order_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.order_multiplestatusview)
    MultipleStatusView multiplestatusview;
    private OrderAdapter adapter;
    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    private String beginTime;
    private String endTime;
    private int day;

    public static OrderFragment newInstance(String stutas) {
        OrderFragment fragment = new OrderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("status", stutas);
        fragment.setArguments(bundle);
        return fragment;
    }

    public String getStutas() {
        String status = getArguments().getString("status");
        return status;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        useEventBus(true);
        initView();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }
        getP().getTransDevices(AppUser.getInstance().getMerchId(), getStutas(), 1, pageSize, beginTime, endTime);
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initAdapter();
        multiplestatusview.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multiplestatusview.showLoading();
                getP().getTransDevices(AppUser.getInstance().getMerchId(), getStutas(), 1, pageSize, beginTime, endTime);
            }
        });
    }

    public void initAdapter() {
        if (adapter == null) {
            adapter = new OrderAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetTransDevicesResult.DataBean, OrderAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetTransDevicesResult.DataBean item, int tag, OrderAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (item.getStatus()) {
                        case 0:
                            Router.newIntent(context)
                                    .to(OrderDtlActivity.class)
                                    .putSerializable("dataBean", item)
                                    .launch();
                            break;
                        case 1:
                            Router.newIntent(context)
                                    .to(OrderDetailActivity.class)
                                    .putString("id", item.getId())
                                    .putSerializable("transDevicesdataBean",item)
                                    .launch();
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
                getP().getTransDevices(AppUser.getInstance().getMerchId(), getStutas(), 1, pageSize, beginTime, endTime);
            }

            @Override
            public void onLoadMore(int page) {
                getP().getTransDevices(AppUser.getInstance().getMerchId(), getStutas(), page, pageSize, beginTime, endTime);
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getTransDevices(AppUser.getInstance().getMerchId(), getStutas(), 1, pageSize, beginTime, endTime);
            }
        });
    }

    public void refresh() {
        getP().getTransDevices(AppUser.getInstance().getMerchId(), getStutas(), 1, pageSize, beginTime, endTime);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    public POrder newP() {
        return new POrder();
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

    public void setAdapter(List<GetTransDevicesResult.DataBean> dataBean, int page) {
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

}
