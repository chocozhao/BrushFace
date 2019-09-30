package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.yzf.king.R;
import com.yzf.king.adapter.WithDrawDetailAdapter;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetTranDtlResult;
import com.yzf.king.present.PWithDrawDetail;
import com.yzf.king.widget.MultipleStatusView;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
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
 * ClassName：TeamManageActivity
 * Description:
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/2/22 10:56
 * Modified By：
 * Fixtime：2019/2/22 10:56
 * FixDescription：
 */
public class WithDrawDetailActivity extends XActivity<PWithDrawDetail> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.withdraw_detail_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.withdraw_detail_multiplestatusview)
    MultipleStatusView multiplestatusview;
    WithDrawDetailAdapter adapter;
    @BindView(R.id.withdraw_detail_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    public static final String TYPE = "type";
    String type;

    String transType = "21";

    @Override
    public void initData(Bundle savedInstanceState) {
        type = getIntent().getStringExtra(TYPE);
        initView();
        initAdapter();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }
        if ("01".equals(type)) {
            transType = "01";

        } else if ("03".equals(type)) {
            transType = "02";
        }
        getP().getTransDtl(AppUser.getInstance().getMerchId(), transType, 1, pageSize);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_withdraw_detail;
    }

    @Override
    public PWithDrawDetail newP() {
        return new PWithDrawDetail();
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
                getP().getTransDtl(AppUser.getInstance().getMerchId(), transType, 1, pageSize);
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
        title.setText("提现记录");
    }

    public void initAdapter() {
        if (adapter == null) {
            adapter = new WithDrawDetailAdapter(context);
        }
        swiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.theme));
        recyclerview.verticalLayoutManager(this);
        recyclerview.setAdapter(adapter);
        recyclerview.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                Logger.i("onRefresh");
                getP().getTransDtl(AppUser.getInstance().getMerchId(), transType, 1, pageSize);
            }

            @Override
            public void onLoadMore(int page) {
                getP().getTransDtl(AppUser.getInstance().getMerchId(), transType, page, pageSize);
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getTransDtl(AppUser.getInstance().getMerchId(), transType, 1, pageSize);
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


    public void setAdapter(List<GetTranDtlResult.DataBean> data, int page) {
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
            adapter.addData(data);
        } else {
            adapter.setData(data);
        }
        if (adapter.getItemCount() < 1) {
            multiplestatusview.showEmpty("暂无数据");
            return;
        }
        if (data.size() < pageSize) {
            recyclerview.setPage(page, page);//当条数少于默认条数时，调整最大页数
            recyclerview.removeAllFootView();
        } else {
            recyclerview.setPage(page, MAX_PAGE);//必须设置setPage，否则上拉加载更多会无效
        }
    }

}
