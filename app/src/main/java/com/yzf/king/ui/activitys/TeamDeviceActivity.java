package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.yzf.king.R;
import com.yzf.king.adapter.TeamDetailAdapter;
import com.yzf.king.adapter.TeamDeviceAdapter;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetTeamTermInfoResult;
import com.yzf.king.present.PTeamDevice;
import com.yzf.king.widget.MultipleStatusView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class TeamDeviceActivity extends XActivity<PTeamDevice> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.team_multiplestatusview)
    MultipleStatusView multiplestatusview;
    TeamDeviceAdapter adapter;
    @BindView(R.id.team_recyclerView)
    XRecyclerView recyclerview;
    @BindView(R.id.team_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.team_device_count_tv)
    TextView teamDeviceCountTv;
    @BindView(R.id.team_device_activate_tv)
    TextView teamDeviceActivateTv;
    @BindView(R.id.team_device_unactivate_tv)
    TextView teamDeviceUnactivateTv;
    @BindView(R.id.team_device_activate_ll)
    LinearLayout teamDeviceActivateLl;
    @BindView(R.id.team_device_unactivate_ll)
    LinearLayout teamDeviceUnactivateLl;
    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    String beginTime;
    String endTime;
    String type;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        initAdapter();
        getP().getTeamDevice(AppUser.getInstance().getMerchId(), 1, pageSize,beginTime,endTime);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_team_device;
    }

    @Override
    public PTeamDevice newP() {
        return new PTeamDevice();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
    }


    private void initAdapter() {
        if (adapter == null) {
            adapter = new TeamDeviceAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetTeamTermInfoResult.DataBean.DataListBean, TeamDeviceAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetTeamTermInfoResult.DataBean.DataListBean item, int tag, TeamDeviceAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case TeamDetailAdapter.TAG_VIEW:
                            Router.newIntent(context)
                                    .to(ActivateDtlActivity.class)
                                    .putSerializable("teamListBean", item)
                                    .launch();
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
                getP().getTeamDevice(AppUser.getInstance().getMerchId(), 1, pageSize,beginTime,endTime);
            }

            @Override
            public void onLoadMore(int page) {
                getP().getTeamDevice(AppUser.getInstance().getMerchId(), page, pageSize,beginTime,endTime);
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getTeamDevice(AppUser.getInstance().getMerchId(), 1, pageSize,beginTime,endTime);
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
        title.setText("团队设备");
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


    public void setAdapter(GetTeamTermInfoResult data, int page) {
        if (data != null) {
            teamDeviceCountTv.setText("我的设备总数:" + data.getData().getCount()+"(台)");
            teamDeviceActivateTv.setText(data.getData().getActiveCount() + "");
            teamDeviceUnactivateTv.setText(data.getData().getUnactiveCount() + "");
        }
        swiperefreshlayout.setRefreshing(false);
        if (data == null) {
            if (page > 1) {
                recyclerview.setPage(page, page);//当条数少于默认条数时，调整最大页数
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
            recyclerview.setPage(page, page);//当条数少于默认条数时，调整最大页数
            recyclerview.removeAllFootView();
        } else {
            recyclerview.setPage(page, MAX_PAGE);//必须设置setPage，否则上拉加载更多会无效
        }
    }


    @OnClick({R.id.team_device_activate_ll, R.id.team_device_unactivate_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.team_device_activate_ll:
                Router.newIntent(context)
                        .to(TeamActivateActivity.class)
                        .putString("teamshopname", "teamshopname")
                        .launch();

                break;
            case R.id.team_device_unactivate_ll:
                JumpActivity(TeamUnactivateActivity.class);
                break;
            default:
        }
    }
}
