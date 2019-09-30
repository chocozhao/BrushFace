package com.yzf.king.ui.activitys;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.adapter.TransStatDtlAdapter;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetTransInfoCountDtlResult;
import com.yzf.king.model.servicesmodels.GetTransInfoCountResult;
import com.yzf.king.present.PTransStatDtl;
import com.yzf.king.widget.MultipleStatusView;
import com.yzf.king.widget.MyDividerItemDecoration;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * ClaseName：TransStatDtlActivity
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/15 18:48
 * Modified By：
 * Fixtime：2019/5/15 18:48
 * FixDescription：
 **/

public class TransStatDtlActivity extends XActivity<PTransStatDtl> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.transstat_dtl_date_tv)
    TextView transstatDtlDateTv;
    @BindView(R.id.transstat_dtl_amt_tv)
    TextView transstatDtlAmtTv;
    @BindView(R.id.trans_dtl_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.transstat_dtl_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.transstat_dtl_multiplestatusview)
    MultipleStatusView multiplestatusview;

    private TransStatDtlAdapter adapter;
    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    private String beginTime;
    private String endTime;
    private GetTransInfoCountResult.DataBean.InfoBean dataBean;

    @Override
    public void initData(Bundle savedInstanceState) {
        dataBean = (GetTransInfoCountResult.DataBean.InfoBean) getIntent().getSerializableExtra("dataBean");
        beginTime = dataBean.getDay();
        endTime = dataBean.getDay();
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_trans_stat_dtl;
    }

    @Override
    public PTransStatDtl newP() {
        return new PTransStatDtl();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initAdapter();
        initToolbar();
        multiplestatusview.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multiplestatusview.showLoading();
                getP().getTransStatDtl(AppUser.getInstance().getMerchId(), null, null, null, beginTime, endTime, 1, pageSize);
            }
        });
        transstatDtlDateTv.setText(dataBean.getDay() + "日交易了" + dataBean.getCount() + "笔,共计");
        transstatDtlAmtTv.setText("￥" + dataBean.getTotalAmt());
        getP().getTransStatDtl(AppUser.getInstance().getMerchId(), null, null, null, beginTime, endTime, 1, pageSize);
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
        title.setText("日数据");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void initAdapter() {
        if (adapter == null) {
            adapter = new TransStatDtlAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetTransInfoCountDtlResult.DataBean, TransStatDtlAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetTransInfoCountDtlResult.DataBean item, int tag, TransStatDtlAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case TransStatDtlAdapter.TAG_VIEW:
                            Router.newIntent(context)
                                    .to(TransStatDetailActivity.class)
                                    .putSerializable("dataBean", item)
                                    .launch();
                            break;
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
                getP().getTransStatDtl(AppUser.getInstance().getMerchId(), null, null, null, beginTime, endTime, 1, pageSize);
            }

            @Override
            public void onLoadMore(int page) {
                getP().getTransStatDtl(AppUser.getInstance().getMerchId(), null, null, null, beginTime, endTime, page, pageSize);
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getTransStatDtl(AppUser.getInstance().getMerchId(), null, null, null, beginTime, endTime, 1, pageSize);
            }
        });
    }

    public void setAdapter(GetTransInfoCountDtlResult data, int page) {
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
            adapter.addData(data.getData());
        } else {
            adapter.setData(data.getData());
        }
        if (adapter.getItemCount() < 1) {
            multiplestatusview.showEmpty("暂无数据");
            return;
        }
        if (data.getData().size() < pageSize) {
            recyclerview.setPage(page, page);//当条数少于默认条数时，调整最大页数
            recyclerview.removeAllFootView();
        } else {
            recyclerview.setPage(page, MAX_PAGE);//必须设置setPage，否则上拉加载更多会无效
        }
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

}
