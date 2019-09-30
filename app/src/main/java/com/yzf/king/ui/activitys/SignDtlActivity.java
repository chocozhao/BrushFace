package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.adapter.SignDtlAdapter;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetMyShopInfoResult;
import com.yzf.king.model.servicesmodels.GetSunMerchInfoListResult;
import com.yzf.king.present.PSignDtl;
import com.yzf.king.widget.MyDividerItemDecoration;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class SignDtlActivity extends XActivity<PSignDtl> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sign_dtl_hear_iv)
    ImageView signDtlHearIv;
    @BindView(R.id.sign_dtl_title_tv)
    TextView signDtlTitleTv;
    @BindView(R.id.sign_dtl_data_tv)
    TextView signDtlDataTv;
    @BindView(R.id.sign_dtl_recyclerView)
    XRecyclerView recyclerview;

    SignDtlAdapter adapter;

    private GetMyShopInfoResult.DataBean.DataListBean dataListBean;

    @Override
    public void initData(Bundle savedInstanceState) {
        dataListBean = (GetMyShopInfoResult.DataBean.DataListBean) getIntent().getSerializableExtra("dataListBean");
        initView();
        initAdapter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sign_dtl;
    }

    @Override
    public PSignDtl newP() {
        return new PSignDtl();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getP().getSunMerchInfoList(AppUser.getInstance().getMerchId(), dataListBean.getShopId());
    }

    public void initAdapter() {
        if (adapter == null) {
            adapter = new SignDtlAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetSunMerchInfoListResult.DataBean.SubListBean, SignDtlAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetSunMerchInfoListResult.DataBean.SubListBean item, int tag, SignDtlAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case SignDtlAdapter.TAG_VIEW:
                            switch (item.getChannelCode()) {
                                //支付宝签约
                                case "ALIPAY":
                                    switch (item.getOrderStatus()) {
                                        case "00":
                                            switch (item.getAddStatus()) {
                                                case "0":
                                                    //全部资料
                                                    Router.newIntent(context)
                                                            .to(MerchApplyPermitActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .putString("applyType", "1")
                                                            .putString("bindType", "1")
                                                            .launch();
                                                    break;
                                                case "1":
                                                    //营业执照不清晰
                                                    Router.newIntent(context)
                                                            .to(ShopSoundPermitActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "2":
                                                    //店铺地址不正确
                                                    Router.newIntent(context)
                                                            .to(ShopSoundAddressActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "3":
                                                    //法人身份证信息不齐
                                                    Router.newIntent(context)
                                                            .to(ShopSoundIdcardActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "4":
                                                    //银行卡信息不正确
                                                    Router.newIntent(context)
                                                            .to(ShopSoundBankcardActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "5":
                                                    //支付宝账户不正确
                                                    Router.newIntent(context)
                                                            .to(ShopSoundAlipayActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "6":
                                                    //联系信息不正确
                                                    Router.newIntent(context)
                                                            .to(ShopSoundContactActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "7":
                                                    //店铺照片不正确
                                                    Router.newIntent(context)
                                                            .to(ShopSoundPhotoActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "8":
                                                    //审核中
                                                    break;
                                                case "9":
                                                    //审核通过
                                                    break;
                                                default:
                                                    break;
                                            }
                                            break;
                                        case "01":
                                            break;
                                        case "02":
                                            break;
                                        case "03":
                                            break;
                                        case "04":
                                            Router.newIntent(context)
                                                    .to(OrderManagePromotionDtlActivity.class)
                                                    .putSerializable("subListBean", item)
                                                    .putSerializable("dataListBean", dataListBean)
                                                    .putString("signtype", "signtype")
                                                    .launch();
                                            break;
                                        case "05":
                                            break;
                                        case "09":
                                            break;
                                        default:
                                            break;
                                    }
                                    break;
                                //微信签约
                                case "WXPAY":
                                    switch (item.getOrderStatus()) {
                                        case "00":
                                            switch (item.getAddStatus()) {
                                                case "0":
                                                    //全部资料
                                                    Router.newIntent(context)
                                                            .to(MerchApplyPermitActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .putString("applyType", "2")
                                                            .putString("bindType", "1")
                                                            .launch();
                                                    break;
                                                case "1":
                                                    //营业执照不清晰
                                                    Router.newIntent(context)
                                                            .to(ShopSoundPermitActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "2":
                                                    //店铺地址不正确
                                                    Router.newIntent(context)
                                                            .to(ShopSoundAddressActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "3":
                                                    //法人身份证信息不齐
                                                    Router.newIntent(context)
                                                            .to(ShopSoundIdcardActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "4":
                                                    //银行卡信息不正确
                                                    Router.newIntent(context)
                                                            .to(ShopSoundBankcardActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "5":
                                                    //支付宝账户不正确
                                                    Router.newIntent(context)
                                                            .to(ShopSoundAlipayActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "6":
                                                    //联系信息不正确
                                                    Router.newIntent(context)
                                                            .to(ShopSoundContactActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "7":
                                                    //店铺照片不正确
                                                    Router.newIntent(context)
                                                            .to(ShopSoundPhotoActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "8":
                                                    //审核中
                                                    break;
                                                case "9":
                                                    //审核通过
                                                    break;
                                                default:
                                                    break;
                                            }
                                            break;
                                        case "01":
                                            break;
                                        case "02":
                                            break;
                                        case "03":
                                            break;
                                        case "04":
                                            Router.newIntent(context)
                                                    .to(OrderManagePromotionDtlActivity.class)
                                                    .putSerializable("subListBean", item)
                                                    .putSerializable("dataListBean", dataListBean)
                                                    .putString("signtype", "signtype")
                                                    .launch();
                                            break;
                                        case "05":
                                            break;
                                        case "09":
                                            break;
                                        default:
                                            break;
                                    }
                                    break;
                                //银联签约
                                case "UNIONPAY":
                                    switch (item.getOrderStatus()) {
                                        case "00":
                                            switch (item.getAddStatus()) {
                                                case "0":
                                                    //全部资料
                                                    break;
                                                case "1":
                                                    //营业执照不清晰
                                                    Router.newIntent(context)
                                                            .to(ShopSoundPermitActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "2":
                                                    //店铺地址不正确
                                                    Router.newIntent(context)
                                                            .to(ShopSoundAddressActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "3":
                                                    //法人身份证信息不齐
                                                    Router.newIntent(context)
                                                            .to(ShopSoundIdcardActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "4":
                                                    //银行卡信息不正确
                                                    Router.newIntent(context)
                                                            .to(ShopSoundBankcardActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "5":
                                                    //支付宝账户不正确
                                                    Router.newIntent(context)
                                                            .to(ShopSoundAlipayActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "6":
                                                    //联系信息不正确
                                                    Router.newIntent(context)
                                                            .to(ShopSoundContactActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "7":
                                                    //店铺照片不正确
                                                    Router.newIntent(context)
                                                            .to(ShopSoundPhotoActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "8":
                                                    //审核中
                                                    break;
                                                case "9":
                                                    //审核通过
                                                    break;
                                                default:
                                                    break;
                                            }
                                            break;
                                        case "01":
                                            break;
                                        case "02":
                                            break;
                                        case "03":
                                            break;
                                        case "04":
                                            Router.newIntent(context)
                                                    .to(OrderManagePromotionDtlActivity.class)
                                                    .putSerializable("subListBean", item)
                                                    .putSerializable("dataListBean", dataListBean)
                                                    .putString("signtype", "signtype")
                                                    .launch();
                                            break;
                                        case "05":
                                            break;
                                        case "09":
                                            break;
                                        default:
                                            break;
                                    }
                                    break;
                                //间联签约
                                case "HKPAY":
                                    switch (item.getOrderStatus()) {
                                        case "00":
                                            switch (item.getAddStatus()) {
                                                case "0":
                                                    //全部资料
                                                    Router.newIntent(context)
                                                            .to(MerchApplyPermitActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .putString("applyType", "3")
                                                            .putString("bindType", "1")
                                                            .launch();
                                                    break;
                                                case "1":
                                                    //营业执照不清晰
                                                    Router.newIntent(context)
                                                            .to(ShopSoundPermitActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "2":
                                                    //店铺地址不正确
                                                    Router.newIntent(context)
                                                            .to(ShopSoundAddressActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "3":
                                                    //法人身份证信息不齐
                                                    Router.newIntent(context)
                                                            .to(ShopSoundIdcardActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "4":
                                                    //银行卡信息不正确
                                                    Router.newIntent(context)
                                                            .to(ShopSoundBankcardActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "5":
                                                    //支付宝账户不正确
                                                    Router.newIntent(context)
                                                            .to(ShopSoundAlipayActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "6":
                                                    //联系信息不正确
                                                    Router.newIntent(context)
                                                            .to(ShopSoundContactActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "7":
                                                    //店铺照片不正确
                                                    Router.newIntent(context)
                                                            .to(ShopSoundPhotoActivity.class)
                                                            .putSerializable("subListBean", item)
                                                            .launch();
                                                    break;
                                                case "8":
                                                    //审核中
                                                    break;
                                                case "9":
                                                    //审核通过
                                                    break;
                                                default:
                                                    break;
                                            }
                                            break;
                                        case "01":
                                            break;
                                        case "02":
                                            break;
                                        case "03":
                                            break;
                                        case "04":
                                            Router.newIntent(context)
                                                    .to(OrderManagePromotionDtlActivity.class)
                                                    .putSerializable("subListBean", item)
                                                    .putSerializable("dataListBean", dataListBean)
                                                    .putString("signtype", "signtype")
                                                    .launch();
                                            break;
                                        case "05":
                                            break;
                                        case "09":
                                            break;
                                        default:
                                            break;
                                    }
                                    break;
                                default:
                                    break;
                            }
                            break;

                        default:
                            break;
                    }
                }
            });
        }
        recyclerview.verticalLayoutManager(this);
        //加一条线
        recyclerview.addItemDecoration(new MyDividerItemDecoration(context, MyDividerItemDecoration.HORIZONTAL_LIST));
        recyclerview.setAdapter(adapter);
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        if (dataListBean != null) {
            if (!AppTools.isEmpty(dataListBean.getLogoUrl())) {
                ILFactory.getLoader().loadCircleImage(dataListBean.getLogoUrl(), signDtlHearIv);
            } else {
                ILFactory.getLoader().loadCircleImage(R.mipmap.login_logo, signDtlHearIv);
            }
            signDtlTitleTv.setText(dataListBean.getShopName());
            signDtlDataTv.setText(Kits.Date.getYmdhms(dataListBean.getInsertTime()));
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
        title.setText("店铺详情");
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


    public void setAdapter(GetSunMerchInfoListResult.DataBean dataBean) {
        if (dataBean != null) {
            adapter.setData(dataBean.getSubList());

        }
    }

}
