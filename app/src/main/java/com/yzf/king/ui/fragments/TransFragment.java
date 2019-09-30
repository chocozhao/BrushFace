package com.yzf.king.ui.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSONObject;
import com.yzf.king.R;
import com.yzf.king.adapter.TransAdapter;
import com.yzf.king.adapter.TransDeviceAdapter;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetBenefitDailyResult;
import com.yzf.king.model.servicesmodels.GetOrdersDaily;
import com.yzf.king.present.PTrans;
import com.yzf.king.ui.activitys.TransDeviceDtlActivity;
import com.yzf.king.ui.activitys.TransDtlActivity;
import com.yzf.king.widget.MultipleStatusView;
import com.yzf.king.widget.MyDividerItemDecoration;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.event.IBus;
import cn.droidlover.xdroidmvp.event.IEvent;
import cn.droidlover.xdroidmvp.kit.Kits;
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
 * ClassName：transFragment
 * Description: 收款账单界面
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/5/18 11:59
 * Modified By：
 * Fixtime：2017/5/18 11:59
 * FixDescription：
 */

public class TransFragment extends XFragment<PTrans> {
    @BindView(R.id.trans_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.trans_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.trans_multiplestatusview)
    MultipleStatusView multiplestatusview;

    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    private String beginTime;
    private String endTime;
    private int day;
    private TransAdapter adapter;
    private TransDeviceAdapter deviceAdapter;

    public static TransFragment newInstance(int days, String transDevice,String termId,String shopId) {
        TransFragment fragment = new TransFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("days", days);
        bundle.putString("transDevice", transDevice);
        bundle.putString("termId", termId);
        bundle.putString("shopId", shopId);
        fragment.setArguments(bundle);
        return fragment;
    }

    public String getType() {
        int days = getArguments().getInt("days");
        day = days;
        String status = null;
        if (days < 0) {
            endTime = Kits.Date.getYymd();
            beginTime = Kits.Date.backDate(endTime, days);
        }
        return status;
    }

    public String getTransDevice() {
        String transDevice = getArguments().getString("transDevice");
        return transDevice;
    }

