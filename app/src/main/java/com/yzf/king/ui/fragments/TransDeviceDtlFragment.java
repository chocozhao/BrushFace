package com.yzf.king.ui.fragments;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSONObject;
import com.yzf.king.R;
import com.yzf.king.adapter.TransDeviceDtlAdapter;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetOrdersDtl;
import com.yzf.king.present.PTransDeviceDtl;
import com.yzf.king.widget.MultipleStatusView;
import com.yzf.king.widget.MyDividerItemDecoration;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.event.IBus;
import cn.droidlover.xdroidmvp.event.IEvent;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * ClaseName：TransDeviceDtlFragment
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/22 20:45
 * Modified By：
 * Fixtime：2019/8/22 20:45
 * FixDescription：
 **/

public class TransDeviceDtlFragment extends XFragment<PTransDeviceDtl> {

    @BindView(R.id.trans_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.trans_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.trans_multiplestatusview)
    MultipleStatusView multiplestatusview;
    private TransDeviceDtlAdapter adapter;
    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    private String beginTime;
    private String endTime;
    private int day;

    public static TransDeviceDtlFragment newInstance(String beginTime, String endTime, String type,String termId,String shopId,String status) {
        TransDeviceDtlFragment fragment = new TransDeviceDtlFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putString("beginTime", beginTime);
        bundle.putString("endTime", endTime);
        bundle.putString("termId", termId);
        bundle.putString("shopId", shopId);
        bundle.putString("status", status);
        fragment.setArguments(bundle);
        return fragment;
    }

    public String getType() {
        String type = getArguments().getString("type");
        return type;
    }
    public String getStatus() {
        String status = getArguments().getString("status");
        return status;
    }

    public String getTermId() {
        String termId = getArguments().getString("termId");
        return termId;
    }
    public String getShopId() {
        String shopId = getArguments().getString("shopId");
        return shopId;
    }

    public String getBeginTime() {
        String type = getArguments().getString("beginTime");
        return type;
    }

    public String getEndTime() {
        String type = getArguments().getString("endTime");
        return type;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        useEventBus(true);
        initView();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }
        getP().getOrdersDtl(AppUser.getInstance().getMerchId(), getStatus(), getShopId(), getTermId(), getType(), 1, pageSize, getBeginTime(), getEndTime());
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
                getP().getOrdersDtl(AppUser.getInstance().getMerchId(), getStatus(), getShopId(), getTermId(), getType(), 1, pageSize, getBeginTime(), getEndTime());
            }
        });
    }

    public void initAdapter() {
        if (adapter == null) {
            adapter = new TransDeviceDtlAdapter(context);
        }
        swiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.theme));
        recyclerview.verticalLayoutManager(context);
        recyclerview.addItemDecoration(new MyDividerItemDecoration(context, MyDividerItemDecoration.HORIZONTAL_LIST));
        recyclerview.setAdapter(adapter);
        recyclerview.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                getP().getOrdersDtl(AppUser.getInstance().getMerchId(), getStatus(), getShopId(), getTermId(), getType(), 1, pageSize, getBeginTime(), getEndTime());
            }

            @Override
            public void onLoadMore(int page) {
                getP().getOrdersDtl(AppUser.getInstance().getMerchId(), getStatus(), getShopId(), getTermId(), getType(), page, pageSize, getBeginTime(), getEndTime());
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getOrdersDtl(AppUser.getInstance().getMerchId(), getStatus(), getShopId(), getTermId(), getType(), 1, pageSize, getBeginTime(), getEndTime());
            }
        });
    }

    public void refresh() {
        getP().getOrdersDtl(AppUser.getInstance().getMerchId(), getStatus(), getShopId(), getTermId(), getType(), 1, pageSize, getBeginTime(), getEndTime());
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_trans;
    }

    @Override
    public PTransDeviceDtl newP() {
        return new PTransDeviceDtl();
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
        IBus.IEvent iEvent = new IEvent();
        //更新金额
        iEvent.setId("set_device_dtl_trans");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amt", "0.00");
//        jsonObject.put("date", getBeginTime() + "-" + getEndTime());
        jsonObject.put("date", getBeginTime());
        jsonObject.put("type", getType());
        iEvent.setObject(jsonObject);
        BusProvider.getBus().post(iEvent);
    }

    public void showErrorView(String msg) {
        multiplestatusview.showError(msg);
    }

    public void showErrorView(NetError error) {
        multiplestatusview.showError(getvDelegate().getErrorType(error));
        IBus.IEvent iEvent = new IEvent();
        //更新金额
        iEvent.setId("set_device_dtl_trans");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amt", "0.00");
//        jsonObject.put("date", getBeginTime() + "-" + getEndTime());
        jsonObject.put("date", getBeginTime());
        jsonObject.put("type", getType());
        iEvent.setObject(jsonObject);
        BusProvider.getBus().post(iEvent);
    }

    public void setAdapter(GetOrdersDtl.DataBean dataBean, int page) {
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
        IBus.IEvent iEvent = new IEvent();
        //更新金额
        iEvent.setId("set_device_dtl_trans");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amt", AppTools.formatF2Y(dataBean.getSumAmt()));
//        jsonObject.put("date", getBeginTime() + "-" + getEndTime());
        jsonObject.put("date", getBeginTime());
        jsonObject.put("type", getType());
        iEvent.setObject(jsonObject);
        BusProvider.getBus().post(iEvent);
        if (page > 1) {
            adapter.addData(dataBean.getDataDtl());
        } else {
            adapter.setData(dataBean.getDataDtl());
        }
        if (adapter.getItemCount() < 1) {
            multiplestatusview.showEmpty("暂无数据");
            return;
        }
        if (dataBean.getDataDtl().size() < pageSize) {
            //当条数少于默认条数时，调整最大页数
            recyclerview.setPage(page, page);
            recyclerview.removeAllFootView();
        } else {
            //必须设置setPage，否则上拉加载更多会无效
            recyclerview.setPage(page, MAX_PAGE);
        }

    }

}
