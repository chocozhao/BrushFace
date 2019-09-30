package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.yzf.king.R;
import com.yzf.king.adapter.AcctAdapter;
import com.yzf.king.adapter.HomeAdapter;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.HomeSource;
import com.yzf.king.model.servicesmodels.GetAcctInfoResult;
import com.yzf.king.present.PAcctInfo;
import com.yzf.king.widget.MultipleStatusView;
import com.yzf.king.widget.StateButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;

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
 * ClassName：AcctInfoActivity
 * Description: 账户页面>会员分润>商户分润
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/2/20 19:52
 * Modified By：
 * Fixtime：2019/2/20 19:52
 * FixDescription：
 */
public class AcctInfoActivity extends XActivity<PAcctInfo> {

    GetAcctInfoResult.DataBeanX dataBean;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.acct_profitamt_tv)
    TextView acctProfitamtTv;
    @BindView(R.id.acct_withdraw_bt)
    StateButton acctWithdrawBt;
    @BindView(R.id.acct_profit_ll)
    LinearLayout acctProfitLl;
    @BindView(R.id.multiplestatusview)
    MultipleStatusView multiplestatusview;
    @BindView(R.id.acct_yesterday_amt_tv)
    TextView acctYesterdayAmtTv;
    @BindView(R.id.acct_recording_bt)
    Button acctRecordingBt;
    @BindView(R.id.acct_today_amt_tv)
    TextView acctTodayAmtTv;
    @BindView(R.id.acct_sum_amt_tv)
    TextView acctSumAmtTv;

    private AcctAdapter adapter;
    String type = "01";

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
        getP().getAcctInfo(AppUser.getInstance().getMerchId());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_acct_info;
    }

    @Override
    public PAcctInfo newP() {
        return new PAcctInfo();
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
        title.setText("账户");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public int getOptionsMenuId() {
        return R.menu.menu_recording;
    }

    /**
     * 标题栏监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                break;
            case R.id.menu_recording:
                JumpActivity(TransActivity.class, false);
                break;
            default:
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
     * 显示双按钮对话框
     *
     * @param msg
     * @param callback
     */
    public void showNoticeDialog(String msg, MaterialDialog.SingleButtonCallback callback) {
        getvDelegate().showNoticeDialog(msg, callback);
    }

    /**
     * 显示双按钮对话框
     *
     * @param msg
     * @param
     */
    public void showDialog(String title, String msg, String positiveText, String negativeText, MaterialDialog.SingleButtonCallback singleButtonCallback, Boolean cancle) {
        getvDelegate().showDialog(title, msg, positiveText, negativeText, singleButtonCallback, cancle);
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

    /**
     * 获取从P层传过来的数据
     *
     * @param data
     */
    public void setData(GetAcctInfoResult.DataBeanX data) {
        dataBean = data;
        multiplestatusview.showContent();
        acctTodayAmtTv.setText(AppTools.formatL2Y(data.getTodayAmt()));
        acctYesterdayAmtTv.setText(AppTools.formatL2Y(data.getYesterdayAmt()));
        acctProfitamtTv.setText(AppTools.formatL2Y(data.getSumAmt()));
//        acctSumAmtTv.setText(AppTools.formatF2Y(data.getSumAmt()));
        List<GetAcctInfoResult.DataBeanX.DataBean> list = data.getData();
        for (GetAcctInfoResult.DataBeanX.DataBean bean : list) {
            if ("01".equals(bean.getAcctType())) {
//                acctProfitamtTv.setText(bean.getAvlbAmt());
//                acctAvlbAmtTv.setText(AppTools.formatAmt(bean.getAvlbAmt()));
                acctSumAmtTv.setText(AppTools.formatL2Y(bean.getFrozenAmt()));
                break;
            }
        }
    }



    /**
     * 两个recycylerview设置
     */
    private void initAdapter() {
//        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
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
                                            .to(ProfitActivity.class)
                                            .putString(ProfitActivity.TYPE, type)
                                            .putString(ProfitActivity.TRANSTYPE, item.getTargetStr())
                                            .putString(ProfitActivity.TITLE, item.getStrRes())
                                            .launch();
                                    break;
                                default:
                            }
                            break;
                        default:
                    }
                }

            });
        }
//        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new MyDividerItemDecoration(context,MyDividerItemDecoration.BOTH_SET));
//        adapter.setData(getData());
    }

    @OnClick({R.id.acct_withdraw_bt, R.id.acct_recording_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.acct_withdraw_bt:
                if (getP().verifyStatus(AppUser.getInstance().getStatus())) {
                    Router.newIntent(context)
                            .to(WithDrawActivity.class)
                            .putString(WithDrawActivity.TYPE, type)
                            .launch();
                }
                break;
            case R.id.acct_recording_bt:
                JumpActivity(WithDrawDetailActivity.class, false);
            default:
        }

    }


}