    public String getTermId() {
        String termId = getArguments().getString("termId");
        return termId;
    }
    public String getShopId() {
        String shopId = getArguments().getString("shopId");
        return shopId;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        useEventBus(true);
        initView();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }
        if ("transDevice".equals(getTransDevice())) {
            getP().getOrdersDaily(AppUser.getInstance().getMerchId(), getType(), getShopId(), getTermId(), 1, pageSize, beginTime, endTime);
        } else {
            getP().getBenefitDaily(AppUser.getInstance().getMerchId(), null, getType(), null, null, 1, pageSize, beginTime, endTime);
        }
    }

    /**
     * 初始化界面
     */
    private void initView() {
        if ("transDevice".equals(getTransDevice())) {
            //交易明细adapter
            initDeviceAdapter();
        } else {
            //收益明细adapter
            initAdapter();
        }
        multiplestatusview.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multiplestatusview.showLoading();
                if ("transDevice".equals(getTransDevice())) {
                    getP().getOrdersDaily(AppUser.getInstance().getMerchId(), getType(), getShopId(), getTermId(), 1, pageSize, beginTime, endTime);
                } else {
                    getP().getBenefitDaily(AppUser.getInstance().getMerchId(), null, getType(), null, null, 1, pageSize, beginTime, endTime);
                }
            }
        });
    }

    public void initAdapter() {
        //收益明细adapter
        if (adapter == null) {
            adapter = new TransAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetBenefitDailyResult.DataBean.DataDtlBean, TransAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetBenefitDailyResult.DataBean.DataDtlBean item, int tag, TransAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case TransAdapter.TAG_VIEW:
                            Router.newIntent(context)
                                    .to(TransDtlActivity.class)
                                    .putString(TransDtlActivity.TYPE, item.getTotalAmt())
                                    .putString(TransDtlActivity.BEGINTIME, item.getDay())
                                    .putString(TransDtlActivity.ENDTIME, item.getDay())
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
                getP().getBenefitDaily(AppUser.getInstance().getMerchId(), null, getType(), null, null, 1, pageSize, beginTime, endTime);
            }

            @Override
            public void onLoadMore(int page) {
                getP().getBenefitDaily(AppUser.getInstance().getMerchId(), null, getType(), null, null, page, pageSize, beginTime, endTime);
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getBenefitDaily(AppUser.getInstance().getMerchId(), null, getType(), null, null, 1, pageSize, beginTime, endTime);
            }
        });
    }

    public void initDeviceAdapter() {
        //交易明细adapter
        if (deviceAdapter == null) {
            deviceAdapter = new TransDeviceAdapter(context);
            deviceAdapter.setRecItemClick(new RecyclerItemCallback<GetOrdersDaily.DataBean.DataDtlBean, TransDeviceAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetOrdersDaily.DataBean.DataDtlBean item, int tag, TransDeviceAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case TransDeviceAdapter.TAG_VIEW:
                            Router.newIntent(context)
                                    .to(TransDeviceDtlActivity.class)
                                    .putString(TransDeviceDtlActivity.TYPE, item.getTotalAmt())
                                    .putString(TransDeviceDtlActivity.BEGINTIME, item.getDay())
                                    .putString(TransDeviceDtlActivity.ENDTIME, item.getDay())
                                    .putString("termId",getTermId())
                                    .putString("shopId",getShopId())
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
        recyclerview.setAdapter(deviceAdapter);
        recyclerview.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                getP().getOrdersDaily(AppUser.getInstance().getMerchId(), getType(), getShopId(), getTermId(), 1, pageSize, beginTime, endTime);
            }

            @Override
            public void onLoadMore(int page) {
                getP().getOrdersDaily(AppUser.getInstance().getMerchId(), getType(), getShopId(), getTermId(), page, pageSize, beginTime, endTime);
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getOrdersDaily(AppUser.getInstance().getMerchId(), getType(), getShopId(), getTermId(), 1, pageSize, beginTime, endTime);
            }
        });
    }

    public void refresh() {
        getP().getOrdersDaily(AppUser.getInstance().getMerchId(), getType(), getShopId(), getTermId(), 1, pageSize, beginTime, endTime);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_trans;
    }

    @Override
    public PTrans newP() {
        return new PTrans();
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

    public void showEmptyView(String msg) {
        multiplestatusview.showEmpty(msg);
        IBus.IEvent iEvent = new IEvent();
        iEvent.setId("set_trans");//更新金额
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amt", "0.00");
        jsonObject.put("date", beginTime + "-" + endTime);
        jsonObject.put("day", day);
        iEvent.setObject(jsonObject);
        BusProvider.getBus().post(iEvent);
    }

    public void showErrorView(String msg) {
        multiplestatusview.showError(msg);
    }

    public void showErrorView(NetError error) {
        multiplestatusview.showError(getvDelegate().getErrorType(error));
        IBus.IEvent iEvent = new IEvent();
        //更新金额
        iEvent.setId("set_trans");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amt", "0.00");
        jsonObject.put("date", beginTime + "-" + endTime);
        jsonObject.put("day", day);
        iEvent.setObject(jsonObject);
        BusProvider.getBus().post(iEvent);
    }

    public void setAdapter(GetBenefitDailyResult.DataBean dataBean, int page) {
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
        IBus.IEvent iEvent = new IEvent();
        //更新金额
        iEvent.setId("set_trans");
        JSONObject jsonObject = new JSONObject();
        if ("transDevice".equals(getTransDevice())) {
            jsonObject.put("amt", AppTools.formatF2Y(dataBean.getSumAmt()));
        } else {
            jsonObject.put("amt", AppTools.formatL2Y(dataBean.getSumAmt()));
        }
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
            //当条数少于默认条数时，调整最大页数
            recyclerview.setPage(page, page);
            recyclerview.removeAllFootView();
        } else {
            //必须设置setPage，否则上拉加载更多会无效
            recyclerview.setPage(page, MAX_PAGE);
        }

    }


    public void setOrdersAdapter(GetOrdersDaily.DataBean data, int page) {
        swiperefreshlayout.setRefreshing(false);
        if (data == null) {
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
        IBus.IEvent iEvent = new IEvent();
        //更新金额
        iEvent.setId("set_trans");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amt", AppTools.formatF2Y(data.getSumAmt()));
        jsonObject.put("date", beginTime + "-" + endTime);
        jsonObject.put("day", day);
        iEvent.setObject(jsonObject);
        BusProvider.getBus().post(iEvent);
        if (page > 1) {
            deviceAdapter.addData(data.getDataDtl());
        } else {
            deviceAdapter.setData(data.getDataDtl());
        }
        if (deviceAdapter.getItemCount() < 1) {
            multiplestatusview.showEmpty("暂无数据");
            return;
        }
        if (data.getDataDtl().size() < pageSize) {
            //当条数少于默认条数时，调整最大页数
            recyclerview.setPage(page, page);
            recyclerview.removeAllFootView();
        } else {
            //必须设置setPage，否则上拉加载更多会无效
            recyclerview.setPage(page, MAX_PAGE);
        }

    }
}
