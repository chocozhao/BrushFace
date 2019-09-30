package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.adapter.AvailableAdapter;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.HomeTest;
import com.yzf.king.model.servicesmodels.GetMyTermResult;
import com.yzf.king.present.PActivate;
import com.yzf.king.widget.MultipleStatusView;
import com.yzf.king.widget.MyDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class ActivateActivity extends XActivity<PActivate> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.available_multiplestatusview)
    MultipleStatusView multiplestatusview;
    @BindView(R.id.available_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.available_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.right_text_tv)
    TextView myShopTv;
    private AvailableAdapter adapter;
    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    private String beginTime;
    private String endTime;
    private String shopname;

    @Override
    public void initData(Bundle savedInstanceState) {
        shopname = getIntent().getStringExtra("shopname");
        initView();
        initAdapter();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }
        getP().getMyTermInfo(AppUser.getInstance().getMerchId(),1,pageSize,beginTime,endTime);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_available;
    }

    @Override
    public PActivate newP() {
        return new PActivate();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        myShopTv.setVisibility(View.GONE);
        multiplestatusview.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multiplestatusview.showLoading();
                getP().getMyTermInfo(AppUser.getInstance().getMerchId(),1,pageSize,beginTime,endTime);
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
        title.setText("已激活设备");
    }

    public void initAdapter() {
        if (adapter == null) {
            adapter = new AvailableAdapter(context,shopname);
                adapter.setRecItemClick(new RecyclerItemCallback<GetMyTermResult.DataBean.DataListBean, AvailableAdapter.ViewHolder>() {
                    @Override
                    public void onItemClick(int position, final GetMyTermResult.DataBean.DataListBean item, int tag, AvailableAdapter.ViewHolder holder) {
                        super.onItemClick(position, item, tag, holder);
                        switch (tag) {
                            case AvailableAdapter.TAG_VIEW:
                                Router.newIntent(context)
                                        .to(ActivateDtlActivity.class)
                                        .putSerializable("dataListBean", item)
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
                getP().getMyTermInfo(AppUser.getInstance().getMerchId(),1,pageSize,beginTime,endTime);
            }

            @Override
            public void onLoadMore(int page) {
                getP().getMyTermInfo(AppUser.getInstance().getMerchId(),page,pageSize,beginTime,endTime);
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getMyTermInfo(AppUser.getInstance().getMerchId(),1,pageSize,beginTime,endTime);
            }
        });
    }

    public void refresh() {
        getP().getMyTermInfo(AppUser.getInstance().getMerchId(),1,pageSize,beginTime,endTime);

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

    public void showEmptyView(String msg) {
        multiplestatusview.showEmpty(msg);
    }

    public void showErrorView(String msg) {
        multiplestatusview.showError(msg);
    }

    public void showErrorView(NetError error) {
        multiplestatusview.showError(getvDelegate().getErrorType(error));
    }

    public void setAdapter(GetMyTermResult data,int page) {
//        if (data != null) {
//            myDeviceCountTv.setText("我的设备总数:" + data.getData().getCount());
//            myDeviceAvailableTv.setText(data.getData().getUseCount()+"");
//            myDeviceActivateTv.setText(data.getData().getActiveCount()+"");
//            myDeviceUnactivateTv.setText(data.getData().getUnactiveCount()+"");
//        }
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
