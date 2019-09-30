package com.yzf.king.ui.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.yzf.king.R;
import com.yzf.king.adapter.MerchApplySupplierDtlAdapter;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetAgentInfoResults;
import com.yzf.king.present.PMerchApplySupplierDtl;
import com.yzf.king.widget.MultipleStatusView;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class MerchApplySupplierDtlActivity extends XActivity<PMerchApplySupplierDtl> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.merch_apply_supplier_del_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.merch_apply_supplier_del_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.merch_apply_supplier_del_multiplestatusview)
    MultipleStatusView multiplestatusview;
    @BindView(R.id.merch_apply_supplier_del_search_sv)
    SearchView merchApplySupplierDelSearchSv;

    // 每页10行
    private int pageSize = 10;
    protected static final int MAX_PAGE = 30;
    String beginTime;
    String endTime;

    MerchApplySupplierDtlAdapter adapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        initAdapter();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }
        getP().getAgentInfo(null, 1, pageSize, beginTime, endTime);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_merch_apply_supplier_dtl;
    }

    @Override
    public PMerchApplySupplierDtl newP() {
        return new PMerchApplySupplierDtl();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        //搜索框跳转按钮
        merchApplySupplierDelSearchSv.setSubmitButtonEnabled(true);
        //搜索框监听获取
        merchApplySupplierDelSearchSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!AppTools.isEmpty(newText)) {
                    getP().getAgentInfo(newText, 1, pageSize, beginTime, endTime);
                }

                return false;
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
        title.setText("选择供应商");
    }

    public void initAdapter() {
        if (adapter == null) {
            adapter = new MerchApplySupplierDtlAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetAgentInfoResults.DataBean, MerchApplySupplierDtlAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetAgentInfoResults.DataBean item, int tag, MerchApplySupplierDtlAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case MerchApplySupplierDtlAdapter.TAG_VIEW:
                            Intent intent = getIntent();
                            intent.putExtra("phone", item.getPhone());
                            intent.putExtra("name", item.getMerchName());
                            intent.putExtra("merchId", item.getMerchId());
                            setResult(0, intent);
                            finish();
                            break;
                        default:
                            break;
                    }
                }
            });
        }
        recyclerview.verticalLayoutManager(this);
        recyclerview.setAdapter(adapter);
        swiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.theme));
        recyclerview.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                getP().getAgentInfo(null, 1, pageSize, beginTime, endTime);
            }

            @Override
            public void onLoadMore(int page) {
                getP().getAgentInfo(null, page, pageSize, beginTime, endTime);
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getAgentInfo(null, 1, pageSize, beginTime, endTime);
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
     * <p>
     * //     * @param error
     */
    public void showError(NetError error) {
        multiplestatusview.showError(getvDelegate().getErrorType(error));
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

    public void setAdapter(List<GetAgentInfoResults.DataBean> dataBean, int page) {
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
            adapter.addData(dataBean);
        } else {
            adapter.setData(dataBean);
        }
        if (adapter.getItemCount() < 1) {
            multiplestatusview.showEmpty("暂无数据");
            return;
        }
        if (dataBean.size() < pageSize) {
            //当条数少于默认条数时，调整最大页数
            recyclerview.setPage(page, page);
            recyclerview.removeAllFootView();
        } else {
            //必须设置setPage，否则上拉加载更多会无效
            recyclerview.setPage(page, MAX_PAGE);
        }

    }


}
