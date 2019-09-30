package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.adapter.ShopManageAdapter;
import com.yzf.king.adapter.ShopManageDtlAdapter;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetMyShopInfoResult;
import com.yzf.king.model.servicesmodels.GetShopInfoDtlResult;
import com.yzf.king.model.servicesmodels.GetShopInfoDtlResults;
import com.yzf.king.model.servicesmodels.GetSunMerchInfoListResult;
import com.yzf.king.present.PShopManageDtl;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class ShopManageDtlActivity extends XActivity<PShopManageDtl> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.shopmanage_dtl_hear_iv)
    ImageView shopmanageDtlHearIv;
    @BindView(R.id.shopmanage_dtl_title_tv)
    TextView shopmanageDtlTitleTv;
    @BindView(R.id.shopmanage_dtl_iv)
    ImageView shopmanageDtlIv;
    @BindView(R.id.shopmanage_dtl_stauts_tv)
    TextView shopmanageDtlStautsTv;
    @BindView(R.id.shopmanage_dtl_shop_rl)
    RelativeLayout shopmanageDtlShopRl;
    @BindView(R.id.shopmanage_dtl_suppliername_tv)
    TextView shopmanageDtlSuppliernameTv;
    @BindView(R.id.shopmanage_dtl_package_tv)
    TextView shopmanageDtlPackageTv;
    @BindView(R.id.shopmanage_dtl_date_tv)
    TextView shopmanageDtlDateTv;
    @BindView(R.id.shopmanage_dtl_address_tv)
    TextView shopmanageDtlAddressTv;
    @BindView(R.id.shopmanage_dtl_income_tv)
    TextView shopmanageDtlIncomeTv;
    @BindView(R.id.shopmanage_dtl_income_rl)
    RelativeLayout shopmanageDtlIncomeRl;
    @BindView(R.id.shopmanage_dtl_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.shopmanage_dtl_recyclerviews)
    XRecyclerView dtlrecyclerview;
    @BindView(R.id.shopmanage_dtl_address_ll)
    LinearLayout shopmanageDtlAddressLl;
    @BindView(R.id.shopmanage_dtl_resubmit_bt)
    Button shopmanageDtlResubmitBt;
    @BindView(R.id.shopmanage_dtl_remark_tv)
    TextView shopmanageDtlRemarkTv;


    // 每页10行
    private int pageSize = 10;
    protected static final int MAX_PAGE = 30;
    GetMyShopInfoResult.DataBean.DataListBean dataBean;
    GetShopInfoDtlResult.DataBean shopDtlDataBean = new GetShopInfoDtlResult.DataBean();
    GetSunMerchInfoListResult.DataBean.SubListBean subListBean = new GetSunMerchInfoListResult.DataBean.SubListBean();
    ShopManageAdapter adapter;
    ShopManageDtlAdapter dtlAdapter;
    String type;
    String myShopType;

    @Override
    public void initData(Bundle savedInstanceState) {
        dataBean = (GetMyShopInfoResult.DataBean.DataListBean) getIntent().getSerializableExtra("dataBean");
        type = getIntent().getStringExtra("type");
        myShopType = getIntent().getStringExtra("myShopType");
        initView();
        getP().getShopInfoDtl(AppUser.getInstance().getMerchId(), dataBean.getShopId());
        getP().getShopInfoDtls(AppUser.getInstance().getMerchId(), dataBean.getShopId());
        getP().getSunMerchInfoList(AppUser.getInstance().getMerchId(), dataBean.getShopId());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_manage_dtl;
    }

    @Override
    public PShopManageDtl newP() {
        return new PShopManageDtl();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        initAdapter();
        initDtlAdapter();
        shopmanageDtlAddressLl.setVisibility(View.GONE);
        if (dataBean != null) {
            shopmanageDtlTitleTv.setText(dataBean.getShopName());
            if (!AppTools.isEmpty(dataBean.getLogoUrl())) {
                ILFactory.getLoader().loadCircleImage(dataBean.getLogoUrl(), shopmanageDtlHearIv);
            } else {
                ILFactory.getLoader().loadCircleImage(R.mipmap.logo, shopmanageDtlHearIv);
            }

        }
    }

    //trem设备adapter
    private void initAdapter() {
        if (adapter == null) {
            adapter = new ShopManageAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetShopInfoDtlResult.DataBean.TermInfoListBean, ShopManageAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetShopInfoDtlResult.DataBean.TermInfoListBean item, int tag, ShopManageAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);

                }
            });
        }
        recyclerview.verticalLayoutManager(this);
        recyclerview.setAdapter(adapter);

    }

    //签约信息adapter
    private void initDtlAdapter() {
        if (dtlAdapter == null) {
            dtlAdapter = new ShopManageDtlAdapter(context);
            dtlAdapter.setRecItemClick(new RecyclerItemCallback<GetShopInfoDtlResults.DataBean.SubMerchListBean, ShopManageDtlAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetShopInfoDtlResults.DataBean.SubMerchListBean item, int tag, ShopManageDtlAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);

                }
            });
        }
        dtlrecyclerview.verticalLayoutManager(this);
        dtlrecyclerview.setAdapter(dtlAdapter);

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

    @OnClick({R.id.shopmanage_dtl_shop_rl, R.id.shopmanage_dtl_income_rl, R.id.shopmanage_dtl_resubmit_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shopmanage_dtl_shop_rl:
                Router.newIntent(context)
                        .to(ShopManageDetailActivity.class)
                        .putSerializable("shopDtlDataBean", shopDtlDataBean)
                        .putSerializable("myShopDataBean", dataBean)
                        .putString("type", type)
                        .putString("myShopType", myShopType)
                        .launch();
                break;
            case R.id.shopmanage_dtl_income_rl:
//                JumpActivity(TransActivity.class);//跳转交易明细
                break;
            case R.id.shopmanage_dtl_resubmit_bt:
                int applyType = 0;
                if (!AppTools.isEmpty(shopDtlDataBean.getApplyType())) {
                    applyType = Integer.parseInt(shopDtlDataBean.getApplyType()) + 1;
                }
                Router.newIntent(context)
                        .to(MerchApplyPermitActivity.class)
                        .putSerializable("subListBean", subListBean)
                        //区别与首页h5传值的applyType,h5传过来的是1是支付宝，2是微信，这里+1是为了在下一个页面的applyType的值统一
                        .putString("applyType", applyType + "")
                        .putString("bindType", "2")
                        .launch();
                break;
            default:
        }
    }

    /**
     * 设备TermInfoListBean
     */
    public void setAdapter(GetShopInfoDtlResult.DataBean data) {
        if (data != null) {
            shopmanageDtlIncomeTv.setText(AppTools.formatF2Y(data.getSumAmt()) + "");
            shopDtlDataBean.setShopName(data.getShopName());
            shopDtlDataBean.setShopId(data.getShopId());
            shopDtlDataBean.setName(data.getName());
            shopDtlDataBean.setPhone(data.getPhone());
            shopDtlDataBean.setInstrtTime(data.getInstrtTime());
            shopDtlDataBean.setProvinceName(data.getProvinceName());
            shopDtlDataBean.setCityName(data.getCityName());
            shopDtlDataBean.setAddress(data.getAddress());
            shopDtlDataBean.setStatus(data.getStatus());
            shopDtlDataBean.setApplyType(data.getApplyType());

            shopmanageDtlSuppliernameTv.setText(data.getSupplierName());
            shopmanageDtlDateTv.setText(data.getConfirmDate());
            shopmanageDtlAddressTv.setText(data.getProvinceName() + data.getCityName() + data.getAddress());

            adapter.setData(data.getTermInfoList());
            switch (data.getStatus()) {
                case 0:
                    shopmanageDtlStautsTv.setText("注册中");
                    shopmanageDtlResubmitBt.setVisibility(View.GONE);
                    shopmanageDtlRemarkTv.setVisibility(View.GONE);
                    break;
                case 1:
                    shopmanageDtlStautsTv.setText("待审核");
                    shopmanageDtlResubmitBt.setVisibility(View.GONE);
                    shopmanageDtlRemarkTv.setVisibility(View.GONE);
                    break;
                case 2:
                    shopmanageDtlStautsTv.setText("审核不通过");
                    if ("2".equals(type) || "3".equals(type)) {
                        shopmanageDtlResubmitBt.setVisibility(View.GONE);
                        shopmanageDtlRemarkTv.setText("备注：" + data.getRemark());
                    } else if ("1".equals(myShopType)) {
                        shopmanageDtlResubmitBt.setVisibility(View.VISIBLE);
                        shopmanageDtlRemarkTv.setText("备注：" + data.getRemark());
                    } else {
                        shopmanageDtlResubmitBt.setVisibility(View.GONE);
                        shopmanageDtlResubmitBt.setVisibility(View.GONE);
                    }
                    break;
                case 3:
                    shopmanageDtlStautsTv.setText("审核通过");
                    shopmanageDtlResubmitBt.setVisibility(View.GONE);
                    shopmanageDtlRemarkTv.setVisibility(View.GONE);
                    break;
//                case 4:
//                    shopmanageDtlStautsTv.setText("已完成");
//                    shopmanageDtlResubmitBt.setVisibility(View.GONE);
//                    shopmanageDtlRemarkTv.setVisibility(View.GONE);
//                    break;
//                case 5:
//                    shopmanageDtlStautsTv.setText("审核失败");
//                    if ("2".equals(type) || "3".equals(type)) {
//                        shopmanageDtlResubmitBt.setVisibility(View.GONE);
//                        shopmanageDtlRemarkTv.setText(data.getRemark());
//                    } else if ("1".equals(myShopType)) {
//                        shopmanageDtlResubmitBt.setVisibility(View.VISIBLE);
//                        shopmanageDtlRemarkTv.setText(data.getRemark());
//                    } else {
//                        shopmanageDtlResubmitBt.setVisibility(View.GONE);
//                        shopmanageDtlResubmitBt.setVisibility(View.GONE);
//                    }
//                    break;
//                case 6:
//                    shopmanageDtlStautsTv.setText("装机失败");
//                    if ("2".equals(type) || "3".equals(type)) {
//                        shopmanageDtlResubmitBt.setVisibility(View.GONE);
//                        shopmanageDtlRemarkTv.setText(data.getRemark());
//                    } else if ("1".equals(myShopType)) {
//                        shopmanageDtlResubmitBt.setVisibility(View.VISIBLE);
//                        shopmanageDtlRemarkTv.setText(data.getRemark());
//                    } else {
//                        shopmanageDtlResubmitBt.setVisibility(View.GONE);
//                        shopmanageDtlResubmitBt.setVisibility(View.GONE);
//                    }
//                    break;
                default:
                    shopmanageDtlStautsTv.setText("无");
                    break;
            }
            if (data.getTermInfoList() != null && data.getTermInfoList().size() < pageSize) {
                //当条数少于默认条数时，调整最大页数
                recyclerview.setPage(1, 1);
                recyclerview.removeAllFootView();
            } else {
                //必须设置setPage，否则上拉加载更多会无效
                recyclerview.setPage(1, MAX_PAGE);
            }


        }

    }

    /**
     * 签约信息TermInfoListBean
     *
     * @param data
     */
    public void setAdapters(GetShopInfoDtlResults.DataBean data) {
        if (data != null) {
            dtlAdapter.setData(data.getSubMerchList());
        }
        if (data.getSubMerchList() != null && data.getSubMerchList().size() < pageSize) {
            //当条数少于默认条数时，调整最大页数
            dtlrecyclerview.setPage(1, 1);
            dtlrecyclerview.removeAllFootView();
        } else {
            //必须设置setPage，否则上拉加载更多会无效
            dtlrecyclerview.setPage(1, MAX_PAGE);
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
