package com.yzf.king.ui.activitys;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.yzf.king.R;
import com.yzf.king.adapter.WaitShopAdapter;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoDtlResult;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoResults;
import com.yzf.king.model.servicesmodels.GetUrlResult;
import com.yzf.king.present.POrderManageShop;
import com.yzf.king.widget.MultipleStatusView;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class OrderManageShopActivity extends XActivity<POrderManageShop> implements DatePickerDialog.OnDateSetListener {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.wait_shop_recyclerview)
    XRecyclerView recyclerview;
    WaitShopAdapter adapter;
    @BindView(R.id.wait_shop_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.wait_shop_multiplestatusview)
    MultipleStatusView multiplestatusview;
    @BindView(R.id.wait_shop_define_bt)
    Button waitShopDefineBt;
    @BindView(R.id.ordermanageshop_hear_iv)
    ImageView ordermanageshopHearIv;
    @BindView(R.id.ordermanageshop_title_tv)
    TextView ordermanageshopTitleTv;
    @BindView(R.id.ordermanageshop_stauts_tv)
    TextView ordermanageshopStautsTv;
    @BindView(R.id.ordermanageshop_address_tv)
    TextView ordermanageshopAddressTv;
    @BindView(R.id.ordermanageshop_count_tv)
    TextView ordermanageshopCountTv;
    @BindView(R.id.ordermanageshop_date_select_tv)
    TextView ordermanageshopSelectTv;
    @BindView(R.id.ordermanageshop_date_select_iv)
    ImageView ordermanageshopIv;
    @BindView(R.id.ordermanageshop_date_select_rl)
    RelativeLayout ordermanageshopDateSelectRl;
    @BindView(R.id.ordermanageshop_device_rl)
    RelativeLayout ordermanageshopDeviceRl;
    @BindView(R.id.ordermanageshop_device_tv)
    TextView ordermanageshopDeviceTv;
    @BindView(R.id.ordermanageshop_device_select_tv)
    TextView ordermanageshopDeviceSelectTv;
    @BindView(R.id.ordermanageshop_shopcontract_cb)
    CheckBox ordermanageshopShopcontractCb;
    @BindView(R.id.ordermanageshop_shopcontract_tv)
    TextView ordermanageshopShopcontractTv;
    @BindView(R.id.ordershopmanage_receipt_tv)
    TextView ordershopmanageReceiptTv;
    @BindView(R.id.ordershopmanage_receipt_ll)
    LinearLayout ordershopmanageReceiptLl;
    @BindView(R.id.ordershopmanage_mail_tv)
    TextView ordershopmanageMailTv;
    @BindView(R.id.ordershopmanage_mail_ll)
    LinearLayout ordershopmanageMailLl;


    String date;
    GetMachinApplyInfoDtlResult.DataBean dataBeanInfo = new GetMachinApplyInfoDtlResult.DataBean();
    GetMachinApplyInfoResults.DataBean dataBean;
    String type;
    @Override
    public void initData(Bundle savedInstanceState) {
        dataBean = (GetMachinApplyInfoResults.DataBean) getIntent().getSerializableExtra("dataBean");
        type = getIntent().getStringExtra("type");
        initView();
        initAdapter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_wait_shop;
    }

    @Override
    public POrderManageShop newP() {
        return new POrderManageShop();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        getP().getMachinApplyInfoDtl(AppUser.getInstance().getMerchId(), dataBean.getOrderId(), dataBean.getShopId());
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
        title.setText(dataBean.getShopName());
    }


    private void initAdapter() {
        if (adapter == null) {
            adapter = new WaitShopAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetMachinApplyInfoDtlResult.DataBean.TermInfoListBean, WaitShopAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetMachinApplyInfoDtlResult.DataBean.TermInfoListBean item, int tag, WaitShopAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case WaitShopAdapter.TAG_VIEW:
                            Router.newIntent(context)
                                    .to(OrderShopActivity.class)
                                    .launch();
                            break;
                        default:
                            break;
                    }
                }
            });
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


    @Override
    protected void onResume() {
        super.onResume();
        ordermanageshopDeviceSelectTv.setText(AppUser.getInstance().getDeviceNum());
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
     * 显示Toast
     *
     * @param msg
     */
    public void showToastLong(String msg) {
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


    @OnClick({R.id.wait_shop_define_bt, R.id.ordermanageshop_date_select_rl, R.id.ordermanageshop_device_rl,
            R.id.ordermanageshop_shopcontract_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wait_shop_define_bt:
                if (AppTools.isEmpty(ordermanageshopSelectTv.getText().toString())) {
                    showToast("请选择装机时间");
                    return;
                }
                if (AppTools.isEmpty(ordermanageshopDeviceSelectTv.getText().toString())) {
                    showToast("请选择设备");
                    return;
                }
                getvDelegate().showNoticeDialog("确认后，被选择的设备将不可再分配给其他商家", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            getP().shopConfirm(AppUser.getInstance().getMerchId(), dataBean.getOrderId(), AppUser.getInstance().getTermId(), date, "SUCCESS");
                        }
                    }
                });
                break;
            case R.id.ordermanageshop_date_select_rl:
                Calendar calendarStart = Calendar.getInstance();
                DatePickerDialog datePickerDialogStart = DatePickerDialog.newInstance(
                        this,
                        calendarStart.get(Calendar.YEAR),
                        calendarStart.get(Calendar.MONTH),
                        calendarStart.get(Calendar.DAY_OF_MONTH)
                );
                FragmentManager fragmentManager = context.getFragmentManager();
                datePickerDialogStart.show(fragmentManager, "Datepickerdialog");
                break;
            case R.id.ordermanageshop_device_rl:
                Router.newIntent(context)
                        .to(AvailableActivity.class)
                        .putString("shopmanage", "shopmanage")
                        .putInt("number", dataBeanInfo.getMachinNum())
                        .launch();
                break;
            case R.id.ordermanageshop_shopcontract_tv:
                List<GetUrlResult.DataBean> list3 = AppUser.getInstance().getUrlBean();
                String target3 = null;//http://h5.yiyoupay.net/link/html/directions.html?topBranchNo=20000029
                if (list3 != null && list3.size() > 0) {
                    for (GetUrlResult.DataBean dataBean : list3) {
                        if (dataBean.getType().equals("distributionUrl")) {
                            target3 = dataBean.getUrl();
                            break;
                        }
                    }
                }
                if (!AppTools.isEmpty(target3)) {
                    AgenWebViewActivity.launch(context, target3, null);
                } else {
                    showToast("暂未开放");
                }
                break;
            default:
        }

    }

    public void setAdapter(GetMachinApplyInfoDtlResult.DataBean data) {
        if (data != null) {
            /*dataBean = new GetMachinApplyInfoDtlResult.DataBean();
            dataBean.setAddress(data.getAddress());
            dataBean.setCityName(data.getCityName());
            dataBean.setProvinceName(data.getProvinceName());
            dataBean.setPhone(data.getPhone());
            dataBean.setStatus(data.getStatus());
            dataBean.setShopLogo(data.getShopLogo());
            dataBean.setShopName(data.getShopName());*/
            dataBeanInfo.setMachinNum(data.getMachinNum());
            if (data.getShopName() != null) {
                ordermanageshopTitleTv.setText(data.getShopName());
            } else {
                ordermanageshopTitleTv.setVisibility(View.GONE);
            }
            ILFactory.getLoader().loadCircleImage(data.getShopLogo(), ordermanageshopHearIv);
            ordermanageshopAddressTv.setText(data.getProvinceName() + data.getCityName() + data.getAddress());
            switch (data.getStatus()) {
                case 0:
                    ordermanageshopStautsTv.setText("审核中");
                    break;
                case 1:
                    ordermanageshopStautsTv.setText("待授权");
                    break;
                case 2:
                    ordermanageshopStautsTv.setText("待铺货");
                    ordermanageshopCountTv.setText(data.getMachinNum() + "台");
                    if ("2".equals(type) || "3".equals(type)) {
                        ordershopmanageReceiptTv.setText(data.getShopId());
                        ordershopmanageMailTv.setText(setAcctno(data.getMailId()));
                    } else if ("1".equals(type)) {
                        ordershopmanageReceiptTv.setText(data.getShopId());
                        ordershopmanageMailTv.setText(data.getMailId());
                    } else {
                        ordershopmanageReceiptLl.setVisibility(View.GONE);
                        ordershopmanageMailLl.setVisibility(View.GONE);
                    }
                    break;
                case 3:
                    ordermanageshopStautsTv.setText("装机中");
                    break;
                case 4:
                    ordermanageshopStautsTv.setText("已完成");
                    break;
                case 5:
                    ordermanageshopStautsTv.setText("审核失败");
                    break;
                case 6:
                    ordermanageshopStautsTv.setText("装机失败");
                    break;
                default:
            }
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
        date = year + month + day;
        ordermanageshopSelectTv.setText(date);
    }
    //脱敏处理
    private String setAcctno(String acctNo) {
        if (!AppTools.isEmpty(acctNo)) {
            acctNo = acctNo.substring(0, 3) + "****" + acctNo.substring(acctNo.length() - 4, acctNo.length());

        }
        return acctNo;
    }


    public void confirmResult(String message) {
        showToastLong(message);
        finish();
    }



}
