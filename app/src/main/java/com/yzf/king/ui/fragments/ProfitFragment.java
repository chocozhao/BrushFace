package com.yzf.king.ui.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSONObject;
import com.yzf.king.R;
import com.yzf.king.adapter.ProfitAdapter;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetProfitResult;
import com.yzf.king.present.PProfit;
import com.yzf.king.ui.activitys.ProfitDtlActivity;
import com.yzf.king.widget.MultipleStatusView;
import com.yzf.king.widget.MyDividerItemDecoration;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.event.IBus;
import cn.droidlover.xdroidmvp.event.IEvent;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XFragment;
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
 * ClassName：profitFragment
 * Description: 分润
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/5/18 11:59
 * Modified By：
 * Fixtime：2017/5/18 11:59
 * FixDescription：
 */

public class ProfitFragment extends XFragment<PProfit> {
    @BindView(R.id.trans_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.trans_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.trans_multiplestatusview)
    MultipleStatusView multiplestatusview;
    private ProfitAdapter adapter;
    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    private String beginTime;
    private String endTime;
    private int day;

    public static ProfitFragment newInstance(int days, String type,String transType) {
        ProfitFragment fragment = new ProfitFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("days", days);
        bundle.putString("type", type);
        bundle.putString("transType", transType);
        fragment.setArguments(bundle);
        return fragment;
    }


    public String getType() {
        int days = getArguments().getInt("days");
        day = days;
        if (days < 0) {
            endTime =Kits.Date.getYymd();
            beginTime = Kits.Date.backDate(endTime, days);
        }
        return getArguments().getString("type");
    }

    public String getTransType() {
        return getArguments().getString("transType");
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        useEventBus(true);
        initView();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }
        getP().getProfitDaily(AppUser.getInstance().getMerchId(), getType(), 1, pageSize, beginTime, endTime,getTransType());
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
                getP().getProfitDaily(AppUser.getInstance().getMerchId(), getType(), 1, pageSize, beginTime, endTime,getTransType());
            }
        });
    }

    public void initAdapter() {
        if (adapter == null) {
            adapter = new ProfitAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetProfitResult.DataBean.DataDtlBean, ProfitAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetProfitResult.DataBean.DataDtlBean item, int tag, ProfitAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case ProfitAdapter.TAG_VIEW:
                            Router.newIntent(context)
                                    .to(ProfitDtlActivity.class)
                                    .putString(ProfitDtlActivity.TYPE, getType())
                                    .putString(ProfitDtlActivity.BEGINTIME, item.getSettDate())
                                    .putString(ProfitDtlActivity.ENDTIME, item.getSettDate())
                                    .putString(ProfitDtlActivity.TRANSTYPE, getTransType())
                                    .launch();
                            break;
                    }
                }
            });
        }
        swiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.theme));//下拉样式的颜色(转圈圈的颜色)
        recyclerview.verticalLayoutManager(context);//垂直
        recyclerview.addItemDecoration(new MyDividerItemDecoration(context, MyDividerItemDecoration.HORIZONTAL_LIST));//加一条线
        recyclerview.setAdapter(adapter);
        recyclerview.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                Logger.i("onRefresh");
                getP().getProfitDaily(AppUser.getInstance().getMerchId(), getType(), 1, pageSize, beginTime, endTime,getTransType());
            }

            @Override
            public void onLoadMore(int page) {
                getP().getProfitDaily(AppUser.getInstance().getMerchId(), getType(), page, pageSize, beginTime, endTime,getTransType());
            }
        });
        recyclerview.useDefLoadMoreView();//加载改样式
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getProfitDaily(AppUser.getInstance().getMerchId(), getType(), 1, pageSize, beginTime, endTime,getTransType());
            }
        });
    }

    public void refresh() {
        getP().getProfitDaily(AppUser.getInstance().getMerchId(), getType(), 1, pageSize, beginTime, endTime,getTransType());

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_trans;
    }

    @Override
    public PProfit newP() {
        return new PProfit();
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

    /**
     * 从P层获取的数据、eventBus传递数据
     * @param msg
     */
    public void showEmptyView(String msg) {
        multiplestatusview.showEmpty(msg);
        IBus.IEvent iEvent = new IEvent();
        iEvent.setId("set_profit");//更新金额
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amt", "0.00");
        jsonObject.put("date", beginTime + "-" + endTime);
        jsonObject.put("day", day);
        iEvent.setObject(jsonObject);
        BusProvider.getBus().post(iEvent);
    }

    /**
     * 从P层获取的错误信息
     * @param msg
     */
    public void showErrorView(String msg) {
        multiplestatusview.showError(msg);
    }

    public void showErrorView(NetError error) {
        multiplestatusview.showError(getvDelegate().getErrorType(error));
        IBus.IEvent iEvent = new IEvent();
        iEvent.setId("set_profit");//更新金额
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amt", "0.00");
        jsonObject.put("date", beginTime + "-" + endTime);
        jsonObject.put("day", day);
        iEvent.setObject(jsonObject);
        BusProvider.getBus().post(iEvent);//发送信息
    }

    /**
     * 从P层获取的数据
     * @param dataBean
     * @param page
     */
    public void setAdapter(GetProfitResult.DataBean dataBean, int page) {
        swiperefreshlayout.setRefreshing(false);
        if (dataBean == null) {
            if (page > 1) {
                recyclerview.setPage(page, page);//当条数少于默认条数时，调整最大页数
                recyclerview.removeAllFootView();

            } else {
                multiplestatusview.showEmpty("暂无数据");
            }
            return;
        }
        multiplestatusview.showContent();
        IBus.IEvent iEvent = new IEvent();
        iEvent.setId("set_profit");//更新金额
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amt", dataBean.getSumAmt());
        jsonObject.put("date", beginTime + "-" + endTime);
        jsonObject.put("day", day);
        iEvent.setObject(jsonObject);
        BusProvider.getBus().post(iEvent);
        if (page > 1) {
            adapter.addData(dataBean.getDataDtl());
        } else {
            adapter.setData(dataBean.getDataDtl());
        }
        if (adapter.getItemCount() < 1) {
            multiplestatusview.showEmpty("暂无数据");
            return;
        }
        if (dataBean.getDataDtl().size() < pageSize) {
            recyclerview.setPage(page, page);//当条数少于默认条数时，调整最大页数
            recyclerview.removeAllFootView();
        } else {
            recyclerview.setPage(page, MAX_PAGE);//必须设置setPage，否则上拉加载更多会无效
        }

    }


}
