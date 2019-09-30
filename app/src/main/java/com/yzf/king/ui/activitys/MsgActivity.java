package com.yzf.king.ui.activitys;

import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.yzf.king.App;
import com.yzf.king.R;
import com.yzf.king.adapter.MsgAdapter;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetPushMsgJGResults;
import com.yzf.king.present.PMsg;
import com.yzf.king.widget.MultipleStatusView;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.cache.SharedPref;
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
 * ClassName：MerchTransActivity
 * Description: 商户交易信息界面
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/3/24 14:10
 * Modified By：
 * Fixtime：2017/3/24 14:10
 * FixDescription：
 */
public class MsgActivity extends XActivity<PMsg> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.msg_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.msg_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.msg_multiplestatusview)
    MultipleStatusView multiplestatusview;
    private MsgAdapter adapter;

    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;

    private String beginTime;
    private String endTime;
    private String merchId;
    public final static String TYPE="type";
    private String type;
    public final static String TITLE="title";
    private String titles;

    @Override
    public void initData(Bundle savedInstanceState) {
        type=getIntent().getStringExtra(TYPE);
        titles=getIntent().getStringExtra(TITLE);
        merchId = AppUser.getInstance().getMerchId();
        initView();
        initAdapter();
        SharedPref.getInstance(App.getContext()).putBoolean("showMsg", false);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }
        getP().getPushMsg(1, pageSize, beginTime, endTime, merchId, null,type);
    }


    public void initAdapter() {
        if (adapter == null) {
            adapter = new MsgAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetPushMsgJGResults.DataBean, MsgAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, GetPushMsgJGResults.DataBean item, int tag, MsgAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case MsgAdapter.TAG_VIEW:
                            Router.newIntent(context)
                                    .to(MsgDetailActivity.class)
                                    .putSerializable("dataBean", item)
                                    .launch();
                            item.setMsgStatus("1");
                            adapter.updateElement(item, position);
                            break;
                            default:
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
                getP().getPushMsg(1, pageSize, beginTime, endTime, merchId, null,type);
            }

            @Override
            public void onLoadMore(int page) {
                getP().getPushMsg(page, pageSize, beginTime, endTime, merchId, null,type);
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getPushMsg(1, pageSize, beginTime, endTime, merchId, null,type);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_msg;
    }


    @Override
    public PMsg newP() {
        return new PMsg();
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
                getP().getPushMsg(1, pageSize, beginTime, endTime, merchId, null,type);
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
        title.setText(titles);
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

    public void setAdapter(List<GetPushMsgJGResults.DataBean> getOrderListResult, int page) {
        swiperefreshlayout.setRefreshing(false);
        multiplestatusview.showContent();
        if (page > 1) {
            adapter.addData(getOrderListResult);
        } else {
            adapter.setData(getOrderListResult);
        }
        if (adapter.getItemCount() < 1) {
            multiplestatusview.showEmpty("暂无数据");
            return;
        }
        if (getOrderListResult.size() < pageSize) {
            recyclerview.setPage(page, page);//当条数少于默认条数时，调整最大页数
            recyclerview.removeAllFootView();
        } else {
            recyclerview.setPage(page, MAX_PAGE);//必须设置setPage，否则上拉加载更多会无效
        }

    }

}
