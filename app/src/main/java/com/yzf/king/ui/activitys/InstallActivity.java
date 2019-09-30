package com.yzf.king.ui.activitys;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.yzf.king.R;
import com.yzf.king.adapter.InstallAdapter;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetInstallResult;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoDtlResult;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoResults;
import com.yzf.king.present.PInstall;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class InstallActivity extends XActivity<PInstall> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ordershopmanage_dtl_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.ordershopmanage_dtl_finish_bt)
    Button ordershopmanageFinishBt;
    @BindView(R.id.ordershopmanage_dtl_hear_iv)
    ImageView ordershopmanageDtlHearIv;
    @BindView(R.id.ordershopmanage_dtl_title_tv)
    TextView ordershopmanageDtlTitleTv;
    @BindView(R.id.ordershopmanage_dtl_stauts_tv)
    TextView ordershopmanageDtlStautsTv;
    @BindView(R.id.ordershopmanage_dtl_address_tv)
    TextView ordershopmanageDtlAddressTv;
    @BindView(R.id.ordershopmanage_dtl_count_tv)
    TextView ordershopmanageDtlCountTv;
    @BindView(R.id.ordershopmanage_dtl_package_tv)
    TextView ordershopmanageDtlPackageTv;
    @BindView(R.id.ordershopmanage_dtl_count_ll)
    LinearLayout ordershopmanageDtlCountLl;
    @BindView(R.id.ordershopmanage_dtl_date_tv)
    TextView ordershopmanageDtlDateTv;
    @BindView(R.id.ordershopmanage_dtl_shopphone_tv)
    TextView ordershopmanageDtlShopphoneTv;
    @BindView(R.id.ordershopmanage_dtl_shopphone_ll)
    LinearLayout ordershopmanageDtlShopphoneLl;
    @BindView(R.id.ordershopmanage_dtl_supplierphone_tv)
    TextView ordershopmanageDtlSupplierphoneTv;
    @BindView(R.id.ordershopmanage_dtl_supplierphone_ll)
    LinearLayout ordershopmanageDtlSupplierphoneLl;
    @BindView(R.id.ordershopmanage_dtl_package_ll)
    LinearLayout ordershopmanageDtlPackageLl;
    @BindView(R.id.ordershopmanage_dtl_remark_tv)
    TextView ordershopmanageDtlRemarkTv;
    @BindView(R.id.ordershopmanage_dtl_supplier_tv)
    TextView ordershopmanageDtlSupplierTv;
    @BindView(R.id.ordershopmanage_dtl_supplier_ll)
    LinearLayout ordershopmanageDtlSupplierLl;
    @BindView(R.id.ordershopmanage_dtl_receipt_tv)
    TextView ordershopmanageDtlReceiptTv;
    @BindView(R.id.ordershopmanage_dtl_receipt_ll)
    LinearLayout ordershopmanageDtlReceiptLl;
    @BindView(R.id.ordershopmanage_dtl_mail_tv)
    TextView ordershopmanageDtlMailTv;
    @BindView(R.id.ordershopmanage_dtl_mail_ll)
    LinearLayout ordershopmanageDtlMailLl;
    @BindView(R.id.right_text_tv)
    TextView rightTextTv;

    /**
     * 每页10行
     */
    private int pageSize = 10;
    protected static final int MAX_PAGE = 30;
    GetMachinApplyInfoResults.DataBean dataBean;
    GetMachinApplyInfoDtlResult.DataBean dataBeanDtl = new GetMachinApplyInfoDtlResult.DataBean();
    InstallAdapter adapter;
    String type;
    private int replaceType;
    private String replaceTerm;

    @Override
    public void initData(Bundle savedInstanceState) {
        dataBean = (GetMachinApplyInfoResults.DataBean) getIntent().getSerializableExtra("dataBean");
        type = getIntent().getStringExtra("type");
        initView();
        initAdapter();
        getP().getMachinApplyInfoDtl(AppUser.getInstance().getMerchId(), dataBean.getOrderId(), dataBean.getShopId());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_install;
    }

    @Override
    public PInstall newP() {
        return new PInstall();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!AppTools.isEmpty(AppUser.getInstance().getTermId())) {
            if (replaceType == 3) {
                getP().shopOperate(AppUser.getInstance().getMerchId(), AppUser.getInstance().getTermId(), dataBean.getOrderId(), null, "ADD", dataBean.getShopId());
            } else if (replaceType == 2) {
                getP().shopOperate(AppUser.getInstance().getMerchId(), replaceTerm, dataBean.getOrderId(), AppUser.getInstance().getTermId(), "CHANGE", dataBean.getShopId());
            }
        }
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
        rightTextTv.setText("撤销装机");
        rightTextTv.setTextColor(getResources().getColor(R.color.btn_blue));
        rightTextTv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    }

    private void initAdapter() {
        if (adapter == null) {
            adapter = new InstallAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetMachinApplyInfoDtlResult.DataBean.TermInfoListBean, InstallAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, GetMachinApplyInfoDtlResult.DataBean.TermInfoListBean item, int tag, InstallAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case InstallAdapter.TAG_DEL:
                            getvDelegate().showErrorDialog("是否真的删除", new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    if (which == DialogAction.POSITIVE) {
                                        getP().shopOperate(AppUser.getInstance().getMerchId(), item.getTermId(), dataBean.getOrderId(), item.getTermId(), "DELETE", dataBean.getShopId());                                    }
                                }
                            });
                            break;
                        case InstallAdapter.TAG_REPLACE:
                            Router.newIntent(context)
                                    .to(AvailableActivity.class)
                                    .putString("shopmanage", "shopmanage")
                                    .putInt("number", dataBean.getMachinNum())
                                    .launch();
                            replaceType = InstallAdapter.TAG_REPLACE;
                            replaceTerm = item.getTermId();
                            break;
                        default:
                            break;
                    }
                }
            });
        }

        recyclerview.verticalLayoutManager(context);
