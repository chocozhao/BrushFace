package com.yzf.king.ui.activitys;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.yzf.king.R;
import com.yzf.king.adapter.AcctAdapter;
import com.yzf.king.adapter.HomeAdapter;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.HomeSource;
import com.yzf.king.model.servicesmodels.CountTransInfoResult;
import com.yzf.king.model.servicesmodels.GetAcctInfoResult;
import com.yzf.king.present.PTransInfo;
import com.yzf.king.widget.MultipleStatusView;
import com.yzf.king.widget.MyDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * ClaseName：${NAME}
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/5/17 17:18
 * Modified By：
 * Fixtime：2019/5/17 17:18
 * FixDescription：
 **/
public class TransInfoActivity extends XActivity<PTransInfo> {

    GetAcctInfoResult.DataBeanX dataBean;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.trans_recyclerView)
    XRecyclerView recyclerView;
    @BindView(R.id.multiplestatusview)
    MultipleStatusView multiplestatusview;
    @BindView(R.id.trans_totalsum_tv)
    TextView transTotalsumTv;
    @BindView(R.id.trans_totalcount_tv)
    TextView transTotalcountTv;
    @BindView(R.id.trans_day_tv)
    TextView transDayTv;
    @BindView(R.id.trans_month_tv)
    TextView transMonthTv;

    private AcctAdapter adapter;
    String type = "03";

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getP().getTransStatInfo(AppUser.getInstance().getMerchId(), null);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_trans_info;
    }

    @Override
    public PTransInfo newP() {
        return new PTransInfo();
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
        title.setText("交易统计");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    /**
     * 标题栏监听
     *
     * @param item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                break;
            default:
                break;
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
        getvDelegate().dismissLoading();
        Router.newIntent(context)
                .to(activity)
                .launch();
    }

    /**
     * 显示Toast
     *
     * @String msg
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
     * 显示双按钮对话框
     *
     * @param msg
     * @param callback
     */
    public void showNoticeDialog(String msg, MaterialDialog.SingleButtonCallback callback) {
        getvDelegate().showNoticeDialog(msg, callback);
    }

    /**
     * 显示错误信息
     *
     * @NetError error
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

    /**
     * 获取从P层传过来的数据
     *
     * @CountTransInfoResult.DataBean data
     */
    @SuppressLint("SetTextI18n")
    public void setData(CountTransInfoResult.DataBean data) {
        multiplestatusview.showContent();
        if (data != null) {
            transTotalsumTv.setText(data.getTotalSum() + "");
            transTotalcountTv.setText(data.getTotalCount() + "");
            transDayTv.setText(data.getDayCount() + "");
            transMonthTv.setText(data.getMonthSum() + "");
        }
    }

    private List<HomeSource> getData() {
        List<HomeSource> arrayList = new ArrayList();
        HomeSource HomeSource0 = new HomeSource();
        HomeSource0.setId(0);
        HomeSource0.setImgRes(R.mipmap.acct_icon);
        HomeSource0.setStrRes("全部交易");
        HomeSource0.setTargetStr(null);
        HomeSource HomeSource1 = new HomeSource();
        HomeSource1.setId(1);
        HomeSource1.setImgRes(R.mipmap.acct_repayment);
        HomeSource1.setStrRes("还款交易");
        HomeSource1.setTargetStr("01");
        HomeSource HomeSource2 = new HomeSource();
        HomeSource2.setId(2);
        HomeSource2.setImgRes(R.mipmap.acct_tx);
        HomeSource2.setStrRes("收款交易");
        HomeSource2.setTargetStr("02");
        HomeSource HomeSource3 = new HomeSource();
        HomeSource3.setId(3);
        HomeSource3.setImgRes(R.mipmap.acct_mpos);
        HomeSource3.setStrRes("MPOS交易");
        HomeSource3.setTargetStr("03");
        HomeSource HomeSource4 = new HomeSource();
        HomeSource4.setId(4);
        HomeSource4.setImgRes(R.mipmap.acct_card);
        HomeSource4.setTargetStr("04");
        HomeSource4.setStrRes("申卡交易");
        HomeSource HomeSource5 = new HomeSource();
        HomeSource5.setId(5);
        HomeSource5.setImgRes(R.mipmap.acct_icon);
        HomeSource5.setTargetStr("05");
        HomeSource5.setStrRes("管理交易");

        arrayList.add(HomeSource0);
        arrayList.add(HomeSource1);
        arrayList.add(HomeSource2);
        arrayList.add(HomeSource3);
        arrayList.add(HomeSource4);
        arrayList.add(HomeSource5);
        return arrayList;
    }

    /**
     * 两个recycylerview设置
     */
    private void initAdapter() {
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        if (adapter == null) {
            adapter = new AcctAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<HomeSource, AcctAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final HomeSource item, int tag, AcctAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case HomeAdapter.TAG_VIEW:
                            switch (position) {
                                case 0:
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                    Router.newIntent(context)
                                            .to(TransStatActivity.class)
                                            .putString(TransStatActivity.TYPE, type)
                                            .putString(TransStatActivity.TRANSTYPE, item.getTargetStr())
                                            .putString(TransStatActivity.TITLE, item.getStrRes())
                                            .launch();
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
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new MyDividerItemDecoration(context, MyDividerItemDecoration.BOTH_SET));
        adapter.setData(getData());
    }

//    @OnClick(R.id.acct_withdraw_bt)
//    public void onViewClicked() {
//        Router.newIntent(context)
//                .to(WithDrawActivity.class)
//                .putString(WithDrawActivity.TYPE, type)
//                .launch();
//    }

}
