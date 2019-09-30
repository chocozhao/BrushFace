package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.yzf.king.R;
import com.yzf.king.adapter.ProfitDtlAdapter;
import com.yzf.king.adapter.TeamAdapter;
import com.yzf.king.adapter.TransDetailAdapter;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetProfitDtlResult;
import com.yzf.king.model.servicesmodels.GetTranDtlResult;
import com.yzf.king.present.PProfitDtl;
import com.yzf.king.widget.MultipleStatusView;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XActivity;
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
 * ClassName：ProfitDtlActivity
 * Description:分润明细
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/2/22 10:56
 * Modified By：
 * Fixtime：2019/2/22 10:56
 * FixDescription：
 */
public class ProfitDtlActivity extends XActivity<PProfitDtl> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.profit_dtl_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.profit_dtl_multiplestatusview)
    MultipleStatusView multiplestatusview;
    public static final String MERCHLEVEL = "merchLevel";
    ProfitDtlAdapter adapter;
    @BindView(R.id.profit_dtl_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.profit_dtl_amt_tv)
    TextView profitDtlAmtTv;
    @BindView(R.id.profit_dtl_date_tv)
    TextView profitDtlDateTv;
    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    private String beginTime;
    private String endTime;
    private String type;
    private String transType;

    public final static String BEGINTIME = "beginTime";
    public final static String ENDTIME = "endTime";
    public final static String TYPE = "type";
    public final static String TRANSTYPE = "transType";

    @Override
    public void initData(Bundle savedInstanceState) {
        beginTime = getIntent().getStringExtra(BEGINTIME);
        endTime = getIntent().getStringExtra(ENDTIME);
        type = getIntent().getStringExtra(TYPE);
        transType = getIntent().getStringExtra(TRANSTYPE);
        initView();
        initAdapter();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }
        getP().getProfitDtl(AppUser.getInstance().getMerchId(), type, null, transType, 1, pageSize, beginTime, endTime);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_profit_dtl;
    }

    @Override
    public PProfitDtl newP() {
        return new PProfitDtl();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        multiplestatusview.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multiplestatusview.showLoading();
                getP().getProfitDtl(AppUser.getInstance().getMerchId(), type, null, transType, 1, pageSize, beginTime, endTime);
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
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left_white_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title.setText("分润明细");
    }

    public void initAdapter() {
        if (adapter == null) {
            adapter = new ProfitDtlAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetProfitDtlResult.DataBean.DataDtlBean, ProfitDtlAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetProfitDtlResult.DataBean.DataDtlBean item, int tag, ProfitDtlAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case TeamAdapter.TAG_VIEW:
                            Router.newIntent(context)
                                    .to(ProfitDetailActivity.class)
                                    .putSerializable("dataBean", item)
                                    .launch();
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
                getP().getProfitDtl(AppUser.getInstance().getMerchId(), type, null, transType, 1, pageSize, beginTime, endTime);
            }

            @Override
            public void onLoadMore(int page) {
                getP().getProfitDtl(AppUser.getInstance().getMerchId(), type, null, transType, page, pageSize, beginTime, endTime);
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getProfitDtl(AppUser.getInstance().getMerchId(), type, null, transType, 1, pageSize, beginTime, endTime);
            }
        });
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


    public void setAdapter(GetProfitDtlResult.DataBean data, int page) {
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
        profitDtlAmtTv.setText("￥" + data.getSumAmt());
        profitDtlDateTv.setText(endTime);
        if (page > 1) {
            adapter.addData(data.getDataDtl());
        } else {
            adapter.setData(data.getDataDtl());
        }
        if (adapter.getItemCount() < 1) {
            multiplestatusview.showEmpty("暂无数据");
            return;
        }
        if (data.getDataDtl().size() < pageSize) {
            recyclerview.setPage(page, page);//当条数少于默认条数时，调整最大页数
            recyclerview.removeAllFootView();
        } else {
            recyclerview.setPage(page, MAX_PAGE);//必须设置setPage，否则上拉加载更多会无效
        }
    }

}
