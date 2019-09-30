package com.yzf.king.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.yzf.king.App;
import com.yzf.king.R;
import com.yzf.king.adapter.HomeAdapter;
import com.yzf.king.adapter.PersonAdapter;
import com.yzf.king.kit.AppConfig;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.HomeSource;
import com.yzf.king.model.servicesmodels.GetMerchInfoResult;
import com.yzf.king.model.servicesmodels.GetUrlResult;
import com.yzf.king.present.PPerson;
import com.yzf.king.ui.activitys.AgenWebViewActivity;
import com.yzf.king.ui.activitys.BankCardActivity;
import com.yzf.king.ui.activitys.HelpMerchApplyActivity;
import com.yzf.king.ui.activitys.OrderActivity;
import com.yzf.king.ui.activitys.SettingActivity;
import com.yzf.king.ui.activitys.ShopManageActivity;
import com.yzf.king.ui.activitys.SignActivity;
import com.yzf.king.ui.activitys.TeamManageActivity;
import com.yzf.king.ui.activitys.TransDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.event.IEvent;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
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
 * ClassName：PersonFragment
 * Description: 我的页面
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/5/18 12:01
 * Modified By：
 * Fixtime：2017/5/18 12:01
 * FixDescription：
 */

public class PersonFragment extends XFragment<PPerson> {
    @BindView(R.id.person_head_iv)
    ImageView personHeadIv;
    @BindView(R.id.person_name_tv)
    TextView personNameTv;
    @BindView(R.id.person_levelone_tv)
    TextView personLevelOneTv;
    @BindView(R.id.person_leveltwe_tv)
    TextView personLevelTweTv;
    @BindView(R.id.person_levelthree_tv)
    TextView personLevelThreeTv;
    @BindView(R.id.person_levelfour_tv)
    TextView personLevelFourTv;
    @BindView(R.id.person_name_rl)
    RelativeLayout personNameRl;
    @BindView(R.id.person_order_ll)
    RelativeLayout personOrderRl;
    @BindView(R.id.person_install_iv)
    ImageView personInstallIv;
    @BindView(R.id.person_shoporder_rl)
    RelativeLayout personInstallRl;
    @BindView(R.id.person_help_rl)
    RelativeLayout personHelpRl;
    @BindView(R.id.person_register_rl)
    RelativeLayout personRegisterRl;
    @BindView(R.id.person_proxy_rl)
    RelativeLayout personProxyRl;
    @BindView(R.id.person_shop_rl)
    RelativeLayout personShopRl;
    @BindView(R.id.person_setting_rl)
    RelativeLayout personSettingRl;
    @BindView(R.id.person_order_payment_ll)
    LinearLayout personOrderCompleteLl;
    @BindView(R.id.person_order_wait_payment_ll)
    LinearLayout personOrderWaitshipLl;
    @BindView(R.id.person_order_waitreceipt_ll)
    LinearLayout personOrderWaitreceiptLl;
    @BindView(R.id.person_order_invalid_ll)
    LinearLayout personOrderInvalidLl;
    @BindView(R.id.person_shop_sign_rl)
    RelativeLayout personShopSignRl;

    int item;
    PersonAdapter adapter;
    PersonAdapter settingAdapter;
    @Override
    public void initData(Bundle savedInstanceState) {
        useEventBus(true);
        initView();
        initAdapter();
        getP().getMerchInfo(AppUser.getInstance().getMerchId());

    }

    /**
     * 初始化界面
     */

    private void initView() {
        if (AppConfig.O_SINGLE) {
            personProxyRl.setVisibility(View.VISIBLE);
        } else {
            personProxyRl.setVisibility(View.GONE);
        }
    }

