package com.yzf.king.ui.fragments;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.yzf.king.R;
import com.yzf.king.adapter.OrderAdapter;
import com.yzf.king.adapter.TransTermAdapter;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetTransDevicesResult;
import com.yzf.king.model.servicesmodels.GetTransTermInfoResult;
import com.yzf.king.present.POrder;
import com.yzf.king.present.POrderDetail;
import com.yzf.king.ui.activitys.ActivateDtlActivity;
import com.yzf.king.ui.activitys.OrderDtlActivity;
import com.yzf.king.widget.MultipleStatusView;
import com.yzf.king.widget.MyDividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * ClaseName：OrderDetailFragment
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/31 17:25
 * Modified By：
 * Fixtime：2019/7/31 17:25
 * FixDescription：
 **/

public class OrderDetailFragment extends XFragment<POrderDetail> {

    @BindView(R.id.order_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.order_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.order_multiplestatusview)
    MultipleStatusView multiplestatusview;
    private TransTermAdapter adapter;
    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    private String beginTime;
    private String endTime;
    private int day;

    public static OrderDetailFragment newInstance(GetTransDevicesResult.DataBean transDevicesdataBean, String id, String stutas) {
        OrderDetailFragment fragment = new OrderDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putSerializable("transDevicesdataBean", transDevicesdataBean);
        bundle.putString("status", stutas);
        fragment.setArguments(bundle);
        return fragment;
    }

    public String getStutas() {
        String status = getArguments().getString("status");
        return status;
    }

    public String getid() {
        String id = getArguments().getString("id");
        return id;
    }

    public GetTransDevicesResult.DataBean getdataBean() {
        GetTransDevicesResult.DataBean transDevicesdataBean = (GetTransDevicesResult.DataBean) getArguments().getSerializable("transDevicesdataBean");
        return transDevicesdataBean;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        useEventBus(true);
        initView();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }
        getP().getTransTermInfo(AppUser.getInstance().getMerchId(), getStutas(), getid(), 1, pageSize, beginTime, endTime);
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
                getP().getTransTermInfo(AppUser.getInstance().getMerchId(), getStutas(), getid(), 1, pageSize, beginTime, endTime);
            }
        });
    }

    public void initAdapter() {
        if (adapter == null) {
            adapter = new TransTermAdapter(context, getdataBean().getGoodsId());
            adapter.setRecItemClick(new RecyclerItemCallback<GetTransTermInfoResult.DataBean, TransTermAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetTransTermInfoResult.DataBean item, int tag, TransTermAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case TransTermAdapter.TAG_VIEW:
                            Router.newIntent(context)
                                    .to(ActivateDtlActivity.class)
                                    .putSerializable("transTermDataBean", item)
                                    .putSerializable("transDevicesdataBean", getdataBean())
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
                Logger.i("onRefresh");
                getP().getTransTermInfo(AppUser.getInstance().getMerchId(), getStutas(), getid(), 1, pageSize, beginTime, endTime);
            }

            @Override
            public void onLoadMore(int page) {
                getP().getTransTermInfo(AppUser.getInstance().getMerchId(), getStutas(), getid(), page, pageSize, beginTime, endTime);
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getTransTermInfo(AppUser.getInstance().getMerchId(), getStutas(), getid(), 1, pageSize, beginTime, endTime);
            }
        });
    }

    public void refresh() {
        getP().getTransTermInfo(AppUser.getInstance().getMerchId(), getStutas(), getid(), 1, pageSize, beginTime, endTime);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    public POrderDetail newP() {
        return new POrderDetail();
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

    public void setAdapter(List<GetTransTermInfoResult.DataBean> dataBean, int page) {
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
