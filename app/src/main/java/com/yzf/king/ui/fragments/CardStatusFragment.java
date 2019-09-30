package com.yzf.king.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.yzf.king.R;
import com.yzf.king.adapter.ProcessPlanAdapter;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetPlanInfoResults;
import com.yzf.king.model.servicesmodels.GetProcessPlanResults;
import com.yzf.king.present.PCardStatusFragment;
import com.yzf.king.widget.MultipleStatusView;

import butterknife.BindView;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

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
 * ClassName：ProcessPlanFragment
 * Description: 收款账单界面
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/5/18 11:59
 * Modified By：
 * Fixtime：2017/5/18 11:59
 * FixDescription：
 */

public class CardStatusFragment extends XFragment<PCardStatusFragment> {
    @BindView(R.id.cardstatus_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.cardstatus_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.cardstatus_multiplestatusview)
    MultipleStatusView multiplestatusview;
    Unbinder unbinder;
    private ProcessPlanAdapter adapter;
    private int page_num = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    private String beginTime;
    private String endTime;
    private String merchId;


    public static CardStatusFragment newInstance(String type, String status, String cardId) {
        CardStatusFragment fragment = new CardStatusFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putString("status", status);
        bundle.putString("cardId", cardId);
        fragment.setArguments(bundle);
        return fragment;
    }

    public String getType() {
        return getArguments().getString("type");
    }

    public String getStatus() {
        return getArguments().getString("status");
    }

    public String getCardId() {
        return getArguments().getString("cardId");
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }
        merchId = AppUser.getInstance().getMerchId();
        getP().GetProcessPlan(1, page_num, beginTime, endTime, merchId, getType(), getStatus(), getCardId());
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
                getP().GetProcessPlan(1, page_num, beginTime, endTime, merchId, getType(), getStatus(), getCardId());
            }
        });
    }

    public void initAdapter() {
        if (adapter == null) {
            adapter = new ProcessPlanAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetProcessPlanResults.DataBean, ProcessPlanAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetProcessPlanResults.DataBean item, int tag, ProcessPlanAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case ProcessPlanAdapter.TAG_VIEW:
                            GetPlanInfoResults.DataBean dataBean = new GetPlanInfoResults.DataBean();
                            dataBean.setOrderId(item.getOrderId());
                            dataBean.setStatus(item.getStatus());
                            if (getType().equals("0")) {
                                if (dataBean.getStatus() != null && dataBean.getStatus().equals("07")) {
                                    showToast("规划正在确认中，请稍后再查看");
                                } else {
//                                    Router.newIntent(context)
//                                            .to(PlanCardDetailsActivity.class)
//                                            .putSerializable("dataBean", dataBean)
//                                            .putString("type", "plan")
//                                            .launch();
                                }
                            } else if (getType().equals("1")) {
                                if (dataBean.getStatus() != null && dataBean.getStatus().equals("07")) {
                                    showToast("规划正在确认中，请稍后再查看");
                                } else {
//                                    Router.newIntent(context)
//                                            .to(PlanCardDetailsActivity.class)
//                                            .putSerializable("dataBean", dataBean)
//                                            .putString("type", "authPlan")
//                                            .launch();
                                }
                            }
                            break;
                        case ProcessPlanAdapter.TAG_END:
                            showNoticeDialog("确定终止该规划吗？", new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    if (which == DialogAction.POSITIVE) {
                                        getvDelegate().showLoading();
                                        getP().planOperator(AppUser.getInstance().getMerchId(), item.getRegId(), "AppEndPlan", getType());
                                    }
                                }
                            });
                            break;
                        case ProcessPlanAdapter.TAG_RESTART:
                            showNoticeDialog("确定重启该规划吗？", new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    if (which == DialogAction.POSITIVE) {
                                        getvDelegate().showLoading();
                                        getP().planOperator(AppUser.getInstance().getMerchId(), item.getRegId(), "AppRebuildPlan", getType());
                                    }
                                }
                            });

                            break;
                    }
                }
            });
        }
        swiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.theme));
        recyclerview.verticalLayoutManager(context);
//        recyclerview.addItemDecoration(new DividerListItemDecoration(context, DividerListItemDecoration.VERTICAL_LIST));
        recyclerview.setAdapter(adapter);
        recyclerview.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                Logger.i("onRefresh");
                getP().GetProcessPlan(1, page_num, beginTime, endTime, merchId, getType(), getStatus(), getCardId());
            }

            @Override
            public void onLoadMore(int page) {
                getP().GetProcessPlan(page, page_num, beginTime, endTime, merchId, getType(), getStatus(), getCardId());
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().GetProcessPlan(1, page_num, beginTime, endTime, merchId, getType(), getStatus(), getCardId());
            }
        });
    }

    public void refresh() {
        getP().GetProcessPlan(1, page_num, beginTime, endTime, merchId, getType(), getStatus(), getCardId());

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_cardstatus;
    }

    @Override
    public PCardStatusFragment newP() {
        return new PCardStatusFragment();
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

    public void setAdapter(GetProcessPlanResults getProcessPlanResults, int page) {
        swiperefreshlayout.setRefreshing(false);
        multiplestatusview.showContent();
        if (page > 1) {
            adapter.addData(getProcessPlanResults.getData());
        } else {
            adapter.setData(getProcessPlanResults.getData());
        }
        if (adapter.getItemCount() < 1) {
            multiplestatusview.showEmpty("暂无数据");
            return;
        }
        if (getProcessPlanResults.getData().size() < page_num) {
            recyclerview.setPage(page, page);//当条数少于默认条数时，调整最大页数
            recyclerview.removeAllFootView();
        } else {
            recyclerview.setPage(page, MAX_PAGE);//必须设置setPage，否则上拉加载更多会无效
        }

    }

}
