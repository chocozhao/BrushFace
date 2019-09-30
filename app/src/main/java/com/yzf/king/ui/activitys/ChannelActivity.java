package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.yzf.king.R;
import com.yzf.king.adapter.ChannelAdapters;
import com.yzf.king.kit.AppConfig;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetCardInfoResult;
import com.yzf.king.model.servicesmodels.GetPlanChannelInfoResult;
import com.yzf.king.model.servicesmodels.GetUrlResult;
import com.yzf.king.present.PChannel;
import com.yzf.king.widget.MultipleStatusView;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.cache.SharedPref;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.XRecyclerView;

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
 * ClassName：ChannelActivity
 * Description: 还款计划选择
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2018/8/29 16:43
 * Modified By：
 * Fixtime：2018/8/29 16:43
 * FixDescription：
 */
public class ChannelActivity extends XActivity<PChannel> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.channel_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.channel_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.channel_multiplestatusview)
    MultipleStatusView multiplestatusview;
    ChannelAdapters adapter;
    GetCardInfoResult.DataBean cardBean = new GetCardInfoResult.DataBean();
    boolean firstFlag = false;


    @Override
    public void initData(Bundle savedInstanceState) {
        cardBean = (GetCardInfoResult.DataBean) getIntent().getSerializableExtra("dataBean");
        if (cardBean != null) {
            Gson gson = new Gson();
            AppUser.getInstance().setCardInfo(gson.toJson(cardBean));//将卡信息存到单例中
        }
        initView();
        initAdapter();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }
        getP().getPlanChannelInfo(AppUser.getInstance().getMerchId());
        if (SharedPref.getInstance(context).getBoolean("showGuide", false)) {
            firstFlag = true;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_channel;
    }

    @Override
    public PChannel newP() {
        return new PChannel();
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
        title.setText("还款计划选择");
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

    public void initAdapter() {
        if (adapter == null) {
            adapter = new ChannelAdapters(context);
        }
        recyclerview.verticalLayoutManager(this);
        recyclerview.setAdapter(adapter);
        adapter.setOnMyItemClickListener(new ChannelAdapters.OnMyItemClickListener() {
            @Override
            public void mLongClick(View view, int i, GetPlanChannelInfoResult.DataBean dataBean) {
            }

            @Override
            public void myClick(View view, int i, GetPlanChannelInfoResult.DataBean dataBean, int tag) {
                switch (tag) {
                    case ChannelAdapters.TAG_VIEW:
                        showErrorDialog(dataBean.getRemark());
                        break;
                    case ChannelAdapters.BT_VIEW:
                        if (dataBean.getPlanType() == 1) {

                            AppUser.getInstance().setPlanMinAmt(String.valueOf(dataBean.getMinAmt()));
                            getvDelegate().showLoading();
                            getP().getChannelInfo(AppUser.getInstance().getMerchId(), cardBean.getCardId(), cardBean.getBankAbbr(), dataBean.getBankAbbr(), "7001");

                        } else if (dataBean.getPlanType() == 2) {
                            Logger.i("channel setIsAgent="+AppUser.getInstance().getIsAgent());
                            if (AppUser.getInstance().getMerchLevel().compareTo("3")>=0) {
                                getvDelegate().showLoading();
                                AppUser.getInstance().setPlanMinAmt(String.valueOf(dataBean.getMinAmt()));
                                getP().getAuthIncomeInfo(AppUser.getInstance().getMerchId(), cardBean.getCardId(), "70980002");
                            } else {
                                showDialog("提示", "升级为为商户后才可以进行全额管理，是否现在升级？", "立即升级", "取消", new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        if (which == DialogAction.POSITIVE) {
                                            List<GetUrlResult.DataBean> list = AppUser.getInstance().getUrlBean();
                                            String target = null;//http://h5.yiyoupay.net/link/html/directions.html?topBranchNo=20000029
                                            if (list != null && list.size() > 0) {
                                                for (GetUrlResult.DataBean dataBean1 : list) {
                                                    if ("upgradeUrl".equals(dataBean1.getType())) {
                                                        target = dataBean1.getUrl();
                                                        break;
                                                    }
                                                }
                                            }
                                            if (!AppTools.isEmpty(target)) {
                                                AgenWebViewActivity.launch(context, target, null);
                                            } else {
                                                showToast("暂未开放");
                                            }
                                        }
                                    }
                                }, true);

                            }
                        }
                        break;
                    case ChannelAdapters.SUP_VIEW:
                        if (dataBean.getPlanType() == 1) {
                            List<GetUrlResult.DataBean> list = AppUser.getInstance().getUrlBean();
                            String target = null;//http://h5.yiyoupay.net/apply/html/creditApplication.html
                            if (list != null && list.size() > 0) {
                                for (GetUrlResult.DataBean bean : list) {
                                    if (bean.getType().equals("supPlanUrl")) {
                                        target = bean.getUrl();
                                        break;
                                    }
                                }
                            }
                            if (!AppTools.isEmpty(target)) {
                                AgenWebViewActivity.launch(context, target, null);
                            } else {
                                showToast("暂无数据");
                            }
                        } else if (dataBean.getPlanType() == 2) {
                            List<GetUrlResult.DataBean> list = AppUser.getInstance().getUrlBean();
                            String target = null;//http://h5.yiyoupay.net/apply/html/creditApplication.html
                            if (list != null && list.size() > 0) {
                                for (GetUrlResult.DataBean bean : list) {
                                    if (bean.getType().equals("supAuthPlanUrl")) {
                                        target = bean.getUrl();
                                        break;
                                    }
                                }
                            }
                            if (!AppTools.isEmpty(target)) {
                                AgenWebViewActivity.launch(context, target, null);
                            } else {
                                showToast("暂无数据");
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        swiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.theme));
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getPlanChannelInfo(AppUser.getInstance().getMerchId());
            }
        });
    }

    public void setAdapter(GetPlanChannelInfoResult getPlanChannelInfoResult) {
        swiperefreshlayout.setRefreshing(false);
        multiplestatusview.showContent();
        adapter.setData(getPlanChannelInfoResult.getData());
        if (adapter.getItemCount() < 1) {
            multiplestatusview.showEmpty("暂无数据");
        }
    }

    public void toWebView(String result) {
        getvDelegate().dismissLoading();
        AgenWebViewActivity.launch(context, result, "");

    }


    public void toPlanCardAct() {
        getvDelegate().dismissLoading();
//        Router.newIntent(context)
//                .to(PlanCardActivity.class)
//                .putSerializable("dataBean", cardBean)
//                .putInt("minAmt", Integer.parseInt(AppUser.getInstance().getPlanMinAmt()))
//                .launch();
    }


    public void toCreditRepaymentAct() {
        getvDelegate().dismissLoading();
//        Router.newIntent(context)
//                .to(CreditRepaymentActivity.class)
//                .putSerializable("dataBean", cardBean)
//                .putInt("minAmt", Integer.parseInt(AppUser.getInstance().getPlanMinAmt()))
//                .launch();
    }
}
