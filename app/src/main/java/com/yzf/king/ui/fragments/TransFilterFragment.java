package com.yzf.king.ui.fragments;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSONObject;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.yzf.king.R;
import com.yzf.king.adapter.TransAdapter;
import com.yzf.king.adapter.TransDeviceAdapter;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetBenefitDailyResult;
import com.yzf.king.model.servicesmodels.GetOrdersDaily;
import com.yzf.king.present.PTransFilter;
import com.yzf.king.ui.activitys.TransDeviceDtlActivity;
import com.yzf.king.ui.activitys.TransDtlActivity;
import com.yzf.king.widget.MultipleStatusView;
import com.yzf.king.widget.MyDividerItemDecoration;
import com.yzf.king.widget.StateButton;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Calendar;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.event.IBus;
import cn.droidlover.xdroidmvp.event.IEvent;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;
import rx.functions.Action1;

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

public class TransFilterFragment extends XFragment<PTransFilter> implements DatePickerDialog.OnDateSetListener {
    @BindView(R.id.trans_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.trans_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.trans_multiplestatusview)
    MultipleStatusView multiplestatusview;
    @BindView(R.id.trans_type_flowlayout)
    TagFlowLayout transTypeFlowlayout;
    @BindView(R.id.trans_status_flowlayout)
    TagFlowLayout transStatusFlowlayout;
    @BindView(R.id.begin_time)
    TextView beginTimeTv;
    @BindView(R.id.trans_begintime_rl)
    RelativeLayout transBegintimeRl;
    @BindView(R.id.end_time)
    TextView endTimeTv;
    @BindView(R.id.trans_endtime_rl)
    RelativeLayout transEndtimeRl;
    @BindView(R.id.trans_search_bt)
    StateButton transSearchBt;
    @BindView(R.id.trans_filter_ll)
    LinearLayout transFilterLl;
    @BindView(R.id.trans_type_flowlayout_ll)
    LinearLayout transTypeFlowlayoutLl;
    @BindView(R.id.trans_status_flowlayout_ll)
    LinearLayout transStatusFlowlayoutLl;
    private TransAdapter adapter;
    private TransDeviceAdapter deviceAdapter;
    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    private String beginTime;
    private String endTime;
    private int day;
    boolean dateStart = false;
    private String type;
    private String status;

    private String[] transName = new String[]
            {"不限", "支付宝", "微信", "银联"};
    private String[] transType = new String[]
            {"", "ALIPAY", "WXPAY", "UNIONPAY"};

    private String[] resultName = new String[]
            {"交易成功", "交易失败", "交易处理中"};
    private String[] resultType = new String[]
            {"01", "02", "00"};

