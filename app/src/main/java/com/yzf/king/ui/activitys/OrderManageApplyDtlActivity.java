package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.adapter.WaitShopAdapter;
import com.yzf.king.kit.ActivityManager;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoDtlResult;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoResults;
import com.yzf.king.model.servicesmodels.GetReApplyOrderResults;
import com.yzf.king.model.servicesmodels.GetSunMerchInfoListResult;
import com.yzf.king.present.POrderManageApplyDtl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.XRecyclerView;


public class OrderManageApplyDtlActivity extends XActivity<POrderManageApplyDtl> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.apply_dtl_hear_iv)
    ImageView applyDtlHearIv;
    @BindView(R.id.apply_dtl_title_tv)
    TextView applyDtlTitleTv;
    @BindView(R.id.apply_dtl_stauts_tv)
    TextView applyDtlStautsTv;
    @BindView(R.id.apply_dtl_address_tv)
    TextView applyDtlAddressTv;
    @BindView(R.id.apply_dtl_shopphone_tv)
    TextView applyDtlShopphoneTv;
    @BindView(R.id.apply_dtl_shopphone_ll)
    LinearLayout applyDtlShopphoneLl;
    @BindView(R.id.apply_dtl_date_tv)
    TextView applyDtlDateTv;
    @BindView(R.id.apply_dtl_name_tv)
    TextView applyDtlNameTv;
    @BindView(R.id.apply_dtl_name_ll)
    LinearLayout applyDtlNameLl;
    @BindView(R.id.apply_dtl_supplierphone_tv)
    TextView applyDtlSupplierPhoneTv;
    @BindView(R.id.apply_dtl_phone_ll)
    LinearLayout applyDtlPhoneLl;
    @BindView(R.id.apply_dtl_package_tv)
    TextView applyDtlPackageTv;
    @BindView(R.id.apply_dtl_package_ll)
    LinearLayout applyDtlPackageLl;
    @BindView(R.id.apply_dtl_install_failure_tv)
    TextView applyDtlInstallFailureTv;
    @BindView(R.id.apply_dtl_review_failure_tv)
    TextView applyDtlReviewFailureTv;
    @BindView(R.id.apply_dtl_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.apply_dtl_address_ll)
    LinearLayout applyDtlAddressLl;
    @BindView(R.id.apply_dtl_date_ll)
    LinearLayout applyDtlDateLl;
    @BindView(R.id.apply_dtl_applydate_tv)
    TextView applyDtlApplydateTv;
    @BindView(R.id.apply_dtl_applydate_ll)
    LinearLayout applyDtlApplydateLl;
    @BindView(R.id.apply_dtl_machinnum_tv)
    TextView applyDtlMachinnumTv;
    @BindView(R.id.apply_dtl_machinnum_ll)
    LinearLayout applyDtlMachinnumLl;
    @BindView(R.id.apply_dtl_receipt_tv)
    TextView applyDtlReceiptTv;
    @BindView(R.id.apply_dtl_receipt_ll)
    LinearLayout applyDtlReceiptLl;
    @BindView(R.id.apply_dtl_mail_tv)
    TextView applyDtlMailTv;
    @BindView(R.id.apply_dtl_mail_ll)
    LinearLayout applyDtlMailLl;
    @BindView(R.id.apply_dtl_resubmit_bt)
    Button applyDtlResubmitBt;
    @BindView(R.id.apply_dtl_order_bt)
    Button applyDtlOrderBt;

    // 每页10行
    private int pageSize = 10;
    protected static final int MAX_PAGE = 30;
    WaitShopAdapter adapter;

    GetMachinApplyInfoResults.DataBean dataBean;
    String type;
    GetSunMerchInfoListResult.DataBean.SubListBean subListBean = new GetSunMerchInfoListResult.DataBean.SubListBean();

    @Override
    public void initData(Bundle savedInstanceState) {
        dataBean = (GetMachinApplyInfoResults.DataBean) getIntent().getSerializableExtra("dataBean");
        type = getIntent().getStringExtra("type");
        initView();
        getP().getMachinApplyInfoDtl(AppUser.getInstance().getMerchId(), dataBean.getOrderId(), dataBean.getShopId());
        getP().getSunMerchInfoList(AppUser.getInstance().getMerchId(), dataBean.getShopId());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_apply_dtl;
    }

    @Override
    public POrderManageApplyDtl newP() {
        return new POrderManageApplyDtl();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        initAdapter();
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
        }
        recyclerview.verticalLayoutManager(this);
        recyclerview.setAdapter(adapter);
        recyclerview.useDefLoadMoreView();
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


    public void setAdapter(GetMachinApplyInfoDtlResult.DataBean data) {
        if (data != null) {
            applyDtlTitleTv.setText(data.getShopName());
            if (!AppTools.isEmpty(data.getShopLogo())) {
                ILFactory.getLoader().loadCircleImage(data.getShopLogo(), applyDtlHearIv);
            } else {
                ILFactory.getLoader().loadCircleImage(R.mipmap.logo, applyDtlHearIv);
            }

            if (!AppTools.isEmpty(data.getProvinceName()) || !AppTools.isEmpty(data.getCityName()) || !AppTools.isEmpty(data.getAddress())) {
                applyDtlAddressTv.setText(data.getProvinceName() + data.getCityName() + data.getAddress());
            } else {
                applyDtlAddressTv.setText("");
            }
            applyDtlShopphoneTv.setText(data.getPhone());
            switch (data.getStatus()) {
                case 0:
                    applyDtlStautsTv.setText("审核中");
                    if (data.getApplyDate() != null) {
                        applyDtlDateTv.setText(data.getApplyDate());
                    } else {
                        applyDtlDateTv.setVisibility(View.GONE);
                    }
                    applyDtlShopphoneLl.setVisibility(View.VISIBLE);
                    applyDtlApplydateLl.setVisibility(View.VISIBLE);
                    applyDtlMachinnumLl.setVisibility(View.VISIBLE);
                    applyDtlApplydateTv.setText(data.getApplyDate());
                    applyDtlMachinnumTv.setText(data.getMachinNum() + "台");
                    if ("2".equals(type) || "3".equals(type)) {
                        applyDtlReceiptTv.setText(data.getShopId());
                        applyDtlMailTv.setText(setAcctno(data.getMailId()));
                        applyDtlResubmitBt.setVisibility(View.GONE);
                    } else if ("1".equals(type)) {
                        applyDtlReceiptTv.setText(data.getShopId());
                        applyDtlMailTv.setText(data.getMailId());
                        applyDtlResubmitBt.setVisibility(View.GONE);
                    } else {
                        applyDtlReceiptLl.setVisibility(View.GONE);
                        applyDtlMailLl.setVisibility(View.GONE);
                    }

                    applyDtlInstallFailureTv.setVisibility(View.GONE);
                    applyDtlReviewFailureTv.setVisibility(View.GONE);
                    applyDtlNameLl.setVisibility(View.GONE);
                    applyDtlPhoneLl.setVisibility(View.GONE);
                    applyDtlPackageLl.setVisibility(View.GONE);
                    applyDtlDateLl.setVisibility(View.GONE);
                    applyDtlResubmitBt.setVisibility(View.GONE);
                    applyDtlOrderBt.setVisibility(View.GONE);

                    break;
                case 1:
                    applyDtlStautsTv.setText("待授权");
                    applyDtlApplydateLl.setVisibility(View.VISIBLE);
                    applyDtlApplydateTv.setText(data.getApplyDate());
                    applyDtlMachinnumLl.setVisibility(View.VISIBLE);
                    applyDtlMachinnumTv.setText(data.getMachinNum() + "台");
                    if ("2".equals(type) || "3".equals(type)) {
                        applyDtlReceiptTv.setText(data.getShopId());
                        applyDtlMailTv.setText(setAcctno(data.getMailId()));
                        applyDtlResubmitBt.setVisibility(View.GONE);
                    } else if ("1".equals(type)) {
                        applyDtlReceiptTv.setText(data.getShopId());
                        applyDtlMailTv.setText(data.getMailId());
                        applyDtlResubmitBt.setVisibility(View.GONE);
                    } else {
                        applyDtlReceiptLl.setVisibility(View.GONE);
                        applyDtlMailLl.setVisibility(View.GONE);
                    }


                    applyDtlInstallFailureTv.setVisibility(View.GONE);
                    applyDtlReviewFailureTv.setVisibility(View.GONE);
                    applyDtlNameLl.setVisibility(View.GONE);
                    applyDtlPhoneLl.setVisibility(View.GONE);
                    applyDtlPackageLl.setVisibility(View.GONE);
                    applyDtlDateLl.setVisibility(View.GONE);
                    applyDtlResubmitBt.setVisibility(View.GONE);
                    applyDtlOrderBt.setVisibility(View.GONE);
                    break;
                case 2:
                    applyDtlStautsTv.setText("待铺货");
                    if (data.getConfirmDate() != null) {
                        applyDtlApplydateTv.setText(data.getConfirmDate());
                    } else {
                        applyDtlDateTv.setVisibility(View.GONE);
                    }
                    applyDtlApplydateTv.setText(data.getApplyDate());
                    applyDtlMachinnumLl.setVisibility(View.VISIBLE);
                    applyDtlMachinnumTv.setText(data.getMachinNum() + "台");
                    if ("2".equals(type) || "3".equals(type)) {
                        applyDtlReceiptTv.setText(data.getShopId());
                        applyDtlMailTv.setText(setAcctno(data.getMailId()));
                        applyDtlResubmitBt.setVisibility(View.GONE);
                    } else if ("1".equals(type)) {
                        applyDtlReceiptTv.setText(data.getShopId());
                        applyDtlMailTv.setText(data.getMailId());
                        applyDtlResubmitBt.setVisibility(View.GONE);
                    } else {
                        applyDtlReceiptLl.setVisibility(View.GONE);
                        applyDtlMailLl.setVisibility(View.GONE);
                    }

                    applyDtlInstallFailureTv.setVisibility(View.GONE);
                    applyDtlReviewFailureTv.setVisibility(View.GONE);
                    applyDtlNameLl.setVisibility(View.GONE);
                    applyDtlPhoneLl.setVisibility(View.GONE);
                    applyDtlPackageLl.setVisibility(View.GONE);
                    applyDtlDateLl.setVisibility(View.GONE);
                    applyDtlOrderBt.setVisibility(View.GONE);

                    break;
                case 3:
                    applyDtlStautsTv.setText("装机中");
                    if (data.getConfirmDate() != null) {
                        applyDtlDateTv.setText(data.getConfirmDate());
                    } else {
                        applyDtlDateTv.setVisibility(View.GONE);
                    }
                    if (data.getSupplierName() != null) {
                        applyDtlNameTv.setText(data.getSupplierName());
                    } else {
                        applyDtlNameTv.setVisibility(View.GONE);
                    }
                    if (data.getPhone() != null) {
                        applyDtlSupplierPhoneTv.setText(data.getSupplierPhone());
                    } else {
                        applyDtlSupplierPhoneTv.setVisibility(View.GONE);
                    }
                    applyDtlApplydateTv.setText(data.getApplyDate());
                    applyDtlMachinnumLl.setVisibility(View.VISIBLE);
                    applyDtlMachinnumTv.setText(data.getMachinNum() + "台");
                    if ("2".equals(type) || "3".equals(type)) {
                        applyDtlReceiptTv.setText(data.getShopId());
                        applyDtlMailTv.setText(setAcctno(data.getMailId()));
                        applyDtlResubmitBt.setVisibility(View.GONE);
                    } else if ("1".equals(type)) {
                        applyDtlReceiptTv.setText(data.getShopId());
                        applyDtlMailTv.setText(data.getMailId());
                        applyDtlResubmitBt.setVisibility(View.GONE);
                    } else {
                        applyDtlReceiptLl.setVisibility(View.GONE);
                        applyDtlMailLl.setVisibility(View.GONE);
                    }

                    applyDtlInstallFailureTv.setVisibility(View.GONE);
                    applyDtlReviewFailureTv.setVisibility(View.GONE);
                    applyDtlPackageLl.setVisibility(View.GONE);
                    applyDtlOrderBt.setVisibility(View.GONE);

                    if (data.getTermInfoList() != null && data.getTermInfoList().size() > 0) {
                        adapter.setData(data.getTermInfoList());
                        if (data.getTermInfoList().size() < pageSize) {
                            //当条数少于默认条数时，调整最大页数
                            recyclerview.setPage(1, 1);
                            recyclerview.removeAllFootView();
                        } else {
                            //必须设置setPage，否则上拉加载更多会无效
                            recyclerview.setPage(1, MAX_PAGE);
                        }
                    }
                    break;
                case 4:
                    applyDtlStautsTv.setText("已完成");
                    if (data.getConfirmDate() != null) {
                        applyDtlDateTv.setText(data.getConfirmDate());
                    } else {
                        applyDtlDateTv.setVisibility(View.GONE);
                    }
                    if (data.getSupplierName() != null) {
                        applyDtlNameTv.setText(data.getSupplierName());
                    } else {
                        applyDtlNameTv.setVisibility(View.GONE);
                    }
                    if (data.getSupplierPhone() != null) {
                        applyDtlSupplierPhoneTv.setText(data.getSupplierPhone());
                    } else {
                        applyDtlSupplierPhoneTv.setVisibility(View.GONE);
                    }
                    applyDtlApplydateTv.setText(data.getApplyDate());
                    applyDtlMachinnumTv.setText(data.getMachinNum() + "台");
                    if ("2".equals(type) || "3".equals(type)) {
                        applyDtlReceiptTv.setText(data.getShopId());
                        applyDtlMailTv.setText(setAcctno(data.getMailId()));
                        applyDtlResubmitBt.setVisibility(View.GONE);
                    } else if ("1".equals(type)) {
                        applyDtlReceiptTv.setText(data.getShopId());
                        applyDtlMailTv.setText(data.getMailId());
                        applyDtlResubmitBt.setVisibility(View.VISIBLE);
                    } else {
                        applyDtlReceiptLl.setVisibility(View.GONE);
                        applyDtlMailLl.setVisibility(View.GONE);
                    }

                    applyDtlInstallFailureTv.setVisibility(View.GONE);
                    applyDtlReviewFailureTv.setVisibility(View.GONE);
                    applyDtlPackageLl.setVisibility(View.GONE);
                    applyDtlResubmitBt.setVisibility(View.GONE);
                    applyDtlOrderBt.setVisibility(View.GONE);

                    if (data.getTermInfoList() != null && data.getTermInfoList().size() > 0) {
                        adapter.setData(data.getTermInfoList());
                        if (data.getTermInfoList().size() < pageSize) {
                            //当条数少于默认条数时，调整最大页数
                            recyclerview.setPage(1, 1);
                            recyclerview.removeAllFootView();
                        } else {
                            //必须设置setPage，否则上拉加载更多会无效
                            recyclerview.setPage(1, MAX_PAGE);
                        }
                    }
                    break;
                case 5:
                    applyDtlStautsTv.setText("审核失败");
                    applyDtlStautsTv.setTextColor(getBaseContext().getResources().getColor(R.color.btn_yellow_dark));
                    applyDtlInstallFailureTv.setVisibility(View.VISIBLE);
                    if (!AppTools.isEmpty(data.getRemark())) {
                        applyDtlInstallFailureTv.setText("备注：" + data.getRemark());
                    }
                    applyDtlResubmitBt.setVisibility(View.VISIBLE);
                    applyDtlReceiptLl.setVisibility(View.VISIBLE);
                    applyDtlMailLl.setVisibility(View.VISIBLE);
                    if ("2".equals(type) || "3".equals(type)) {
                        applyDtlReceiptTv.setText(data.getShopId());
                        applyDtlMailTv.setText(setAcctno(data.getMailId()));
                        applyDtlResubmitBt.setVisibility(View.GONE);
                    } else if ("1".equals(type)) {
                        applyDtlReceiptTv.setText(data.getShopId());
                        applyDtlMailTv.setText(data.getMailId());
                        applyDtlResubmitBt.setVisibility(View.VISIBLE);
                    } else {
                        applyDtlReceiptLl.setVisibility(View.GONE);
                        applyDtlMailLl.setVisibility(View.GONE);
                    }

                    applyDtlAddressLl.setVisibility(View.GONE);
                    applyDtlDateLl.setVisibility(View.GONE);
                    applyDtlShopphoneLl.setVisibility(View.GONE);
                    applyDtlNameLl.setVisibility(View.GONE);
                    applyDtlPhoneLl.setVisibility(View.GONE);
                    applyDtlPackageLl.setVisibility(View.GONE);
                    applyDtlMachinnumLl.setVisibility(View.GONE);
                    applyDtlReviewFailureTv.setVisibility(View.GONE);
                    applyDtlApplydateLl.setVisibility(View.GONE);
                    applyDtlOrderBt.setVisibility(View.GONE);
                    break;
                case 6:
                    applyDtlStautsTv.setText("装机失败");
                    applyDtlStautsTv.setTextColor(getBaseContext().getResources().getColor(R.color.btn_yellow_dark));
                    applyDtlSupplierPhoneTv.setText(data.getSupplierPhone());
                    applyDtlApplydateTv.setText(data.getApplyDate());
                    applyDtlNameTv.setText(data.getSupplierName());
                    applyDtlApplydateTv.setText(data.getConfirmDate());
                    applyDtlMachinnumTv.setText(data.getMachinNum() + "台");
                    applyDtlInstallFailureTv.setText("备注：" + data.getRemark());

                    applyDtlMachinnumLl.setVisibility(View.VISIBLE);
                    applyDtlResubmitBt.setVisibility(View.VISIBLE);
                    applyDtlReceiptLl.setVisibility(View.VISIBLE);
                    applyDtlMailLl.setVisibility(View.VISIBLE);
                    applyDtlInstallFailureTv.setVisibility(View.VISIBLE);

                    if ("2".equals(type) || "3".equals(type)) {
                        applyDtlReceiptTv.setText(data.getShopId());
                        applyDtlMailTv.setText(setAcctno(data.getMailId()));
                    } else if ("1".equals(type)) {
                        applyDtlReceiptTv.setText(data.getShopId());
                        applyDtlMailTv.setText(data.getMailId());
                    } else {
                        applyDtlReceiptLl.setVisibility(View.GONE);
                        applyDtlMailLl.setVisibility(View.GONE);
                    }

                    applyDtlReviewFailureTv.setVisibility(View.GONE);
                    applyDtlPackageLl.setVisibility(View.GONE);
                    applyDtlReviewFailureTv.setVisibility(View.GONE);
                    applyDtlResubmitBt.setVisibility(View.GONE);
                    applyDtlOrderBt.setVisibility(View.GONE);
                    break;
                case 7:
                    applyDtlStautsTv.setText("订单失效");
                    applyDtlStautsTv.setTextColor(getBaseContext().getResources().getColor(R.color.btn_yellow_dark));
                    if (data.getConfirmDate() != null) {
                        applyDtlApplydateTv.setText(data.getConfirmDate());
                    } else {
                        applyDtlDateTv.setVisibility(View.GONE);
                    }
                    applyDtlApplydateTv.setText(data.getApplyDate());
                    applyDtlMachinnumLl.setVisibility(View.VISIBLE);
                    applyDtlMachinnumTv.setText(data.getMachinNum() + "台");
                    if ("2".equals(type) || "3".equals(type)) {
                        applyDtlReceiptTv.setText(data.getShopId());
                        applyDtlMailTv.setText(setAcctno(data.getMailId()));
                        applyDtlResubmitBt.setVisibility(View.GONE);
                    } else if ("1".equals(type)) {
                        applyDtlReceiptTv.setText(data.getShopId());
                        applyDtlMailTv.setText(data.getMailId());
                        applyDtlResubmitBt.setVisibility(View.GONE);
                    } else {
                        applyDtlReceiptLl.setVisibility(View.GONE);
                        applyDtlMailLl.setVisibility(View.GONE);
                    }

                    applyDtlInstallFailureTv.setVisibility(View.GONE);
                    applyDtlReviewFailureTv.setVisibility(View.GONE);
                    applyDtlNameLl.setVisibility(View.GONE);
                    applyDtlPhoneLl.setVisibility(View.GONE);
                    applyDtlPackageLl.setVisibility(View.GONE);
                    applyDtlDateLl.setVisibility(View.GONE);

                    applyDtlOrderBt.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }

    }

    private String setAcctno(String acctNo) {
        if (!AppTools.isEmpty(acctNo)) {
            acctNo = acctNo.substring(0, 3) + "****" + acctNo.substring(acctNo.length() - 4, acctNo.length());

        }
        return acctNo;
    }

    @OnClick({R.id.apply_dtl_resubmit_bt,R.id.apply_dtl_order_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.apply_dtl_resubmit_bt:
//                String target1 = AppConfig.H5_URL+ "smilePay/#/applyForStore?merchId="+AppUser.getInstance().getMerchId();
//                if (!AppTools.isEmpty(target1)) {
//                    AgenWebViewActivity.launch(context, target1, null);
//                } else {
//                    showToast("暂未开放");
//                }
                Router.newIntent(context)
                        .to(MerchApplyPermitActivity.class)
                        .putSerializable("subListBean", subListBean)
                        .putString("applyType", "2")
                        .putString("bindType", "2")
                        .launch();
                break;
            case R.id.apply_dtl_order_bt:
                getP().reApplyOrder(AppUser.getInstance().getMerchId(),dataBean.getOrderId());
                break;
            default:
                break;
        }

    }

    /**
     * 补交资料SubListBean
     *
     * @param data
     */
    public void setSunMerchInfoListAdapter(GetSunMerchInfoListResult.DataBean data) {
        if (data != null) {
            subListBean.setShopId(data.getShopId());
        }
    }


}
