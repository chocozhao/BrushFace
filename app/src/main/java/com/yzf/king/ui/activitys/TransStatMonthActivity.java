package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.adapter.TransStatDayAdapter;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetTransInfoCountResult;
import com.yzf.king.present.PTransStatMonths;
import com.yzf.king.widget.MultipleStatusView;
import com.yzf.king.widget.MyDividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * ClaseName：TransStatDayFragment
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/13 20:05
 * Modified By：
 * Fixtime：2019/5/13 20:05
 * FixDescription：
 **/
public class TransStatMonthActivity extends XActivity<PTransStatMonths> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.trans_multiplestatusview)
    MultipleStatusView multiplestatusview;
    @BindView(R.id.recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.transstat_monthdtl_date_tv)
    TextView transstatMonthdtlDateTv;
    @BindView(R.id.transstat_monthdtl_amt_tv)
    TextView transstatMonthdtlAmtTv;
//    @BindView(R.id.trans_swiperefreshlayout)
//    SwipeRefreshLayout swiperefreshlayout;

    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    private String beginTime;
    private String endTime;
    public final static String COUNT = "count";
    public final static String TOTALAMT = "totalamt";
    public final static String DAY = "day";
    private GetTransInfoCountResult.DataBean.InfoBean getTransInfoCountResultdataBean;
    private TransStatDayAdapter adapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        getTransInfoCountResultdataBean = (GetTransInfoCountResult.DataBean.InfoBean) getIntent().getSerializableExtra("dataBean");
        beginTime = getTransInfoCountResultdataBean.getYear() + getTransInfoCountResultdataBean.getMonth() + "01";
        endTime = getTransInfoCountResultdataBean.getYear() + getTransInfoCountResultdataBean.getMonth() + "31";
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_trans_stat_month;
    }

    @Override
    public PTransStatMonths newP() {
        return new PTransStatMonths();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        initAdapter();
        getP().getTransStatMonths(AppUser.getInstance().getMerchId(), "0", null, null, null, beginTime, endTime);

        transstatMonthdtlDateTv.setText(getTransInfoCountResultdataBean.getMonth()+"月交易了"+getTransInfoCountResultdataBean.getCount()+"笔,共计");
        transstatMonthdtlAmtTv.setText("￥"+getTransInfoCountResultdataBean.getTotalAmt());
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
        title.setText("月数据详情");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void initAdapter() {
        if (adapter == null) {
            adapter = new TransStatDayAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetTransInfoCountResult.DataBean.InfoBean, TransStatDayAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetTransInfoCountResult.DataBean.InfoBean item, int tag, TransStatDayAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case TransStatDayAdapter.TAG_VIEW:
                            Router.newIntent(context)
                                    .to(TransStatDtlActivity.class)
                                    .putSerializable("dataBean",item)
                                    .launch();
                            break;
                    }
                }
            });
        }
//        swiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.theme));
        recyclerview.verticalLayoutManager(context);
        recyclerview.addItemDecoration(new MyDividerItemDecoration(context, MyDividerItemDecoration.HORIZONTAL_LIST));
        recyclerview.setAdapter(adapter);
//        recyclerview.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
//            @Override
//            public void onRefresh() {
//                getP().getTransStatMonths(AppUser.getInstance().getMerchId(), "0", null,null, null,beginTime, endTime);
//            }
//
//            @Override
//            public void onLoadMore(int page) {
//                getP().getTransStatMonths(AppUser.getInstance().getMerchId(), "0", null,null, null,beginTime, endTime);
//            }
//        });
//        recyclerview.useDefLoadMoreView();
//        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                getP().getTransStatMonths(AppUser.getInstance().getMerchId(), "0", null,null, null,beginTime, endTime);
//            }
//        });
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


    public void setAdapter(final List<GetTransInfoCountResult.DataBean.InfoBean> data) {
//        swiperefreshlayout.setRefreshing(false);
        if (data == null) {
            multiplestatusview.showEmpty("暂无数据");
            return;
        } else {
            multiplestatusview.showContent();
            adapter.setData(data);
        }
        if (adapter.getItemCount() < 1) {
            multiplestatusview.showEmpty("暂无数据");
            return;
        }
    }

}
