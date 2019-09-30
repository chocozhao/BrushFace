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
import com.yzf.king.adapter.MyDeviceAdapter;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetMyTermResult;
import com.yzf.king.present.PMyDevice;
import com.yzf.king.widget.MultipleStatusView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class MyDeviceActivity extends XActivity<PMyDevice> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.my_device_recyclerView)
    XRecyclerView recyclerview;
    MyDeviceAdapter adapter;
    @BindView(R.id.my_device_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.my_device_available_ll)
    LinearLayout myDeviceAvailableLl;
    @BindView(R.id.my_device_activate_ll)
    LinearLayout myDeviceActivateLl;
    @BindView(R.id.my_device_unactivate_ll)
    LinearLayout myDeviceUnactivateLl;
    @BindView(R.id.my_device_count_tv)
    TextView myDeviceCountTv;
    @BindView(R.id.my_device_available_tv)
    TextView myDeviceAvailableTv;
    @BindView(R.id.my_device_activate_tv)
    TextView myDeviceActivateTv;
    @BindView(R.id.my_device_unactivate_tv)
    TextView myDeviceUnactivateTv;
    @BindView(R.id.my_device_multiplestatusview)
    MultipleStatusView multiplestatusview;

    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    String beginTime;
    String endTime;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        initAdapter();
        getP().getMyDevice(AppUser.getInstance().getMerchId(), 1, pageSize,beginTime,endTime);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_my_device;
    }

    @Override
    public PMyDevice newP() {
        return new PMyDevice();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
    }

    private void initAdapter() {
        if (adapter == null) {
            adapter = new MyDeviceAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetMyTermResult.DataBean.DataListBean, MyDeviceAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetMyTermResult.DataBean.DataListBean item, int tag, MyDeviceAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case MyDeviceAdapter.TAG_VIEW:
                            Router.newIntent(context)
                                    .to(ActivateDtlActivity.class)
                                    .putSerializable("dataListBean",item)
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
                Logger.i("onRefresh");
                getP().getMyDevice(AppUser.getInstance().getMerchId(), 1, pageSize,beginTime,endTime);
            }

            @Override
            public void onLoadMore(int page) {
                getP().getMyDevice(AppUser.getInstance().getMerchId(), page, pageSize,beginTime,endTime);
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getMyDevice(AppUser.getInstance().getMerchId(), 1, pageSize,beginTime,endTime);

            }
        });

    }

    @OnClick({R.id.my_device_available_ll, R.id.my_device_activate_ll, R.id.my_device_unactivate_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_device_available_ll:
                JumpActivity(AvailableActivity.class);
                break;
            case R.id.my_device_activate_ll:
                Router.newIntent(context)
                        .to(ActivateActivity.class)
                        .putString("shopname","shopname")
                        .launch();
                break;
            case R.id.my_device_unactivate_ll:
                JumpActivity(UnactivateActivity.class);
                break;
            default:
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
        title.setText("我的设备");
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


    public void setAdapter(GetMyTermResult data,int page) {
        if (data != null) {
            myDeviceCountTv.setText("我的设备总数:"  + data.getData().getCount()+" (台)");
            myDeviceAvailableTv.setText(data.getData().getUseCount()+"");
            myDeviceActivateTv.setText(data.getData().getActiveCount()+"");
            myDeviceUnactivateTv.setText(data.getData().getUnactiveCount()+"");
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


}