    public static TransFilterFragment newInstance(int days, String transDevice,String termId,String shopId) {
        TransFilterFragment fragment = new TransFilterFragment();
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
            multiplestatusview.showContent();
        }
    }

    /**
     * 初始化界面
     */
    private void initView() {
        endTime = Kits.Date.getYymd();
        beginTime = Kits.Date.backDate(-60);
        beginTimeTv.setText(beginTime);
        endTimeTv.setText(endTime);
        if ("transDevice".equals(getTransDevice())) {
            //交易明细adapter
            initDeviceAdapter();
            transTypeFlowlayoutLl.setVisibility(View.GONE);
            transStatusFlowlayoutLl.setVisibility(View.VISIBLE);
        } else {
            //收益明细adapter
            initAdapter();
        }
        initFlowlayout();
        multiplestatusview.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multiplestatusview.showLoading();
                if ("transDevice".equals(getTransDevice())) {
                    //交易明细请求
                    getP().getOrdersDaily(AppUser.getInstance().getMerchId(), getType(), getShopId(), getTermId(), 1, pageSize, beginTime, endTime);
                } else {
                    //收益明细请求
                    getP().getBenefitDaily(AppUser.getInstance().getMerchId(), null, getType(), null, null, 1, pageSize, beginTime, endTime);
                }
            }
        });
    }

    private void initFlowlayout() {
       /* transTypeFlowlayout.setMaxSelectCount(1);
        transTypeFlowlayout.setAdapter(new TagAdapter<String>(transName) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                LayoutInflater mInflater = LayoutInflater.from(getActivity());
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        transTypeFlowlayout, false);
                return tv;
            }

        });
        transTypeFlowlayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                type = "";
                for (Integer integer : selectPosSet) {
                    type = type + transType[integer] + ",";
                }
                if (type != null && type.length() > 0) {
                    type = type.substring(0, type.length() - 1);
                }
            }
        });*/

        transStatusFlowlayout.setMaxSelectCount(1);
        transStatusFlowlayout.setAdapter(new TagAdapter<String>(transName) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                LayoutInflater mInflater = LayoutInflater.from(getActivity());
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        transStatusFlowlayout, false);
                tv.setText(s);
                return tv;
            }

        });
        transStatusFlowlayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                status = "";
                for (Integer integer : selectPosSet) {
                    status = status + transType[integer] + ",";
                }
                if (status != null && status.length() > 0) {
                    status = status.substring(0, status.length() - 1);
                }
            }
        });
    }

    //收益明细adapter
    public void initAdapter() {
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

    //交易明细adapter
    public void initDeviceAdapter() {
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
                                    .putString("status",status)
                                    .launch();
                            break;
                        default:
                            break;
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

    @Override
    public void bindEvent() {
        BusProvider.getBus().toObservable(IEvent.class)
                .subscribe(new Action1<IEvent>() {
                    @Override
                    public void call(IEvent iEvent) {
                        //TODO 事件处理
                        if (iEvent.getId().equals("show_trans_filter")) {
                            multiplestatusview.showContent();
                            if ("transDevice".equals(getTransDevice())) {
                                if (deviceAdapter.getItemCount() < 1) {
                                    transFilterLl.setVisibility(View.VISIBLE);
                                    recyclerview.setVisibility(View.GONE);
                                    swiperefreshlayout.setEnabled(false);
                                }
                            } else {
                                if (adapter.getItemCount() < 1) {
                                    transFilterLl.setVisibility(View.VISIBLE);
                                    recyclerview.setVisibility(View.GONE);
                                    swiperefreshlayout.setEnabled(false);
                                }
                            }

                        }
                        if (iEvent.getId().equals("click_trans_filter")) {
                            multiplestatusview.showContent();
                            transFilterLl.setVisibility(View.VISIBLE);
                            recyclerview.setVisibility(View.GONE);
                            swiperefreshlayout.setEnabled(false);
                        }
                    }
                });
    }

    public void refresh() {
        if ("transDevice".equals(getTransDevice())) {
            //交易明细请求
            getP().getOrdersDaily(AppUser.getInstance().getMerchId(), getType(), getShopId(), getTermId(), 1, pageSize, beginTime, endTime);
        } else {
            //收益明细请求
            getP().getBenefitDaily(AppUser.getInstance().getMerchId(), null, getType(), null, null, 1, pageSize, beginTime, endTime);
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_trans_filter;
    }

    @Override
    public PTransFilter newP() {
        return new PTransFilter();
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
        //更新金额
        iEvent.setId("set_trans");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amt", "0.00");
        if (beginTime != null && endTime != null) {
            jsonObject.put("date", beginTime + "-" + endTime);
        } else {
            jsonObject.put("date", "不限时间");
        }
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
        if (beginTime != null && endTime != null) {
            jsonObject.put("date", beginTime + "-" + endTime);
        } else {
            jsonObject.put("date", "不限时间");
        }
        jsonObject.put("day", day);
        iEvent.setObject(jsonObject);
        BusProvider.getBus().post(iEvent);
    }

    /**
     * 收益明细获取数据
     *
     * @param dataBean
     * @param page
     */
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
        jsonObject.put("amt", AppTools.formatL2Y(dataBean.getSumAmt()));
        if (beginTime != null && endTime != null) {
            jsonObject.put("date", beginTime + "-" + endTime);
        } else {
            jsonObject.put("date", "不限时间");
        }
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


    /**
     * 交易明细数据
     *
     * @param data
     * @param page
     */
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
        if (beginTime != null && endTime != null) {
            jsonObject.put("date", beginTime + "-" + endTime);
        } else {
            jsonObject.put("date", "不限时间");
        }
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

    @OnClick({R.id.trans_begintime_rl, R.id.trans_endtime_rl, R.id.trans_search_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.trans_begintime_rl:
                Calendar calendarStart = Calendar.getInstance();
                DatePickerDialog datePickerDialogStart = DatePickerDialog.newInstance(
                        this,
                        calendarStart.get(Calendar.YEAR),
                        calendarStart.get(Calendar.MONTH),
                        calendarStart.get(Calendar.DAY_OF_MONTH)
                );
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                datePickerDialogStart.show(fragmentManager, "Datepickerdialog");
                dateStart = true;
                break;
            case R.id.trans_endtime_rl:
                Calendar calendarEnd = Calendar.getInstance();
                DatePickerDialog datePickerDialogEnd = DatePickerDialog.newInstance(
                        this,
                        calendarEnd.get(Calendar.YEAR),
                        calendarEnd.get(Calendar.MONTH),
                        calendarEnd.get(Calendar.DAY_OF_MONTH)
                );
                fragmentManager = getActivity().getFragmentManager();
                datePickerDialogEnd.show(fragmentManager, "Datepickerdialog");
                dateStart = false;
                break;
            case R.id.trans_search_bt:
                multiplestatusview.showLoading();
                transFilterLl.setVisibility(View.GONE);
                recyclerview.setVisibility(View.VISIBLE);
                swiperefreshlayout.setEnabled(true);
                if ("transDevice".equals(getTransDevice())) {
                    //交易明细请求
                    getP().getOrdersDaily(AppUser.getInstance().getMerchId(), status, getShopId(), getTermId(), 1, pageSize, beginTime, endTime);
                } else {
                    //收益明细请求
                    getP().getBenefitDaily(AppUser.getInstance().getMerchId(), null, getType(), null, null, 1, pageSize, beginTime, endTime);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        monthOfYear = monthOfYear + 1;
        String month;
        if (monthOfYear < 10) {
            month = "0" + monthOfYear;
        } else {
            month = monthOfYear + "";
        }
        String day;
        if (dayOfMonth < 10) {
            day = "0" + dayOfMonth;
        } else {
            day = dayOfMonth + "";
        }

        if (dateStart) {
            beginTime = year + month + day;
            beginTimeTv.setText(beginTime);
        } else {
            endTime = year + month + day;
            endTimeTv.setText(endTime);
        }

    }


}
