package com.yzf.king.ui.activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.yzf.king.R;
import com.yzf.king.adapter.TeamDetailAdapter;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.utils.PermissionPageUtils;
import com.yzf.king.model.servicesmodels.GetSubmerchDetailResult;
import com.yzf.king.present.PTeamDetail;
import com.yzf.king.widget.MultipleStatusView;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;
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
 * ClassName：TeamManageActivity
 * Description:
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/2/22 10:56
 * Modified By：
 * Fixtime：2019/2/22 10:56
 * FixDescription：
 */
public class TeamDetailActivity extends XActivity<PTeamDetail> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tram_detail_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.tram_detail_multiplestatusview)
    MultipleStatusView multiplestatusview;

    TeamDetailAdapter adapter;

    @BindView(R.id.tram_detail_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    public static final String MERCHLEVEL = "merchLevel";
    String merchLevel;

    @Override
    public void initData(Bundle savedInstanceState) {
        merchLevel = getIntent().getStringExtra(MERCHLEVEL);
        initView();
        initAdapter();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }
        getP().getSubMerchDetail(AppUser.getInstance().getMerchId(), merchLevel, 1, pageSize);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_team_detail;
    }

    @Override
    public PTeamDetail newP() {
        return new PTeamDetail();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        multiplestatusview.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multiplestatusview.showLoading();
                getP().getSubMerchDetail(AppUser.getInstance().getMerchId(), merchLevel, 1, pageSize);
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
        title.setText("团队详情");
    }

    public void initAdapter() {
        if (adapter == null) {
            adapter = new TeamDetailAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetSubmerchDetailResult.DataBean, TeamDetailAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetSubmerchDetailResult.DataBean item, int tag, TeamDetailAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case TeamDetailAdapter.TAG_VIEW:
                            if (item.getMerchLevel().equals("1")) {
                               return;
                            }
                            Router.newIntent(context)
                                    .to(TeamManageActivity.class)
                                    .putString("sub", "sub")
                                    .putString("merchId",item.getMerchId())
                                    .launch();
                            break;
                            default:break;
                    }
                }
            });
            adapter.setOnMyItemClickListener(new TeamDetailAdapter.OnMyItemClickListener() {
                @Override
                public void myClick(View v, final GetSubmerchDetailResult.DataBean item) {
                    getRxPermissions()
                            .request(Manifest.permission.CALL_PHONE)
                            .subscribe(new Action1<Boolean>() {
                                @Override
                                public void call(Boolean granted) {
                                    if (granted) {
                                        //TODO 许可
                                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + item.getPhone()));
                                        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                            return;
                                        }
                                        context.startActivity(intent);

                                    } else {
                                        //TODO 未许可
//                                    showToast("权限未获取");
                                        showNoticeDialog("尚未获取权限，是否去开启权限?", new MaterialDialog.SingleButtonCallback() {
                                            @Override
                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                if (which == DialogAction.POSITIVE) {
                                                    PermissionPageUtils permissionPageUtils = new PermissionPageUtils(context);
                                                    permissionPageUtils.jumpPermissionPage();
                                                }
                                            }
                                        });
                                    }
                                }
                            });

                }
            });
        }
        swiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.theme));
        recyclerview.verticalLayoutManager(this);
        recyclerview.setAdapter(adapter);
        recyclerview.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                Logger.i("onRefresh");
                getP().getSubMerchDetail(AppUser.getInstance().getMerchId(), merchLevel, 1, pageSize);
            }

            @Override
            public void onLoadMore(int page) {
                getP().getSubMerchDetail(AppUser.getInstance().getMerchId(), merchLevel, page, pageSize);
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getSubMerchDetail(AppUser.getInstance().getMerchId(), merchLevel, 1, pageSize);
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
        recyclerview.removeAllFootView();
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
     * @param error
     */
    public void showError(NetError error) {
        getvDelegate().showError(error);
    }

    public void showEmptyView(String msg) {
        swiperefreshlayout.setRefreshing(false);
        multiplestatusview.showEmpty(msg);
    }

    public void showErrorView(String msg) {
        swiperefreshlayout.setRefreshing(false);
        multiplestatusview.showError(msg);
    }

    public void showErrorView(NetError error) {
        swiperefreshlayout.setRefreshing(false);
        multiplestatusview.showError(getvDelegate().getErrorType(error));
    }


    public void setAdapter(List<GetSubmerchDetailResult.DataBean> data, int page) {
        swiperefreshlayout.setRefreshing(false);
        if (data == null) {
            if (page > 1) {
                recyclerview.setPage(page, page);//当条数少于默认条数时，调整最大页数
                recyclerview.removeAllFootView();

            } else {
                multiplestatusview.showEmpty("暂无数据");
            }
            return;
        }
        multiplestatusview.showContent();
        if (page > 1) {
            adapter.addData(data);
        } else {
            adapter.setData(data);
        }
        if (adapter.getItemCount() < 1) {
            multiplestatusview.showEmpty("暂无数据");
            return;
        }
        if (data.size() < pageSize) {
            recyclerview.setPage(page, page);//当条数少于默认条数时，调整最大页数
            recyclerview.removeAllFootView();
        } else {
            recyclerview.setPage(page, MAX_PAGE);//必须设置setPage，否则上拉加载更多会无效
        }
    }

}