//        recyclerview.addItemDecoration(new MyDividerItemDecoration(context, MyDividerItemDecoration.HORIZONTAL_LIST));
        recyclerview.setAdapter(adapter);
    }

    /**
     * 标题栏监听
     *
     * @param item
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
    public void jumpActivity(Class<?> activity, boolean isfinish) {
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
    public void jumpActivity(Class<?> activity) {
        Router.newIntent(context)
                .to(activity)
                .launch();
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


    @OnClick({R.id.ordershopmanage_dtl_finish_bt, R.id.ordershopmanage_dtl_add_bt, R.id.right_text_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ordershopmanage_dtl_finish_bt:
                getvDelegate().showErrorDialog("请确认所有安装的设备都已经完成一笔交易，并且确认商家收到结算款", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            getP().installConfirm(AppUser.getInstance().getMerchId(), dataBean.getOrderId(), null, "SUCCESS");
                        }
                    }
                });
                break;
            case R.id.ordershopmanage_dtl_add_bt:
                Router.newIntent(context)
                        .to(AvailableActivity.class)
                        .putString("shopmanage", "shopmanage")
                        .putInt("number", dataBean.getMachinNum())
                        .launch();
                replaceType = InstallAdapter.TAG_ADD;
                break;
            case R.id.right_text_tv:
                getvDelegate().showErrorDialog("是否真的撤销装机", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            getP().shopOperate(AppUser.getInstance().getMerchId(), null, dataBean.getOrderId(), null, "CANCLE", dataBean.getShopId());
                        }
                    }
                });

                break;
            default:
        }
    }

    public void setAdapter(GetMachinApplyInfoDtlResult.DataBean data) {
        if (data != null) {
            switch (data.getStatus()) {
                case 0:
                    ordershopmanageDtlStautsTv.setText("审核中");
                    break;
                case 1:
                    ordershopmanageDtlStautsTv.setText("待授权");
                    break;
                case 2:
                    ordershopmanageDtlStautsTv.setText("待铺货");
                    break;
                case 3:
                    ordershopmanageDtlStautsTv.setText("装机中");
                    ordershopmanageDtlTitleTv.setText(dataBean.getShopName());
                    ordershopmanageDtlAddressTv.setText(data.getProvinceName() + data.getCityName() + data.getAddress());
                    ordershopmanageDtlDateTv.setText(data.getConfirmDate());
                    ordershopmanageDtlShopphoneTv.setText(data.getPhone());
                    ordershopmanageDtlSupplierphoneTv.setText(data.getSupplierPhone());
                    ordershopmanageDtlSupplierTv.setText(data.getSupplierName());
                    ordershopmanageDtlSupplierphoneTv.setText(data.getSupplierPhone());
                    if (!AppTools.isEmpty(data.getShopLogo())) {
                        ILFactory.getLoader().loadCircleImage(data.getShopLogo(), ordershopmanageDtlHearIv);
                    } else {
                        ILFactory.getLoader().loadCircleImage(R.mipmap.logo, ordershopmanageDtlHearIv);
                    }
                    ordershopmanageDtlCountTv.setText(data.getMachinNum() + "台");
                    if (!AppTools.isEmpty(data.getRemark())) {
                        ordershopmanageDtlRemarkTv.setText("备注：" + data.getRemark());
                    }
                    ordershopmanageDtlPackageLl.setVisibility(View.GONE);
//                    ordershopmanageDtlFailureBt.setVisibility(View.VISIBLE);
//                    ordershopmanageFinishBt.setVisibility(View.VISIBLE);
                    if ("2".equals(type) || "3".equals(type)) {
                        ordershopmanageDtlReceiptTv.setText(data.getShopId());
                        ordershopmanageDtlMailTv.setText(setAcctno(data.getMailId()));
                    } else if ("1".equals(type)) {
                        ordershopmanageDtlReceiptTv.setText(data.getShopId());
                        ordershopmanageDtlMailTv.setText(data.getMailId());
                    } else {
                        ordershopmanageDtlReceiptLl.setVisibility(View.GONE);
                        ordershopmanageDtlMailLl.setVisibility(View.GONE);
                    }
                    break;
                case 4:
                    ordershopmanageDtlStautsTv.setText("已完成");
                    break;
                case 5:
                    ordershopmanageDtlStautsTv.setText("审核失败");
                    break;
                case 6:
                    ordershopmanageDtlStautsTv.setText("装机失败");
                    break;
                default:
                    break;
            }
            if (data.getTermInfoList() != null && !data.getTermInfoList().isEmpty() && data.getTermInfoList().size() > 0) {
                adapter.setData(data.getTermInfoList());
            }
            if (data != null&& !data.getTermInfoList().isEmpty() && data.getTermInfoList().size() < pageSize) {
                //当条数少于默认条数时，调整最大页数
                recyclerview.setPage(1, 1);
                recyclerview.removeAllFootView();
            } else {
                //必须设置setPage，否则上拉加载更多会无效
                recyclerview.setPage(1, MAX_PAGE);
            }
        }

    }

    public void setResult(GetInstallResult result) {
        getvDelegate().showErrorDialog(result.getMessage(), new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                if (which == DialogAction.POSITIVE) {
                    finish();
                }
            }
        });
    }

    private String setAcctno(String acctNo) {
        if (!AppTools.isEmpty(acctNo)) {
            acctNo = acctNo.substring(0, 3) + "****" + acctNo.substring(acctNo.length() - 4, acctNo.length());

        }
        return acctNo;
    }


    public void toRefreshMachinApplyInfoDtl() {
        getP().getMachinApplyInfoDtl(AppUser.getInstance().getMerchId(), dataBean.getOrderId(), dataBean.getShopId());
    }
}
