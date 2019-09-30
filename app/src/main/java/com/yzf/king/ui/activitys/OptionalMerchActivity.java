package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yzf.king.R;
import com.yzf.king.adapter.PosPayAdapter;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetPosChanlFeeResult;
import com.yzf.king.present.POptionalMerch;
import com.yzf.king.widget.MultipleStatusView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

public class OptionalMerchActivity extends XActivity<POptionalMerch> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.optional_multiplestatusview)
    MultipleStatusView multiplestatusview;
    @BindView(R.id.optional_recyclerview)
    XRecyclerView recyclerview;


    PosPayAdapter adapter;



    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        initAdapter();
//        getP().getOptionalMerch(AppUser.getInstance().getMerchId());
        getP().getPosChanlFee(AppUser.getInstance().getMerchId());

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_optional_merch;
    }

    @Override
    public POptionalMerch newP() {
        return new POptionalMerch();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();

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
        title.setText("选择行业");
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

    public void showEmptyView(String msg) {
        multiplestatusview.showEmpty(msg);
    }

    public void showErrorView(String msg) {
        multiplestatusview.showError(msg);
    }

    public void showErrorView(NetError error) {
        multiplestatusview.showError(getvDelegate().getErrorType(error));
    }

    public void initAdapter() {
        if (adapter == null) {
            adapter = new PosPayAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetPosChanlFeeResult.DataBean, PosPayAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetPosChanlFeeResult.DataBean item, int tag, PosPayAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case PosPayAdapter.TAG_VIEW:
                            Gson gson = new Gson();
                            AppUser.getInstance().setMposMerchInfo(gson.toJson(item));
                            AppUser.getInstance().setMposFeeInfo(gson.toJson(item));
                            getvDelegate().dismissLoading();
                            Router.newIntent(context)
                                    .to(MerchPickActivity.class)
                                    .putString("channelId","70940000")
                                    .launch();
                            Router.pop(context);
//                                getvDelegate().showLoading();
//                                getP().mPosPay(item);
                                /*IndustryInfo industryInfo = new IndustryInfo();
                                industryInfo.setIndustryCode(item.getTradeCode());
                                industryInfo.setIndustryName(item.getTradeName());
                                industryInfo.setBizCode(item.getTradeCode());
                                PaySdkEnvionment.startPay(context, AppTools.craeateOrderId(), AppUser.getInstance().getAMT(), EnumClass.TYPE_OPEMODE.TYPE_NORMAL, EnumClass.TYPE_GETFUND.TYPE_GETFUND_CUSTOM_INDUSTRY_D0, industryInfo, null, item.getTradeName(), judgeAttachParams(item), token);*/
                            break;
                            default:break;
                    }
                }
            });
        }
        recyclerview.verticalLayoutManager(this);
        recyclerview.setAdapter(adapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void setAdapter(List<GetPosChanlFeeResult.DataBean> dataBean) {
        multiplestatusview.showContent();
        if (dataBean == null) {
            showEmptyView("暂无数据");
            return;
        }
        adapter.setData(dataBean);
        if (adapter.getItemCount() < 1) {
            showEmptyView("暂无数据");
            return;
        }
    }
}
