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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.yzf.king.R;
import com.yzf.king.adapter.TeamAdapter;
import com.yzf.king.adapter.TeamDetailAdapter;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.utils.PermissionPageUtils;
import com.yzf.king.model.servicesmodels.GetSubmerchDetailResult;
import com.yzf.king.model.servicesmodels.GetTeamInfoResult;
import com.yzf.king.present.PTeam;
import com.yzf.king.widget.MultipleStatusView;

import butterknife.BindView;
import butterknife.ButterKnife;
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
 * Description: 团队管理
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/2/22 10:56
 * Modified By：
 * Fixtime：2019/2/22 10:56
 * FixDescription：
 */
public class TeamManageActivity extends XActivity<PTeam> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.team_sub_ll)
    LinearLayout teamSubLl;
    @BindView(R.id.team_subsub_ll)
    LinearLayout teamSubsubLl;
    @BindView(R.id.team_total_ll)
    LinearLayout teamTotalLl;
    @BindView(R.id.team_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.team_multiplestatusview)
    MultipleStatusView multiplestatusview;
    @BindView(R.id.team_subinfo_ll)
    LinearLayout teamSubinfoLl;

    @BindView(R.id.team_sub_tv)
    TextView teamSubTv;
    @BindView(R.id.team_subsub_tv)
    TextView teamSubsubTv;
    @BindView(R.id.team_total_tv)
    TextView teamTotalTv;
    TeamAdapter adapter;
    @BindView(R.id.team_name_tv)
    TextView teamNameTv;
    @BindView(R.id.team_phone_tv)
    TextView teamPhoneTv;
    @BindView(R.id.team_level_tv)
    TextView teamLevelTv;
    @BindView(R.id.team_time_tv)
    TextView teamTimeTv;
    @BindView(R.id.team_sub_name_tv)
    TextView teamSubNameTv;
    @BindView(R.id.team_subsub_name_tv)
    TextView teamSubsubNameTv;
    @BindView(R.id.team_total_name_tv)
    TextView teamTotalNameTv;
    @BindView(R.id.team_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.share_team_tv)
    TextView shareTeamTv;
    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;
    String beginTime;
    String endTime;

    String count;
    @Override
    public void initData(Bundle savedInstanceState) {
        count = getIntent().getStringExtra("count");
        initView();
        initAdapter();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }
        getP().getTeamInfo(AppUser.getInstance().getMerchId(),1,pageSize,beginTime,endTime);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_team;
    }

    @Override
    public PTeam newP() {
        return new PTeam();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        shareTeamTv.setText("团队人数："+count+"人");
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
        title.setText("团队管理");
    }

    public void initAdapter() {
        if (adapter == null) {
            adapter = new TeamAdapter(context);
//            adapter.setRecItemClick(new RecyclerItemCallback<GetTeamInfoResult.DataBean.DataListBean, TeamAdapter.ViewHolder>() {
//                @Override
//                public void onItemClick(int position, final GetTeamInfoResult.DataBean.DataListBean item, int tag, TeamAdapter.ViewHolder holder) {
//                    super.onItemClick(position, item, tag, holder);
//                    switch (tag) {
//                        case TeamAdapter.TAG_VIEW:
//
//                        default:
//                            break;
//                    }
//                }
//            });
            adapter.setOnMyItemClickListener(new TeamAdapter.OnMyItemClickListener() {
                @Override
                public void myClick(View v, final GetTeamInfoResult.DataBean.DataListBean item) {
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
        recyclerview.verticalLayoutManager(this);
        recyclerview.setAdapter(adapter);
        swiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.theme));
        recyclerview.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                Logger.i("onRefresh");
                getP().getTeamInfo(AppUser.getInstance().getMerchId(),1,pageSize,beginTime,endTime);
            }

            @Override
            public void onLoadMore(int page) {
                getP().getTeamInfo(AppUser.getInstance().getMerchId(),page,pageSize,beginTime,endTime);
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getTeamInfo(AppUser.getInstance().getMerchId(),1,pageSize,beginTime,endTime);
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
     * <p>
     * //     * @param error
     */
    public void showError(NetError error) {
        multiplestatusview.showError(getvDelegate().getErrorType(error));
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

    public void setAdapter(GetTeamInfoResult.DataBean dataBean,int page) {
        swiperefreshlayout.setRefreshing(false);
        if (dataBean == null) {
            if (page > 1) {
                //当条数少于默认条数时，调整最大页数
                recyclerview.setPage(page, page);
                recyclerview.removeAllFootView();

            } else {
                multiplestatusview.showEmpty("暂无数据");
            }
            return;
        }
        multiplestatusview.showContent();
        if (page > 1) {
            adapter.addData(dataBean.getDataList());
        } else {
            adapter.setData(dataBean.getDataList());
        }
        if (adapter.getItemCount() < 1) {
            multiplestatusview.showEmpty("暂无数据");
            return;
        }
        if (dataBean.getDataList().size() < pageSize) {
            //当条数少于默认条数时，调整最大页数
            recyclerview.setPage(page, page);
            recyclerview.removeAllFootView();
        } else {
            //必须设置setPage，否则上拉加载更多会无效
            recyclerview.setPage(page, MAX_PAGE);
        }

    }


}
