package com.yzf.king.ui.fragments;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSONObject;
import com.yzf.king.R;
import com.yzf.king.adapter.TransAdapter;
import com.yzf.king.adapter.TransDetailAdapter;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetBenefitDtlResult;
import com.yzf.king.present.PTransDtl;
import com.yzf.king.ui.activitys.TransDtlActivity;
import com.yzf.king.widget.MultipleStatusView;
import com.yzf.king.widget.MyDividerItemDecoration;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.event.IBus;
import cn.droidlover.xdroidmvp.event.IEvent;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * ClaseName：TransDtlFragment
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/30 15:02
 * Modified By：
 * Fixtime：2019/7/30 15:02
 * FixDescription：
 **/

public class TransDtlFragment extends XFragment<PTransDtl> {
    @BindView(R.id.trans_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.trans_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.trans_multiplestatusview)
    MultipleStatusView multiplestatusview;
    private TransDetailAdapter adapter;
    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    private String beginTime;
    private String endTime;
    private int day;

    public static TransDtlFragment newInstance(String beginTime, String endTime, String type) {
        TransDtlFragment fragment = new TransDtlFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putString("beginTime", beginTime);
        bundle.putString("endTime", endTime);
        fragment.setArguments(bundle);
        return fragment;
    }

    public String getType() {
        String type = getArguments().getString("type");
        return type;
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
        getP().getBenefitDtl(AppUser.getInstance().getMerchId(), getType(), null, null, null, 1, pageSize, getBeginTime(), getEndTime());
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
                getP().getBenefitDtl(AppUser.getInstance().getMerchId(), getType(), null, null, null, 1, pageSize, getBeginTime(), getEndTime());
            }
        });
    }

    public void initAdapter() {
        if (adapter == null) {
            adapter = new TransDetailAdapter(context);
        }
        swiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.theme));
        recyclerview.verticalLayoutManager(context);
        recyclerview.addItemDecoration(new MyDividerItemDecoration(context, MyDividerItemDecoration.HORIZONTAL_LIST));
        recyclerview.setAdapter(adapter);
        recyclerview.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                getP().getBenefitDtl(AppUser.getInstance().getMerchId(), getType(), null, null, null, 1, pageSize, getBeginTime(), getEndTime());
            }

            @Override
            public void onLoadMore(int page) {
                getP().getBenefitDtl(AppUser.getInstance().getMerchId(), getType(), null, null, null, page, pageSize, getBeginTime(), getEndTime());
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getBenefitDtl(AppUser.getInstance().getMerchId(), getType(), null, null, null, 1, pageSize, getBeginTime(), getEndTime());
            }
        });
    }

    public void refresh() {
        getP().getBenefitDtl(AppUser.getInstance().getMerchId(), getType(), null, null, null, 1, pageSize, getBeginTime(), getEndTime());
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_trans;
    }

    @Override
    public PTransDtl newP() {
        return new PTransDtl();
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
        iEvent.setId("set_dtl_trans");
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
        iEvent.setId("set_dtl_trans");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amt", "0.00");
//        jsonObject.put("date", getBeginTime() + "-" + getEndTime());
        jsonObject.put("date", getBeginTime());
        jsonObject.put("type", getType());
        iEvent.setObject(jsonObject);
        BusProvider.getBus().post(iEvent);
    }

    public void setAdapter(GetBenefitDtlResult.DataBean dataBean, int page) {
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
        iEvent.setId("set_dtl_trans");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amt", AppTools.formatL2Y(dataBean.getSumAmt()));
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
