package com.yzf.king.ui.fragments;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.yzf.king.R;
import com.yzf.king.adapter.MyDeviceAdapter;
import com.yzf.king.adapter.TransTeamDeviceAdapter;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetTeamTermInfoResult;
import com.yzf.king.present.PTransTeamDevice;
import com.yzf.king.ui.activitys.TransActivity;
import com.yzf.king.widget.MultipleStatusView;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * ClaseName：TeamDeviceFragment
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/8/22 16:02
 * Modified By：
 * Fixtime：2019/8/22 16:02
 * FixDescription：
 **/

public class TeamDeviceFragment extends XFragment<PTransTeamDevice> {

    @BindView(R.id.promotion_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.promotion_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.promotion_multiplestatusview)
    MultipleStatusView multiplestatusview;
    @BindView(R.id.shop_count_tv)
    TextView shopCountTv;
    private TransTeamDeviceAdapter adapter;
    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    private String beginTime;
    private String endTime;
    private int day;

    public static TeamDeviceFragment newInstance(String type) {
        TeamDeviceFragment fragment = new TeamDeviceFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    public String getType() {
        String type = getArguments().getString("type");
        return type;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        useEventBus(true);
        initView();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }
        getP().getTransTeamDevice(AppUser.getInstance().getMerchId(), 1, pageSize,beginTime,endTime);
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
                getP().getTransTeamDevice(AppUser.getInstance().getMerchId(), 1, pageSize,beginTime,endTime);
            }
        });
    }

    public void initAdapter() {
        if (adapter == null) {
            adapter = new TransTeamDeviceAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetTeamTermInfoResult.DataBean.DataListBean, TransTeamDeviceAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetTeamTermInfoResult.DataBean.DataListBean item, int tag, TransTeamDeviceAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case MyDeviceAdapter.TAG_VIEW:
                            Router.newIntent(context)
                                    .to(TransActivity.class)
                                    .putString("termId",item.getTermId())
                                    .putString("transDevice","transDevice")
                                    .launch();
                            break;
                        default:
                    }
                }
            });
        }
        swiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.theme));
        recyclerview.verticalLayoutManager(context);
        recyclerview.setAdapter(adapter);
        recyclerview.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                getP().getTransTeamDevice(AppUser.getInstance().getMerchId(), 1, pageSize,beginTime,endTime);
            }

            @Override
            public void onLoadMore(int page) {
                getP().getTransTeamDevice(AppUser.getInstance().getMerchId(),page,pageSize,beginTime,endTime);
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getTransTeamDevice(AppUser.getInstance().getMerchId(), 1, pageSize,beginTime,endTime);
            }
        });
    }

    public void refresh() {
        getP().getTransTeamDevice(AppUser.getInstance().getMerchId(), 1, pageSize,beginTime,endTime);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_manage;
    }

    @Override
    public PTransTeamDevice newP() {
        return new PTransTeamDevice();
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

    public void setAdapter(GetTeamTermInfoResult dataBean, int page) {
        if (dataBean != null) {
            shopCountTv.setText("团队设备总数："+dataBean.getData().getActiveCount()+"(台)");
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
