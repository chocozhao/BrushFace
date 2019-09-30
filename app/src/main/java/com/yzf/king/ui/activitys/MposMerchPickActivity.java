package com.yzf.king.ui.activitys;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
import com.yzf.king.R;
import com.yzf.king.adapter.MposMerchPickAdapter;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.utils.CommonAdapter;
import com.yzf.king.kit.utils.DividerItemDecoration;
import com.yzf.king.kit.utils.HeaderRecyclerAndFooterWrapperAdapter;
import com.yzf.king.kit.utils.OnItemClickListener;
import com.yzf.king.kit.utils.ViewHolder;
import com.yzf.king.model.servicesmodels.GetPosChanlFeeResult;
import com.yzf.king.model.servicesmodels.MposMerchPickBean;
import com.yzf.king.model.servicesmodels.MposMerchPickHeaderBean;
import com.yzf.king.model.servicesmodels.MposMerchPickTopHeaderBean;
import com.yzf.king.present.PMposMerchPick;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * ClaseName：MposMerchPickActivity
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/6/4 16:22
 * Modified By：
 * Fixtime：2019/6/4 16:22
 * FixDescription：
 **/

public class MposMerchPickActivity extends XActivity<PMposMerchPick> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    //    @BindView(R.id.city_recyclerview)
//    XRecyclerView recyclerview;
//    @BindView(R.id.city_swiperefreshlayout)
//    SwipeRefreshLayout swiperefreshlayout;

    private RecyclerView mRv;
    private MposMerchPickAdapter adapter;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;
    private LinearLayoutManager mManager;

    //设置给InexBar、ItemDecoration的完整数据集
    private List<BaseIndexPinyinBean> mSourceDatas;
    //头部数据源
    private List<MposMerchPickHeaderBean> mHeaderDatas;
    //主体部分数据源（城市数据）
    private List<MposMerchPickBean> mBodyDatas;

    private SuspensionDecoration mDecoration;
    /**
     * 右侧边栏导航区域
     */
    private IndexBar mIndexBar;

    /**
     * 显示指示器DialogText
     */
    private TextView mTvSideBarHint;
    String city = null;
    private int pageSize = 10;// 每页10行
    protected static final int MAX_PAGE = 30;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        initAdapter();
        getP().getLocationInfo(AppUser.getInstance().getMerchId());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mpos_merch_pick;
    }

    @Override
    public PMposMerchPick newP() {
        return new PMposMerchPick();
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
        title.setText("选择地区");
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

    /**
     * 城市选择设置
     */
    private void initAdapter() {
        mRv =  findViewById(R.id.rv);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(context));

        mSourceDatas = new ArrayList<>();
        mHeaderDatas = new ArrayList<>();
        List<String> locationCity = new ArrayList<>();
        locationCity.add("定位中");
        mHeaderDatas.add(new MposMerchPickHeaderBean(locationCity, "定位城市", "定"));

        List<String> hotCitys = new ArrayList<>();
        mHeaderDatas.add(new MposMerchPickHeaderBean(hotCitys, "热门城市", "热"));
        mSourceDatas.addAll(mHeaderDatas);

        adapter = new MposMerchPickAdapter(context, R.layout.mpos_merch_pick_item_select_city, mBodyDatas);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                MposMerchPickBean mposMerchPickBean = (MposMerchPickBean) o;
//                Router.newIntent(context)
//                        .to(MposMerchPickDtlActivity.class)
//                        .putString("cityName", ((MposMerchPickBean) o).getCity())
//                        .launch();
//                Router.pop(context);
                GetPosChanlFeeResult.DataBean bean = AppUser.getInstance().getMposFeeBean();
                bean.setCity(mposMerchPickBean.getCity());
                Gson gson = new Gson();
                AppUser.getInstance().setMposFeeInfo(gson.toJson(bean));
                finish();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(adapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                switch (layoutId) {
                    case R.layout.mpos_merch_pick_item_header:
                        final MposMerchPickHeaderBean mposMerchPickHeaderBean = (MposMerchPickHeaderBean) o;
                        //网格
                        RecyclerView recyclerView = holder.getView(R.id.rvCity);
                        recyclerView.setAdapter(
                                new CommonAdapter<String>(context, R.layout.mpos_merch_pick_item_header_item, mposMerchPickHeaderBean.getCityList()) {
                                    @Override
                                    public void convert(ViewHolder holder, final String cityName) {
                                        holder.setText(R.id.tvName, cityName);
                                        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
//                                                Router.newIntent(context)
//                                                        .to(MposMerchPickDtlActivity.class)
//                                                        .putString("cityName", cityName)
//                                                        .launch();
//                                                Router.pop(context);
                                                GetPosChanlFeeResult.DataBean bean = AppUser.getInstance().getMposFeeBean();
                                                bean.setCity(cityName);
                                                Gson gson = new Gson();
                                                AppUser.getInstance().setMposFeeInfo(gson.toJson(bean));
                                                finish();
                                            }
                                        });
                                    }
                                });
                        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
                        break;
                    case R.layout.mpos_merch_pick_item_header_top:
                        MposMerchPickTopHeaderBean meituanTopHeaderBean = (MposMerchPickTopHeaderBean) o;
                        holder.setText(R.id.tvCurrent, meituanTopHeaderBean.getTxt());
                        break;
                    default:
                        break;
                }
            }
        };
        mHeaderAdapter.setHeaderView(1, R.layout.mpos_merch_pick_item_header, mHeaderDatas.get(0));
        mHeaderAdapter.setHeaderView(2, R.layout.mpos_merch_pick_item_header, mHeaderDatas.get(1));


        mRv.setAdapter(mHeaderAdapter);
        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(context, mSourceDatas)
                .setmTitleHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics()))
                .setColorTitleBg(0xffefefef)
                .setTitleFontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()))
                .setColorTitleFont(context.getResources().getColor(android.R.color.black))
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size()));
        mRv.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));

        //使用indexBar
        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) findViewById(R.id.indexBar);//IndexBar

        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size());


    }


    /**
     * 组织数据源
     *
     * @param city
     * @return
     */
    public void initDatas(String city) {
        final String[] data = getResources().getStringArray(R.array.provinces);
        mBodyDatas = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            MposMerchPickBean cityBean = new MposMerchPickBean();
            cityBean.setCity(data[i]);//设置城市名称
            mBodyDatas.add(cityBean);
        }
        //先排序
        mIndexBar.getDataHelper().sortSourceDatas(mBodyDatas);

        adapter.setDatas(mBodyDatas);
        mHeaderAdapter.notifyDataSetChanged();
        mSourceDatas.addAll(mBodyDatas);

        mIndexBar.setmSourceDatas(mSourceDatas)//设置数据
                .invalidate();
        mDecoration.setmDatas(mSourceDatas);
        MposMerchPickHeaderBean header1 = mHeaderDatas.get(0);
        header1.getCityList().clear();
        header1.getCityList().add(city);

        MposMerchPickHeaderBean header3 = mHeaderDatas.get(1);
        List<String> hotCitys = new ArrayList<>();
        hotCitys.add("深圳");
        hotCitys.add("广州");
        hotCitys.add("东莞");
        header3.setCityList(hotCitys);
        mHeaderAdapter.notifyItemRangeChanged(1, 2);

    }
}


