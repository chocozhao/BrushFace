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
import com.yzf.king.adapter.ProfitAdapter;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetProfitResult;
import com.yzf.king.present.PProfitFilter;
import com.yzf.king.ui.activitys.ProfitDtlActivity;
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
import cn.droidlover.xdroidmvp.log.Logger;
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

public class ProfitFilterFragment extends XFragment<PProfitFilter> implements DatePickerDialog.OnDateSetListener {
    @BindView(R.id.profit_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.profit_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.profit_multiplestatusview)
    MultipleStatusView multiplestatusview;
    @BindView(R.id.profit_type_flowlayout)
    TagFlowLayout profitTypeFlowlayout;
    @BindView(R.id.profit_status_flowlayout)
    TagFlowLayout profitStatusFlowlayout;
    @BindView(R.id.begin_time)
    TextView beginTimeTv;
    @BindView(R.id.profit_begintime_rl)
    RelativeLayout profitBegintimeRl;
    @BindView(R.id.end_time)
    TextView endTimeTv;
    @BindView(R.id.profit_endtime_rl)
    RelativeLayout profitEndtimeRl;
    @BindView(R.id.profit_search_bt)
    StateButton profitSearchBt;
    @BindView(R.id.profit_filter_ll)
    LinearLayout profitFilterLl;
    private ProfitAdapter adapter;
    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    private String beginTime;
    private String endTime;
    private int day;
    boolean dateStart = false;
    private String type;
    private String status;

    private String[] transName = new String[]
            {"快捷支付", "Mpos", "管家代还", "权益升级", "账户提现"};
    private String[] transType = new String[]
            {"78，80", "43,52,53,54,55,56,57,58,59", "61,62,63,64,70,71,72,73", "82,83,84,85,86,87,88,89", "01,20"};

    private String[] resultName = new String[]
            {"交易成功", "交易失败", "交易处理中"};
    private String[] resultType = new String[]
            {"01", "02", "00"};

    public static ProfitFilterFragment newInstance(int days, String type,String transType) {
        ProfitFilterFragment fragment = new ProfitFilterFragment();
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
            endTime = Kits.Date.getYymd();
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
            multiplestatusview.showContent();
        }

//        getP().getProfitDaily(AppUser.getInstance().getMerchId(), getType(), 1, pageSize, beginTime, endTime);
    }

    /**
     * 初始化界面
     */
    private void initView() {
        endTime = Kits.Date.getYymd();
        beginTime = Kits.Date.backDate(-60);
        beginTimeTv.setText(beginTime);
        endTimeTv.setText(endTime);
        initAdapter();
        initFlowlayout();//交易结果初始化
        multiplestatusview.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multiplestatusview.showLoading();
                getP().getProfitDaily(AppUser.getInstance().getMerchId(), getType(), 1, pageSize, beginTime, endTime,getTransType());
            }
        });
    }

    private void initFlowlayout() {
        profitTypeFlowlayout.setAdapter(new TagAdapter<String>(transName) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                LayoutInflater mInflater = LayoutInflater.from(getActivity());
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        profitTypeFlowlayout, false);
                return tv;
            }

        });
        profitTypeFlowlayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
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
        });

        profitStatusFlowlayout.setAdapter(new TagAdapter<String>(resultName) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                LayoutInflater mInflater = LayoutInflater.from(getActivity());
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        profitStatusFlowlayout, false);
                tv.setText(s);
                return tv;
            }

        });
        profitStatusFlowlayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                status = "";
                for (Integer integer : selectPosSet) {
                    status = status + resultType[integer] + ",";
                }
                if (status != null && status.length() > 0) {
                    status = status.substring(0, status.length() - 1);
                }
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
                getP().getProfitDaily(AppUser.getInstance().getMerchId(), getType(), 1, pageSize, beginTime, endTime,getTransType());
            }

            @Override
            public void onLoadMore(int page) {
                getP().getProfitDaily(AppUser.getInstance().getMerchId(), getType(), page, pageSize, beginTime, endTime,getTransType());
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getProfitDaily(AppUser.getInstance().getMerchId(), getType(), 1, pageSize, beginTime, endTime,getTransType());
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
                        if (iEvent.getId().equals("show_profit_filter")) {
                            multiplestatusview.showContent();
                            if (adapter.getItemCount() < 1) {
                                profitFilterLl.setVisibility(View.VISIBLE);
                                recyclerview.setVisibility(View.GONE);
                                swiperefreshlayout.setEnabled(false);
                            }
                        }
                        if (iEvent.getId().equals("click_profit_filter")) {
                            multiplestatusview.showContent();
                            profitFilterLl.setVisibility(View.VISIBLE);
                            recyclerview.setVisibility(View.GONE);
                            swiperefreshlayout.setEnabled(false);
                        }
                    }
                });
    }

    public void refresh() {
        getP().getProfitDaily(AppUser.getInstance().getMerchId(), getType(), 1, pageSize, beginTime, endTime,getTransType());

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_profit_filter;
    }

    @Override
    public PProfitFilter newP() {
        return new PProfitFilter();
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
        iEvent.setId("set_profit");//更新金额
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amt", "0.00");
        if (beginTime != null) {
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
        iEvent.setId("set_profit");//更新金额
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amt", "0.00");
        if (beginTime != null) {
            jsonObject.put("date", beginTime + "-" + endTime);
        } else {
            jsonObject.put("date", "不限时间");
        }
        jsonObject.put("day", day);
        iEvent.setObject(jsonObject);
        BusProvider.getBus().post(iEvent);
    }

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
        if (beginTime != null) {
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
            recyclerview.setPage(page, page);//当条数少于默认条数时，调整最大页数
            recyclerview.removeAllFootView();
        } else {
            recyclerview.setPage(page, MAX_PAGE);//必须设置setPage，否则上拉加载更多会无效
        }

    }


    @OnClick({R.id.profit_begintime_rl, R.id.profit_endtime_rl, R.id.profit_search_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.profit_begintime_rl:
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
            case R.id.profit_endtime_rl:
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
            case R.id.profit_search_bt:
                multiplestatusview.showLoading();
                profitFilterLl.setVisibility(View.GONE);
                recyclerview.setVisibility(View.VISIBLE);
                swiperefreshlayout.setEnabled(true);
                getP().getProfitDaily(AppUser.getInstance().getMerchId(), getType(), 1, pageSize, beginTime, endTime,getTransType());
                break;
                default:
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
