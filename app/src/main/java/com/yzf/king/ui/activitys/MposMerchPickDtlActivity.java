package com.yzf.king.ui.activitys;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yzf.king.R;
import com.yzf.king.adapter.MerchPickAdapter;
import com.yzf.king.adapter.TeamAdapter;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetChannelMerchResult;
import com.yzf.king.model.servicesmodels.GetLocationResult;
import com.yzf.king.present.PMposMerchPickDtl;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.model.LocatedCity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

import static com.tencent.mm.sdk.platformtools.ChannelUtil.channelId;

/**
 * ClaseName：MposMerchPickDtlActivity
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/6/4 19:34
 * Modified By：
 * Fixtime：2019/6/4 19:34
 * FixDescription：
 **/

public class MposMerchPickDtlActivity extends XActivity<PMposMerchPickDtl> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    //     @BindView(R.id.tram_detail_multiplestatusview)
//     MultipleStatusView multiplestatusview;
    MerchPickAdapter adapter;
    @BindView(R.id.city_recyclerview)
    XRecyclerView recyclerview;
    @BindView(R.id.city_swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;

    String cityName;
    @Override
    public void initData(Bundle savedInstanceState) {
        cityName = getIntent().getStringExtra("cityName");
        initView();
        initAdapter();
        getP().getMposChannelMerchInfo(AppUser.getInstance().getMerchId(), AppUser.getInstance().getMposFeeBean().getTransType(), cityName, null, 1, pageSize, null);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_city_pick;
    }

    @Override
    public PMposMerchPickDtl newP() {
        return new PMposMerchPickDtl();
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
        title.setText("选择商户");
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

    public void setInfo(GetLocationResult.DataBean data) {
        CityPicker.from(MposMerchPickDtlActivity.this).locateComplete(new LocatedCity(data.getCity(), data.getCountryName(), data.getRegion()), LocateState.SUCCESS);
    }

    public void initAdapter() {
        if (adapter == null) {
            adapter = new MerchPickAdapter(context);
            adapter.setRecItemClick(new RecyclerItemCallback<GetChannelMerchResult.DataBean, MerchPickAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, final GetChannelMerchResult.DataBean item, int tag, MerchPickAdapter.ViewHolder holder) {
                    super.onItemClick(position, item, tag, holder);
                    switch (tag) {
                        case TeamAdapter.TAG_VIEW:
                            Gson gson = new Gson();
                            AppUser.getInstance().setMposMerchInfo(gson.toJson(item));
                            finish();
                            break;
                            default:break;
                    }
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
                getP().getMposChannelMerchInfo(AppUser.getInstance().getMerchId(), AppUser.getInstance().getMposFeeBean().getTransType(), cityName, null, 1, pageSize, null);
            }

            @Override
            public void onLoadMore(int page) {
                getP().getMposChannelMerchInfo(AppUser.getInstance().getMerchId(), AppUser.getInstance().getMposFeeBean().getTransType(), cityName, null, page, pageSize, null);
            }
        });
        recyclerview.useDefLoadMoreView();
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getP().getMposChannelMerchInfo(AppUser.getInstance().getMerchId(), AppUser.getInstance().getMposFeeBean().getTransType(), cityName, null, 1, pageSize, null);
            }
        });
    }


    public void setAdapter(List<GetChannelMerchResult.DataBean> data, int page) {
        swiperefreshlayout.setRefreshing(false);
        getvDelegate().dismissLoading();
        title.setText("选择商户");
        if (page > 1) {
            adapter.addData(data);
        } else {
            adapter.setData(data);
        }
        if (data.size() < pageSize) {
            recyclerview.setPage(page, page);//当条数少于默认条数时，调整最大页数
            recyclerview.removeAllFootView();
        } else {
            recyclerview.setPage(page, MAX_PAGE);//必须设置setPage，否则上拉加载更多会无效
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
//    public void showEmptyView(String msg) {
//        multiplestatusview.showEmpty(msg);
//    }
//
//    public void showErrorView(String msg) {
//        multiplestatusview.showError(msg);
//    }
//
//    public void showErrorView(NetError error) {
//        multiplestatusview.showError(getvDelegate().getErrorType(error));
//    }


}
