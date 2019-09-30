package com.yzf.king.ui.fragments;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.yzf.king.R;
import com.yzf.king.adapter.OrderManageAdapter;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoResults;
import com.yzf.king.present.POrderPromotion;
import com.yzf.king.ui.activitys.InstallActivity;
import com.yzf.king.ui.activitys.OrderManageApplyDtlActivity;
import com.yzf.king.ui.activitys.OrderManagePromotionDtlActivity;
import com.yzf.king.ui.activitys.OrderManageShopActivity;
import com.yzf.king.widget.MultipleStatusView;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * ClaseName：OrderManageFragment
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/7/12 09:46
 * Modified By：
 * Fixtime：2019/7/12 09:46
 * FixDescription：
 **/

public class OrderManageFragment extends XFragment<POrderPromotion> {

    @BindView(R.id.order_promotion_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.order_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.order_multiplestatusview)
    MultipleStatusView multiplestatusview;
    private OrderManageAdapter adapter;
    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    private String beginTime;
    private String endTime;
    private String type;
    private String status;

    public static OrderManageFragment newInstance(String type, String status) {
        OrderManageFragment fragment = new OrderManageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
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

    @Override
    public void initData(Bundle savedInstanceState) {
        useEventBus(true);
        initView();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getP().getMachinApplyInfo(AppUser.getInstance().getMerchId(), getType(), getStatus(), null,null,1,pageSize,beginTime,endTime);
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
                getP().getMachinApplyInfo(AppUser.getInstance().getMerchId(), getType(), getStatus(), null,null,1,pageSize,beginTime,endTime);
            }
        });
    }

    public void initAdapter() {
        if (adapter == null) {
            adapter = new OrderManageAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetMachinApplyInfoResults.DataBean, OrderManageAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetMachinApplyInfoResults.DataBean item, int tag, OrderManageAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case OrderManageAdapter.TAG_VIEW:
                            switch (item.getStatus()) {
                                case 0://审核中
                                    Router.newIntent(context)
                                            .to(OrderManageApplyDtlActivity.class)
                                            .putSerializable("dataBean",item)
                                            .putString("type",getType())
                                            .launch();
                                    break;
                                case 1://支付宝入驻
                                    if ("1".equals(getType())) {
                                        Router.newIntent(context)
                                                .to(OrderManagePromotionDtlActivity.class)
                                                .putSerializable("applyDataBean",item)
                                                .putString("type",getType())
                                                .launch();
                                    }else {
                                        Router.newIntent(context)
                                                .to(OrderManageApplyDtlActivity.class)
                                                .putSerializable("dataBean",item)
                                                .putString("type",getType())
                                                .launch();
                                    }
                                    break;
                                case 2://待铺货
                                    if (item.getSupplierStatus() == 1 && "3".equals(getType())) {
                                            AppUser.getInstance().setDeviceNum("");
                                            Router.newIntent(context)
                                                    .to(OrderManageShopActivity.class)
                                                    .putSerializable("dataBean",item)
                                                    .putString("type",getType())
                                                    .launch();
                                    } else {
                                        Router.newIntent(context)
                                                .to(OrderManageApplyDtlActivity.class)
                                                .putSerializable("dataBean",item)
                                                .putString("type",getType())
                                                .launch();
                                    }
                                    break;
                                case 3://装机中
                                    if ("3".equals(getType())) {
                                        Router.newIntent(context)
                                                .to(InstallActivity.class)
                                                .putSerializable("dataBean",item)
                                                .putString("type",getType())
                                                .launch();
                                    }else {
                                        Router.newIntent(context)
                                                .to(OrderManageApplyDtlActivity.class)
                                                .putSerializable("dataBean",item)
                                                .putString("type",getType())
                                                .launch();
                                    }
                                    break;
                                case 4://完成
                                    Router.newIntent(context)
                                            .to(OrderManageApplyDtlActivity.class)
                                            .putSerializable("dataBean",item)
                                            .putString("type",getType())
                                            .launch();
                                    break;
                                case 5://审核失败
                                    Router.newIntent(context)
                                            .to(OrderManageApplyDtlActivity.class)
                                            .putSerializable("dataBean",item)
                                            .putString("type",getType())
                                            .launch();
                                    break;
                                case 6://装机失败
                                    Router.newIntent(context)
                                            .to(OrderManageApplyDtlActivity.class)
                                            .putSerializable("dataBean",item)
                                            .putString("type",getType())
                                            .launch();
                                    break;
                                case 7://订单失效
                                    Router.newIntent(context)
                                            .to(OrderManageApplyDtlActivity.class)
                                            .putSerializable("dataBean",item)
                                            .putString("type",getType())
                                            .launch();
                                    break;
                                    default:
                            }
                        default:
                            break;
                    }
                }
            });
        }
        swiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.theme));
        recyclerview.verticalLayoutManager(context);
//        recyclerview.addItemDecoration(new MyDividerItemDecoration(context, MyDividerItemDecoration.HORIZONTAL_LIST));
        recyclerview.setAdapter(adapter);
        recyclerview.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                getP().getMachinApplyInfo(AppUser.getInstance().getMerchId(), getType(), getStatus(), null,null,1,pageSize,beginTime,endTime);
            }

            @Override
            public void onLoadMore(int page) {
                getP().getMachinApplyInfo(AppUser.getInstance().getMerchId(), getType(), getStatus(), null,null,page,pageSize,beginTime,endTime);
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getMachinApplyInfo(AppUser.getInstance().getMerchId(), getType(), getStatus(), null,null,1,pageSize,beginTime,endTime);
            }
        });
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_order_promotion;
    }

    @Override
    public POrderPromotion newP() {
        return new POrderPromotion();
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

    public void setAdapter(List<GetMachinApplyInfoResults.DataBean> dataBean,int page) {
        multiplestatusview.showContent();
        swiperefreshlayout.setRefreshing(false);
        if (dataBean == null) {
            if (page > 1) {
                recyclerview.setPage(page, page);//当条数少于默认条数时，调整最大页数
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
            recyclerview.setPage(page, page);//当条数少于默认条数时，调整最大页数
            recyclerview.removeAllFootView();
        } else {
            recyclerview.setPage(page, MAX_PAGE);//必须设置setPage，否则上拉加载更多会无效
        }
    }

}