    /**
     * 两个recycylerview设置
     */
    private void initAdapter() {
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        recyclerview.verticalLayoutManager(context);
        if (adapter == null) {
            adapter = new PersonAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<HomeSource, PersonAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final HomeSource item, int tag, PersonAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case HomeAdapter.TAG_VIEW:
                            if (getP().verifyStatus(AppUser.getInstance().getStatus())) {
                                switch (position) {
                                    case 0:
                                        Router.newIntent(context)
                                                .to(TeamManageActivity.class)
                                                .putString("merchId", AppUser.getInstance().getMerchId())
                                                .launch();
                                        break;
                                    case 1:
                                        JumpActivity(BankCardActivity.class);

                                        break;
                                    case 2:
//                                        JumpActivity(MerchFeeActivity.class);
                                        break;
                                    case 3:
                                        List<GetUrlResult.DataBean> list = AppUser.getInstance().getUrlBean();
                                        String target = null;
                                        if (list != null && list.size() > 0) {
                                            for (GetUrlResult.DataBean dataBean : list) {
                                                if ("sharePolicyUrl".equals(dataBean.getType())) {
                                                    target = dataBean.getUrl();
                                                    break;
                                                }
                                            }
                                        }
                                        if (!AppTools.isEmpty(target)) {
                                            AgenWebViewActivity.launch(context, target, null);
                                        } else {
                                            showToast("暂未开放");
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
            });
        }
//        recyclerview.setAdapter(adapter);
        adapter.setData(getData());

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_person;
    }

    @Override
    public PPerson newP() {
        return new PPerson();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            context.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void bindEvent() {
        BusProvider.getBus().toObservable(IEvent.class)
                .subscribe(new Action1<IEvent>() {
                    @Override
                    public void call(IEvent iEvent) {
                        //TODO 事件处理
                        if (iEvent.getId().equals("refresh_person_info")) {
//                            Logger.i("refresh_person_info");
//                            setInfo(result);
                        }
                    }
                });
    }


    public void JumpActivity(Class<?> activity, boolean finish) {
        Router.newIntent(context)
                .to(activity)
                .launch();
        if (finish) {
            Router.pop(context);
        }
    }

    public void JumpActivity(Class<?> activity) {
        Router.newIntent(context)
                .to(activity)
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

    /**
     * 显示双按钮对话框
     *
     * @param msg
     * @param
     */
    public void showDialog(String title, String msg, String positiveText, String negativeText, MaterialDialog.SingleButtonCallback singleButtonCallback, Boolean cancle) {
        getvDelegate().showDialog(title, msg, positiveText, negativeText, singleButtonCallback, cancle);
    }

    public void showError(NetError error) {
        getvDelegate().showError(error);
    }


    @OnClick({R.id.person_name_rl, R.id.person_order_ll, R.id.person_shoporder_rl,
            R.id.person_help_rl, R.id.person_register_rl, R.id.person_proxy_rl,
            R.id.person_shop_rl, R.id.person_setting_rl, R.id.person_order_payment_ll,
            R.id.person_order_wait_payment_ll, R.id.person_order_waitreceipt_ll, R.id.person_order_invalid_ll,
            R.id.person_shop_sign_rl,R.id.person_trans_details_rl
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_name_rl:
//                if (getP().verifyStatus(AppUser.getInstance().getStatus())) {
//                    JumpActivity(MerchInfoActivity.class);
//                }
                break;
            case R.id.person_order_ll:
//                Router.newIntent(context)
//                        .to(OrderActivity.class)
//                        .putInt("item", 0)
//                        .launch();

                break;
            case R.id.person_shoporder_rl:
                JumpActivity(ShopManageActivity.class);
                break;
            case R.id.person_trans_details_rl:
                JumpActivity(TransDetailsActivity.class);
                break;
            case R.id.person_help_rl:
                JumpActivity(HelpMerchApplyActivity.class);
                /*List<GetUrlResult.DataBean> list = AppUser.getInstance().getUrlBean();
                String target = null;//http://h5.yiyoupay.net/link/html/directions.html?topBranchNo=20000029
                if (list != null && list.size() > 0) {
                    for (GetUrlResult.DataBean dataBean : list) {
                        if (dataBean.getType().equals("shopUrl")) {
                            target = dataBean.getUrl();
                            break;
                        }
                    }
                }
                if (!AppTools.isEmpty(target)) {
                    AgenWebViewActivity.launch(context, target, null);
                } else {
                    showToast("暂未开放");
                }*/
                break;
            case R.id.person_register_rl:
                List<GetUrlResult.DataBean> list1 = AppUser.getInstance().getUrlBean();
                String target1 = null;//http://h5.yiyoupay.net/link/html/directions.html?topBranchNo=20000029
                if (list1 != null && list1.size() > 0) {
                    for (GetUrlResult.DataBean dataBean : list1) {
                        if (dataBean.getType().equals("registerUrl")) {
                            target1 = dataBean.getUrl();
                            break;
                        }
                    }
                }
                if (!AppTools.isEmpty(target1)) {
                    AgenWebViewActivity.launch(context, target1, null);
                } else {
                    showToast("暂未开放");
                }
                break;
            case R.id.person_proxy_rl:
                List<GetUrlResult.DataBean> list2 = AppUser.getInstance().getUrlBean();
                String target2 = null;//http://h5.yiyoupay.net/link/html/directions.html?topBranchNo=20000029
                if (list2 != null && list2.size() > 0) {
                    for (GetUrlResult.DataBean dataBean : list2) {
                        if (dataBean.getType().equals("agencyUrl")) {
                            target2 = dataBean.getUrl();
                            break;
                        }
                    }
                }
                if (!AppTools.isEmpty(target2)) {
                    AgenWebViewActivity.launch(context, target2, null);
                } else {
                    showToast("暂未开放");
                }
                break;
            case R.id.person_shop_rl:
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
            case R.id.person_setting_rl:
                JumpActivity(SettingActivity.class);
                break;
            case R.id.person_order_payment_ll:
                Router.newIntent(context)
                        .to(OrderActivity.class)
                        .putInt("item", 0)
                        .launch();
                break;
            case R.id.person_order_wait_payment_ll:
                Router.newIntent(context)
                        .to(OrderActivity.class)
                        .putInt("item", 1)
                        .launch();
                break;
            case R.id.person_order_waitreceipt_ll:
                Router.newIntent(context)
                        .to(OrderActivity.class)
                        .putInt("item", 3)
                        .launch();
                break;
            case R.id.person_order_invalid_ll:
                Router.newIntent(context)
                        .to(OrderActivity.class)
                        .putInt("item", 4)
                        .launch();
                break;
            case R.id.person_shop_sign_rl:
                JumpActivity(SignActivity.class);
                break;
            default:
                break;
        }
    }

    private List<HomeSource> getData() {
        List<HomeSource> arrayList = new ArrayList();
        HomeSource homeSource0 = new HomeSource();
        homeSource0.setId(0);
        homeSource0.setImgRes(R.mipmap.person_register_contract);
        homeSource0.setStrRes("注册合约");
        HomeSource homeSource1 = new HomeSource();
        homeSource1.setId(1);
        homeSource1.setImgRes(R.mipmap.person_merchant_contract);
        homeSource1.setStrRes("商家合约");
        HomeSource homeSource2 = new HomeSource();
        homeSource2.setId(2);
        homeSource2.setImgRes(R.mipmap.person_shop_contract);
        homeSource2.setStrRes("铺货合约");

        arrayList.add(homeSource0);
        arrayList.add(homeSource1);
        arrayList.add(homeSource2);
        return arrayList;
    }


    /**
     * 1、从P层获取过来的用户名和用户卡开通情况
     * 2、判断是否开通授权书
     */
    public void setInfo(GetMerchInfoResult result) {
        if (result.getData() != null) {
            personNameTv.setText(result.getData().getMerchName());
            //1：业务员  2：代理
            if ("1".equals(result.getData().getMerchLevel())) {
                personLevelOneTv.setText(AppUser.getInstance().getMerchlevelname());
            } else if ("2".equals(result.getData().getMerchLevel())) {
                personLevelOneTv.setText(AppUser.getInstance().getMerchlevelname2());
            } else {
                personLevelOneTv.setVisibility(View.GONE);
            }
            if (result.getData().getAgentFlag() == 1) {
                //供货商
                personLevelTweTv.setText(AppUser.getInstance().getAgentlevelname());
            } else {
                personLevelTweTv.setVisibility(View.GONE);
            }
            if (result.getData().getShopFlag() == 1) {
                //商家
                personLevelThreeTv.setText(AppUser.getInstance().getShoplevelname());
            } else {
                personLevelThreeTv.setVisibility(View.GONE);
            }
            if (result.getData().getBranchFlag() == 1) {
                //分公司
                personLevelFourTv.setText(AppUser.getInstance().getBranchlevelname());
            } else {
                personLevelFourTv.setVisibility(View.GONE);
            }
        }

    }

}
