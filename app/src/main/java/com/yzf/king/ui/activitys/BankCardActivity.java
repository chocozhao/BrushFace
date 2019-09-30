package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yzf.king.R;
import com.yzf.king.adapter.BankCardAdapter;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.ChooseItem;
import com.yzf.king.model.servicesmodels.GetCardInfoResult;
import com.yzf.king.present.PBankCard;
import com.yzf.king.widget.BottomDialog;
import com.yzf.king.widget.MultipleStatusView;

import java.util.ArrayList;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.event.IEvent;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
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
 * ClassName：BankCardActivity
 * Description: 银行卡管理界面>信用卡卡包、智能规划、全额管理、收款、银行卡
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/4/1 11:49
 * Modified By：
 * Fixtime：2017/4/1 11:49
 * FixDescription：
 */

public class BankCardActivity extends XActivity<PBankCard> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bankcard_swipemenurecyclerview)
    SwipeMenuRecyclerView swipemenurecyclerview;
    @BindView(R.id.bankcard_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.bankcard_multiplestatusview)
    MultipleStatusView multiplestatusview;
    private BankCardAdapter adapter;
    private int delposition = 0;

    private String transType;
    public final static String TRANSTYPE = "transType";
    public final static String VIP = "vip";
    public final static String PLAN = "plan";
    public final static String AUTHPLAN = "authPlan";
    public final static String CARD = "card";


    @Override
    public void initData(Bundle savedInstanceState) {
        useEventBus(true);
        transType = getIntent().getStringExtra(TRANSTYPE);
        if (transType == null) {
            transType = CARD;
        }

        initView();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }
        getP().getCardInfo(AppUser.getInstance().getMerchId());
    }

    @Override
    public void onResume() {
        super.onResume();
//        getP().getCardInfo(AppUser.getInstance().getMerchId());
    }

    @Override
    public void bindEvent() {
        BusProvider.getBus().toObservable(IEvent.class)
                .subscribe(new Action1<IEvent>() {
                    @Override
                    public void call(IEvent iEvent) {
                        //TODO 事件处理
                        if (iEvent.getId().equals("refresh_card")) {
                            getP().getCardInfo(AppUser.getInstance().getMerchId());
                        }
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bank_card;
    }

    @Override
    public int getOptionsMenuId() {
        return R.menu.menu_bankcard;
    }

    @Override
    public PBankCard newP() {
        return new PBankCard();
    }

    public void initAdapter() {
        if (adapter == null) {
            adapter = new BankCardAdapter(context);
            adapter.setOnMyItemClickListener(new BankCardAdapter.OnMyItemClickListener() {
                @Override
                public void myClick(View v, int pos, GetCardInfoResult.DataBean item) {
                    if (getP().verifyStatus(AppUser.getInstance().getStatus())) {
                        if (transType.equals(CARD)) {//详情
                            Router.newIntent(context)
                                    .to(BankCardDetailActivity.class)
                                    .putSerializable("dataBean", item)
                                    .launch();
                        }
                        if (transType.equals(VIP)) {//套现
                            AppUser.getInstance().setMposMerchInfo("");
//                            Router.newIntent(context)
//                                    .to(CreditWithDrawActivity.class)
//                                    .putSerializable("dataBean", item)
//                                    .launch();
                        }
                    }
                }

                @Override
                public void mLongClick(View v, final int pos, final GetCardInfoResult.DataBean item) {
                    BottomDialog bottomDailog = new BottomDialog(context);
                    ArrayList<ChooseItem> imgArray = new ArrayList<>();
                    imgArray.add(new ChooseItem("删除银行卡", null));
                    imgArray.add(new ChooseItem("修改银行卡", null));
                    bottomDailog.showAlert(null, imgArray, new BottomDialog.OnAlertSelectId() {
                        @Override
                        public void onClick(int whichButton) {
                            if (whichButton == 0) {
                                showNoticeDialog("是否删除该银行卡", new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        if (which == DialogAction.POSITIVE) {
                                            getvDelegate().showLoading();
                                            delposition = pos;
                                            getP().deleteBankCard(AppUser.getInstance().getMerchId(), item.getCardId(), "4");
                                        }
                                    }
                                });
                            } else if (whichButton == 1) {
//                                Router.newIntent(context)
//                                        .to(ModifiedCreditCardctivity.class)
//                                        .putSerializable("dataBean", item)
//                                        .launch();
                            }
                        }
                    });
                }
            });
        }
        /*swipemenurecyclerview.setSwipeMenuCreator(swipeMenuCreator);
        swipemenurecyclerview.setSwipeMenuItemClickListener(mMenuItemClickListener);*/

        swiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.theme));
        swipemenurecyclerview.setLayoutManager(new LinearLayoutManager(context));
        swipemenurecyclerview.setAdapter(adapter);
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getCardInfo(AppUser.getInstance().getMerchId());
            }
        });
/*
        adapter.setData(AppUser.getInstance().getcList());
        if (adapter.getItemCount() < 1) {
            multiplestatusview.showEmpty("还没有绑定过银行卡");
            return;
        }*/
    }


    /**
     * RecyclerView的Item的Menu点击监听。
     */
    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            final int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                if (menuPosition == 0) {
                    showNoticeDialog("是否删除该银行卡", new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            if (which == DialogAction.POSITIVE) {
                                getvDelegate().showLoading();
                                delposition = menuPosition;
                                getP().deleteBankCard(AppUser.getInstance().getMerchId(), adapter.getDataSource().get(menuPosition).getCardId(), "4");
                            }
                        }
                    });
                } else if (menuPosition == 1) {
//                    Router.newIntent(context)
//                            .to(ModifiedCreditCardctivity.class)
//                            .putSerializable("dataBean", adapter.getDataSource().get(menuPosition))
//                            .launch();
                }
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
//                showToast( "list第" + adapterPosition + "; 左侧菜单第" + menuPosition);
            }
        }
    };

    /**
     * 菜单创建器，在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.text_70);

            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(context)
                        .setBackground(R.drawable.delete_selector)
                        .setText("删除")
                        .setTextColor(getResources().getColor(R.color.text_white))
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。

                SwipeMenuItem addItem = new SwipeMenuItem(context)
                        .setBackground(R.drawable.modify_selector)
                        .setText("修改")
                        .setTextColor(getResources().getColor(R.color.text_white))
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(addItem); // 添加菜单到右侧。
            }
        }
    };

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        initAdapter();
        multiplestatusview.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multiplestatusview.showLoading();
                getP().getCardInfo(AppUser.getInstance().getMerchId());
            }
        });
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
        if (transType.equals(VIP)) {
            title.setText("收款");
        } else if (transType.equals(PLAN)) {
            title.setText("全额管理");
        } else if (transType.equals(AUTHPLAN)) {
            title.setText("智能规划");
        } else {
            title.setText("银行卡");
        }
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
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                break;
            case R.id.menu_bankcard:
                JumpActivity(BindCardActivity.class, false);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 解绑卡后刷新卡列表
     *
     * @param msg
     */
    public void refresh(String msg) {
        getvDelegate().toastShort(msg);
        adapter.removeElement(delposition);
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
     * 将跳转的activity抽出来
     *
     * @param activity
     */
    public void JumpActivity(Class<?> activity) {
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

    public void setAdapter(GetCardInfoResult GetCardInfoResult) {
        swiperefreshlayout.setRefreshing(false);
        multiplestatusview.showContent();
        adapter.setData(GetCardInfoResult.getData());
        if (adapter.getItemCount() < 1) {
            multiplestatusview.showEmpty("还没有绑定过银行卡");
            return;
        }
    }


}
